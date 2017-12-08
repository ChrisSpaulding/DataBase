package javafrontend;

//Format taken from David A. 

//ADD SETTER FOR PORTABILITY

import com.sun.org.apache.xpath.internal.SourceTree;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JavaFrontEnd {
    static Scanner reader = new Scanner (System.in);

    public static void main(String args[]) {
      
        try {
            String  sqlStatement="";
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cat",
                            "bob", "123");
            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            Flight currentFlight = new Flight();
            
            System.out.println("If you are a returning customer please enter your "
                    + "Customer ID number otherwise enter -1");
            int ID= reader.nextInt();
            Customer customer = new Customer(currentFlight);
            if(!customer.existingCustomer(ID)){
                currentFlight=customer.addCustomer();
            }
            else{
                currentFlight.setTicketPerson(ID);
            }
            System.out.println("Currently you can only buy a ticket for yourself");
            
            
            SelectFlight sf = new SelectFlight(currentFlight);
            currentFlight=sf.createTicket();
            currentFlight.setPaymentPerson(currentFlight.getTicketPerson());
           
            sqlStatement= "Insert into Flights(Unique_Flight_number,"
                    + " Airline_Name, DepartureDate, PaymentPerson, TicketPerson,"
                    + " BookingNumber) VALUES (\'"+currentFlight.getFlightNumber()+
                    "\',\'"+currentFlight.getAirlingName()+"\',\'"+currentFlight.getSqlDate()+"\'"
                    + ",\'"+currentFlight.getPaymentPerson()+"\',\'"+
                    currentFlight.getTicketPerson()+"\',\'"+currentFlight.getBookingNumber()
                    +"\');";
            stmt.executeUpdate(sqlStatement);
            
            TicketPrinter TP = new TicketPrinter(currentFlight);
            TP.printTicket();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
           
        }

        System.out.println("Opened database successfully");

    }
}