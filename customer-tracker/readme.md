## Customer Tracker

### Project Description

This decision project is part of a demo which goal is to analyze customers movement in a retail store, determine their behaviour (for example: "customer stopped in men's clothes department") and use Machine Learning to model for purchase/product recommendation.

This objective of this service is to identify focused or browsing customers based on the MQTT events emitted by IoT sensors in the store. (In the demo it is possible to use a scenario simulator to substitute the IoT devices).

This service is currently on v1, and the implemented rules defines:

- If a customer visits a same department more than three times in a row, the customer is identified **as focused**
- If a customer walks through different departments more than three times, the customer is identified **as browsing**

Whenever a **focused** or **browsing** customer is identified, an MQTT message should be emitted to alert the ecosystem.

### Project Details

This project was generated using [start.jbpm.org](start.jbpm.org). It generates a SpringBoot-based application composed of three projects. See below the details of each project:

- `customer-tracker-model`: Contains the models (POJOs) that will be shared between the Java service and the rules project. This is packaged as a jar and used by the two other projects.
- `customer-tracker-kjar`: Contains all the decision logic. This is the kjar that will be deployed in the KIE Server (decision engine). 
- `customer-tracker-service`: The project that holds the KIE Server (decision engine). In this scenario, it is also responsible for dealing with the integration with external services.

#### Local usage

**Pre-requisite:** To try out this project, you'll need an MQTT broker to interact with it and send events. The first thing to do, is start the broker. You can use Docker and Mosquitto to make things easier.

```
docker run -d --rm -p 1883:1883 --name mosquitto eclipse-mosquitto mosquitto -c /mosquitto-no-auth.conf
```

**Starting the project:** Now that you have a broker up and running, you can start the decision service.

1. Clone it to your machine and enter the `customer-tracker-service` folder.

2. Next, you can start it using the following command *(use `.bat` script if you are using Windows)*:

   ```
   ./launch.sh 
   ```

A SpringBoot application should start. 

It will listen to an MQTT Broker configured in the `application.properties` file, these are the default configurations:

```
mqtt.hostname=localhost
mqtt.port=1883
mqtt.username=username
mqtt.password=password
mqtt.topic=move-event
```

**Testing the project**: The application is now ready to evaluate any MQTT events published on the `move-event` topic. To send a new MQTT message using Mosquito, you can use the following command:

```
docker exec mosquitto mosquitto_pub -h 127.0.0.1 -t "move-event" -m '{"id":"1","ts":0,"x":700,"y":443}}'
```

You can check the logs to validate the behavior of the service:

```
2021-05-17 23:41:59.798 DEBUG 18905 --- [l: mqtt-service] c.d.r.handlers.IncomingMessageHandler    : Received Message: {"id":"1","ts":0,"x":700,"y":443}}
...
RULES EXECUTION: New trackedCustomerId 1 in Boys at: 0
```

And if you simulate the behavior of a focused customer, you can check the following output:

```.
2021-05-17 23:43:14.793 DEBUG 18905 --- [l: mqtt-service] c.d.r.handlers.IncomingMessageHandler    : Received Message: {"id":"1","ts":0,"x":700,"y":443}}
RULES EXECUTION: Existing customerId [1] identified in [Boys]
RULES EXECUTION: Customer [1] visited [Boys] [3] times in sequence
RULES EXECUTION: Focused Customer [1] at [Boys], [3] times
2021-05-17 23:43:14.797 DEBUG 18905 --- [l: mqtt-service] c.d.r.handlers.IncomingMessageHandler    : Emmit message to focused topic for CustomerID 1
```



### Messages payloads

#### Input: **customer move** 

| Name | Type | Description | Accepted values |
|---|---|---|---|
| id | string | ID of the customer | ...|
| ts | integer | Timestamp (unix time) | ...|
| x | integer | coordinate x | ...|
| y | integer | coordinate y | ...|

Example of payload:

```json
{
  "id": "127",
  "ts": 192322800,
  "x": 0,
  "y": 0
}
```

#### Output: **Focus Event** 

| Name | Type    | Description           | Accepted values |
| ---- | ------- | --------------------- | --------------- |
| id   | string  | ID of the customer    | ...             |
| ts   | integer | Timestamp (unix time) | ...             |
| dep  | string  | Department name      | ...             |
| x    | integer | coordinate x          | ...             |
| y    | integer | coordinate y          | ...             |

Example of payload:

```json
{
  "id": "127",
  "ts": 192322800,
  "dep": "Boys"
}
```

