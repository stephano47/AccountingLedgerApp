package com.pluralsight;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To the Account Ledger App!");
        System.out.println("----------------------------------------");
        System.out.println("Home Screen");
        System.out.println("What would you like to do?: Please type the letter that is in parantheses");
        System.out.println("(D) Add Deposit: \n"+ "(P) Make Payment (Debit) \n"+"(L) View Ledger Menu \n"+"(X) Exit");
        String userChoice = scanner.nextLine();

        switch(userChoice){
            case "D":
                depositMenu();
            break;
            case "P":
                paymentMenu();
            break;
            case "L":
                ledgerMenu();
            break;
            case "X":
                exitApp();
            break;
            default:
                System.out.println("Invalid Input Please try again.");

        }
    }

    static void depositMenu(){
        System.out.println("Deposit Menu");

    }

    static void paymentMenu(){
        System.out.println("Payment Menu");
    }
    static void ledgerMenu(){
        System.out.println("Ledger Menu");
    }
    static void exitApp(){
        System.out.println("Exiting Application..");
        System.exit(0);
    }
}