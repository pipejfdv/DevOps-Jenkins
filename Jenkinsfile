// Laboratorio DevOps - UNINPAHU - Semanas 5 y 6
// Pipeline: Checkout -> Build -> Test -> Package -> Build Image -> Deploy -> Health Check
pipeline {
    agent any
    tools { maven 'Maven-3.9' }
    options {
        skipDefaultCheckout(true)
        timestamps()
        disableConcurrentBuilds()
    }
    triggers {
        // Jenkins revisa GitHub cada 5 minutos buscando nuevos commits.
        pollSCM('H/5 * * * *')
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Clonando repositorio...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Compilando el proyecto...'
                sh 'mvn -B clean compile'
            }
        }
        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias con JUnit...'
                sh 'mvn -B test'
            }
            post {
                always { junit 'target/surefire-reports/*.xml' }
            }
        }
        stage('Package') {
            steps {
                echo 'Empaquetando el archivo JAR...'
                sh 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        stage('Build Image') {
            steps {
                echo 'Construyendo imagen Docker...'
                sh 'docker build -t calculadora-ci:${BUILD_NUMBER} .'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Desplegando contenedor...'
                sh 'docker rm -f calculadora-app || true'
                sh "docker run -d --name calculadora-app -p 8081:8080 calculadora-ci:${env.BUILD_NUMBER}"
            }
        }
        stage('Health Check') {
            steps {
                echo 'Verificando salud del despliegue...'
                sh 'sleep 8'
                sh 'curl -f http://localhost:8081/salud || curl -f http://host.docker.internal:8081/salud'
            }
        }
    }
    post {
        success {
            echo 'Pipeline finalizado correctamente - 7 etapas en verde.'
        }
        failure {
            echo 'Pipeline falló. Revisa Stage View y Console Output.'
        }
    }
}
