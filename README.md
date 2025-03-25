# Mini Project Report: DevOps Pipeline for Scientific Calculator

## Aim
To show the DevOps pipeline in action by taking an example menu-driven calculator app.

## Requirement Analysis
Since this is a demo application, basic operations like add and multiply haven’t been shown. A user can perform the following scientific calculations:
- Square root function - √x
- Factorial function - x!
- Natural logarithm (base e) - ln(x)
- Power function - xb

To achieve this we propose a simple CLI-based menu-driven application.

## Technologies Used
1. **Java** - as the underlying programming language.
2. **IntelliJ IDEA** - as an Integrated Developer Environment.
3. **JUnit** - an open-source testing framework for Java.
4. **Maven** - a build automation tool.
5. **Git** - a source code management tool.
6. **Jenkins** - a CI/CD tool.
7. **ngrok** - to expose local servers.
8. **GitHub Webhooks** - to trigger automated actions.
9. **Docker** - a containerization platform.
10. **Ansible** - a configuration management tool.
11. **Host OS** - Ubuntu 22.0.4 LTS (jammy).

## Important Links
- **GitHub Repository**: [Scientific Calculator](https://github.com/Mohit-Marfatia/Scientific-Calculator)
- **DockerHub Repository**: [Scientific Calculator Image](https://hub.docker.com/r/mt2024090/scientific-calculator)

## Step by Step Guide
### 1. Java Installation
```sh
sudo apt update && sudo apt install -y openjdk-17-jdk
java --version
```
### 2. IntelliJ Project Setup
- Download and install IntelliJ (Community Edition is sufficient).
- Create a new project using Maven and set JDK version 17.
- Create **Calculator.java** in the `src` folder.
- Create **CalculatorTest.java** in the `test` folder.

### 3. JUnit Configuration
Add the following dependency in `pom.xml`:
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```
### 4. Maven Setup
Add the following plugin in `pom.xml`:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```
```sh
sudo apt install maven
mvn clean package
```
### 5. Git & GitHub Setup
```sh
sudo apt install git
git --version
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin <GitHub repo URL>
git push origin main
```

### 6. Docker Setup
```sh
sudo apt install docker.io
docker --version
docker build -t mt2024090/scientific-calculator .
docker run -d --name scientific_calculator mt2024090/scientific-calculator
```
Create a **Dockerfile**:
```Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/ScientificCalculator-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
```
Push the image to DockerHub:
```sh
docker login
docker push mt2024090/scientific-calculator:latest
```

### 7. Ansible Setup
```sh
sudo apt install ansible
ansible --version
```
Create **deploy.yml**:
```yaml
- name: Deploy Scientific Calculator
  hosts: localhost
  tasks:
    - name: Pull Latest Docker Image
      command: docker pull mt2024090/scientific-calculator:latest
    - name: Stop and Remove Existing Container
      command: docker rm -f scientific_calculator
    - name: Run Scientific Calculator Container
      command: docker run -d --name scientific_calculator mt2024090/scientific-calculator
```
Push `deploy.yml` to GitHub.

### 8. Jenkins Setup
```sh
sudo apt install jenkins
sudo systemctl start jenkins
```
Configure Jenkins:
- Install required plugins: Git, Docker, Ansible, Pipeline.
- Set up credentials for GitHub and DockerHub.
- Create a new pipeline project and use the following `Jenkinsfile`:
```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'docker build -t mt2024090/scientific-calculator .'
                sh 'docker push mt2024090/scientific-calculator:latest'
            }
        }
        stage('Deploy') {
            steps {
                ansiblePlaybook playbook: 'deploy.yml', inventory: 'inventory'
            }
        }
    }
}
```

### 9. GitHub Webhooks & Ngrok
```sh
sudo apt install ngrok
ngrok http 8080
```
- Configure GitHub webhook using the `ngrok` public URL.
- Enable **GitHub SCM polling** in Jenkins.

### Final Output
Run the following command to test the application:
```sh
docker exec -it scientific_calculator java -jar app.jar
```
This completes the CI/CD pipeline setup for the Scientific Calculator project.

