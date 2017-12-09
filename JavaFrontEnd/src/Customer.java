

import java.sql.*;

/**
 *
 * @author chris_000
 */
public class Customer {

    Flight currentFlight;
    DBCreds DBC;

    Customer(Flight x, DBCreds dbc) {
        currentFlight = x;
        this.DBC = dbc;
    }

    public Flight addCustomer() {
        Connection c = null;
        try {
            String sqlStatement = "";
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DBC.getDataBaseLocation(), DBC.getUser(), DBC.getPW());
            c.createStatement();

            PersonInfoGetter pig = new PersonInfoGetter();

            pig.getAllData();
            Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "select Person_key from Person where first_name like \'" + pig.getFirstname() + "\' and last_name like \'" + pig.getLastname() + "\';";
            ResultSet re = stmt.executeQuery(sqlQuery);
            int personKey;
            if (re.first()) {
                personKey = re.getInt("Person_key");
            } else {
                personKey = -1;
            }
            if (personKey == -1) {
                sqlStatement = "INSERT INTO Person (first_name, last_name) "
                        + "VALUES ( \'" + pig.getFirstname() + "\' ,\'" + pig.getLastname() + "\');";
                stmt.executeUpdate(sqlStatement);
                re = stmt.executeQuery(sqlQuery);
                if (re.first()) {
                    personKey = re.getInt("Person_key");
                } else {
                    personKey = -1;
                }
            }

            sqlQuery = "select CityName from City where CityName like \'" + pig.getCity() + "\' and StateName like \'" + pig.getState() + "\' and CountryName like \'" + pig.getCountry() + "\';";
            re = stmt.executeQuery(sqlQuery);
            if (re.first()) {

            } else {
                sqlStatement = "insert into City (CityName, StateName,CountryName)"
                        + " values (\'" + pig.getCity() + "\',\'" + pig.getState() + "\',\'" + pig.getCountry() + "\');";
                stmt.executeUpdate(sqlStatement);

            }

            String sqlmailingAddress = "INSERT INTO mailingaddress (Person,street, city_Name, stateName, zipcode, countryName) "
                    + "VALUES (\'" + personKey + "\', \'" + pig.getAddress() + "\',\'" + pig.getCity() + "\' , "
                    + "\'" + pig.getState() + "\',\'" + pig.getZipcode() + "\',\'" + pig.getCountry() + "\');";

            stmt.executeUpdate(sqlmailingAddress);
            currentFlight.setTicketPerson(personKey);
            return currentFlight;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }

        System.out.println("Opened database successfully");
        return currentFlight;
    }

    public int createPerson() throws ClassNotFoundException, SQLException {
        String sqlStatement = "";
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(DBC.getDataBaseLocation(), DBC.getUser(), DBC.getPW());
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        PersonInfoGetter pig = new PersonInfoGetter();
        pig.getName();
        sqlStatement = "INSERT INTO Person (first_name, last_name) "
                + "VALUES ( \'" + pig.getFirstname() + "\' ,\'" + pig.getLastname() + "\');";
        stmt.executeUpdate(sqlStatement);
        return this.getPersonKey(pig.getFirstname(), pig.getLastname());
        

    }

    public boolean existingCustomer(int ID) throws Exception {
        String sqlStatement = "";

        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(DBC.getDataBaseLocation(), DBC.getUser(), DBC.getPW());
        c.createStatement();
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        sqlStatement = "select Person_key from Person where Person_key=" + ID + ";";
        ResultSet re = stmt.executeQuery(sqlStatement);
        return re.first();
    }

    public int getPersonKey(String firstName, String lastName) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(DBC.getDataBaseLocation(), DBC.getUser(), DBC.getPW());
        c.createStatement();
        Statement stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String sqlQuery = "select Person_key from Person where first_name like \'" + firstName + "\' and last_name like \'" + lastName + "\';";
        ResultSet re = stmt.executeQuery(sqlQuery);
        if (re.first()) {
            System.out.println("Should Get person key: ");
            int x=re.getInt("Person_key");
            System.out.print(x);
            return x; 
        }
        return -1;
    }
}
