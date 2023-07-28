package com.example.myapplication;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y) {
         this.x = x;
         this.y = y;
    }

    public float getX() {//getters
        return this.x;
    }//getters

    public void setX(float x) {//setters
        this.x = x;
    }//setters

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
