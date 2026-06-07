package org.example.amazonlockersystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OtpInfo {
    private String value;
    private String lockerId;
    private String slotId;
    private LocalDate ttl;
}
