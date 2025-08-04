Generated using the following URL: https://start.spring.io/#!type=gradle-project&language=java&platformVersion=4.0.0-M1&packaging=jar&jvmVersion=24&groupId=com.example&artifactId=SB4.0-WebTestClient&name=SB4.0-WebTestClient&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.SB4.0-WebTestClient&dependencies=actuator,webflux,spring-webclient

Pushed to GitHub with the commands:

```
git remote add origin git@github.com:magnus-larsson/SB4.0-WebTestClient.git
git branch -M main
git push -u origin main
```

Run the failing test:

```
git clone https://github.com/magnus-larsson/SB4.0-WebTestClient.git
cd SB4.0-WebTestClient.git

./gradlew test -i
```

Test will fail with the output:
```
ApplicationTests > createCompositeProductTest() STANDARD_OUT
    2025-08-04T15:40:14.201+02:00  INFO 43346 --- [SB4.0-WebTestClient] [    Test worker] [ ] c.e.sb40.webtestclient.ApplicationTests  : ### sending productId: 5
    2025-08-04T15:40:14.248+02:00  INFO 43346 --- [SB4.0-WebTestClient] [flux-http-nio-2] [ ] c.e.sb40.webtestclient.MyRestController  : ### receiving productId: 0
    2025-08-04T15:40:14.252+02:00  INFO 43346 --- [SB4.0-WebTestClient] [    Test worker] [ ] c.e.sb40.webtestclient.ApplicationTests  : ### test result: 0

ApplicationTests > createCompositeProductTest() FAILED
    org.opentest4j.AssertionFailedError: expected: <5> but was: <0>
```

Edit `build.gradle` and comment out the dependency to `:spring-cloud-starter-stream-kafka'`, then rerun the test and it will succeed.