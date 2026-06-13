package org.example.realSystems.amazonlockersystem.repository;

import org.example.realSystems.amazonlockersystem.model.Locker;

import java.util.*;


public class LockerRepository {

    public Map<String,Locker> lockerList = new HashMap<>();

    public Map<String, List<Locker>> zipCodeToLockers = new HashMap<>();

    public void addLocker(Locker locker){
        lockerList.put(locker.getLockerId(),locker);
        if(zipCodeToLockers.containsKey(locker.getZipcode())){
            zipCodeToLockers.get(locker.getZipcode()).add(locker);
        }else{
            zipCodeToLockers.put(locker.getZipcode(),new ArrayList<Locker>());
            zipCodeToLockers.get(locker.getZipcode()).add(locker);
        }
    }

    public Locker getLockerById(String lockerId) {
        return lockerList.get(lockerId);
    }
}
