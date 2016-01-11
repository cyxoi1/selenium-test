# Demo QA Store Selenium GUI Test

## Comments
  - Currently only configured to test against `FirefoxDriver`. Other drivers (Chrome, Safari, IE) would likely be 
    needed for testing in Selenium Grid or SauceLabs.

## Test Execution
---

This project utilizes the Gradle.
To execute tests, run the following from the root of the checkout.

    ./gradlew test

## IDE Configuration
---

Both IntelliJ and Eclipse are supported, if preferred.
Run on of the following to configure an IDE. Tests may then be executed within the IDE.

### IntelliJ IDEA

To create an IDEA project, run the following task from the root:

    ./gradlew idea
    
    
### Eclipse 

To create an Eclipse project, run the following task from the root:

    ./gradlew eclipse
