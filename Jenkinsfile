pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6' // Adjust names as configured in Jenkins
        jdk 'JDK 11'
    }

    environment {
        SONARQUBE_SERVER = 'My SonarQube Server' // Jenkins configured name
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_SERVER}") {
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=sonarqube-jenkins-demo \
                          -Dsonar.host.url=http://your-sonarqube-server:9000 \
                          -Dsonar.login=your-token
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
