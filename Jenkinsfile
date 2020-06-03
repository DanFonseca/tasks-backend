pipeline{
    agent any
    stages{
        stage ('Build Backend'){
            steps{
                bat 'mvn clena package -DskipTests=true'
            }
        }
    }
}