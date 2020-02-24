# Synopsis

Roll Dice Micro Service based on Spring Boot

# Services available

- **Dice Distribution Simulation** : Rolls a piece or set of dice with defined number of sides, rolled for a given number of times

- **Dice Distribution Simulation Count** : Gets the number of times a given dice number–dice side combination is rolled, grouped by all existing dice number–dice side combinations.

- **Dice Simulation Relative Distribution** : Returns the relative distributions, given a dice number–dice side combination against the total number of rolls made for the given combination.

# Installation

Make sure that the following are installed on your machine:

- Java 8:
 <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>

- Maven:
 <https://maven.apache.org/>

- MySQL:
 <https://dev.mysql.com/downloads/>

# Running the application

- Create a database schema in MySQL named ```rolldice```, default DB URL points to a locally running instance of MySQL at ```localhost``` and port ```3306```
- Create MySQL DB user named ```diceroll``` with password ```diceroll```. Or, modify the username and password values in application.yml file
- Maven is required to build OR run the Sping Boot application, Spring Boot application runs on the default port, 8080
  - To build the project, run ```mvn install``` on the project root folder
  - To run the project, either run by ```mvn spring-boot:run``` on the project root folder, OR
  - Run ```java -jar ./target/roll-dice-service-1.0.jar``` on the project root folder

# Endpoints available

## Dice Distribution Simulation

Rolls a piece of with defined number of sides rolled for a given number of times

**URL** : `/api/dice/roll`

**Method** : `GET`

**Request PARAMS** :

- **numOfDice** : Number of Dice to roll, minimum number of dice is 1

- **numOfSideOfDice** : Number of sides of a given dice, minimun number of sides is 4

- **numOfRolls** : Number of times each dice is rolled, minimum is 1

**Response BODY** :

- **rollSum** : The sum total of the values that appeared on the dice

- **rollSumCount** : Number of times the total sum has appeared per each dice roll

**Content examples**
    GET /api/dice/roll?numOfDice=2&numOfSideOfDice=6&numOfRolls=5

For 2 pieces of 6 sided dice rolled 5 times, response is:

```json
[
    {
        "rollSum": 8,
        "rollSumCount": 2
    },
    {
        "rollSum": 9,
        "rollSumCount": 1
    },
    {
        "rollSum": 2,
        "rollSumCount": 1
    },
    {
        "rollSum": 7,
        "rollSumCount": 1
    }
]
```

## Dice Distribution Simulation Count

Gets the number of times a given dice number–dice side combination is rolled

**URL** : `/api/dice/simulation/total`

**Method** : `GET`

**Request PARAMS** :

- **numOfDice** : Number of Dice to roll, minimum number of dice is 1

- **numOfSideOfDice** : Number of sides of a given dice, minimun number of sides is 4

**Response BODY** :

- **numOfSimulations** : The number of simulations given a dice number–dice side combination

- **totalRolls** : Number of times the dice number–dice side combination has been rolled

**Content examples**
    GET <http://localhost:8080/api/dice/simulation/total?numOfDice=2&numOfSideOfDice=6>

For 2 pieces of 6 sided dice rolled 5 times, response is:

```json
{
    "numOfSimulations": 1,
    "totalRolls": 5
}
```

## Dice Simulation Relative Distribution

Given a dice number–dice side combination, returns their list of relative distributions.
That is, :
    The number of times a given dice sum has appeared per total number of dice rolls, in percentage.  

**URL** : `/api/dice/relative/distribution`

**Method** : `GET`

**Request PARAMS** :

- **numOfDice** : Number of Dice to roll, minimum number of dice is 1

- **numOfSideOfDice** : Number of sides of a given dice, minimun number of sides is 4

**Response BODY** :

- **relativeDistribution** : The number of simulations given a dice number–dice side combination

- **rollSum** : Number of times the dice number–dice side combination has been rolled

- **rollSumCount** : Number of times the dice number–dice side combination has been rolled

**Content example**
    GET <http://localhost:8080/api/dice/relative/distribution?numOfDice=2&numOfSideOfDice=6>

For 2 pieces of 6 sided dice rolled 5 times, response is:

```json
[
    {
        "relativeDistribution": "40.0%",
        "rollSum": 8,
        "rollSumCount": 2
    },
    {
        "relativeDistribution": "20.0%",
        "rollSum": 9,
        "rollSumCount": 1
    },
    {
        "relativeDistribution": "20.0%",
        "rollSum": 2,
        "rollSumCount": 1
    },
    {
        "relativeDistribution": "20.0%",
        "rollSum": 7,
        "rollSumCount": 1
    }
]
```
