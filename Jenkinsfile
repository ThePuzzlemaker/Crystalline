pipeline {
	agent any
	stages {
		stage('Build') {
			steps {
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
}
