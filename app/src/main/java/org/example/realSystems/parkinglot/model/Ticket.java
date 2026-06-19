package org.example.realSystems.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.realSystems.parkinglot.enums.Payment_Status;
import org.example.realSystems.parkinglot.strategy.PaymentStrategy;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private PaymentStrategy paymentStrategy;
    @Setter private Payment_Status paymentStatus;
    private Date entryTime;
    @Setter private Date exitTime;

    @Setter private String parkingFloorId;
    @Setter private String parkingSlot;
}
