package org.example.realSystems.parkinglot.strategy;

import org.example.realSystems.parkinglot.model.Ticket;

public interface PricingStrategy {
    public double calculateFees(Ticket ticket);
}
