pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building..'
                bat './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
                bat './gradlew test'
                bat './gradlew jacocoTestReport'
                bat './gradlew --info sonarqube'

                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'build/reports/tests/test',
                    reportFiles: 'index.html',
                    reportName: 'JUnit Report'
                  ]

                 // Publish the Java Code Coverage Report
                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'build/jacocoHtml',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Report'
                  ]                             
            }
        }
            
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                bat './gradlew jar'
            }
        }
    }
    post {
            always {
                echo 'Succesfully'
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                junit 'build/test-results/**/*.xml'
            }
        }
}
