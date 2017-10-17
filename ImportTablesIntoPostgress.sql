CREATE TABLE StateTable (
    State_Abrv varchar(2) PRIMARY KEY,
    FIPS int,
    State_Name varchar(50)
	
);

CREATE TABLE City (
    City_Name varchar(255),
    City_Abrv varchar(5) Primary key, 
    City_Market_ID int,
	State_Abrv varchar(2),
	State_Abrv varchar(2) FOREIGN KEY REFERENCES StateTable(State_Abrv) 
);

CREATE Table Airport (
	Airport_ID varchar(15) PRIMARY KEY,
	Airport_SEQ_ID varchar(15),
	City_Market_ID varchar(15) Foreign key references City_Abrv)
	);
	
CREATE Table Quarters (
	MonthNumber int PRIMARY key,
	Quarter int
);

CREATE Table Carrier(
	Airline_Id varchar(15) PRIMARY key,
	CarrierAbv varchar(5),
	Carrier_Entity varchar(10),
	Carrier_Name varchar(255),
	Unique_carrier varchar(5),
	Unique_carrier_Entity varchar(15),
	Unique_carrier_name varchar(255),
	Carrier_group int,
	Carrier_Group_New int,
	Region varchar(255),
	Start_Date_Source DATE,
	Thru_Date_Source DATE	
);

CREATE Table flight(
	Airline_Id Foreign key references Carrier(Airline_Id),
	Flight_Year int, 
	Flight_Month int, 
	Ditance_Group int, 
	Flight_Class varchar(5),
	Dest_Airport_ID varchar(15) FOREIGN key references Airport(Airport_ID),
	Origin_Airport_Id varchar(15) FOREIGN key references Airport(Airport_ID),
	Departures_Scheduled int, 
	Departures_Performed int, 
	Payload int, 
	Seats int, 
	Passengers int, 
	Freight int, 
	Distance int, 
	Ramp_to_Ramp int, 
	Air_Time int, 
	Distance_Group int
);

