## Used tools and libraries:
```bash
- Java-11, maven, tesng.
- major used libs > Selenium, Rest assured, Appium and allure reporting.
- Json reader, Property file reader.
- Running tests in docker containers
```

## How to run
## for both api test and web tests
```bash
- I use github actions for the running the tests and deploying allure report to github page
  - Make sure to make the repo public 
  - For getting report for web tests run the e2e-tests.yml workflow then visit "https://abdelaziznassif.github.io/q-pros-task"
     - sample: "https://drive.google.com/file/d/1xcV8k1LULY5jWDjQEZQd7sNuD4ZckUpg/view?usp=sharing"
  - For getting report for web tests run the api-tests.yml workflow then visit "https://abdelaziznassif.github.io/q-pros-task"
    - sample: "https://drive.google.com/file/d/1GAZ_JNehOAfyH0S7WF_Lv85Y_ouQdX3j/view?usp=sharing"
  - For web parallel execution on different browsers is not done yet because I ran out of time, I will contiune on in the next few days
```
## for mobile tests, you can now run it locally
  - install Java 11 and maven on your local machine
  - install node.js on your local machine
  - download appium server > npm install -g appium
  - enable uiautomator2 driver > appium driver install uiautomator2
  - install android studio
  - install emulator on android studio then update the desired caps accordigly
  - run the tests > mvn clean -DargLine="-Xmx6g" -D"junit.jupiter.execution.parallel.enabled=false" -D"junit.jupiter.execution.parallel.config.strategy=dynamic" -Dtest="com/mobile/tests/**" test
  - then from terminal run allure serve to get the report
```

## Design patterns used:
```bash
- Page object model design pattern
- Data-driven design pattern
  - Getting environment variables .env file locally and from pipelines/.env.prod file remotely
  - Getting test data from Json file
```

## How to run the project:
### to run all tests: 
```bash
- Run BE tests > mvn clean test -Dtest="com.petStore.tests.*.**" test
- Run E2E tests > mvn clean test
```
### Open allure report locally from cmd
```bash
Allure serve
```

## How to contribute:
### Before adding to the project
```bash
- Switch to main branch
- Pull latest main branch: git pull
- Take a copy of test or main branch: git checkout -b new-branch-name main
```
### After finishing follow the below steps:
```bash
- git add .
- git commit -m "Adding related message to your PR"
- git push origin branch-name
- From GitHub: Create PR by Comparing your branch to test or main branch
```
