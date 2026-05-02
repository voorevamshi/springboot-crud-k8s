pipeline{
    agent any
     tools {
        maven 'maven' // or whatever tool you are using
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/voorevamshi/springboot-crud-k8s']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t vamshivoore/springboot-crud-k8s:v1 .'
                }
            }
        }
        stage('Push image docker hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'docker_hub_pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u vamshivoore -p ${dockerhubpwd}'
                  }
                  sh 'docker push vamshivoore/springboot-crud-k8s:v1'
                }
            }
        }
    }
}
