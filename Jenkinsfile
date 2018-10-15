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
    stage('Build and Install') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn clean install"
        }
      }
    }
    stage('Build Reports') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/maven-repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn checkstyle:checkstyle"
          sh "mvn findbugs:findbugs"
          sh "mvn pmd:pmd"
        }
      }
    }
    stage('Publish Reports') {
      steps {
        checkstyle canComputeNew: false, pattern: '**/checkstyle-result.xml'
        findbugs canComputeNew: false, shouldDetectModules: true,
            pattern: '**/target/findbugsXml.xml'
        jacoco exclusionPattern: '**/jaxb/*.class'
        pmd canComputeNew: false, pattern: '**/pmd.xml'
      }
    }
  }
}
