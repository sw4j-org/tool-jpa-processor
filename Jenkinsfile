pipeline {
  agent any
  stages {
    stage('Clean') {
      deleteDir()
    }
    stage('Checkout') {
      checkout scm
    }
  }
}
