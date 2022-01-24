# RestAssuredWithBDD
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/Z8Z87PJ6C)
## If you are using my code, care to buy a coffee for me ;) . My number -> +91-7507253075 (WhatsApp) 

# API Automation Framework

This project intended to Automate Testing of given API. 
This is Java based Rest Assured project. It follows  BDD framework

## What i need to run project in my system?

* Install eclipse 
* Install maven
* JDK 1.8
* Install plugin for testNG (https://www.guru99.com/install-testng-in-eclipse.html)

## How do I set up Framework in my system? 

### Running Test in local system 
 * Download the zip or clone the Git repository.
 * Unzip the zip file (if you downloaded one).
 * Open Eclipse
 	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip file
	* Select the right project
 * Once Project is imported follow below steps
 	* Right Click on Project -> Run as -> run config
 	* Select Maven build -> Click on new (top right corner on same window)
 	* Select Base Directory
 	* give goal as test
 	* In Environment tab, click on Add. Give name "URL" and value as your host url (like https://api.openweathermap.org) 
 	* Click on Save and run

### Running in Jenkins Pipeline
 * We Need create bat file and to run using testng.xml 
 * Follow below two link to create pipeline.
     * http://www.seleniumeasy.com/testng-tutorials/how-to-run-testng-xml-via-batch-file-example
     * https://www.seleniumeasy.com/jenkins-tutorials/invoke-testng-xml-tests-from-jenkins

### Running using maven command
 * we can use below maven command to run test either via command prompt or pipeline.
      * ```mvn clean test -Dversion=1.0 -Dbrowser=Chrome -Dcucumber.options="--tags @debug1"```
   
## How do I know if Test is passed? 
 * Option 1 : In Console Failures should be zero
 * Option 2 : Navigate to AutomationReport folder and check most recent HTML report.
 
## How do I onboard new test? 
 * In Feature file we need write new test
 * Parameterize query param to test different value.
 
## No, I want to onboard new test with different HTTP method like POST, PUT, Patch etc.. ? 
 * Again, you need to write new test in given feature file
 * write method for HTTP operation
 * Rest methods can be reused.
 
## Any More Questions, Feel free to ping me ;)    


