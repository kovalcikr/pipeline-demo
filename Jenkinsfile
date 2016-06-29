stage 'start'
echo 'test'

stage 'compile'
node {
    git url: 'https://github.com/kovalcikr/pipeline-demo.git'
    def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    sh 'mvn clean verify -DskipTests'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
}

stage 'test'
node {
    git url: 'https://github.com/kovalcikr/pipeline-demo.git'
    def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    sh 'mvn clean verify -Dmaven.test.failure.ignore'
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
}
