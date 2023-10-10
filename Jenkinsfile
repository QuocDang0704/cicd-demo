pipeline {

    agent any

    tools { 
        maven 'my-maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
    }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Packaging/Pushing image') {
            steps {
                script {
                    def dockerImage = 'dangquoc0704/springboot' // Thay đổi tên image Docker của bạn
                    def registryCredentials = 'dockerhub' // Thay đổi tên thông tin đăng nhập trong Jenkins

                    // Đăng nhập vào Docker Registry
                    withDockerRegistry(credentialsId: registryCredentials, url: 'https://index.docker.io/v1/') {
                        // Xây dựng image
                        sh "docker build -t ${dockerImage} ."

                        // Đẩy image lên Docker Hub
                        sh "docker push ${dockerImage}"
                    }
                }
            }
        }


        // stage('Deploy MySQL to DEV') {
        //     steps {
        //         echo 'Deploying and cleaning'
        //         sh 'docker image pull mysql:8.0'
        //         sh 'docker network create dev || echo "this network exists"'
        //         sh 'docker container stop khalid-mysql || echo "this container does not exist" '
        //         sh 'echo y | docker container prune '
        //         sh 'docker volume rm khalid-mysql-data || echo "no volume"'

        //         sh "docker run --name khalid-mysql --rm --network dev -v khalid-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
        //         sh 'sleep 20'
        //         sh "docker exec -i khalid-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
        //     }
        // }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull dangquoc0704/springboot'
                sh 'docker container stop dangquoc0704-springboot || echo "this container does not exist" '
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name dangquoc0704-springboot -p 8081:8080 --network dev dangquoc0704/springboot'
            }
        }
 
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}