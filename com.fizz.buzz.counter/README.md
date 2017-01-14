# Requirements: 
Java 7 or greater
Requirements for building the executable jar your self:
Maven 3 or greater

# Instructions for running the FizzBuzz counter program
## (Assuming java 7 or greater is installed)

i) drop the "FizzBuzz.jar" in a directory of your choice.
{YOUR_HOME}/{YOUR_DIR}/FizzBuzz.jar

ii) navigate to the directory containing the jar

iii) run the following command:
java -jar FizzBuzz.jar

iv) enter values as prompted


# Instructions for generating the executable jar your self:
## (Assuming you have maven and java 7 or greater installed)

i) drop the source code folder "com.fizz.buzz.counter" at: 
{YOUR_HOME}/{YOUR_DIR}/

ii) navigate to the directory of the pom.xml
{YOUR_HOME}/{YOUR_DIR}/com.fizz.buzz.counter/pom.xml

iii) run the following command: "mvn clean install"

iv) "FizzBuzz.jar" should now exist at:
{YOUR_HOME}/{YOUR_DIR}/com.fizz.buzz.counter/target/FizzBuzz.jar

# Results
## The console should output a correct range of numbers as entered by the user
### correct range meaning: end date greater than start date
### The Text "Fizz" will be written in place of a number for multiples of three ONLY
### The Text "Buzz" will be written in addition to a number for multiples of five ONLY
### The Text "FizzBuzz" will be written in addition to a number for multiples of BOTH three and five
## A file "FizzBuzz_Output.txt" should be generated in the same directory as the jar, formatted in UTF-8
 
