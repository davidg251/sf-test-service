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
                expression {
                    return env.GIT_BRANCH == "origin/master"
                }
            }

            steps {
                
                withMaven(maven: 'maven', jdk: 'jdk') {
                    sh(" mvn clean package")
                }
                
                dockerauth = sh (script:'aws ecr get-login --no-include-email --region ${REGION}', returnStdOut:true).trim()
                sh(dockerauth)
                sh '''
                #!/bin/bash
                make dbuild
                make dtag
                make dpush'''
                
            }
        }
    }
}