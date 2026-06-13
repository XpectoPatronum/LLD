package org.example.realSystems.amazonlockersystem.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class Slot {
    private String slotId;
    private AtomicBoolean available;
    private Size size;
    private Package storedPackage;

    public Slot(String slotId, Size size, AtomicBoolean available) {
        this.slotId = slotId;
        this.size = size;
        this.available = available;
    }

    public boolean isAvailable(){
        return (available.get());
    }

    public boolean acquire(Package pack){
        storedPackage = pack;
        return available.compareAndSet(true,false);
    }

    public void release(){
        storedPackage = null;
        available.set(true);
    }


    public boolean willFit(Size check){
        return(check.getLength() <= this.size.getLength() &&
            check.getBreadth() <= this.size.getBreadth() &&
            check.getHeight() <= this.size.getHeight());
    }
}
