// Laboratorio DevOps - UNINPAHU - Semana 2
// Pipeline declarativo: Checkout (GitLab) -> Build -> Test -> Reporte JUnit
//
// Requiere:
//  - Credencial en Jenkins de tipo "Username with password" (o "GitLab API token")
//    con id: gitlab-credentials
//  - Plugin "GitLab" y "Pipeline: Stage View" instalados en Jenkins
//  - Docker disponible en el host (para el agente Maven efimero)

pipeline {

    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2' // cachea dependencias entre builds
        }
    }

    options {
        gitLabConnection('gitlab-connection') // nombre configurado en Manage Jenkins > System
        timestamps()
    }

    triggers {
        // Alternativa si no se configura Webhook desde GitLab:
        // revisa el repo cada 5 minutos buscando commits nuevos
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Clonando repositorio desde GitLab...'
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
                echo 'Ejecutando pruebas unitarias (JUnit)...'
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
                echo 'Empaquetando artefacto .jar...'
                sh 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            updateGitlabCommitStatus name: 'jenkins-ci', state: 'success'
            echo 'Pipeline finalizado correctamente.'
        }
        failure {
            updateGitlabCommitStatus name: 'jenkins-ci', state: 'failed'
            echo 'El pipeline fallo. Revisa el reporte de pruebas.'
        }
    }
}
