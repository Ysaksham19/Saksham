pipeline {
  agent any

  stages {
    stage('Install Dependencies') {
      steps {
        bat 'cd client-app && yarn install'
      }
    }
    stage('Build and Test') {
      steps {
        bat 'cd client-app && yarn test'
      }
    }
  }
}
