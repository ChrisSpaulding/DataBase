Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]:
Password for user postgres:
psql (9.6.4)
WARNING: Console code page (437) differs from Windows code page (1252)
         8-bit characters might not work correctly. See psql reference
         page "Notes for Windows users" for details.
Type "help" for help.

postgres=#
postgres=# --Chirs Spaulding
postgres=# -- Queries
postgres=#
postgres=# --4c use Query to display all countries that border Germany.
postgres=#
postgres=# Select country2  from Borders where 'Germany'=country1;
  country2
-------------
 Poland
 Czechia
 Austria
 Switzerland
 France
 Luxemburg
 Belgium
 Netherlands
(8 rows)


postgres=#
postgres=# --4d
postgres=# Select name from Countries where population>35000000;
  name
---------
 Germany
 Poland
 France
(3 rows)


postgres=#
postgres=# --4e
postgres=# Select country2 from Borders
postgres-# Join countries on
postgres-# country2 = Countries.name
postgres-# where
postgres-# country1= 'Germany' and
postgres-# countries.population>35000000;
 country2
----------
 Poland
 France
(2 rows)


postgres=#
