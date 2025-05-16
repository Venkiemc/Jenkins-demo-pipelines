pipeline {
    agent any
    
    stages {
        stage('SCM') {
            steps {
                git branch: 'webhookdemo', url: 'https://github.com/Venkiemc/Jenkins-demo-pipelines.git'
            }
        }

        stage('Build and Unit Test') {
            steps {
                bat 'mvn clean package'  // This will generate the JAR and run tests
            }
        }
        stage('Done') {
            steps {
                echo 'Pipeline steps completed!'
            }
        }
    }
}
