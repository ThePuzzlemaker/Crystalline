pipeline {
	agent any
	stages {
		stage('Build') {
			steps {
			    sh 'chmod +x version.sh'
			    sh './version.sh'
				sh 'chmod +x gradlew'
				sh './gradlew setupCiWorkspace clean build apiJar'
			}
		}
		stage('Deploy') {
			steps {
				archive 'build/libs/*jar'
			}
		}
	}
	post {
		success {
			withCredentials([string(credentialsId: 'DC-Webhook-URL', variable: 'DC_URL')]) {
				discordSend description: "Build #${env.BUILD_NUMBER} succeded!", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: env.DC_URL
			}
		}
		failure {
			withCredentials([string(credentialsId: 'DC-Webhook-URL', variable: 'DC_URL')]) {
				discordSend description: "Build #${env.BUILD_NUMBER} failed!", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: env.DC_URL
			}
		}
	}
}
