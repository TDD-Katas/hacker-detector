        HACKER DETECTION SYSTEM

COMPILING AND RUNNING

The project uses Maven for build and requires at least Java 6. 
To build the project you should run:
> mvn clean install
This command will compile and run all tests.

THE TESTS

The project has three types of tests: unit, integration and acceptance. 
Apart from testing the system, they have also the role of being a living documentation.

*Unit tests*
The unit tests describe the smallest behavior of the application. 
All the unit test are isolated from others using stubs.
The unit tests can be found in the folder ./src/test/java and their filename ends with *Test.java 
These tests can be run using:
> mvn test

*Integration test*
Because the unit test use stubs, the default constructors are usually not touched by them. 
The integration tests are responsible for testing the default bindings between objects.
The Integration tests can be found in the folder ./src/test/java  and their filename ends with *IT.java 
These tests can be run using:
> mvn verify

*Acceptance test*
These test show that the system does what is supposed to do from the business point of view. 
They can be viewed as testing by example. While unit tests and integration test are aware of the internal implementation, acceptance test only care about inputs and output.
The Acceptance tests tests can be found in the folder ./src/test/specs
These tests can be run using:
> mvn verify
The acceptance test have been implemented using Concordion. The output of running the test is an .html file located at:
/target/specs/com/sky/detector/HackerDetector.html

TECHNIQUE

I implemented this exercise using Acceptance TDD. So, I first turned your specifications into acceptance tests using Concordion. (see /src/test/specs/**)
Concentrating on passing one acceptance test at a time, I wrote unit tests to describe the desired behavior. The tests where written using a mockist style approach.
After each test passed, I did refactorings in order to remove duplication and improve readability.

In order to improve code readability and promote encapsulation, one refactoring was to wrap the primitives from the login attempt. 
Thus, classes like IPAddress, LoginDate and Action, had appeared. These are value classes and do not need to be tested explicitly.

When all acceptance tests have been passed I then turned to writing tests in order to reduce the memory usage of the application
Thus, a test like "when_storing_a_failed_login_should_delete_logins_older_then_five_minutes" pushed the design towards a more efficient implementation.

All code was written under Git version control system, so you can browse the history to see how exactly I had performed the exercise.

OTHER THOUGHTS

What I found interesting about this exercise was that converting and validating the input took almost as many test as the actual business logic.





