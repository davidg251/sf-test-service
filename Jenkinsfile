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
             }

            when {
                branch 'master'
            }

            steps {
                
                withMaven(maven: 'maven', jdk: 'jdk') {
                    sh(" mvn clean package")
                }
                
                sh """${5}(aws ecr get-login --no-include-email --region ${REGION})")
                make dbuild
                make dtag
                make dpush"""
            }
        }
    }
}