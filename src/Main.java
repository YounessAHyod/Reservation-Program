import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Flight {
    String flightId;
    String departure;
    String destination;
    Map<Integer, String> seats;

    public Flight(String flightId, String departure, String destination) {

        this.flightId = flightId;

        this.departure = departure;

        this.destination = destination;

        this.seats = new HashMap<>();


        for (int i = 1; i <= 5; i++) {

            this.seats.put(i, "Available");
        }
    }

    public boolean bookSeat(int seatNumber, String passengerInfo) {

        if (this.seats.containsKey(seatNumber) && this.seats.get(seatNumber).equals("Available")) {

            this.seats.put(seatNumber, passengerInfo);

            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return "Flight " + flightId + " from " + departure + " to " + destination;
    }
}

class FlightBookingSystem {
    List<Flight> flights;
    Scanner scanner;

    public FlightBookingSystem() {

        this.flights = new ArrayList<>();

        this.scanner = new Scanner(System.in);

        initializeFlights();
    }

    private void initializeFlights() {
        flights.add(new Flight("PL1020", "Warsaw", "Gdansk"));

        flights.add(new Flight("PL1122", "Krakow", "Warsaw"));

        flights.add(new Flight("PL1230", "Wroclaw", "Poznan"));

        flights.add(new Flight("PL1333", "Bydgoszcz", "Łódź"));

        flights.add(new Flight("PL1450", "Tychy", "Legnica"));

        flights.add(new Flight("PL1554", "Olsztyn", "Katowiece"));

    }

    public void showMenu() {

        while (true) {

            System.out.println("Welcome to Youness Airline");

            System.out.println("1. Choose Flight");

            System.out.println("2. Check Flight Reserved");

            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    chooseFlight();
                    break;

                case 2:
                    checkReservedFlight();
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private void chooseFlight() {

        System.out.println("Available Flights:");

        for (int i = 0; i < flights.size(); i++) {

            System.out.println((i + 1) + ". " + flights.get(i));

        }

        System.out.print("Choose a flight (1-" + flights.size() + "): ");

        int flightChoice = scanner.nextInt() - 1;

        Flight selectedFlight = flights.get(flightChoice);

        System.out.println("Available seats for Flight " + selectedFlight.flightId + ":");

        for (Map.Entry<Integer, String> entry : selectedFlight.seats.entrySet()) {

            if (entry.getValue().equals("Available")) {

                System.out.println("Seat " + entry.getKey() + " is available.");
            }
        }

        System.out.print("Enter seat number to book: ");

        int seatNumber = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter your full name: ");

        String name = scanner.nextLine();

        System.out.print("Enter your passport number: ");

        String passport = scanner.nextLine();

        String passengerInfo = "Name: " + name + ", Passport: " + passport;


        if (selectedFlight.bookSeat(seatNumber, passengerInfo)) {

            System.out.println("Booking Completed");

        } else {

            System.out.println("Booking failed. Seat may be already booked or does not exist.");
        }
    }

    private void checkReservedFlight() {

        System.out.print("Enter your passport number: ");

        String passport = scanner.nextLine();

        boolean reservationFound = false;

        for (Flight flight : flights) {

            for (Map.Entry<Integer, String> entry : flight.seats.entrySet()) {

                if (entry.getValue().contains(passport)) {

                    System.out.println("Your reserved flight: " + flight);

                    System.out.println("Seat number: " + entry.getKey());

                    System.out.println("Passenger details: " + entry.getValue());

                    reservationFound = true;
                }
            }
        }
        if (!reservationFound) {
            System.out.println("No reservation found for the provided passport number.");
        }
    }
}

public class Main {

    public static void main(String[] args) {

        FlightBookingSystem system = new FlightBookingSystem();
        
        system.showMenu();
    }
}
