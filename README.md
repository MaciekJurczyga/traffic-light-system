# Traffic Light System

A simulation project of an intelligent traffic light system designed to optimize vehicle flow at an intersection by dynamically selecting traffic light phases.

## Running the Application in terminal (base app)
App run via terminal takes 2 arguments, 
1. Absolute path to input.json
2. Absolute path to output.json
   
**input.json structure**
```json
  {
  "commands": [
    {
      "type": "addVehicle",
      "vehicleId": "vehicle1",
      "startRoad": "south",
      "endRoad": "north"
    },

    {
      "type": "step"
    }
  ]
}
  ```

### Option 1: Fetching jar from Github and executing
*Note: Java 17 is required.*

- **Linux:**  
  ```bash
  sudo apt install openjdk-17-jdk
  ```
- **Mac:**  
  ```bash
  brew install openjdk@17
  ```

*Note: CHANGE PATHS IN THE COMAND TO YOUR ACTUAL PATHS :)*

```bash
curl -L -o cli.jar https://github.com/MaciekJurczyga/traffic-light-system-project/releases/download/1.0.0/cli-1.0-SNAPSHOT.jar && java -jar cli.jar "/absolute/path/to/input.json" "/absolute/path/to/output.json"
```

### Option 2: Build application yourself
**Note:** Java 17 is required.

- **Linux:**  
  ```bash
  sudo apt install openjdk-17-jdk
  ```
- **Mac:**  
  ```bash
  brew install openjdk@17
  ```
*Note: CHANGE PATHS IN THE COMAND TO YOUR ACTUAL PATHS :)*
```bash
cd traffic-light-system
chmod +x gradlew
./gradlew clean build
java -jar cli/build/libs/cli-1.0-SNAPSHOT.jar "/absolute/path/to/input.json" "/absolute/path/to/output.json"
```

## Running Web Application with Docker

*Note 1: Docker and docker compose are required*

In the root directory of the repository:

```bash
docker compose up
```
Images are stored in GHCR, so they shouldn't build locally

Then visit: [http://localhost:3000](http://localhost:3000/)


## Project Structure

The project consists of two main parts: the simulation engine and the user interface (UI).

*   **Simulation Engine (traffic-light-system)**
    *   A Java application divided into 3 modules.
    *   `core`: The main module, responsible for the simulation logic, algorithm execution, and generating the output results file.
    *   `web`: Exposes a REST API endpoint to run the simulation via a web browser and the UI.
    *   `cli`: Allows for running and controlling the simulation from the command-line interface.

*   **User Interface (UI)**
    *   A simple simulation visualization built with React and TypeScript.
    *   The interface is split into two sections:
        *   **JSON Builder:** Used to construct the input commands for the simulation.
        *   **Visualization:** Graphically presents the simulation's progress and results.

## Intersection Layout

The intersection modeled in the simulation consists of four access roads. Each road has two lanes:
*   **Right lane:** For driving straight or turning right.
*   **Left lane:** Exclusively for grade-separated left turns.

<img width="822" height="666" alt="Intersection Layout" src="https://github.com/user-attachments/assets/1a7bc627-0da0-4f28-87e8-464ba6abc5d3" />

### Traffic Light Phases

The system includes **13 possible traffic light phases**. These phases consist of both solid lights (green light, unconditional passage) and conditional right-turn lights (i.e., a green arrow, where the driver must yield).

In the example below:
*   **Orange arrows** represent solid lights (non-conflicting passage).
*   **Red arrows** represent conditional right turns. The simulation model assumes that vehicles on lanes with a solid green light follow their designated paths, allowing vehicles making a conditional right turn to proceed without issues.

<img width="819" height="689" alt="Sample traffic light phase" src="https://github.com/user-attachments/assets/340cfc3f-786d-48e9-b31a-02c20ea2a82d" />

---

## Algorithm

Before each simulation step, the algorithm calculates the optimal traffic light phase to activate. The decision-making process is based on a scoring system that can be modified with three parameters:

*   `WAITING_TIME_BONUS`: A value that increases the weight of vehicle waiting time.
*   `STRAIGHT_OR_RIGHT_LANE_PRIORITY`: The base score (priority) for a lane designated for straight or right-turning traffic.
*   `LEFT_LINE_PRIORITY`: The base score (priority) for a lane designated for left-turning traffic.

### Step 1: Select the Best Phase (PRE-STEP)

The algorithm iterates through all 13 possible phases and calculates a total score for each one.

**For lanes with a solid green light:**
1.  The lane's priority (`STRAIGHT_OR_RIGHT_LANE_PRIORITY` or `LEFT_LINE_PRIORITY`) is added to the score.
2.  The number of vehicles waiting in that lane is added to the score.
3.  The sum of the waiting times of all vehicles in that lane is added to the score.

**For lanes with a conditional right turn (green arrow):**
1.  The lane's priority is added to the score.
2.  The algorithm counts only the leading vehicles in the queue that intend to turn right and adds this number to the score.
3.  The sum of the waiting times for these specific right-turning vehicles is added to the score

*Note: This calculation method for conditional turns prevents a false postivite phase score incrementation for right-turning vehicles if the first car in the queue intends to go straight.*

The phase with the highest total score is selected and set as the current traffic light phase for the intersection.

### Step 2: Vehicle Movement (STEP)

The first vehicle from each lane with an active green light (solid or conditional) proceeds through the intersection.

### Step 3: Update Waiting Times (POST-STEP)

The waiting time for every vehicle that was stopped at a red light during the step is incremented by `WAITING_TIME_BONUS`. This accumulating time is a key factor in the scoring calculations for subsequent steps.

### Algorithm tuning

Behaviour of algorithm can be changed by modification of mentioned parameters. They are localted in TunableParameters class.

## Validation

User input is strictly validated.
1) Each command must have type, either step or addVehicle
2) Each road dircetion must be valid, eg. westtt is not valid
3) U-turns are not supported, so startRoad: south, endRoad: sound will throw exception
4) vehicleId must be unique through the entrie simulation
5) Each input must not be null or empty

## Rest API

Application exposes one endopint: /simulation/run

Request body:
```json
{
  "commands": [
    {
      "type": "addVehicle",
      "vehicleId": "vehicle1",
      "startRoad": "south",
      "endRoad": "north"
    },

    {
      "type": "step"
    }
  ]
}
```
This input is validated accoriding to section above (Validation)

## CI/CD

Project has simple CI/CD based on Github Actions. 
There are 2 workflows, one for running tests and building project, and second for building and pushing docker images to GHCR.

