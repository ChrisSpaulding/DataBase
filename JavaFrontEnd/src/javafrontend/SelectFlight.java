
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

/**
 *
 * @author chris_000
 */
public class SelectFlight {
    
    Scanner in;
    
    SelectFlight(){
        in= new Scanner(System.in);
    }
    public boolean selectFlight(){
    
    Connection c = null;
    try {
            String  sqlStatement="";
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cat",
                            "bob", "123");
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
            System.out.println(re.getInt("FlightNumber"));
                    }
            
            
            //booking number = current time as int
            
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
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
    return true;
    }
    
    private boolean checkForString(String in, ArrayList al){
        System.out.print(al);
        for(int i=0; i<al.size();i++){
            if(in.equals(al.get(i).toString()))
            return true;
        }
        return false;
    }
}
