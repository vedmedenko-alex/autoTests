API automation demo (Carina-style template)

What is included:
- Maven project (pom.xml) with Carina placeholders + RestAssured + TestNG
- 5 imperative tests (src/test/java/com/demo/api/tests/imperative)
- 5 declarative tests (src/test/java/com/demo/api/tests/declarative) using an interface + factory
- _config.properties with base_url=http://localhost

How to run:
1. Start httpbin in Docker:
   docker run -p 80:80 kennethreitz/httpbin

2. From project root, run:
   mvn clean test
