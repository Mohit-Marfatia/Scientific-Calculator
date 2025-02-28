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
                    def DOCKER_IMAGE_NAME = 'mt2024090/scientific-calculator'
                    echo "Building Docker Image: ${DOCKER_IMAGE_NAME}"
                    sh "docker build -t ${DOCKER_IMAGE_NAME}:latest ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    def DOCKER_IMAGE_NAME = 'mt2024090/scientific-calculator'
                    echo "Pushing Docker Image: ${DOCKER_IMAGE_NAME}"

                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                        sh "docker tag ${DOCKER_IMAGE_NAME} ${DOCKER_IMAGE_NAME}:latest"
                        sh "docker push ${DOCKER_IMAGE_NAME}:latest"
                    }
                }
            }
        }
        stage('Run Ansible Playbook') {
            steps {
                script {
                    ansiblePlaybook(
                        playbook: 'deploy.yml',
                        inventory: 'inventory'
                    )
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
