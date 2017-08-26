pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        deleteDir()
      }
    }
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      withMaven(jdk: 'Current JDK 8',
          maven: 'Current Maven 3',
          mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
        sh "mvn clean compile"
      }
    }
    stage('Test') {
      withMaven(jdk: 'Current JDK 8',
          maven: 'Current Maven 3',
          mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
        sh "mvn test"
      }
    }
    stage('Integration Test') {
      withMaven(jdk: 'Current JDK 8',
          maven: 'Current Maven 3',
          mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
        sh "mvn verify"
      }
    }
  }
}
