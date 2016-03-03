# DeliverySystem 
This application is developed for the delivery management of the monthly magazine.

The different management entities are called circles.
As of date 3/3/2016, the circle included in the application are as follows

- National
- Zone
- Sector
- Unit

## National
A user logging in with the National id would be able to view all the zones under it. The table would contain the name of the zones and the number of subscribers they contain and also the number of magazines that were delivered by the zone for any particular month.
Also they would be able to drill down to Sectors, Units and subscriber details.

## Zone
A user logging in with the Zone id would be able to view all the sectors under it. The table would contain the name of the sectors and the number of subcribers they contain and also the number of magazines that were delivered by the sector for any particular month.
Also they would be able to drill down to Units and subscriber details.

## Sector
A user logging in with the Sector id would be able to view all the units under it. The table would contain the name of the units and the number of subscribers they contain and also the number of magazines that were delivered by the unit for any particular month.
Also they would be able to drill down to subscriber details.

## Unit
A user logging in with the Unit id would be able to view all the subscribers under it. They would contain the details whether the magazine for a particular unit has been delivered or not.
The edit functionality is available here only.

Currently this uses MySql database
