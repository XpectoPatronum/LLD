package org.example.realSystems.amazonlockersystem.service.strategy.lockerSlotStrategy;

import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.model.Slot;

import java.util.List;

public interface SlotStrategy {
    Slot chooseSlot(List<Slot> slotList, Package parcel);
}
