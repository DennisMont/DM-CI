pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building..'
                sh './gradlew clean build -p DM-CI'
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
                sh './gradlew test'
                sh './gradlew jacocoTestReport'

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
                sh './gradlew jar'
            }
        }
    }
    post {
        always {
            echo 'Succesfully'
            junit 'build/test-results/**/*.xml'
        }
    }
}
