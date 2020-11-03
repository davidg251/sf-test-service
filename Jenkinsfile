#!/usr/bin/env groovy
pipeline {
    
    agent any

    environment {
        ACCOUNT = credentials("aws_account")
        ECR_REPO = 'demoback'
    }

    stages {

        stage("Prepare") {
            steps {
                checkout scm
            }
        }

        stage("Run unit tests") {
            steps {
                withMaven(maven: 'maven', jdk: 'jdk') {
                    sh("mvn clean test -U")
                }
            }
            
        }

        stage("Build and Publish to ECR") {
            
            environment {
                 REGION='us-east-2'
                 HELM_CHART_PATH='/root/sf-backend-0.1.0.tgz'
                 KUBECONFIG='root/.kube/config'
             }

            when {
                expression {
                    return env.GIT_BRANCH == "origin/master"
                }
            }

            steps {
                
                withMaven(maven: 'maven', jdk: 'jdk') {
                    sh(" mvn clean package")
                }
                
                sh '''
                #!/bin/bash
                $(aws ecr get-login --no-include-email --region ${REGION})
                make dbuild
                make dtag
                make dpush
                '''

                sh '''
                #!/bin/bash
                aws eks --region ${REGION} update-kubeconfig --name sf-eks-cluster
                helm upgrade sf-backend "${HELM_CHART_PATH}" --set image.tag="$(./version.sh)" --kubeconfig /root/.kube/config
                '''
            
            }
        }
    }
}