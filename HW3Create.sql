
CREATE TABLE StateTable (
    State_Abrv varchar(2), 
    FIPS int,
    State_Name varchar(50),
	PRIMARY KEY (State_Abrv)
);

--fk = state_abrv
CREATE TABLE City (
    City_Name varchar(255),
    City_Abrv varchar(5) , 
    City_Market_ID int,
	WAC int,
	State_Abrv varchar(2),
	Primary key(City_Abrv)
);

-- fk ==abriviation (should be city_abvr)
CREATE Table Airport (
	Airport_ID varchar(15),
	Airport_SEQ_ID varchar(15),
	Abriviation varchar(15),
	PRIMARY KEY (Airport_ID)
	);
	
	
CREATE Table Quarters (
	MonthNumber int,
	Quarter int,
	PRIMARY key (MonthNumber)
);


CREATE Table Carrier(
	Airline_Id varchar(15),
	CarrierAbv varchar(5),
	Carrier_Entity varchar(10),
	Carrier_Name varchar(255),
	Unique_carrier varchar(15),
	Unique_carrier_Entity varchar(15),
	Unique_carrier_name varchar(255),
	Carrier_group int,
	Carrier_Group_New int,
	Region varchar(255),
	Start_Date_Source DATE,
	Thru_Date_Source DATE,
	PRIMARY key (Airline_Id)
);

-- fk= Airline_id; flight_month; Origin_Airport_Id; Dest_Airport_ID;  
CREATE Table flight(
	Airline_Id varchar(15),
	Flight_Year int, 
	Flight_Month int, 
	Ditance_Group int, 
	Flight_Class varchar(5),
	Dest_Airport_ID varchar(15),
	Origin_Airport_Id varchar(15),
	Departures_Scheduled int, 
	Departures_Performed int, 
	Payload int, 
	Seats int, 
	Passengers int, 
	Freight int, 
	Mail int,
	Distance int, 
	Ramp_to_Ramp int, 
	Air_Time int, 
	Distance_Group int,
	PRIMARY KEY (Airline_Id, Dest_Airport_ID, Origin_Airport_Id, Flight_Year, Flight_Month, Flight_Class)
);


#issue 
ALTER TABLE airport
ADD FOREIGN KEY (Abriviation) REFERENCES City(City_Abrv);

ALTER TABLE city
ADD FOREIGN KEY (State_Abrv) REFERENCES StateTable(State_Abrv);

ALTER TABLE city
ADD FOREIGN KEY (State_Abrv) REFERENCES StateTable(State_Abrv);


ALTER TABLE flight
ADD FOREIGN KEY (Airline_Id) REFERENCES Carrier(Airline_Id);


ALTER TABLE flight
ADD FOREIGN KEY (Flight_Month) REFERENCES quarters(monthnumber);

ALTER TABLE flight
ADD FOREIGN KEY (Origin_Airport_Id) REFERENCES Airport(Airport_ID);
 
 
ALTER TABLE flight
ADD FOREIGN KEY (Dest_Airport_ID) REFERENCES Airport(Airport_ID);
 