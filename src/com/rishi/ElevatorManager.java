package com.rishi;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by rishi on 2017-06-04.
 */

public class ElevatorManager {
    private ArrayList<Elevator> elevators;
    private ArrayList<LinkedList> queues;

    public ElevatorManager (int n){
        elevators = new ArrayList<>();
        queues = new ArrayList<>();

        //create n elevators
        for (int i = 0; i < n; i++){
            elevators.add(new Elevator());
        }
    }

    //sort queues by request type, then ascending or descending
    private LinkedList sortQueue (LinkedList<Request> queue){
        RequestType type = queue.get(0).getType();
        //sort priority queue for DOWN
        if(type == RequestType.DOWN){
            //bubble sort
            for(int j = 1; j<=queue.size()-1; j++) {
                for (int i = 1; i < queue.size() - j; i++) {
                    if (queue.get(i).getFloor() < queue.get(i + 1).getFloor()) {
                        Request temp = queue.get(i);
                        queue.set(i, queue.get(i + 1));
                        queue.set(i + 1, temp);

                    }
                }
            }
        }
        //sort priority queue for UP
        else if (type == RequestType.UP){
            //bubble sort
            for(int j = 1; j<=queue.size()-1; j++) {
                for (int i = 1; i < queue.size() - j; i++) {
                    if (queue.get(i).getFloor() > queue.get(i + 1).getFloor()) {
                        Request temp = queue.get(i);
                        queue.set(i, queue.get(i + 1));
                        queue.set(i + 1, temp);
                    }
                }
            }
        }
        return queue;
    }

    //convert masterQueue to efficient queues for Elevators to follow
    public void genQueues (LinkedList<Request> masterQueue){

        while(masterQueue.peekFirst() != null) {
            //initialize a temporary queue
            LinkedList<Request> tempQueue = new LinkedList<>();
            tempQueue.add(masterQueue.poll());
            RequestType queueType = tempQueue.get(0).getType();
            int floorLimit = tempQueue.get(0).getFloor(); //floor that serves as threshold for decisions

            //iterate through queue selecting requests that are greater for type UP and less than for type DOWN
            for (int i = 0; i < masterQueue.size(); i++){
                Request currentRequest = masterQueue.get(i);

                if(currentRequest.getType() == queueType) {
                    if (queueType == RequestType.DOWN && currentRequest.getFloor() < floorLimit) {
                        tempQueue.add(masterQueue.remove(i));
                        i--;
                    } else if (queueType == RequestType.UP && currentRequest.getFloor() > floorLimit) {
                        tempQueue.add(masterQueue.remove(i));
                        i--;
                    } else if (queueType == currentRequest.getType() && currentRequest.getFloor() == tempQueue.get(0).getFloor()) {
                        masterQueue.remove(i);
                        i--;
                    }
                }

            }

            //sort and add temporary queue to list
            queues.add(sortQueue(tempQueue));
        }
    }

    //start elevators after queues are generated
    public void runQueues(){
        System.out.println("\nStarting Elevators!");
        int numBusy = 0; //keep track of which elevators are already busy
        for(LinkedList<Request> queue: queues){

            //assume all elevators finish queues at same time and reset
            if(numBusy == elevators.size()){
                for(Elevator elevator: elevators){
                    elevator.setState(States.STATIC);
                    numBusy = 0;
                }
            }

            //minimize distance of available elevators and queue head's floor no.
            int bestIndex = 0;
            int minDistance = Math.abs(elevators.get(0).getFloor() - queue.get(0).getFloor());

            for(Elevator elevator: elevators){
                if (Math.abs(elevator.getFloor() - queue.get(0).getFloor()) < minDistance && elevator.getState() == States.STATIC){
                    bestIndex = elevators.indexOf(elevator);
                }
            }

            System.out.println("\nElevator " + (bestIndex+1));

            //assign this queue to elevator[bestIndex]
            elevators.get(bestIndex).go(queue);
            numBusy++;
        }

    }

    //for debugging
    public void printQueues(){
        int count = 1;
        for(LinkedList<Request> list: queues){
            System.out.println("QUEUE NO. "+count);
            count++;
            for(int i = 0; i < list.size(); i++){
                System.out.println("Floor "+list.get(i).getFloor()+"; Going "+list.get(i).getType());
            }
        }
    }

}
