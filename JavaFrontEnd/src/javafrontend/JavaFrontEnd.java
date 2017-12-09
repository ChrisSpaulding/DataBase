package javafrontend;

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
      DBCreds DBC = new DBCreds();
      String in;
      System.out.println("Press y to set up database press n to continue");
      if(reader.nextLine().equals('y')){
          DBC.setUpConnection();
      }else{
          DBC.useDefaultConnection();
      }
      
        try {
            String con="y";
            while(con.equals("y")){
            String  sqlStatement="";
            Class.forName("org.postgresql.Driver");
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        
            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            Flight currentFlight = new Flight();
            
            System.out.println("If you are a returning customer please enter your "
                    + "Customer ID number otherwise enter -1");
            int ID= reader.nextInt();
            Customer customer = new Customer(currentFlight,DBC);
            if(!customer.existingCustomer(ID)){
                currentFlight=customer.addCustomer();
            }
            else{
                currentFlight.setPaymentPerson(ID);
            }
            System.out.println("To purchase the ticket for someone else hit 1");
            if(reader.nextInt()==1){
                System.out.println("Please enter thier ID number"
                        + "-1 if they are a new customer");
                int userAnswer;
                userAnswer=reader.nextInt();
                if(!customer.existingCustomer(userAnswer)){
                    userAnswer= customer.createPerson();
                }
                currentFlight.setTicketPerson(userAnswer);
                
            }
                 
            
            SelectFlight sf = new SelectFlight(currentFlight,DBC);
            currentFlight=sf.createTicket();
            sqlStatement= "Insert into Flights(Unique_Flight_number,"
                    + " Airline_Name, DepartureDate, PaymentPerson, TicketPerson,"
                    + " BookingNumber) VALUES (\'"+currentFlight.getFlightNumber()+
                    "\',\'"+currentFlight.getAirlingName()+"\',\'"+currentFlight.getSqlDate()+"\'"
                    + ",\'"+currentFlight.getPaymentPerson()+"\',\'"+
                    currentFlight.getTicketPerson()+"\',\'"+currentFlight.getBookingNumber()
                    +"\');";
            stmt.executeUpdate(sqlStatement);
            
            TicketPrinter TP = new TicketPrinter(currentFlight,DBC);
            if(TP.printTicket()){
            System.out.println("You have made your reservation, if you entered invalide dates "
                    + "they may have been altered, please check your ticket.");
            }
            System.out.println("If you would like to purchase another ticket please enter y");
            con=reader.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
           
        }

        System.out.println("Opened database successfully");

    }
    
}