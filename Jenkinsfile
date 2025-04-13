pipeline {
    agent any
    tools {
        jdk 'JDK17'  // Changed to JDK17 to match your Java 17.0.12
        maven 'Maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/AbhishekBawa25/GoogleJenkins.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
                }
            }
        }
    }
}