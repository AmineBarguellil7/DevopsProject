pipeline {
    agent {
        label "ubuntu-agent"
    }
    environment {
        SONAR_TOKEN = '245e11b6ee8c2814c31821231545b5701f3c6131'
        registry = "aminebarguellil/skiapp" 
        DOCKER_IMAGE_TAG = "latest"
    }
    
    stages {
        
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'amine', 
                    url: 'https://github.com/rimbsaad/DevopsProject.git',
                    credentialsId: 'Git-Credentials'; 
            }
        }
        
        
        stage("Unit Testing") {
            steps {
                sh "mvn clean test -Ptest"
            }
        }
        
        stage("SRC analysis Testing") {
            steps {
                sh "mvn verify sonar:sonar -Dsonar.token=${SONAR_TOKEN}"
            }
        }
        
        stage("Build Package") {
            steps {
                sh "mvn clean package -Pprod"
            }
        }
        
        stage("Build Docker Image") {
            steps {
                sh "sudo docker build -t ${registry}:${DOCKER_IMAGE_TAG} ."
            }
        }
        
        stage("Deploy artifact to nexus") {
            steps {
                sh "mvn deploy -Pprod"
            }
        }
        
        stage("Deploy Image to Nexus") {
            steps {
                sh "sudo docker login -u admin -p nexus 192.168.33.10:8083"
                sh "sudo  docker tag aminebarguellil/skiapp  192.168.33.10:8083/repository/docker-repo/ski_app:latest"
                sh "sudo docker push 192.168.33.10:8083/repository/docker-repo/ski_app:latest"
            }
        }
        
        
        stage("Start Containers") {
            steps {
                sh "sudo docker run --name mysqldb --network mynetwork -e MYSQL_ROOT_PASSWORD=root -v /home/mysql/data:/var/lib/mysql -d mysql:8"
                sh "sudo docker run -p 9092:9092 --network mynetwork -d ${registry}"
            }
        }
        
    }
}
