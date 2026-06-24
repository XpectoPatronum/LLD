package org.example.realSystems.elevatorDesign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {

    }

    // ************ Entities ***************

    @RequiredArgsConstructor
    public class Elevator{
        String elevatorId;
        int currentFloor;
        Set<Request> requests = new HashSet<>();
        Status status;

        void step(){

        }
    }

    class ElevatorController{
        List<Elevator> elevators;
        ElevatorController(List<Elevator> elevators){
            this.elevators = elevators;
        }

        void addElevator(Elevator elevator){
            elevators.add(elevator);
        }

        void step(){
            for(Elevator elevator : elevators){
                elevator.step();
            }
        }

        //returns the elevator id that will serve the request
        Elevator requestElevator(Integer floor, RequestType requestType){
            Request request = new Request(floor, requestType);
            return getBestElevator(request);
        }

        Elevator getBestElevator(Request request){
            Elevator bestElevator = null;
            bestElevator = getMovingTowardsUs(request);
            if(bestElevator != null){
                return bestElevator;
            }

            bestElevator = findNearestIdle(request);
            if(bestElevator != null){
                return bestElevator;
            }

            bestElevator = findNearest(request);
            return  bestElevator;
        }

        Elevator getMovingTowardsUs(Request request){
            Elevator best = null;
            int minDistance = Integer.MAX_VALUE;
            for(Elevator elevator : elevators){
                if(elevator.status == Status.MOVING_DOWN && request.requestType == RequestType.HALL_CALL_UP || elevator.status == Status.MOVING_UP && request.requestType == RequestType.HALL_CALL_DOWN){
                    continue;
                }
                if(elevator.status == Status.MOVING_UP && elevator.currentFloor > request.getFloor()){
                    continue;
                }
                if(elevator.status == Status.MOVING_DOWN && elevator.currentFloor < request.getFloor()){
                    continue;
                }
                minDistance = Math.min(minDistance, elevator.currentFloor - request.getFloor());
                if(minDistance == elevator.currentFloor - request.getFloor()){
                    best = elevator;
                }
            }
            return best;
        }

        Elevator findNearestIdle(Request request){
            Elevator best = null;
            int minDistance = Integer.MAX_VALUE;
            for(Elevator elevator : elevators){
                if(elevator.status == Status.IDLE){
                    minDistance = Math.min(minDistance, elevator.currentFloor - request.getFloor());
                    if(minDistance == elevator.currentFloor - request.getFloor()){
                        best = elevator;
                    }
                }
            }
            return best;
        }

        Elevator findNearest(Request request){
            Elevator best = null;
            int minDistance = Integer.MAX_VALUE;
            for(Elevator elevator : elevators){
                minDistance = Math.min(minDistance, elevator.currentFloor - request.getFloor());
                if(minDistance == elevator.currentFloor - request.getFloor()){
                    best = elevator;
                }
            }
            return best;
        }
    }


    @Data
    @AllArgsConstructor
    class Request{
        Integer floor;
        RequestType requestType;
    }


    // ************ Enums *************

    enum Status {
        MOVING_UP,
        MOVING_DOWN,
        IDLE
    }

    enum RequestType{
        HALL_CALL_UP,
        HALL_CALL_DOWN,
        DESTINATION_CALL
    }


}
