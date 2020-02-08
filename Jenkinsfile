node {

    def app
    def mvnHome

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */
        checkout scm

        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool name: 'MAVEN_HOME', type: 'maven'
    }

    stage('Build') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }

    stage('Build image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        app = docker.build("habeebcycle/devopstest:${env.BUILD_NUMBER}")
    }

    stage('Test image') {
        /* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) */
        if (isUnix()) {
            sh 'echo "Skipping this stage"'
        }else{
            bat("echo 'Skipping this stage'")
        }
        /*
        app.inside {
            if (isUnix()) {
                sh 'echo "Tests passed"'
            }else{
                bat("echo 'Tests Passed'")
            }
        }
        */
    }

    stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'id_jenkins_dockerhub') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
        }
    }

    stage ('Run Application') {
        try {
            // Start database container here
            // sh 'docker run -d --name db -p 8091-8093:8091-8093 -p 11210:11210 arungupta/oreilly-couchbase:latest'

            // Run application using Docker image
            //sh "DB=`docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' db`"
            //sh "docker run -e DB_URI=$DB habeebcycle/devopstest:${env.BUILD_NUMBER}"

            // First try to stop existing container with the same name
            try{
                if (isUnix()) {
                    sh "docker stop api-jenkins-docker "
                }else{
                    bat("docker stop api-jenkins-docker ")
                }
            }catch (error){}

            if (isUnix()) {
                sh "docker run -d --rm --name api-jenkins-docker -p 8090:8090 habeebcycle/devopstest:${env.BUILD_NUMBER}"
            }else{
                bat("docker run -d --rm --name api-jenkins-docker -p 8090:8090 habeebcycle/devopstest:${env.BUILD_NUMBER}")
            }
            // Run tests using Maven
            //dir ('webapp') {
            //  sh 'mvn exec:java -DskipTests'
            //}
        } catch (error) {
        } finally {
            // Stop and remove database container here
            //sh 'docker-compose stop db'
            //sh 'docker-compose rm db'
        }
    }
}