## Used tools and libraries:
```bash
- Java-11, maven, Junit5.
- major used libs > Selenium, Rest assured, Appium and allure reporting.
- Json reader, Property file reader.
- Running tests in docker containers
```

## How to run
```bash
# for both api test and web tests 
- I use github actions for the running the tests and deploying allure report to github page
  - Make sure to make the repo public
  - For getting report for web tests run the e2e-tests.yml workflow then visit "https://abdelaziznassif.github.io/q-pros-task"
  - For getting report for web tests run the api-tests.yml workflow then visit "https://abdelaziznassif.github.io/q-pros-task"
  - For web parallel execution on different browsers is not done yet because I ran out of time, I will contiune on in the next few days
# for mobile tests, you can now run it locally
  - download appium server
  - enable uiautomator2 driver
  - install android studio
  - install emulator on android studio then update the desired caps accordigly
  - run the tests
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
- Run BE tests > mvn clean -DargLine="-Xmx6g" -D"junit.jupiter.execution.parallel.enabled=true" -D"junit.jupiter.execution.parallel.config.strategy=dynamic" -Dtest="com/velents/tests/apiE2eTests/*/**" test -Dgroups="${RUN_ENV} & ${RUN_TYPE}"
- Run E2E tests > mvn clean -DargLine="-Xmx6g" -D"junit.jupiter.execution.parallel.enabled=false" -Dtest="com/velents/tests/regressionE2eTests/*/**" test -Dgroups="${{ parameters.runEnv }}"
```
### Open allure report locally from cmd
```bash
Allure serve
```

## How to contribute:
### Before adding to the project
```bash
- Switch to test or main branch
- Pull latest test or main branch: git pull
- Take a copy of test or main branch: git checkout -b new-branch-name main
```
### After finishing follow the below steps:
```bash
- git add .
- git commit -m "Adding related message to your PR"
- git push origin branch-name
- From GitHub: Create PR by Comparing your branch to test or main branch
```
