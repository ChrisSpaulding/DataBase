package javafrontend;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author chris_000
 */
public class PersonInfoGetter {
    String error;
    Scanner reader;
    String firstname;
    String lastname;
    String address;
    String city;
    String state;
    String zipcode;
    String phone;
    String email;
    String country;

    PersonInfoGetter() {
        error = "";
        reader = new Scanner(System.in);
        firstname="";
        lastname="";
        address="";
        city ="";
        state= "";
        zipcode="";
        phone="";
        email="";
        country="";
    
    }
    
    
    public boolean getName(){
        this.firstname= this.askFirstName();
        this.lastname= this.askLastName();
        return this.ErrorFinder();
    }
    
    public boolean getAllData(){
       
        try{
        FileWriter pw = new FileWriter("HW6.error", true); 
        askCust();
        boolean errors= ErrorFinder();
        if (errors){
            pw.append(error);
            pw.close();
            return false;
        }
        pw.close();
        return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
        finally
        {
        
        }
    }

    private void askCust() {
        firstname = askFirstName();
        lastname = askLastName();
        address = askStreetAddress();
        city = askCityAddress();
        state = askState();
        zipcode = askZipCode();
        phone = askPhone();
        email = askEmail();
        country= askCountry();

        System.out.println(firstname + lastname + address + city + state + zipcode + phone + email);
    }

    private String askCountry(){
        System.out.println( "Please enter your country");
        return reader.nextLine();
    }
    private String askEmail() {
        System.out.println(" Please enter your email address");
        return reader.nextLine();
    }

    private String askPhone() {
        System.out.println("Please enter your full 10 digit phone number with only the digits");
        return reader.nextLine();
    }

    private String askZipCode() {
        System.out.println("Please enter your zip code");
        return reader.nextLine();
    }

    private String askState() {
        System.out.println("Please enter your state");
        return reader.nextLine();
    }

    private String askCityAddress() {
        System.out.println("Please enter your city");
        return reader.nextLine();
    }

    private String askStreetAddress() {
        System.out.println("Please enter your street address (do not enter the city, state or zip)");
        return reader.nextLine();
    }

    private String askLastName() {
        System.out.println("Please enter your Last Name");
        return reader.nextLine();
    }

    private String askFirstName() {
        System.out.println("Please enter your First Name:");
        return reader.nextLine();
    }

    private boolean ErrorFinder() {
        boolean errors = false;
        if(!this.lastname.equals("")){
        errors = checkLastName();}
        if(! this.address.equals("")){
        errors = checkAddress();}
        if(!this.city.equals("")){
        errors = checkCity();}
        if(!this.state.equals("")){
        errors = checkState();}
        if(!this.zipcode.equals("")){
        errors = checkZip();}
        if(!this.phone.equals("")){
        errors = checkPhone();}
        if(!this.email.equals("")){
        errors = checkEmail();}
        return errors;
    }

    private boolean checkLastName() {
        if (this.lastname.length() > 52) {
            this.error = error + "\nLast name is larger than 128 letters";
            return true;
        }
        return false;
    }

    private boolean checkState() {
        if (this.state.length() > 128) {
            this.error = error + "\nState name is larger than 128 letters";
            return true;
        }
        return false;
    }

    private boolean checkCity() {
        if (this.city.length() > 128) {
            this.error = error + "\nCity name is larger than 128 letters";
            return true;
        }
        return false;
    }

    private boolean checkZip() {
        for (int i = 0; i < zipcode.length(); i++) {
            char num = zipcode.charAt(i);
            if (!Character.isDigit(num)) {
                error = error + "\n zipcode contains non didget as pos " + i;
                return true;
            }
        }
        if (zipcode.length()!=5){
            error = error + "\n zipcade is not 5 didgets long";
            return true;
        }
        return false;
    }

    private boolean checkPhone() {
        for (int i = 0; i < phone.length(); i++) {
            char num = phone.charAt(i);
            if (!Character.isDigit(num)) {
                error = error + "\n phone number contains non didget as pos " + i;
                return true;
            }
        }
        return false;
    }

    private boolean checkEmail() {
        if (email != null) {
            if (email.indexOf('@') == -1) {
                error = error + "\n email does not contain an @ symbol.";
                return true;
            } else if (email.indexOf('.') == -1) {
                error = error + "\n email does not contain a period.";
                return true;
            } else if (email.indexOf('.') < email.indexOf('@')) {
                error = error + "\ndoes not have a period after the @ symbol.";
                return true;
            } else {
                return false;
            }
        } else {
            error = error + "\n No email entered";
            return true;
        }
    }

    private boolean checkAddress() {
        if (address.length() > 128) {
            error = error + "\n Address is greater than 128";
            return true;
        }
        return false;
    }
    
    public String getCountry(){
        return this.country;
    }
    public String getAddress(){
        return this.address;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public String getLastname(){
        return lastname;
    }
    
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getZipcode(){
        return zipcode;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    
}

