# Ecommerce Automated Tests

This project is an example to demonstrate the use of automated tests to guarantee the quality and the correct functioning of the applications throughout their development and maintenance period.

### Pre-requisites:
[Git](https://git-scm.com/)

[Java](https://www.digitalocean.com/community/tutorials/como-instalar-o-java-com-apt-no-ubuntu-18-04-pt)

[Maven](https://maven.apache.org/)

[Browserstack](browserstack.com) account

[Docker](https://docs.docker.com/engine/install/ubuntu/)

[Docker-compose](https://docs.docker.com/compose/install/)

## How to test?
Clone project:
> git clone https://github.com/viniciusflores/ecommerce_automated_tests.git

Start the grid, if the test will be local
> docker-compose up -d

Run all Tests
> mvn clean test
* Maven will download all dependencies, build the project and run the tests with this only one command.

Access Browserstack to see the test execution and results
> Access browserstack dashboard: https://automate.browserstack.com/dashboard/
>> username: desafiotecnico2020+java@gmail.com
>> password: P@ssw0rd


* The test run takes place through the test suites provided by TestNG. They are configured in the pom.xml file.
* By default only includes tests in the cloud, but feel free to run the others.