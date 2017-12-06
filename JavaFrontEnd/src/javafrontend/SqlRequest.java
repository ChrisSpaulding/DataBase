
package javafrontend;
import java.sql.*;

/**
 *
 * @author chris_000
 */
public class SqlRequest {
    
    SqlRequest(){
        
    }
    public void addCustomer(){
    Connection c = null;
        try {
            String  sqlStatement="";
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cat",
                            "bob", "123");
            c.createStatement();
            
            PersonInfoGetter pig= new PersonInfoGetter();
            
            pig.getAllData();
            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            String sqlQuery= "select Person_key from Person where first_name like \'"+pig.getFirstname()+"\' and last_name like \'"+pig.getLastname()+"\';";
            ResultSet re = stmt.executeQuery(sqlQuery);
            int personKey;
            if(re.first()){
                personKey=re.getInt("Person_key");
            }
            else{
                personKey=-1;
            }
            if(personKey==-1){
                System.out.println("update table");
                sqlStatement="INSERT INTO Person (first_name, last_name) "
                        + "VALUES ( \'" +pig.getFirstname()+ "\' ,\'"+pig.getLastname()+"\');";
                stmt.executeUpdate(sqlStatement);
                re=stmt.executeQuery(sqlQuery);
                if(re.first()){
                    personKey=re.getInt("Person_key");
                }
                else{
                    personKey=-1;
                }
            }
            
            System.out.println("\n" +personKey);
                
            
            sqlQuery= "select CityName from City where CityName like \'"+pig.getCity()+"\' and StateName like \'"+pig.getState()+"\' and CountryName like \'"+ pig.getCountry()+"\';";
            re = stmt.executeQuery(sqlQuery);
            if(re.first()){
                
            }
            else{
                sqlStatement= "insert into City (CityName, StateName,CountryName)"
                        + " values (\'"+pig.getCity()+"\',\'"+pig.getState()+"\',\'"+pig.getCountry()+"\');";
                        stmt.executeUpdate(sqlStatement);
                
            }
            
            
            String sqlmailingAddress = "INSERT INTO mailingaddress (Person,street, city_Name, stateName, zipcode, countryName) "
                        + "VALUES (\'"+personKey+ "\', \'" +pig.getAddress()+"\',\'"+pig.getCity()+"\' , "
                        + "\'"+pig.getState()+"\',\'"+pig.getZipcode()+"\',\'"+pig.getCountry()+"\');";
            
                
            stmt.executeUpdate(sqlmailingAddress);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            
        }

        System.out.println("Opened database successfully");

    }
    
    public String createPerson(){
        PersonInfoGetter pig = new PersonInfoGetter();
        pig.getName();
        return pig.firstname +" "+pig.lastname;
    }

}
