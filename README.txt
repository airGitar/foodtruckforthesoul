### The SF Food Truck Application ##

SFFoodTrucks is a simple Java command-line application written using the Spring Boot framework.

The user can view a list of food trucks open in San Francisco at the current time. 
Results are organized into pages of size 10 for viewing comfort. The application behaviour is user-input driven.


## SYSTEM REQUIREMENTS ##

Linux/Windows/MacOSX
JDK/JRE 8 or later
Apache Maven 3.2+

Once Java and Maven have been installed, make sure to set the following environment variables:

JAVA_HOME - to the home directory of your JDK installation
M2_HOME - to the home directory of your Maven installation
Add "%M2_HOME%\bin" and %JAVA_HOME%\bin to the PATH variable to run both Maven and Java from the command line.

Commands to verify installation-
>mvn -v
>java -version


## INSTALLATION ##

Unzip the sffoodtruck folder on your local machine.
Navigate to the application home directory "/sffoodtruck" on your command line.

Enter the following command to install the SFFoodTruckApplication-
>mvn package

This will create the sffoodtruck-1.0.0.jar file in the home directory.



## RUN SFFOODTRUCKS...RUN! ##

Enter the following command-
>java -jar "target/sffoodtruck-1.0.0.jar"

NOTE:
1. On user prompt, enter any key to proceed, or Q/q to exit the application.
2. To exit the application at any point, enter Q/q on prompt.
3. Entering any other key will show you a list of 10 open Food Trucks at the current time.


## SAMPLE OUTPUT ##
-----------------------------------------------------------------------------------------------------------------------------------------------
C:\Westeros\sffoodtruck\target>java -jar sffoodtruck-1.0.0.jar

>> Hungry? I can tell you what Food Trucks are open in San Francisco right now! >>
>> Press any key to continue, Q/q to Quit >>


>> Loading..
NAME            |                ADDRESS
Alfaro Truck  |  306 VALENCIA ST
Bay Area Dots, LLC  |  567 BAY ST
Bay Area Dots, LLC  |  900 BEACH ST
Bay Area Mobile Catering, Inc. dba. Taqueria Angelica's  |  1455 MARKET ST
Betty McGee Creole  |  1201 04TH ST
BHUK Burger  |  11 PHELAN AVE
Bob Johnson  |  1709 REVERE AVE
Bob Johnson  |  Assessors Block 0733/Lot010
Bonito Poke  |  409 ILLINOIS ST
CARDONA'S FOOD TRUCK  |  1888 MISSION ST

>> Press [Enter] to view the next page, Q/q to quit >>

NAME            |                ADDRESS
Casey's Pizza, LLC  |  1 POST ST
Chairman SF, LLC  |  34 ELLIS ST
Chairman SF, LLC  |  34 ELLIS ST
Cochinita  |  2601 24TH ST
Creme Brulee To Go  |  801 MARKET ST
Creme Brulee To Go  |  870 MARKET ST
Curry Up Now  |  370 DRUMM ST
Del Popolo, LLC.  |  2772 MARIPOSA ST
DO UC US Mobile Catering  |  2590 MARIN ST
El Gallo Jiro  |  3055 23RD ST

>> Press [Enter] to view the next page, Q/q to quit >>

-----------------------------------------------------------------------------------------------------------------------------------------------

## IMPLEMENTATION ##

This application is written in the Spring Boot framework, and uses the Socrata Open Data API to retrieve data from the SF Government Mobile Food Schedule resource.

More info on the SODA API is available at:
https://github.com/socrata/soda-java
https://dev.socrata.com/

Information on the underlying data source for this application is available here:
https://dev.socrata.com/foundry/data.sfgov.org/bbb8-hzi6

















