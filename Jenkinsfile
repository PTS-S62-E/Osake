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
                input message: 'Please check that the build has finished.'
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
                input message: 'Please check that all tests have passed'
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
                input message: 'Please check the report is Sonarqube'
            }
        }
    }
    stage ('Deploy') {
        steps {
            withMaven(
                maven: 'Maven 3.5.3',
                mavenSettingsConfig: 'maven_artifactory'
            ) {
                sh 'mvn deploy -Dusername=proftaak -Dpassword=proftaak -Dbuildnumber=$(($(date +%s) / 60 / 60 / 24))'
                input message: 'Please check if the application has been deployed to Artifactory'
            }
        }
    }
  }
}
