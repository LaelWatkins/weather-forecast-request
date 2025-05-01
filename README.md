Daily Weather Forecast Requester
----
**Daily Forecast summary API**


### Build
1. Clone repo
2. Build with the Maven command, ``mvn clean install``

### Start the service
1. Open an commandline/terminal application.
2. Navigate to this projects root directory/folder
3. Run either Java or Maven commands: ``java -jar target/forecast-0.0.1-SNAPSHOT.jar`` or ``mvn spring-boot:run``

### Test the service
Submit a request using curl or any http tool. (eg. SoapUI)
``curl http://localhost:8080/api/v1/weather/forecast/daily``
