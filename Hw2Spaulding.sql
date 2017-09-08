--SQL File for HW2 Chris Spaulding
CREATE TABLE Countries (
name VARCHAR(20) NOT NULL,
latitude INT,
longitude INT,
area INT,
population INT,
gdp INT,
gdpYear INT,
PRIMARY KEY (name)
);

--Germany
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES('Germany', 5100,9000, 357022,80594017,3979,2016);

--Netherlands
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Netherlands', 5230,545, 41543,17084719,871, 2016);

--Belgium
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Belgium',5050,400,30528,11491346,
507,2016);

--Luxemburg
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Luxemburg',4945, 610,2586, 594130,59,2016);

--Poland
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Poland',5200,2000,312685, 38476269,1052,2016);

--Czechia
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Czechia', 4945,1530,78867,10674723,351,2016 );

--Austria
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Austria',4720,1320,83871,8754413,416,2016);

--France
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('France', 4600,200, 643801, 67106161, 2699, 2016);

--Switzerland
Insert into Countries(name,latitude,longitude,area, population, gdp, gdpyear) VALUES ('Switzerland', 4700,700,41277,8236303,493,2016);


--create borders table

CREATE TABLE Borders (country1 VARCHAR(20), country2 VARCHAR(20) , PRIMARY KEY (country1,country2),
FOREIGN KEY (country1) REFERENCES Countries(name),
FOREIGN KEY (country2) REFERENCES Countries(name)
);


--Germany Borders
Insert into Borders(country1,country2) VALUES('Germany','Czechia');

Insert into Borders(country1,country2) VALUES('Germany','Austria');


Insert into Borders(country1,country2) VALUES('Germany','Switzerland');

Insert into Borders(country1,country2) VALUES('Germany','France');

Insert into Borders(country1,country2) VALUES('Germany','Luxemburg');

Insert into Borders(country1,country2) VALUES('Germany','Belgium');
Insert into Borders(country1,country2) VALUES('Germany','Netherlands');

--Netherlands


Insert into Borders(country1,country2) VALUES('Netherlands','Germany');

Insert into Borders(country1,country2) VALUES('Netherlands','Belgium');

--BELGIUM
Insert into Borders(country1,country2) VALUES('Belgium','France');
Insert into Borders(country1,country2) VALUES('Belgium','Luxemburg');
Insert into Borders(country1,country2) VALUES('Belgium','Netherlands');

--Luxemburg

Insert into Borders(country1,country2) VALUES('Luxemburg','Belgium');
Insert into Borders(country1,country2) VALUES('Luxemburg','France');
Insert into Borders(country1,country2) VALUES('Luxemburg','Germany');

--Poland

Insert into Borders(country1,country2) VALUES('Poland','Germany');
Insert into Borders(country1,country2) VALUES('Poland','Czechia');

--Czechia
INSERT into Borders (country1, country2)
VALUES('Czechia','Poland');
INSERT into Borders (country1, country2)
VALUES('Czechia','Austria');
INSERT into Borders (country1, country2)
VALUES('Czechia','Germany');

--Austria
INSERT into Borders (country1, country2)
VALUES('Austria','Czechia');
INSERT into Borders (country1, country2)
VALUES('Austria','Switzerland');
INSERT into Borders (country1, country2)
VALUES('Austria','Germany');

--France
INSERT into Borders (country1, country2)
VALUES('France','Luxemburg');
INSERT into Borders (country1, country2)
VALUES('France','Germany');
INSERT into Borders (country1, country2)
VALUES('France','Switzerland');
INSERT into Borders (country1, country2)
VALUES('France','Belgium');

--Switzerland

INSERT into Borders (country1, country2)
VALUES('Switzerland','Austria');
INSERT into Borders (country1, country2)
VALUES('Switzerland','France');
INSERT into Borders (country1, country2)
VALUES('Switzerland','Germany');



