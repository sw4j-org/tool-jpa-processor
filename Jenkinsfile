pipeline {
  agent any
  stages {
    stage('Set GitHub Status Start') {
      steps {
        step([$class: 'GitHubPRStatusBuilder',
         statusMessage: [content: '${GITHUB_PR_COND_REF} run started']])
      }
    }
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
    stage('Artifact Install (for Reports)') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn install"
        }
      }
    }
    stage('Build Reports') {
      steps {
        withMaven(jdk: 'Current JDK 7',
            maven: 'Current Maven 3',
            mavenLocalRepo: '${JENKINS_HOME}/repositories/${EXECUTOR_NUMBER}/') {
          sh "mvn checkstyle:checkstyle"
          sh "mvn findbugs:findbugs"
          sh "mvn pmd:pmd"
        }
      }
    }
    stage('Publish Reports') {
      steps {
        checkstyle canComputeNew: false, pattern: '**/checkstyle-result.xml'
        findbugs canComputeNew: false, pattern: '**/findbugsXml.xml'
        jacoco exclusionPattern: '**/jaxb/*.class'
        pmd canComputeNew: false, pattern: '**/pmd.xml',
      }
    }
    stage('Set GitHub Status End') {
      steps {
        step([$class: 'GitHubPRBuildStatusPublisher',
          buildMessage: [
            failureMsg: [content: 'Can\'t set status; build failed.'],
            successMsg: [content: 'Can\'t set status; build succeeded.']],
          errorHandler: [buildStatus: 'UNSTABLE'],
          statusMsg: [content: '${GITHUB_PR_COND_REF} run ended'],
          unstableAs: 'FAILURE'])
      }
    }
  }
}
