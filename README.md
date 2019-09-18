# bowling-scoring
To build and run the project you need to have Java 8+ and Maven 3.6+ installed in your system. After the build a jar file **bowlingscoring.jar** is generated in the target folder. Also, there are some test files in the src / main / resources folder.

``` shell
# clone the source code, you can also download the .zip file from github
git clone https://github.com/Eljhoset/bowling-scoring.git

# Enter the project directory
cd bowling-scoring

# Compile the project
mvn clean package

# Optianl can compile with integration tests
mvn clean package integration-test

# Use one of the follow command to test with the provided sample files.
java -jar target/bowlingscoring.jar src/main/resources/regular_game.txt
java -jar target/bowlingscoring.jar src/main/resources/regular_game_two_players.txt
java -jar target/bowlingscoring.jar src/main/resources/all_zeros.txt
java -jar target/bowlingscoring.jar src/main/resources/all_strikes.txt
java -jar target/bowlingscoring.jar src/main/resources/all_fouls.txt

```
