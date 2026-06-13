package org.example.realSystems.atm.repository;

import lombok.Data;
import org.example.realSystems.atm.model.ATM;
import org.example.realSystems.atm.model.AtmStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class AtmRepository {
    Map<String, ATM> atmIdToATM = new HashMap<>();

    public void save(ATM atm) {
        atmIdToATM.put(atm.getAtmId(),atm);
    }

    public ATM getATM(String atmId) {
        if(!atmIdToATM.containsKey(atmId)){
            return atmIdToATM.get(atmId);
        }else{
            System.out.println("No ATM found with id " + atmId);
            return null;
        }
    }

    public void updateATMStatusById(String atmId, AtmStatus newAtmStatus) {
        atmIdToATM.get(atmId).setAtmStatus(newAtmStatus);
    }
}
