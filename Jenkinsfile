pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
                sh 'ls -la' 
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                sh 'cd ScientificCalculator && mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running JUnit tests...'
                sh 'cd ScientificCalculator && mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image...'
                sh 'docker build -t $DOCKER_IMAGE:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker Image to Docker Hub...'
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker push $DOCKER_IMAGE:latest'
                }
            }
        }

    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
    }
}
