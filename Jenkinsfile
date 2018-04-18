pipeline {
  agent any

  server = Artifactory.server 'Artifactory'
  downloadSpec = """ {
      "files": [
          {
              "pattern": "libs-release-local/com/pts6/common/*.jar",
              "target": "common"
          }
      ]
  }"""

  uploadSpec = """ {
      "files": [
          {
              "pattern": "target/*.jar",
              "target": "libs-release-local/"
          }
      ]
  }"""

  stages {
    stage('Build') {
        steps {
          withMaven(
            maven: 'Maven 3.5.3',
            mavenSettingsConfig: 'maven_artifactory'
          ) {
                sh 'mvn clean install package -P development'
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
            }
        }
    }
    stage ('Deploy') {
        steps {
            withMaven(
                maven: 'Maven 3.5.3',
                mavenSettingsConfig: 'maven_artifactory'
            ) {

                server.upload(uploadSpec)
            }
        }
    }
  }
}
