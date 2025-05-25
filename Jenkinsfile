pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1' // Jenkins global tool name
        jdk 'JDK 11'        // Jenkins global JDK name
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://your-repo-url.git', branch: 'main' // Replace with actual repo URL
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
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and tests succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
