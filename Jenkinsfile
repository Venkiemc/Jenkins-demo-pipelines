pipeline {
    agent any

    stages {
        stage('SetupWorkspace') {
            steps {
                echo 'Setting up workspace...'
                sh 'mkdir -p simple-ms-app/src/main/java/com/demo'

                // Create Java file
                sh '''
                cat > simple-ms-app/src/main/java/com/demo/App.java <<EOF
                package com.demo;
                public class App {
                    public static void main(String[] args) {
                        System.out.println("Hello from Master-Slave pipeline!");
                    }
                }
EOF
                '''

                // Create a pom.xml
                sh '''
                cat > simple-ms-app/pom.xml <<EOF
                <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.demo</groupId>
    <artifactId>simple-maven-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
            </plugin>
        </plugins>
    </build>
</project>
EOF
                '''
            }
        }

        stage('Maven Clean') {
            steps {
                sh 'cd simple-ms-app && mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                sh 'cd simple-ms-app && mvn compile'
            }
        }

        stage('Done') {
            steps {
                echo 'Pipeline steps completed!'
            }
        }
    }
}
