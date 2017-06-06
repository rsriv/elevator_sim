# Elevator Management

Simple ElevatorManager that takes input of queue of elevator call requests and parses it into efficient and intuitive queues that are handled by n individual elevators. Scalable for number of floors and any number of elevators.



## Algorithm

While the queue is not empty, give priority to first request in queue (with floor no. 'start'), then iterate through it and select any requests that meet the criteria that the floor number is less than start if it's going down, or greater than start if it's going up. When done iterating through queue, add this queue to the list of queues, which are then passed to the list of n elevators, prioritizing the elevator closest to the starting floor and assuming all elevators have handled one request.


## Assumptions

* The time an elevator takes to complete its queue is independent of the queue's length
* All requests are within a relatively short time frame
* Users entering the elevator are targeting a floor in the direction of their request
* There is some other listener that enqueues the requests from inside the elevator
	* Button presses should send a Request that has the floor of the button pressed and the current direction of the elevator


## Next Steps

* Add listener to enqueue button presses from inside the elevator
* Realtime simulation
* Further optimize request handling   
