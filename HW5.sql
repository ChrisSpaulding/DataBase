

Create Table Person (
	Person_key serial primary key, 
	first_name varchar(52),
	last_name varchar(52)
	);
	
Create Table Emails (
	Person int,
	email varchar(80),
	FOREIGN KEY (person) REFERENCES Person(Person_key),
	PRIMARY KEY(Person, email)
	);


Create Table Country(
	CountryName varchar(128),
	Country_Code int,
	PRIMARY KEY (CountryName)
	);
	
Create Table StateTable(
	StateName varchar(128),
	CountryName varchar(128),
	FOREIGN KEY (CountryName) REFERENCES Country(CountryName),
	PRIMARY KEY (StateName, CountryName)
	);
	
Create Table City(
	CityCode varchar(10),
	City_Name varchar(128),
	CountryName varchar(128),
	StateName varchar(128),
	FOREIGN KEY (CountryName) REFERENCES Country(CountryName),
	PRIMARY KEY (CityCode)
	);
	
	
Create Table MailingAddress(
	Person int,
	CityCode varchar(10),
	CountryName varchar(128),
	Street varchar(128),
	StateName varchar(128),
	ZipCode varchar(20),
	FOREIGN KEY (Person) REFERENCES Person(Person_key),
	FOREIGN KEY ( CountryName) REFERENCES Country(CountryName),
	FOREIGN KEY ( StateName, CountryName) REFERENCES StateTable(StateName, CountryName),
	PRIMARY KEY( Person, Street, ZipCode) 
);


Create Table Airline(
	Airline_Name varchar(255), 
	Country varchar(128),
	PRIMARY KEY (Airline_Name)
	);

	
--need to enforce countryCode refriential integrity. 	
Create Table Phone (
	Person int, 
	countryCode int,
	areaCode int, 
	localNumber int,
	FOREIGN KEY (Person) REFERENCES Person(Person_key),
	PRIMARY KEY (Person, countryCode, areaCode, localNumber)
	);


	
Create Table FlightNumber(
	FlightNumber int, 
	OriginCityCode varchar(10),
	DestinationCityCode varchar(10),
	FOREIGN KEY (OriginCityCode) REFERENCES City(CityCode),
	FOREIGN KEY (DestinationCityCode) REFERENCES City(CityCode),
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
	PRIMARY KEY (Airline_Name, DepartureDate, DepartureTime, TicketPerson, Unique_Flight_number, BookingNumber)
	);
	
	