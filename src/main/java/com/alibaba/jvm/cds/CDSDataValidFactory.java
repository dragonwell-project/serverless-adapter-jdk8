package com.alibaba.jvm.cds;

import com.alibaba.jvm.cds.valid.SignedJarValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CDSDataValidFactory {
    private static final CDSDataValidFactory INST = new CDSDataValidFactory();
    private List<CDSDataValidator> validators = new ArrayList<>();

    private CDSDataValidFactory() {
        validators.add(new SignedJarValidator());
    }

    public static CDSDataValidFactory getInstance() {
        return INST;
    }

    public Optional<String> isInvalid(Classes4CDS.CDSData cdsData) {
        for (CDSDataValidator cdv : validators) {
            Optional<Boolean> v = cdv.isInvalid(cdsData);
            if (!v.isPresent()) {
                return Optional.of("Invalid by " + cdv.getClass().getName() + " with unknown reason.");
            }
            if (v.get()) {
                return Optional.of("Invalid by " + cdv.getClass().getName());
            }
        }
        return Optional.empty();
    }
}

