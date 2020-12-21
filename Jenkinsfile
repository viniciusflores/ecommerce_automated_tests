pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -DskipTests=true'
      }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
  }
}