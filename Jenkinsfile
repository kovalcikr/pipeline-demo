stage 'start'
echo 'test'

stage 'compile'
node {
    checkout scm
    def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    sh 'mvn clean verify -DskipTests'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
}

stage 'test'
node {
    checkout scm
    def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    def a = sh 'mvn clean verify'
    echo "Status: $a"
    step([$class: 'JUnitResultArchiver', keepLongStdio: true, testResults: '**/target/surefire-reports/TEST-*.xml'])
}
