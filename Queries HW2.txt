--Chirs Spaulding 
-- Queries

--4c use Query to display all countries that border Germany. 

Select country2  from Borders where 'Germany'=country1;

--4d 
Select name from Countries where population>35000000;

--4e 
Select country2 from Borders 
Join countries on 
country2 = Countries.name
where
country1= 'Germany' and 
countries.population>35000000;
