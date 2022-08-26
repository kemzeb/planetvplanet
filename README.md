# planetvplanet

Compare Solar System planets with exoplanets from the great beyond... all in
your web browser.

![Screenshot of web page](https://raw.githubusercontent.com/kemzeb/planetvplanet/main/docs/screenshots/screenshot.png)

## Features
* Searching for planets/exoplanets with  the aid of a search dropdown menu
* Choosing a random planet/exoplanet
* View data associated to a specific planet/exoplanet
* View the similarities and differences between a planet and exoplanet

## Tech Stack
### Frontend
* JavaScript
* React
### Backend
* Java
* Spring Boot
* MySQL

## Usage
1. Fire up a local MySQL server (either with Docker or by downloading it from its official website and running it).
    * If prompted for a username/password, type "root"
    * Assuming you are in a terminal session communicating to a MySQL server, create a database called "planet_data" using the following command: `create database planet_data;`
2. Assuming your in the root directory of this project, build the JAR file using Maven and run:
    ```
    ./mvnw package
    java -jar target/planetvplanet-0.0.1-SNAPSHOT.jar
    ```
3. Assuming your are in the `app` directory, start the web frontend using `npm start`

After following these steps, you should be able to interact with this app!

**Please note that** I am relatively new to frontend development; CSS and I are not the best of pals right now. This project is mainly an opportunity for me to mess around with exoplanet data, Spring Boot, and frontend development.