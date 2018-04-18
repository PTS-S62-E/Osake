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
                script {
                    server = Artifactory.server "Artifactory"
                    buildInfo = Artifactory.newBuildInfo()

                    def rtMaven = Artifactory.newMavenBuild()
                            rtMaven.tool = "Maven 3.5.3"
                            rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server
                            rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
                            rtMaven.run pom: 'pom.xml', goals: 'install', buildInfo: buildInfo

                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
  }
}
