

Create Table Person (
	Person_key serial primary key, 
	first_name varchar(128),
	last_name varchar(128)
	);
	
Create Table Emails (
	Person int,
	email varchar(80),
	FOREIGN KEY (person) REFERENCES Person(Person_key),
	PRIMARY KEY(Person, email)
	);

	
Create Table City(
	CityCode varchar(10),
	CityName varchar(128),
	StateName varchar(128),
	CountryName varchar(128),
	PRIMARY KEY (CityName,  StateName,CountryName)
	);
	
	
Create Table MailingAddress(
	Person int,
	City_Name varchar(128),
	CountryName varchar(128),
	Street varchar(128),
	StateName varchar(128),
	ZipCode varchar(20),
	FOREIGN KEY (Person) REFERENCES Person(Person_key),
	FOREIGN KEY (City_Name, StateName, CountryName) REFERENCES City(CityName, StateName, CountryName),
	PRIMARY KEY( Person, Street, ZipCode) 
);


Create Table Airline(
	Airline_Name varchar(255), 
	Country varchar(128),
	PRIMARY KEY (Airline_Name)
	);

	
Create Table Phone (
	Person int, 
	phoneNumber int,
	FOREIGN KEY (Person) REFERENCES Person(Person_key),
	PRIMARY KEY (Person, phoneNummber)
	);

	
Create Table FlightNumber(
	FlightNumber int, 
	OriginCityName varchar(128),
	OriginCityState varchar(128),
	OriginCityCountry varchar(128),
	DestCityName varchar(128),
	DestCityState varchar(128),
	DestCityCountry varchar(128),
	FOREIGN KEY (OriginCityName, OriginCityState, OriginCityCountry) REFERENCES City(CityName,StateName,CountryName),
	FOREIGN KEY (DestCityName, DestCityState, DestCityCountry) REFERENCES City(CityName,StateName,CountryName),
	PRIMARY KEY (FlightNumber)
	);

	
Create Table Flights (
	Unique_Flight_number int,
	Airline_Name varchar(68),
	DepartureDate Date,
	DepartureTime Time with Time Zone,
	ArivalDate Date, 
	ArivalTime Time with Time Zone,
	PaymentPerson int,
	TicketPerson int,
	BookingNumber int,
	FOREIGN key (PaymentPerson) REFERENCES Person(Person_key),
	FOREIGN key (TicketPerson) REFERENCES Person(Person_key),
	FOREIGN KEY (Airline_Name) REFERENCES Airline(Airline_Name),
	FOREIGN KEY (Unique_Flight_number) REFERENCES FlightNumber(FlightNumber),
	PRIMARY KEY (Airline_Name, DepartureDate, DepartureTime, TicketPerson, Unique_Flight_number, BookingNumber)
	);
	
	
\Copy Airline From 'C:\Users\chris_000\Documents\School\Database\Cat\Airline.csv' CSV HEADER;


\Copy City from 'C:\Users\chris_000\Documents\School\Database\Cat\City.csv' CSV HEADER;

\Copy Flights from  'C:\Users\chris_000\Documents\School\Database\Cat\Flights.csv' CSV HEADER;

\Copy FlightNumber From 'C:\Users\chris_000\Documents\School\Database\Cat\Flight_Number.csv' CSV HEADER;


\Copy MailingAddress From 'C:\Users\chris_000\Documents\School\Database\Cat\Mailing.csv' CSV HEADER;

\Copy Person From 'C:\Users\chris_000\Documents\School\Database\Cat\Person.csv' CSV HEADER;

