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
        stage('Network Initialization') {
            steps {
                sh 'docker network create e2 || true'
            }
        }
        stage('DB Initialization') {
            steps {
                sh 'docker stop mysql_e2 || true'
                sh 'docker rm  mysql_e2 || true'
                sh '''docker run --name mysql_e2 \
                    -e MYSQL_ROOT_PASSWORD=Boomersooner7 \
                    -e MYSQL_DATABASE=e2 \
                    -e MYSQL_USER=dev \
                    -e MYSQL_PASSWORD=Boomersooner7 \
                    --network e2 -d \
                    mysql'''
            }
        }
        stage('Run') {
            steps {
                sh 'docker stop jenkins_e2 || true'
                sh 'docker rm jenkins_e2 || true'
                sh 'docker run --name jenkins_e2 -p 8088:8088 --network e2 plipka07/jenkins_e2'
            }
        }
    }
}