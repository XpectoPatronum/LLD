package org.example.realSystems.amazonlockersystem.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Locker {
    private String lockerId;
    private String zipcode;
    private List<Slot> slots;

    public Slot getSlotById(String slotId){
        for(Slot s : slots){
            if(Objects.equals(s.getSlotId(), slotId)){
                return s;
            }
        }
        return null;
    }
}
