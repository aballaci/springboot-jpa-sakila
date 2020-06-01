# Spring Boot JPA 
A Spring Cloud On Kubernetes Example 
 ###Features
 * micrometer (for gathering springboot )
 * actuator 
 * Prometheus 
 * Grafana 
 * Postgresql Sakila DB

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com/) - Used containerise the application
* [Kubernetes](https://kubernetes.io/) - Used to generate a scalable cloud microservice
* [DB Documentation](https://www.ntu.edu.sg/home/ehchua/programming/sql/sampledatabases.html) - MySQL's Sample Salika (DVD Rental) Database


## Getting Started

You will need:

* Docker desktop
* Docker-Kubernetes or Minikube (Kubernetes Dashboard or similar)
* [Helm 3](https://helm.sh/docs/intro/install/)
* [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* superbenchmarker ```choco install superbenchmarker```
### Prerequisites

uses aballaci/springboot-sakila-jpa:v3 in Dockerhub

you can build your own version:

```docker build -t namespace/image-name:version```

```docker push namespace/image-name:version```

install prometheus: 

```helm install prometheus ./prometheus```

install the postgres persistent volumes that populate the database:

```kubectl create -f persistent-volume.yml```

install postgres

``` helm install postgres postgresql/ ```

install grafana:

```helm upgrade grafana ./grafana/```

create the prometheus datasource:

url: ```http://prometheus-server```
 
access: ``Server(default)``

install the dashboard in grafana importing it from grafana_dashboard_spring-boot-statistics_rev2.json

### Installing

<code> kubectl apply -f springboot-jpa.yaml </code>

```
Metrics: http://localhost:8080/actuator/metrics

Metrics in prometheus format: http://localhost:8080/actuator/prometheus
```

Grafana dashboard:

![screenshot](https://github.com/aballaci/springboot-jpa-sakila/blob/master/docs/img/grafana.png)

## Running the tests

`Run the load tests: ```sh load-test.sh```

![screenshot](https://github.com/aballaci/springboot-jpa-sakila/blob/master/docs/img//load_test_c4_100.png)

* [Actors](http://localhost:31449/actors) - http://localhost:{port}/actors
* [Films](http://localhost:31449/film) - http://localhost:{port}/film
* [Actorinfo](http://localhost:31449/actorinfo) - http://localhost:{port}/actorinfo
* [Custom Query](http://localhost:31449/actorinfo/search/findByActorsLastName?name=GUINESS) - http://localhost:{port}/actorinfo/search/findByActorsLastName?name=GUINESS

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/aballaci/springboot-jpa-sakila/tags). 

## Authors

* **Armand Ballaci** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](Licence.md) file for details
