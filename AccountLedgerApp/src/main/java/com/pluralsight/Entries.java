package com.pluralsight;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Entries {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;


    public Entries(LocalDate date, LocalTime time, String description, String vendor, double amount ) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.vendor = vendor;
        this.time = time;
    }
    public LocalDate getDate() {
        return date;

    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getTime() {
        return time;
    }
    public String toString() {
        return "Ledger{" +
                "date=" + date +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }

}
