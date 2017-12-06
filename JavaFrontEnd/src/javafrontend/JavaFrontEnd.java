package javafrontend;

//Format taken from David A. 

//ADD SETTER FOR PORTABILITY

import com.sun.org.apache.xpath.internal.SourceTree;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class JavaFrontEnd {
    static Scanner reader = new Scanner (System.in);

    public static void main(String args[]) {
        Connection c = null;
        try {
            
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cat",
                            "bob", "123");
            SqlRequest SQLrequest = new SqlRequest();
            //SQLrequest.addCustomer();
            SelectFlight sf = new SelectFlight();
            sf.selectFlight();
            
            c.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
           
        }

        System.out.println("Opened database successfully");

    }
}