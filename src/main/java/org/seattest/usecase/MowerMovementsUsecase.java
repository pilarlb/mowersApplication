package org.seattest.usecase;

import org.seattest.entities.Mower;
import org.seattest.entities.Plateau;

import java.util.HashMap;

import static java.util.Objects.isNull;
import static org.seattest.util.GlobalConstants.*;

public class MowerMovementsUsecase {

    /**
     * Changes the position of the mower using a list of movements
     */
    public String moves(Mower mower){

        char[] movementsList = mower.getMovements().toCharArray();

        for(int i=0; i < movementsList.length; i++){

            if(equalChar(movementsList[i], LEFT) || equalChar(movementsList[i], RIGHT)){

                moveHeading(mower, movementsList[i]);

            }else if(equalChar(movementsList[i], MOVE)){

                moveForward(mower);

            }else{

                System.out.println (ERROR_MOVEMENT_NOT_RECOGNISED + "in "+ i + "position");
            }
        }

        return mowerPositionToString(mower);
    }

    private String mowerPositionToString(Mower mower){
        return mower.getCoordinateX() + " " + mower.getCoordinateY() + " " + mower.getHeading();
    }

    /**
     * Validates the next position of the mower is not out of the plateau border
     */
    private boolean validateIsNotOutOfBorder(Plateau plateau, int coordinateX, int coordinateY){
        return (plateau.getCoordinateX() >= coordinateX && plateau.getCoordinateY() >= coordinateY)
                && (coordinateX > -1 && coordinateY > -1);

    }

    private boolean validateIsNotOccupiedPosition(Plateau plateau, int coordinateX, int coordinateY){
        if(!isNull(plateau.getOccupiedPosition())) {
            String[] occupiedPosition = plateau.getOccupiedPosition().split(" ");
            int coordX = Integer.parseInt(occupiedPosition[0]);
            int coordY = Integer.parseInt(occupiedPosition[1]);

            return coordX != coordinateX || coordY != coordinateY;
        }
        return true;
    }

    private boolean validations(Plateau plateau, int coordinateX, int coordinateY){
        return validateIsNotOutOfBorder(plateau, coordinateX, coordinateY) &&
                validateIsNotOccupiedPosition(plateau, coordinateX, coordinateY);
    }

    /**
     * This method changes the coordinates X or Y of the mower, according to the heading mower
     */
    private int moveForward(Mower mower){

        int movementsNumber = 0;

        HashMap<Character, Integer> cardinalPoint = createMapMovements();

        if(equalChar(mower.getHeading(), NORTH) || equalChar(mower.getHeading(), SOUTH)){
            int positionY = mower.getCoordinateY() + cardinalPoint.get(mower.getHeading());

            if(validations(mower.getPlateau(), mower.getCoordinateX(), positionY)){

                mower.setCoordinateY(positionY);
                movementsNumber++;
            }else {
                throw new RuntimeException("Occupied position or movement out of border");
            }

        } else{
            int positionX = mower.getCoordinateX() + cardinalPoint.get(mower.getHeading());
            if(validations(mower.getPlateau(), positionX, mower.getCoordinateY())){

                mower.setCoordinateX(positionX);
                movementsNumber++;
            }else {
                throw new RuntimeException("Occupied position or movement out of border");
            }
        }

        return movementsNumber;
    }

    /**
     * This method changes the heading mower, according to the direction
     */
    private int moveHeading(Mower mower, char direction){

        int movementsNumber = 0;

        HashMap<Character, Character> leftDirection = createMapLeftDirection();
        HashMap<Character, Character> rightDirection = createMapRightDirection();

        if(equalChar(direction, LEFT)){

            mower.setHeading(leftDirection.get(mower.getHeading()));

        }else {

            mower.setHeading(rightDirection.get(mower.getHeading()));

        }
        movementsNumber++;

        return movementsNumber;
    }
    private HashMap<Character, Integer> createMapMovements(){
        HashMap<Character, Integer> cardinalPoint = new HashMap<>();
        cardinalPoint.put(EAST,  1);
        cardinalPoint.put(WEST, -1);
        cardinalPoint.put(NORTH, 1);
        cardinalPoint.put(SOUTH, -1);
        return cardinalPoint;
    }
    private HashMap<Character, Character> createMapRightDirection(){
        HashMap<Character, Character> rightDirection = new HashMap<>();
        rightDirection.put(NORTH, EAST);
        rightDirection.put(EAST, SOUTH);
        rightDirection.put(SOUTH, WEST);
        rightDirection.put(WEST, NORTH);
        return rightDirection;
    }

    private HashMap<Character, Character> createMapLeftDirection(){
        HashMap<Character, Character> leftDirection = new HashMap<>();
        leftDirection.put(NORTH, WEST);
        leftDirection.put(EAST, NORTH);
        leftDirection.put(SOUTH, EAST);
        leftDirection.put(WEST, SOUTH);
        return leftDirection;
    }

    /**
     * Compares if two chars are equals
     */
    private boolean equalChar(char a, char b){
        return Character.compare(a, b) == 0;
    }
}
