select * from flight where Dest_Airport_ID like '13806';
 

select * from flight where 

select  Dest_Airport_ID, flight.Dest_Airport_ID.Abriviation, sum(Passengers)
from flight
Left Join Airport on flight.Dest_Airport_ID = Airport.Airport_ID
Group by Dest_Airport_ID;
 
 
# selects top 3 flights from CO  
Select flight.Origin_Airport_ID, Airport.Abriviation  
from flight
Inner Join Airport on flight.Origin_Airport_ID = Airport.Airport_ID
where flight.Origin_Airport_ID IN (select Airport.Airport_ID 
from Airport
Inner Join City on City.City_Abrv=Airport.Abriviation 
where City.State_Abrv like 'CO') 
limit 3;

#find all airport codes in CO works

select Airport.Airport_ID 
from Airport
Inner Join City on City.City_Abrv=Airport.Abriviation 
where City.State_Abrv like 'CO';


# display correct collums works

 select City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name, flight.passengers
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv

limit 3;

# all constraints work 
 select City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name, flight.passengers
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv
where flight.Origin_Airport_ID IN (select Airport.Airport_ID 
	from Airport
		Inner Join City on City.City_Abrv=Airport.Abriviation 
		where City.State_Abrv like 'CO') 
		
		limit 3;
	
	
		
# displays all departures from Colorado by airline with sum of passengers		 
 select City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name, sum (flight.passengers)
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv
where flight.Origin_Airport_ID IN (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
Group BY City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name;


#freight instead of passengers
		 
 select City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name, sum (flight.Payload)
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv
where flight.Origin_Airport_ID IN (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
Group BY City.City_Name, StateTable.State_Name, Airport.Abriviation, Carrier.Carrier_Name;


#sub querey 
Select city.City_Name 
from flight
	join Airport
		on flight.Dest_Airport_ID = Airport.Airport_ID
	join city 
		on Airport.abriviation=City_Abrv
where flight.Dest_Airport_ID in (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO') 
limit 5;
				
				
#3 to CO not from in CO				

		 
 select City.City_Name AS Origin_City, StateTable.State_Name, Carrier.Carrier_Name, Dest.city_name As Destination, sum(flight.passengers)
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv
	Join (
	Select city.City_Name, flight.Dest_Airport_ID
	from flight
	join Airport
		on flight.Dest_Airport_ID = Airport.Airport_ID
	join city 
		on Airport.abriviation=City_Abrv
	where flight.Dest_Airport_ID in (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO') 

	) AS Dest on Dest.Dest_Airport_ID = flight.Dest_Airport_ID
where flight.Origin_Airport_ID not IN (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
	 and flight.Dest_Airport_ID in (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
Group BY Origin_City, StateTable.State_Name, Carrier.Carrier_Name, Dest.city_name
;

				
#4 limmeted to flight distance 				

		 
 select City.City_Name AS Origin_City, StateTable.State_Name, Carrier.Carrier_Name
 from flight
	Join Airport 
		on flight.Origin_Airport_ID = Airport.Airport_ID
	Join City 
		on City.City_Abrv= Airport.Abriviation
	Join Carrier 
		on flight.Airline_ID = Carrier.Airline_ID
	Join StateTable 
		on City.State_Abrv like StateTable.State_Abrv
	Join (
	Select city.City_Name, flight.Dest_Airport_ID
	from flight
	join Airport
		on flight.Dest_Airport_ID = Airport.Airport_ID
	join city 
		on Airport.abriviation=City_Abrv
	where flight.Dest_Airport_ID in (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.City_Name like 'Denver') 

	) AS Dest on Dest.Dest_Airport_ID = flight.Dest_Airport_ID
where flight.Origin_Airport_ID not IN (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
	 and flight.Dest_Airport_ID in (select Airport.Airport_ID 
			from Airport
			Inner Join City on City.City_Abrv=Airport.Abriviation 
			where City.State_Abrv like 'CO')
	and flight.Ramp_to_Ramp BETWEEN 500 and 1200
Group BY Origin_City, StateTable.State_Name, Carrier.Carrier_Name
;
