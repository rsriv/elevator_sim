package com.rishi;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	// sample elevator manager with 5 elevators
        ElevatorManager em = new ElevatorManager(5);

        //sample queue of raw requests
        LinkedList<Request> queue = new LinkedList<>();
        queue.add(new Request(7,RequestType.DOWN));
        queue.add(new Request(9,RequestType.UP));
        queue.add(new Request(10,RequestType.UP));
        queue.add(new Request(1,RequestType.DOWN));
        queue.add(new Request(4,RequestType.DOWN));
        queue.add(new Request(8,RequestType.DOWN));
        queue.add(new Request(4,RequestType.UP));
        queue.add(new Request(6,RequestType.DOWN));
        queue.add(new Request(5,RequestType.UP));
        queue.add(new Request(10,RequestType.DOWN));
        queue.add(new Request(5,RequestType.DOWN));
        queue.add(new Request(2,RequestType.UP));

        //pass raw requests to elevator manager
        em.genQueues(queue);

        //for debugging
        //em.printQueues();

        //simulate Elevators handling queues
        em.runQueues();

    }
}
