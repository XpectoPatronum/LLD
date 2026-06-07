package org.example.amazonlockersystem.service.strategy.lockerSlotStrategy;

import org.example.amazonlockersystem.model.Package;
import org.example.amazonlockersystem.model.Slot;

import java.util.List;

public interface SlotStrategy {
    Slot chooseSlot(List<Slot> slotList, Package parcel);
}
