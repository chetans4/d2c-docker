This Payment service, used to manage all operations relegated to payment transactions.

http://localhost:9082/actuator
http://localhost:9082/actuator/circuitbreakers
http://localhost:9082/actuator/circuitbreakerevents

- Can plan email trigger from CircuitBreakerEventListener

#### Build Docker Image
> sudo docker build -t d2c-payment-service .

#### Run Docker Image
[//]: # (8082 machine port:9082 container port)
> sudo docker run -p 8082:9082 d2c-payment-service

#### Run Application with Gradle Commands
> ./gradlew bootRun  
> ./gradlew bootRun --args='--spring.profiles.active=docker'

#### Start prometheus
> sudo docker-compose -f docker-compose.yml up

#### Other Docker Commands:
> sudo docker ps  
> docker ps -a  
> sudo docker images

[//]: # (Remove container  )
> docker rm 634b6763e39d   
> docker image rm 4786e098b0a6  
> docker exec -it 9e49ffb3181f sh

#### Run with temp docker container
> docker run --rm -it -v $(pwd):/app -w /app -p 9082:9082 gradle:8.10-jdk17 ./gradlew bootRun

### Run with docker-compose
> sudo docker-compose -f docker-compose.yml up

#### Configure the Grafana.
- Open http://localhost:3000 (admin/admin)
- **Configure integration with Prometheus**
    - Access configuration
    - Add data source
    - Select Prometheus
    - Use url "http://localhost:9090" and access with value "Browser"
- **Configure dashboard**
    - Access "home"
    - Import dashboard
    - Upload dashboard.json from /docker