# I) Deliverables:
1i) sudoku-service-demo.jar (executable jar)
ii) sudoku.yml (needed as argument for executable jar, should be on same path)
iii) Source Code: see enclosed zip :"Source.rar"
iv)README.txt


# II) How to run the service:
java -jar sudoku-service-demo.jar server sudoku.yml

Please note the port used for this service to run is 8004.
If you wish to change the port number, simply open up the YAML file: sudoku.yml
and change the value of "port" to what ever you want.

# III) Swagger Documentation
in a browser, open up the following url:
http://localhost:8004/service/swagger

i) Once opened, click on the link: "default" to view the service.  Here you can interigate and interact with the services.
ii) click on the method: "GET /sudoku/solve"

Here you can interact with the endpoint
i) enter a valid sudoku string under parameters at: "unsolvedSudoku"
ii) Click "Try it out!"

Please note after do so, you will be given a valid curl command in the screen

# IV) The Single Service are described below:
Additional helper services....
GET http://localhost:8004/service/sudoku/solve?unsolvedSudoku="YOUR UNSOLVED SUDOKU HERE"

Notes about the service endpoint method:
As noted from the example, the unsolved sudoku string is passed as a url parameter (unsolvedSudoku).
The REST verb used is a simple GET.

The passed url parameter should be a 81 comma sepereated characters or digits.

The commas need to be encoded for a URL as follows: %2C
Please note you do not need to do this if using the swagger documentation page!

digits should be 1 through 9, no leading zeros or decimals
the only supported character is a lowercase "x"

# V) The Code

## a) Tech Stack:
JSON, RESTful based microservice (dropwizard)

## b) Tests include:
i) Simple Unit tests for the Manager class used to process the sudoku string (SudokuManagerTest.java)
ii) Unit Tests using Mockito to test the service and the reponse (SudokuApplicationTest.java)

## c) Documentation:
In addition to the unit tests and this document,
The SudokuSolutionManager contains substantial comments (javadocs) about the implemented logic.

## d) Solution and Tradeoff Notes:
I did some reading about programming strategies used for solving sudoku puzzles, and I was surprised to find it is not trivial matter.
programming every sudoku solution strategy would take some time.

That being said, the solution I'Ve offered will solve simple sudoku puzzles, as well as most of those of medium difficulty.

The business logic used takes makes two passes over an unsolved sudoku string:
i) a simple "brute force" pass
ii) a check of rows, columns and cells for 2 or more sets of doubles (see javadocs for more details)

The provided example suduko provided is fairly simple, and the "first pass" is enough to solve it.
However I tried my code out with a far more difficult sudoku and fould it was not enough.
While the "second pass" does go further to solving it, it cannot complete it.

# VI) TEST DATA 
#####  SUDOKU 01 INCOMPLETE AND VALID #############################################################################################################################
x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x

Will return JSON:

{ solution:
'4,3,5,2,6,9,7,8,1,6,8,2,5,7,1,4,9,3,1,9,7,8,3,4,5,6,2,8,2,6,1,9,5,3,4,7,3,7,4,6,8,2,9,1,5,
9,5,1,7,4,3,6,2,8,5,1,9,3,2,6,8,7,4,2,4,8,9,5,71,3,6,7,63,4,1,8,2,5,9' }
###################################################################################################################################################################

SUDOKU 02 INCOMPLETE AND VALID #############################################################################################################################
//DIFFICULT level Sudoku, taken from New York Post Plains, Trains and Sudoku 
x,x,x,x,x,x,x,x,x,7,x,x,x,x,4,8,x,x,3,5,x,x,8,2,1,x,x,5,1,x,x,x,3,6,x,x,8,x,x,x,x,x,x,x,9,x,x,2,5,x,x,x,1,7,x,x,9,3,7,x,x,4,1,x,x,1,2,x,x,x,x,3,x,x,x,x,x,x,x,x,x

Will return JSON:
 "{ error: 'cannot be completed' }"


All below will return the following JSON:

 "{ error: 'Please Enter a valid sudoku string : 81 comma seperated numbers and placeholders (using the letter 'x') ' }"

SUDOKU 03 INCOMPLETE AND INVALID (Too Long)
x,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x

SUDOKU 04 INCOMPLETE AND INVALID (Incompatible characters) zzz,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x

SUDOKU 05 INCOMPLETE AND INVALID (Incompatible numbers 1) x,x,x,02,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x

SUDOKU 06 INCOMPLETE AND INVALID (Incompatible numbers 2) x,x,x,99,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x
###################################################################################################################################################################











 













