
package javafrontend;

/**
 *
 * @author chris_000
 */
public class Airlines {

    public String pickAirline(String city){
        
        if( city.equals("Toronto")|| city.equals("Montreal")){
                return "AirCan";
        }
        else if(city.equals("New York")|| city.equals("Chicago")){
                return "United";
        }
        else {
            return "BA";
        }
    
    }
}
