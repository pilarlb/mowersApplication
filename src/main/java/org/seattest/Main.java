package org.seattest;

import org.seattest.entities.Mower;
import org.seattest.entities.Plateau;
import org.seattest.usecase.MowerMovementsUsecase;


import java.util.*;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        Plateau plateau;
        MowerMovementsUsecase usecase = new MowerMovementsUsecase();
        Mower mowerOne, mowerTwo;

        System.out.println("Enter upper-right coordinates of the plateau");

        plateau = createPlateau(scanner.nextLine());

        mowerOne = getAnswersFromScanner(plateau);
        mowerTwo = getAnswersFromScanner(plateau);

        Thread mowerOneMoves = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(usecase.moves(mowerOne));
            }
        });

        Thread mowerTwoMoves = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(usecase.moves(mowerTwo));
            }
        });

        mowerOneMoves.start();

        try {
            mowerOneMoves.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mowerTwoMoves.start();
    }

    /**
     * Gets answers from scanner for create a mower and set movements
     */
    private static Mower getAnswersFromScanner(Plateau plateau){

        System.out.println("Enter position and heading for the mower");

        Mower mower = createMower(scanner.nextLine().toUpperCase(), plateau);

        System.out.println("Enter movements for the mower");

        mower.setMovements(scanner.nextLine().toUpperCase());

        return mower;
    }
    private static Mower createMower(String mowerPosition, Plateau plateau){
        Mower mower = new Mower();
        String[] mowerCharacteristics = mowerPosition.split(" ");
        mower.setCoordinateX(Integer.parseInt(mowerCharacteristics[0]));
        mower.setCoordinateY(Integer.parseInt(mowerCharacteristics[1]));
        mower.setHeading((mowerCharacteristics[2]).charAt(0));
        mower.setPlateau(plateau);
        return mower;
    }

    private static Plateau createPlateau(String characteristics){
        Plateau plateau = new Plateau();

        int[] coordinatesPlateau = (Arrays.stream(characteristics.split(" "))
                .mapToInt(Integer::parseInt)).toArray();

        plateau.setCoordinateX(coordinatesPlateau[0]);
        plateau.setCoordinateY(coordinatesPlateau[1]);
        return plateau;
    }

}