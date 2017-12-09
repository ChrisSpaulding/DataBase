
package javafrontend;

import java.util.Scanner;

/**
 *
 * @author chris
 */
public class DBCreds {
    Scanner in;
    private String DataBaseLocation;
    private String DataBaseUser;
    private String DataBasePW;
    
    DBCreds(){
        in= new Scanner(System.in);
    }
    
    public void setPw(String pw){
        DataBasePW=pw;
    }
    String getPW(){
        return DataBasePW;
    }
    public void setDataBaseUser(String user){
        DataBaseUser= user;
    }
    String getUser(){
        return DataBaseUser;
    }
    public void setDataBaseLocation(String path){
        this.DataBaseLocation=path;
    }
    String getDataBaseLocation(){
        return DataBaseLocation;
    }
    
    public boolean setUpConnection(){
        System.out.println("\nEnter User name:");
        this.DataBaseUser=in.nextLine();
        System.out.println("\nEnter password:");
        this.DataBasePW=in.nextLine();
        System.out.println("Please enter the db path: \n example:"
                + "jdbc:postgresql://localhost:5432/cat");
        String test =in.nextLine();
        System.out.println("I saw: "+test);
        this.DataBaseLocation=test;
        return true;
    }
    public boolean useDefaultConnection(){
        this.DataBaseLocation="jdbc:postgresql://localhost:5432/cat";
        this.DataBaseUser="bob";
        this.DataBasePW="123";
        return true;
    }
    
}
