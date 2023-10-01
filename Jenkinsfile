pipeline {
    agent any 
    tools {
        maven "M3"
    }
    stages {
        stage('Compile and Clean') { 
            steps {
                // Run Maven on a Unix agent.
                sh "mvn clean compile"
            }
        }
        stage('deploy') { 
            steps {
                sh "mvn package"
            }
        }
    }
}
