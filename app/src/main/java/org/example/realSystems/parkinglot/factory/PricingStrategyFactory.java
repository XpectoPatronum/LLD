package org.example.realSystems.parkinglot.factory;

import org.example.realSystems.parkinglot.enums.Pricing_Strategy;
import org.example.realSystems.parkinglot.strategy.EventBasedPricingStrategy;
import org.example.realSystems.parkinglot.strategy.PricingStrategy;
import org.example.realSystems.parkinglot.strategy.TimeBasedPricingStrategy;

public class PricingStrategyFactory {
    public static PricingStrategy getInstance(Pricing_Strategy pricingStrategy) {
        if(pricingStrategy == Pricing_Strategy.TIME_BASED) {
            return new TimeBasedPricingStrategy();
        }else if(pricingStrategy == Pricing_Strategy.EVENT_BASED) {
            return new EventBasedPricingStrategy();
        } else{
          throw new  IllegalArgumentException("PricingStrategy not supported");
        }
    }
}
