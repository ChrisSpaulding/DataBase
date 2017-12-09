
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris_000
 */
public class TicketPrinter {

    Flight ticket;
    DBCreds DBC;

    TicketPrinter(Flight nextFlight,DBCreds DBC) {
        ticket = nextFlight;
        this.DBC=DBC;
    }

    public boolean printTicket() {

        try {
            FileWriter FW = new FileWriter("HW6.flight", true);
            String check=formatTicket();
            FW.append("\n");
            FW.append(check);
            FW.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(TicketPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private String formatTicket() throws IOException {
        try {
            String text = "";
            String PassengerName = lookUpPerson(ticket.getTicketPerson());
            String PurchaserName= lookUpPerson(ticket.getPaymentPerson());
            String origAirport=getOrigAirport(ticket.flightNumber);
            String destAirport= getDestAirport(ticket.flightNumber);

            String flightDepartureTime = getFlightDepartureTime(ticket.getFlightNumber());
            String flightArivalTime = getFlightArivalTime(ticket.getFlightNumber());
            text = "Passenger Name= " + PassengerName +"\nPaid for by: "+PurchaserName
                    + "\nAirline: " + ticket.getAirlingName()
                    + "\n Departure Date and Time: " + ticket.getSqlDate() + " " + flightDepartureTime
                    + "\n Arival Time:" + flightArivalTime+"\n Departing Airport: "+ origAirport+"\n "
                    + "Destination Airport: "+ destAirport;

            return text;
        } catch (Exception ex) {
            FileWriter er = new FileWriter("HW6.error");
            er.append("error with printing ticket");
        }
        return "Failed To Print";
    }

    private String lookUpPerson(int personKey) throws Exception {
        String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
        
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement = "select first_name, last_name from Person where Person_key=" + personKey;
        ResultSet re = stmt.executeQuery(sqlStatement);
        if (re.first()) {
            return re.getString("first_name") + " " + re.getString("last_name");
        }
        return "Could not find Person name";
    }

    private String getFlightDepartureTime(int flightNumber) throws Exception {
        String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
        
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement = "Select Departuretime from FlightNumber where FlightNumber ="
                + flightNumber;
        ResultSet re = stmt.executeQuery(sqlStatement);

        if (re.first()) {
            return re.getString(1);
        }
        return "no time found";
    }

    private String getFlightArivalTime(int flightNumber) throws Exception {
        String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
        
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement = "Select ArivalTime from FlightNumber where FlightNumber ="
                + flightNumber;
        ResultSet re = stmt.executeQuery(sqlStatement);

        if (re.first()) {
            return re.getString(1);
        }
        return "no time found";
    }

    private String getOrigAirport(int flightNumber) throws Exception{
        String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
        
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement = "Select OriginCityName from FlightNumber where FlightNumber ="
                + flightNumber;
        ResultSet re = stmt.executeQuery(sqlStatement);

        if (re.first()) {
            return re.getString(1);
        }
        return "no City found";
    }

    private String getDestAirport(int flightNumber) throws Exception{
    String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
            Connection c= DriverManager.getConnection(DBC.getDataBaseLocation(),DBC.getUser(),DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        sqlStatement = "Select DestCityName from FlightNumber where FlightNumber ="
                + flightNumber;
        ResultSet re = stmt.executeQuery(sqlStatement);

        if (re.first()) {
            return re.getString(1);
        }
        return "no time found";}

}
