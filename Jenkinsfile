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
                script {
                    def DOCKER_IMAGE_NAME = 'mohitmarfatia/scientific-calculator'
                    echo "Building Docker Image: ${DOCKER_IMAGE_NAME}"
                    sudo docker.build("${DOCKER_IMAGE_NAME}", '.')
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    def DOCKER_IMAGE_NAME = 'mohitmarfatia/scientific-calculator'
                    echo "Building Docker Image: ${DOCKER_IMAGE_NAME}"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | sudo docker login -u $DOCKER_USER --password-stdin"
                        sh "sudo docker tag ${DOCKER_IMAGE_NAME} ${DOCKER_IMAGE_NAME}:latest"
                        sh "sudo docker push ${DOCKER_IMAGE_NAME}:latest"
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
