#Project has two modules: api-test-slotegrator + ui-test-slotegrator

## The first module is api-test-slotegrator

For testing REST API was used RestAssured framework.

**For execution API tests please use command below from directory module (../test-slotegrator/api-test-slotegrator):**

```mvn clean test```

**For creating report please use command from directory module:**

```mvn allure:serve```

## The second module is ui-test-slotegrator

For testing UI was used Java + Selenium + Cucumber framework.

**For execution UI tests please use command below from directory module (../test-slotegrator/ui-test-slotegrator):**

```mvn clean test```

**For creating report please use command from directory module:**

```mvn allure:serve```

