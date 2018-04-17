pipeline {
  agent any
  stages {
    stage('Build') {
        steps {
          withMaven(
            maven: 'Maven 3.5.3',
            mavenSettingsConfig: 'maven_artifactory'
          ) {
                sh 'mvn clean install package -P development'
                input message: 'Please check if build has succeeded'
          }
      }
    }
    stage ('Test') {
        steps {
            withMaven(
                maven: 'Maven 3.5.3',
                mavenSettingsConfig: 'maven_artifactory'
            ) {
                sh 'mvn test -P development'
                input message: 'Please check if test have executed successfully'
            }
        }
    }
    stage ('Analyze') {
        steps {
            withMaven(
                maven: 'Maven 3.5.3',
                mavenSettingsConfig: 'maven_artifactory'
            ) {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://85.144.215.28:9001 -Dsonar.login=089ecbe71f30a12f9af77098b09921b83cf88786'
                input message: 'Please check the Analyze report in Sonarqube'
            }
        }
    }
    stage ('Deploy') {
        steps {
            withMaven(
                maven: 'Maven 3.5.3',
                mavenSettingsConfig: 'maven_artifactory'
            ) {
                input message: 'Please download the .jar file from the Artifacts-tab and deploy it to Artifactory'
            }
        }
    }
  }
}
