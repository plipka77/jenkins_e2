pipeline {
    agent any
    stages {
        stage('Package') {
            steps {
                withMaven {
                    sh 'mvn package -DskipTests'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t plipka07/jenkins_e2 --build-arg JAR_FILE=target/*.jar .'
            }
        }
        stage('Run') {
            steps {
                sh 'docker run --name jenkins_e2 -p 8080:8080 --network e2 plipka07/jenkins_e2'
            }
        }
    }
}