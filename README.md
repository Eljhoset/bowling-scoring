# Ten-pin bowling-scoring
To build and run the project you need to have Java 8+ and Maven 3.6+ installed in your system. After the build, a `target` directory is generated for you with a jar file named **bowlingscoring.jar** in it. Additionally, some test files are provided in the `src/main/resources` directory, follow the instructions bellow to run the program.

``` shell
# Clone the source code or download the .zip file from github.
git clone https://github.com/Eljhoset/bowling-scoring.git

# Enter the project directory.
cd bowling-scoring

# Compile the project.
mvn clean package

# Optionally, you can compile the project with integration tests as follow.
mvn clean package integration-test

# Use one of the follow command to test with the provided sample files.
java -jar target/bowlingscoring.jar src/main/resources/regular_game.txt
java -jar target/bowlingscoring.jar src/main/resources/regular_game_two_players.txt
java -jar target/bowlingscoring.jar src/main/resources/all_zeros.txt
java -jar target/bowlingscoring.jar src/main/resources/all_strikes.txt
java -jar target/bowlingscoring.jar src/main/resources/all_fouls.txt

```
