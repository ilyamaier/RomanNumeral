# RomanNumeral

A simple Spring Boot app that supports two queries:

- ```/romannumeral?query={integer}```, which takes an int (in range 1-3999) and returns its Roman numeral
- ```/romannumeral?min={integer}&max={integer}``` that takes two ints in range (1-3999) and returns all the corresponding Roman numerals of the numbers in that range

Here is also the [reference](https://en.wikipedia.org/wiki/Roman_numerals) from Wikipedia on how Roman numerals are constructed. 

## Running the application

Simply run ```./gradlew bootRun``` in your command line to run the project locally on ```localhost```.

To run the app in a docker container use:
```
docker build -t romannumeral:1.0 .
docker run -d -p 8080:8080 -t romannumeral:1.0
```

## Project Structure
The project is structured package-by-layer. The main logic of the two queries is contained in ```controllers/MainController.java```. All query methods and all methods for conversing numbers have a detailed documentation in JavaDoc style. Besides that, there are also JUnit tests for the query requests as well as for the number conversion methods.

## Dependencies used
-```org.springframework.boot:spring-boot-starter-web``` for core logic

-```org.springframework.boot:spring-boot-starter-test``` for testing