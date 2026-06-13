package org.example.realSystems.amazonlockersystem.service.strategy.lockerSlotStrategy;

import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.model.Slot;

import java.util.List;

public class FirstSlotFindStrategy implements SlotStrategy {
    @Override
    public Slot chooseSlot(List<Slot> slotList, Package parcel) {
        for(Slot slot : slotList){
            if(slot.acquire(parcel)){
                return slot;
            }
        }
        return null;
    }
}
