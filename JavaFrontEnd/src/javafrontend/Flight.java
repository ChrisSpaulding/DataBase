
package javafrontend;

/**
 *
 * @author chris_000
 */
public class Flight {

    
    int flightNumber;
    String AirlineName;
    int DepartureYear;
    int DepartureMonth;
    int DepartureDay;
    int PaymentPerson;
    int TicketPerson; 
    int bookingNumber;

    Flight(){
        this.flightNumber=-1;
        this.AirlineName="BA";
        this.DepartureDay=-1;
        this.DepartureMonth=-1;
        this.DepartureYear=-1;
        this.PaymentPerson=-1;
        this.TicketPerson=-1;
        this.bookingNumber=-1;
    }

public void setflightNumber(int fn){
this.flightNumber=fn;
}
public int getFlightNumber(){
    return flightNumber;
}

public void setAirlineName(String aN){
    this.AirlineName = aN;
}

public String getAirlingName(){
    return this.AirlineName;
}
public void setDepartureDay(int x){
    if(x<=31 && x>0){
        this.DepartureDay=x;
    }
    else {
        this.DepartureDay=15;
    }
}
public int getDepartureDay(){
    return this.DepartureDay;
}

public void setDepartureMonth(int x){
    if(x<13 && x>0){
        this.DepartureMonth=x;}
    else{
        this.DepartureMonth =12;
    }
}
public int getDepartureMonth(){
    return this.DepartureMonth;
}

public void setDepartureYear(int x){
    if(x>=2017 && x <2020){
        this.DepartureYear=x;}
    else {
        this.DepartureYear=2018;
    }
}
public int getDepartureYear(){
    return this.DepartureDay;
}
public void setTicketPerson(int person){
    if(person>0){
        this.TicketPerson=person;
    }
    else{
        TicketPerson =-1;
    }
}

public int getTicketPerson(){
    return this.TicketPerson;
}
public void setPaymentPerson(int person){
    if(person>0){
        this.PaymentPerson=person;
    }
    else{
        PaymentPerson =-1;
    }
}

public int getPaymentPerson(){
    return this.PaymentPerson;
}

public void setBookingNumber(int x){
    this.bookingNumber=x;
}

public long getBookingNumber(){
    return this.bookingNumber;
}
public String getSqlDate(){
    return this.DepartureMonth+"-"+this.DepartureDay+"-"+this.DepartureYear;
}

}
