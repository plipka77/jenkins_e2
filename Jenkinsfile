pipeline {
    agent any
    stages {
        stage("Clean-up") {
            steps {
                sh 'docker stop mysql_e2 || true'
                sh 'docker rm  mysql_e2 || true'
                sh 'docker stop jenkins_e2 || true'
                sh 'docker rm jenkins_e2 || true'
            }
        }
        stage('Test/Package') {
            steps {
                withMaven {
                    sh 'mvn install'
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
                sh '''docker run --name mysql_e2 \
                    -p 3309:3306 \
                    -e MYSQL_ROOT_PASSWORD=Hello.WORLD88* \
                    -e MYSQL_DATABASE=e2 \
                    -e MYSQL_USER=dev \
                    -e MYSQL_PASSWORD=Hello.WORLD88* \
                    -v /var/lib/jenkins/jenkins_e2_mysql:/var/lib/mysql \
                    --network e2 -d \
                    mysql'''
                sh 'scripts/db_test.sh'
            }
        }
        stage('Run') {
            steps {
                sh 'docker run --name jenkins_e2 -p 8088:8088 --network e2 -d plipka07/jenkins_e2'
            }
        }
    }
}