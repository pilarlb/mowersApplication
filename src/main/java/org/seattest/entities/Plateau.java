package org.seattest.entities;

public class Plateau {

    private int coordinateX;
    private int coordinateY;
    private String occupiedPosition;

    public Plateau() {
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getOccupiedPosition() {
        return occupiedPosition;
    }

    public void setOccupiedPosition(String occupiedPosition) {
        this.occupiedPosition = occupiedPosition;
    }
}
