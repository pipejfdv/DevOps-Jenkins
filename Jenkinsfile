// Laboratorio DevOps - UNINPAHU - Semana 2
// Pipeline declarativo: Checkout (GitHub) -> Build -> Test -> Package
 
pipeline {
 
    agent any
 
    tools {
        maven 'Maven-3.9'
    }
 
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
                echo 'Clonando repositorio público desde GitHub...'
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
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
 
        stage('Package') {
            steps {
                echo 'Empaquetando el archivo JAR...'
                sh 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }
    }
 
    post {
        success {
            echo 'Pipeline finalizado correctamente.'
        }
 
        failure {
            echo 'El pipeline falló. Revisa la consola y el reporte de pruebas.'
        }
    }
}
