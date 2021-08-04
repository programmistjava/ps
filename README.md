# peopleFlow

To start your application in the dev profile, run:

./gradlew

http://localhost:8081/

Use cred: login: user passwd: user and port 8081

 get request  http://localhost:8081/api/employees
 
 outputs the test date



Build  Restful API doing the following:

- An Endpoint to support adding an employee with very basic employee details including (name, contract information, age, you can decide.) With initial state "ADDED" which incidates that the employee isn't active yet.

DONE - SEE  EmployeeResource controller class createEmployee method

Post request  http://localhost:8081/api/employees

- Another endpoint to change the state of a given employee to "In-CHECK" or any of the states defined above in the state machine

DONE - SEE  EmployeeResource controller class updateStatusEmployee method

get  request http://localhost:8081/api/employees/3/state/ACTIVE 

+ CRUD avalability was implemeted for Employee entity.
 


Please provide a solution with the  above features with the following consideration.

- Being simply executable with the least effort Ideally using Docker and docker-compose or any smailiar approach.

DONE
Please 
./gradlew bootJar -Pdev jibDockerBuild 
docker-compose -f src/main/docker/appDev.yml up -d
(to start with dev profile)

And 

./gradlew bootJar -Pprod jibDockerBuild 
docker-compose -f src/main/docker/app.yml up -d
(to start with prod profile)

 
- For state machine could be as simple as of using ENUM or by using https://projects.spring.io/spring-statemachine/ 
- Please provide testing for your solution.

DONE
 
gradlew test integrationTest jacocoTestReport

- Providing an API Contract e.g. OPENAPI spec. is a big plus 

DONE
OpenAPI documentation endpoint for those APIs is at /v3/api-docs


Added security
Added docker image generation and docker compose support  
Added sonar
Added liquibase
Added spring profiles for dev, prod with full properties setting.
Using mysql for prod 
Using h2 in memory for dev

## Development

To start your application in the dev profile, run:

```
./gradlew
```


## Building for production

### Packaging as jar

To build the final jar and optimize the peopleFlow application for production, run:

```
./gradlew -Pprod clean bootJar
```

To ensure everything worked, run:

```
java -jar build/libs/*.jar
```

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./gradlew -Pprod -Pwar clean bootWar
```

## Testing

To launch your application's tests, run:

```
./gradlew test integrationTest jacocoTestReport
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off authentication in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the gradle plugin.

Then, run a Sonar analysis:

```
./gradlew -Pprod clean check jacocoTestReport sonarqube
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mysql database in a docker container, run:

```
docker-compose -f src/main/docker/mysql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/mysql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./gradlew bootJar -Pprod jibDockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```



