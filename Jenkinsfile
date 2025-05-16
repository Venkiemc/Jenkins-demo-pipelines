pipeline {
    agent any

    tools {
        maven "maven3.9.9"
        jdk "JDK21"
    }

    stages {
        stage('SCM') {
            steps {
                git branch: 'main', url: 'https://github.com/Venkiemc/sonarqube-jenkins-project.git'
            }
        }

        stage('UNIT TEST') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Checkstyle Analysis') {
            steps {
                bat 'mvn checkstyle:checkstyle'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Now Archiving it...'
                archiveArtifacts artifacts: '**/target/*.jar'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
