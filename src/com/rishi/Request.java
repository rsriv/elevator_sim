package com.rishi;

/**
 * Created by rishi on 2017-06-05.
 */
public class Request {
    private int floor;
    private RequestType dir;

    public Request (int floor, RequestType dir){
        this.floor = floor;
        this.dir = dir;
    }

    public int getFloor(){
        return this.floor;
    }

    public RequestType getType(){
        return this.dir;
    }
}
