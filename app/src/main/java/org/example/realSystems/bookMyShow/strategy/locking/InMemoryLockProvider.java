package org.example.realSystems.bookMyShow.strategy.locking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InMemoryLockProvider implements LockProvider {
    public static class Expiry{
        long deadline;
        String owner;
        public Expiry(long deadline, String owner) {
            this.deadline = deadline;
            this.owner = owner;
        }
    }

    ConcurrentHashMap<String,Expiry> locks = new ConcurrentHashMap<>();
    ScheduledExecutorService sweeper = Executors.newScheduledThreadPool(1);

    public InMemoryLockProvider() {
        sweeper.scheduleAtFixedRate(this::sweep,1,1, TimeUnit.MINUTES);
    }

    private void sweep(){
        long now =  System.currentTimeMillis();
        locks.entrySet().removeIf(e -> e.getValue().deadline <= now);
    }

    @Override
    public boolean tryLock(String key, String userId, long ttlMilliseconds) {
        long now =  System.currentTimeMillis();
        Expiry expiry = new Expiry(now + ttlMilliseconds,userId);
        return locks.compute(key,(k,v)-> (v == null || v.deadline <= now) ? expiry : v) == expiry;
    }

    @Override
    public void unlock(String key) {
        locks.remove(key);
    }

    @Override
    public boolean isLockExpired(String key) {
        Expiry expiry = locks.get(key);
        long now =  System.currentTimeMillis();
        return (expiry!=null && expiry.deadline < now);
    }

    @Override
    public boolean isLockedBy(String key, String userId) {
        Expiry expiry = locks.get(key);
        return (expiry.owner.equals(userId));
    }
}
