package efs.task.syntax;

import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class GuessNumberGame {

    int maxNumber;
    int maxAttempts;
    int actualAttempt;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try{
            maxNumber = Integer.parseInt(argument);
            if (maxNumber < 1 || maxNumber > UsefulConstants.MAX_UPPER_BOUND) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        maxAttempts = (int)(Math.log(maxNumber) / Math.log(2)) + 1;
        actualAttempt = 0;
    }

    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + maxNumber + ">");
        Random random = new Random();
        int correctAnswer = Math.abs(random.nextInt()) % maxNumber + 1;
        Scanner scanner = new Scanner(System.in);
        boolean win = false;

        while (actualAttempt < maxAttempts) {
            actualAttempt++;
            print_live_bar();
            int number = read_number(scanner);
            if (number == -1);
            else if (number > correctAnswer) {
                System.out.println("To "+ UsefulConstants.TO_MUCH);
            }
            else if (number < correctAnswer) {
                System.out.println("To "+ UsefulConstants.TO_LESS);
            }
            else {
                System.out.println(UsefulConstants.YES + "!");
                win = true;
                break;
            }
        }
        
        if (win) {
            System.out.println(
                UsefulConstants.CONGRATULATIONS + ", " + actualAttempt + 
                " tyle prób zajęło Ci odgadnięcie liczby " + correctAnswer
                );
        }
        else {
            System.out.println(
                UsefulConstants.UNFORTUNATELY + ", wyczerpales limit prób (" 
                + maxAttempts + ") do odgadnięcia liczby " + correctAnswer
                );
        }
            
        scanner.close();
    }

    public void print_live_bar() {
        System.out.print("Twoje próby: [");
        int i;
        for (i = 0; i < actualAttempt; i++){
            System.out.print("*");
        }
        for (;i < maxAttempts; i++){
            System.out.print(".");
        }
        System.out.println("]");
    }

    public int read_number(Scanner scanner) {
        String number_string = "";
        int number = -1;
        try {
            System.out.println(UsefulConstants.GIVE_ME + " liczbę:");
            number_string = scanner.nextLine();
            number = Integer.parseInt(number_string);
        } 
        catch (Exception e) {
            System.out.println("Hmm, "+ number_string + " to " + UsefulConstants.NOT_A_NUMBER);
        } 
        return number;
    }
}