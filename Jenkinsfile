pipeline {
    agent any 
    tools {
        maven "M3"
    }
    stages {
        stage('Compile and Clean') { 
            steps {
                // Run Maven to clean and compile.
                sh "mvn clean compile"
            }
        }
        stage('Package') { 
            steps {
                // Package the application.
                sh "mvn package"
            }
        }
        stage('Docker Compose') {
            steps {
                // Ensure docker-compose is installed.
                sh "which docker-compose || (echo 'docker-compose not found' && exit 1)"

                // Run docker-compose to start the application.
                sh "docker-compose up -d"
            }
        }
    }
}
