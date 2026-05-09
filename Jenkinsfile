pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                bat 'mvn.cmd clean'
            }
        }
        stage('Compile') {
            steps {
                bat 'mvn.cmd compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn.cmd test -Dmaven.test.failure.ignore=true'
            }
        }
        stage('PMD') {
            steps {
                bat 'mvn.cmd pmd:pmd'
            }
        }
        stage('JaCoCo') {
            steps {
                bat 'mvn.cmd jacoco:report'
            }
        }
        stage('Javadoc') {
            steps {
                bat 'mvn.cmd javadoc:javadoc'
            }
        }
        stage('Site') {
            steps {
                bat 'mvn.cmd site'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn.cmd package -DskipTests'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}