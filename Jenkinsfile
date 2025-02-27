pipeline {
    agent any

    environment {
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        DOCKER_IMAGE = "mt2024090/scientific-calculator" 
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
                script {
                    docker.build("${DOCKER_IMAGE_NAME}", '.')
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub-credentials') {
                        sh "docker tag ${DOCKER_IMAGE_NAME} ${DOCKER_IMAGE_NAME}:latest"
                        sh "docker push ${DOCKER_IMAGE_NAME}:latest"
                    }
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
