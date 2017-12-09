
package javafrontend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
/**
 *
 * @author chris_000
 */
public class SelectFlight {
    
    Scanner in;
    Flight ticket;
    DBCreds DBC;
    
    SelectFlight(Flight currentFlight, DBCreds dbc){
        in= new Scanner(System.in);
        ticket=currentFlight;
        this.DBC=dbc;
    }
    
    public Flight createTicket(){
        selectFlight();
        setValidTicketDate();
        return this.ticket;
    }
    
    
    public void selectFlight(){
    
    Connection c = null;
    try {
            String  sqlStatement="";
            Class.forName("org.postgresql.Driver");
            c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
            c.createStatement();
            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            System.out.println("Please enter the city you would like to fly from");
            System.out.println("Please choose one of the following:");
            
            
            sqlStatement= "select distinct Origincityname from flightnumber;";
            ResultSet re = stmt.executeQuery(sqlStatement);
            
            ArrayList<String> RSet= new ArrayList();
            while(re.next()){
                RSet.add(re.getString("origincityname"));
                System.out.println(re.getString("origincityname"));
            }
            
            String UI="";
            UI=in.nextLine();
            while(!this.checkForString(UI, RSet)){
                System.out.println("please enter a valid city to fly from");
                UI=in.nextLine();
            }
            String origenCity=UI;
            
             Airlines air= new Airlines();
            ticket.setAirlineName(air.pickAirline(origenCity));
            sqlStatement = "select Destcityname from flightnumber where origincityname like \'" +UI+"\'";
            re= stmt.executeQuery(sqlStatement);
            RSet.clear();
            while(re.next()){
                RSet.add(re.getString("destcityname"));
                System.out.println(re.getString("destcityname"));
            }
            int i=0;
            while(!this.checkForString(UI, RSet) && i!=3){
                System.out.println("Please enter a valid city to fly to");
                UI=in.nextLine();
                i++;
            }
            String destCity=UI;
            
            //find flight number 
            sqlStatement ="select flightnumber from flightnumber where OriginCityName like\'"+origenCity+"\' and"
                    + " DestCityName like \'"+destCity+"\';";
            re= stmt.executeQuery(sqlStatement);
            
            if(re.first()){
            ticket.setflightNumber(re.getInt("FlightNumber"));
            }
            else {
            System.out.print("Might not be connected to a database or no flight found");}
            
            //booking number = current time as int
            Date date = new Date();
            Long BN=date.getTime();
            ticket.setBookingNumber(BN.intValue());
    }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        finally {
        if(c!=null){
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SelectFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    
    private boolean checkForString(String in, ArrayList al){
        System.out.print(al);
        for(int i=0; i<al.size();i++){
            if(in.equals(al.get(i).toString()))
            return true;
        }
        return false;
    }
    
    
    private void setValidTicketDate() {
        while(ticket.getDepartureDay()==-1 || ticket.getDepartureMonth()==-1|| ticket.getDepartureYear()==-1){
              System.out.println("Please enter the day you would like to fly as a number");
            int day= in.nextInt();
            System.out.println("Please enter the year you would like to fly");
            int year= in.nextInt();
            System.out.println("Please enter the month you would like to fly as a number");
            int month = in.nextInt();
          
            ticket.setDepartureDay(day);
            ticket.setDepartureMonth(month);
            ticket.setDepartureYear(year);
        }
    }
}
