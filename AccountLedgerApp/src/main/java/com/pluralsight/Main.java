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
    public static ArrayList<Entries> inventory = getEntries();

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

    static void depositMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deposit Menu");
        System.out.print("Enter the amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter the description of the deposit: ");
        String description = scanner.nextLine();
        System.out.print("Enter the vendor/source of the deposit: ");
        String vendor = scanner.nextLine();

        // Creating and adding the deposit entry to the inventory
        Entries depositEntry = new Entries(LocalDate.now(), LocalTime.now(), description, vendor, amount);
        inventory.add(depositEntry);

        saveInventory(); // Save the inventory after adding a new deposit
        System.out.println("Deposit added successfully!");

    }

    static void paymentMenu() {

        System.out.println("Payment Menu");
    }

    static void exitApp() {
        System.out.println("Exiting Application..");
        System.exit(0);
    }

    static void ledgerHome() {
        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("Ledger Menu");
            System.out.println("----------------------------------------");
            System.out.println("What would you like to do?: Please type the letter that is in parantheses");
            System.out.println("(A) Display All: \n" + "(D) Display Deposits \n" + "(P) Dipslay Payments \n" +
                    "(R) Display Reports \n" + "(X) Back To Home Screen");
            String ledgerOption = scanner.nextLine();

            switch (ledgerOption) {
                case "A":
                    allDisplay();
                    break;
                case "D":
                    depositDisplay();
                    break;
                case "P":
                    paymentDisplay();
                    break;
                case "R":
                    reportDisplay();
                case "X":
                    return;
                default:
                    System.out.println("Invalid Input Please try again.");

            }
        } while (true);
    }

    public static void depositDisplay() {
        System.out.println("Displaying Deposits:");
        for (Entries p : inventory) {
            if (p.getAmount() > 0) { // Display only deposits (positive amounts)
                System.out.printf("%s | %s | %s | %.2f \n", p.getDate(), p.getDescription(),
                        p.getVendor(), p.getAmount());
            }
        }
    }

    public static void allDisplay() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        for (Entries p : inventory) {
            String timeFormatted = p.getTime().format(timeFormat);
            System.out.printf("%s | %s | %s | %s | %.2f \n", p.getDate(), timeFormatted,
                    p.getDescription(), p.getVendor(), p.getAmount());
        }
    }

    public static void paymentDisplay() {
        if (inventory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        boolean hasPayments = false; // To track if there are any payments
        for (Entries p : inventory) {
            try {
                if (p.getAmount() < 0) { // Display only payments (negative amounts)
                    hasPayments = true; // Set the flag if a payment is found
                    System.out.printf("%s | %s | %s | %.2f \n", p.getDate(), p.getDescription(), p.getVendor(),
                            p.getAmount());
                }
            } catch (Exception e) {
                System.out.println("Error displaying payment entry: " + e.getMessage());
            }
        }

        if (!hasPayments) {
            System.out.println("No payments found.");
        }
    }


    public static void reportDisplay() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Report Menu");
            System.out.println("----------------------------------------");
            System.out.println("Choose a reporting option:");
            System.out.println("(1) Month To Date \n(2) Previous Month \n(3) Year To Date \n(4) Previous Year \n(5) Search By Vendor \n(0) Exit Report Menu");
            String reportOption = scanner.nextLine().toUpperCase();

            switch (reportOption) {
                case "1":
                    reportMonthToDate();
                    break;
                case "2":
                    reportPreviousMonth();
                    break;
                case "3":
                    reportYearToDate();
                    break;
                case "4":
                    reportPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    return; // Exit report menu
                default:
                    System.out.println("Invalid Input. Please try again.");
            }
        } while (true);
    }

    public static void reportMonthToDate() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        System.out.println("Month To Date Transactions:");
        displayReportBetweenDates(startOfMonth, LocalDate.now());
    }

    public static void reportPreviousMonth() {
        LocalDate now = LocalDate.now();
        LocalDate startOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
        System.out.println("Previous Month Transactions:");
        for (Entries p : inventory) {



    }

    public static void reportYearToDate() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        System.out.println("Year To Date Transactions:");
        displayReportBetweenDates(startOfYear, LocalDate.now());
    }

    public static void reportPreviousYear() {
        LocalDate startOfLastYear = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate endOfLastYear = LocalDate.now().minusYears(1).withMonth(12).withDayOfMonth(31);
        System.out.println("Previous Year Transactions:");

    }

    public static void searchByVendor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vendor name to search: ");
        String vendorName = scanner.nextLine();
        System.out.println("Transactions for Vendor: " + vendorName);
        boolean found = false;

        for (Entries p : inventory) {
            if (p.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.printf("%s | %s | %s | %.2f \n", p.getDate(), p.getDescription(), p.getVendor(),
                        p.getAmount());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for vendor: " + vendorName);
        }
    }

    public static void displayReportBetweenDates(LocalDate startDate, LocalDate endDate) {
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

        public static void saveInventory () {

            try {

                FileWriter fw = new FileWriter("transactions.csv");

                for (Entries p : inventory) {
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





