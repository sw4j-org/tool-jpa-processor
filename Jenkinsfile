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
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn clean compile"
        }
      }
    }
    stage('Test') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn test"
        }
      }
    }
    stage('Integration Test') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn verify"
        }
      }
    }
  }
}
