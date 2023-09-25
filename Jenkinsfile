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
    }
}
