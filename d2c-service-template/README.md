# D2C Service Template

### Some useful Gradle commands:
#### Refresh dependencies
> ./gradlew dependencies --refresh-dependencies  

#### Build jar file
> ./gradlew bootJar  
> ./gradlew clean build

### Run application test cases
> ./gradlew test

### Run Application with Gradle Commands
> To run directly: ./gradlew bootRun  
>  ./gradlew bootRun --args='--spring.profiles.active=test'  
> To run after build jar: java -jar build/libs/xyz-0.0.1-SNAPSHOT.jar

### Run for sonar review
> ./gradlew sonarqube \
-Dsonar.projectKey=d2c-service-template \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=sonar-qube-key