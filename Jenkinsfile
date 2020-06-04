pipeline{
    agent any
    stages{
        
        stage ('Build Backend'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        
        stage ('Unit Test'){
            steps{
                bat 'mvn test'
            }
        }

        stage('Sonar Analisys'){
            environment{
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL'){
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000  -Dsonar.login=06c427ae28f9f9b5e4990b1f1fbfc9c2efd9623a -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/scr/test/**,**/model/**,**Application.java"     
                }
            }
        }
        stage('Quality Gate'){
            steps{
                sleep(5)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline:true
                }
            }
        }

        stage('Deploy BackEnd'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'TomCat_Login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        
        stage('Test API'){
            steps{
                dir('api-test'){
                    git credentialsId: 'DanFonseca_GIT', url: 'https://github.com/DanFonseca/api-test '
                    bat 'mvn test'
                }
            }
        }

        stage('Deploy FrontEnd'){
            steps{
                dir('frontend'){
                    git credentialsId: 'DanFonseca_GIT', url: 'https://github.com/DanFonseca/tasks-frontend.git'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomCat_Login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-frontend', war: 'target/tasks.war'
                }
            }
        }

        stage('Functional Tests'){
            steps{
                dir('functional-test'){
                    git credentialsId: 'DanFonseca_GIT', url: 'https://github.com/DanFonseca/test-funcional-selinium'
                    bat 'mvn test'
                }
            }
        }
        stage ('Deploy Prod'){
            steps{
                bat 'docker-compose up -d'
            }
        }
    }
}

