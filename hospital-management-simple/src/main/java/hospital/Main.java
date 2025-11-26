package hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hospital Management System - Simple Student Project
 * A basic hospital management system for tracking doctors, patients, appointments, and bills.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hospital Management System Started!");
        System.out.println("Access at: http://localhost:8080");
    }
}
