pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                bat '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage ('run'){
        steps{
        bat 'java -jar EpamIntLabJavaBasics2-1.0-SNAPSHOT.jar'
        }
        }
    }
}