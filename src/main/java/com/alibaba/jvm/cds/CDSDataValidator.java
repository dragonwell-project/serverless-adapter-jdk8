package com.alibaba.jvm.cds;

import java.util.Optional;

public interface CDSDataValidator {
    Optional<Boolean> isInvalid(Classes4CDS.CDSData cdsData);
}
