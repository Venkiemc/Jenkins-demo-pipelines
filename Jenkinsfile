pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9' // Use the label you configured in Jenkins
        jdk 'JDK 21'         // This should match Jenkins tool config
    }

    environment {
        SONARQUBE_SERVER = 'My SonarQube Server'
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
