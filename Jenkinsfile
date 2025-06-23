pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'
    }
    
    environment {
        IMAGE_NAME = 'dockerhub-username/your-app'
        EC2_HOST = 'ec2-xy-.comput-1.amazonaws.com'
    }
    
    triggers {
        githubPush()
    }
    
    stages {
        stage('Checkout') {
            steps {
                git 'path-to-git-repo'
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
        
        stage('Sonarqube Analysis') {
            steps {
                withSonarQubeEnv('MySonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectkey=myapp'
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${IMAGE_NAME}:%{BUILD_NUMBER}")
                }
            }
        }
        
        stage('Push to Dockerhub') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub-creds') {
                        docker.image("${IMAGE_NAME}:%{BUILD_NUMBER}").push()
                    }
                }
            }
        }
        
        stage('Deploy to AWS EC2') {
            steps {
                sshagent(credentials: ['aws-ec2-ssh']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ec2-user@${EC2_HOST} << EOF
                    docker pull ${IMAGE_NAME}:${BUILD_NUMBER}
                    docker stop myapp || true
                    docker rm myapp || true
                    docker run -d -p 80:8080 --name myapp ${IMAGE_NAME}:${BUILD_NUMBER}
                EOF
                """
                }
            }
        }
        
    }
    
}