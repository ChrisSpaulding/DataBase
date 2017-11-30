package javafrontend;

//Format taken from David A. 

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
            InfoGetter IG = new InfoGetter();
            boolean goodData=false;
            while(!goodData){
               goodData= IG.getData();
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");

    }
}