package com.pluralsight;
import java.io.*;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
public class Main {
    public static ArrayList <Entries> inventory = getEntries();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("Welcome To the Account Ledger App!");
            System.out.println("----------------------------------------");
            System.out.println("Home Screen");
            System.out.println("What would you like to do?: Please type the letter that is in parantheses");
            System.out.println("(D) Add Deposit: \n" + "(P) Make Payment (Debit) \n" + "(L) Display Ledger \n" + "(X) Exit");
           String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "D":
                    depositMenu();
                    break;
                case "P":
                    paymentMenu();
                    break;
                case "L":
                    ledgerHome();
                    break;
                case "X":
                    exitApp();
                    return;
                default:
                    System.out.println("Invalid Input Please try again.");

            }
        } while (true);
    }
    static void depositMenu(){
        System.out.println("Deposit Menu");

    }

    static void paymentMenu(){

        System.out.println("Payment Menu");
    }

    static void exitApp(){
        System.out.println("Exiting Application..");
        System.exit(0);
    }

    static void ledgerHome(){
        Scanner scanner = new Scanner(System.in);



        do {
            System.out.println("Ledger Menu");
            System.out.println("----------------------------------------");
            System.out.println("What would you like to do?: Please type the letter that is in parantheses");
            System.out.println("(A) Display All: \n" + "(D) Display Deposits \n" +"(P) Dipslay Payments \n" +
                    "(R) Display Reports \n" + "(X) Back To Home Screen");
            String ledgerOption = scanner.nextLine();

            switch (ledgerOption) {
                case "A":
                    allDisplay();
                    break;
                case "D":
                    //depositDisplay();
                    break;
                case "P":
                    //paymentDisplay();
                    System.out.println("Welcome to the Ledger Menu!\n" + "------------------------");
                    break;
                case "R":
                    //reportDisplay();
                case "X":
                    return;
                default:
                    System.out.println("Invalid Input Please try again.");

            }
        } while (true);
    }
    public static void allDisplay(){
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        for (Entries p : inventory){
            String timeFormatted = p.getTime().format(timeFormat);
        System.out.printf("%s | %s | %s | %s | %.2f \n", p.getDate(), timeFormatted,
                  p.getDescription(), p.getVendor(), p.getAmount());
      }


    }
    public static ArrayList<Entries> getEntries() {
        ArrayList<Entries> inventory = new ArrayList<Entries>();

        // this method loads product objects into inventory
        // and its details are not shown

        try {
            System.out.println("transactions.csv");
            BufferedReader br = new BufferedReader(new FileReader("transactions.csv"));
            String input;
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            while ((input = br.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                LocalDate date = LocalDate.parse(tokens[0]);
                LocalTime time = LocalTime.parse(tokens[1], timeFormat);
                String description = tokens[2];
                String vendor = tokens[3];
                double amount = Double.parseDouble(tokens[4]);
                Entries p = new Entries(date, time, description, vendor, amount);
                inventory.add(p);
            }


            // inventory.add(new Product(Integer.parseInt(tokens[0]), tokens[1], Float.parseFloat(tokens[2])));


            br.close();

        } catch (Exception e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

        return inventory;
    }

    public static void saveInventory(){

        try{

            FileWriter fw = new FileWriter("transactions.csv");

            for(Entries p : inventory){
                String data = p.getDate() + "|" + p.getTime() + "|"
                        + p.getDescription() + p.getVendor() + p.getAmount() + "\n";
                fw.write(data);
            }
            fw.close();



        } catch (Exception e) {
            System.out.println("FILE WRITE ERROR");
        }

    }
}





