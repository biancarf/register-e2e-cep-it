node {

    stage('Clone repository') {
        checkout scm
    }

    stage('Docker Setup') {
        parallel (
            "Start Compose": {
                sh "docker-compose up -d --scale chrome=5"
            }
	    )
    }

        stage('Execute') {
                sh "java -version"
                sh "mvn clean install"
        }

        stage('Docker Teardown') {
        parallel(
          "Stop Compose": {
                /* Tear down docker compose */
            sh "docker-compose down --rmi local"
          },
          "Remove Image": {
            /* Delete the image which got created earlier */
            sh "docker rmi pytest-with-src -f"
          }
	    )
    }
}

