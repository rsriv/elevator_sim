package com.rishi;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rishi on 2017-06-05.
 */
public class Elevator {
    private States currentState;
    private int currentFloor;

    public Elevator(){
        currentState = States.STATIC;
        currentFloor = 0; //all elevators start at floor 0
    }

    public void go (LinkedList<Request> queue){
        for (int i = 0; i < queue.size(); i++){
            if(queue.get(i).getFloor() > currentFloor){
                currentState = States.UP;
            }
            else {
                currentState = States.DOWN;
            }

            currentFloor = queue.get(i).getFloor();
            System.out.println("Floor " + currentFloor + "; Going "+currentState);

            //listen for requests and enqueue them here
        }
        queue.clear();
        System.out.println("Completed queue; Waiting...");
    }

    //getters and setters
    public int getFloor(){
        return currentFloor;
    }

    public States getState(){
        return currentState;
    }

    public void setState(States state){
        this.currentState = state;
    }
}
