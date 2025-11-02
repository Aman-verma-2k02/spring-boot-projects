**\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*Kafka Commands\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\***


**Make kafka container on docker using docker-composer.yml file(run in same directory)**

docker compose up -d

**Open Kafka Bash(Compulsory)**

docker exec -it kafka bash

**--> List All Topics**

kafka-topics --list --bootstrap-server kafka:9092

**--> Delete Topics**

kafka-topics --delete --topic demo.order --bootstrap-server kafka:9092

**--> create Topic**

kafka-topics --create --topic demo.order --partitions 4 --replication-factor 1 --bootstrap-server kafka:9092

**--> Create producer**

kafka-console-producer --topic demo.order --bootstrap-server kafka:9092

**--> Create Consumer**

kafka-console-consumer --topic demo.order --from-beginning --bootstrap-server kafka:9092

kafka-console-consumer --topic demo.order --bootstrap-server kafka:9092 --property print.key=true --property print.partition=true

**--> create Consumer with group**

kafka-console-consumer --topic demo.order --bootstrap-server kafka:9092 --group group11 --property print.key=true --property print.partition=true

**--> create-producer-with key:value**

kafka-console-producer --topic demo.order --bootstrap-server kafka:9092 --property "parse.key=true" --property "key.separator=:"

**--> topic details like brokers (leader, replicas, ISR(insync replica))**
kafka-topics --bootstrap-server localhost:9092 --describe
kafka-topics --bootstrap-server localhost:29092 --describe

