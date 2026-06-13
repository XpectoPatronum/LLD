package org.example.realSystems.amazonlockersystem.repository;

import org.example.realSystems.amazonlockersystem.model.Package;
import org.example.realSystems.amazonlockersystem.model.PackageStatus;
import org.example.realSystems.amazonlockersystem.model.Size;

import java.util.HashMap;
import java.util.Map;

public class PackageRepository {
    Map<String, Package> packageIdToPackage = new HashMap<>();

    public Package createPackage(String id, Size size){
        Package pckg = new Package();
        pckg.setPackageId(id);
        pckg.setSize(size);
        pckg.setStatus(PackageStatus.CREATED);
        packageIdToPackage.put(id,pckg);
        return pckg;
    }
    public Package getPackageIdToPackage(String id){
        return packageIdToPackage.get(id);
    }
}
