# Hotel Bookings API
Hotel Bookings exercise as part of the XXXXXX company hiring process

## Prerequisites
You need to have installed:
- jdk 14
- docker v19.03.0+ (if an older docker server is available, change the "version" from 3.8 to whatever suits you, in the docker-compose.yml file. Look out for compatibility issues)

## Running the system
You need to follow the steps below:
1. unzip the file or clone from https://github.com/spyridon-ninos/hotel-bookings.git
1. cd /path/to/source/code/directory
1. run: `mvn clean install`
1. run: `docker-compose up --build`
1. open a browser window/tab
1. go to: `localhost:5000/swagger-ui.html`

At this point you are ready to use the system

## Security Considerations
None considered for this exercise

## Architectural description
* **Code design**: as suggested in best practices - most important is the functional style (wherever obviously required) used
* **Code organization**: The code is generally divided to business logic and code infrastructure. This organization is mostly obvious in the business layer.
* **Code architecture**: the hexagonal/binds-and-ports architecture is used to logically divide the code, however due to the fact that it's conceptually easier we refer to the different sections of the code as layers. The architecture is explained in great detail in the Microsoft Application Architecture Guide (see https://docs.microsoft.com/en-us/previous-versions/msp-n-p/ff650706(v=pandp.10)). Java packages were used to implement the architecture's layers:
  1. _Service layer_ (com.ninos.hotelbookings.service): this is the layer that includes all the java services/endpoints ("rest controllers") to other (micro-)services
  1. _Core layer_ (com.ninos.hotelbookings.core): this is the layer that includes all the business and infrastructure related java classes
  1. _Integration layer_ (com.ninos.hotelbookings.integration): this is the layer that includes all the code required for the service to communicate with peers (databases, other services etc). This is most commonly known as "data layer"
* **System architecture**: a one-tier system architecture (hotel-bookings service and the db) has been implemented due to requirements of the exercise

## Deployment Model
* The service is tailored to be deployed as a docker container
* The service configuration can be used as it is, in order to be deployed to a kubernetes cluster (using Helm charts)
* If a standalone deployment is required (or a non-kubernetes/helm compatible) then the application configuration file should be adapted

## Availability Model
* The service can be adjusted in order to be used in a high availability deployment model. It is a stateless service but the persistence logic should be adjusted for multiple running instances
