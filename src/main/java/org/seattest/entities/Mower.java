package org.seattest.entities;

public class Mower {

    private int coordinateX;
    private int coordinateY;
    private char heading;
    private Plateau plateau;
    private String movements;

    public Mower() {
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

    public char getHeading() {
        return heading;
    }

    public void setHeading(char heading) {
        this.heading = heading;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }
}
