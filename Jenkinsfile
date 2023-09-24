pipeline {
    agent any 

    stages {
        stage('Checkout') {
            steps {
                // Get the code from the version control system.
                checkout scm
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t ognjen/pet-clinic:latest .'
                }
            }
        }
        
        stage('Docker Hub Login and Push') {
            environment {
                DOCKER_CREDS = credentials('febc0000-09f6-4a73-b268-e9248549f51e')
            }
            steps {
                script {
                    // Login to Docker Hub
                    sh 'echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin'
                    
                    // Push the Docker image to Docker Hub
                    sh 'docker push ognjen/pet-clinic:latest'
                }
            }
        }
    }
}
