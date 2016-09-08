stage 'start'
echo 'test'

stage 'compile'
node {
    checkout scm
    def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
    env.PATH = "${mvnHome}/bin:${env.PATH}"
    sh 'mvn --batch-mode -V -U -e -Dmaven.test.failure.ignore=true clean verify -DskipTests'
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
}

stage 'test'
parallel firstBranch: {
    node {
        checkout scm
        def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
        env.PATH = "${mvnHome}/bin:${env.PATH}"
        try {
            sh 'mvn --batch-mode -V -U -e -Dmaven.test.failure.ignore=true clean verify -fae'
        } finally {
            step([$class: 'JUnitResultArchiver', keepLongStdio: true, testResults: '**/target/surefire-reports/TEST-*.xml'])
        }
    }
}, secondBranch: {
    node {
        checkout scm
        def mvnHome = tool name: 'maven-3.3.9', type: 'hudson.tasks.Maven$MavenInstallation'
        env.PATH = "${mvnHome}/bin:${env.PATH}"
        try {
            sh 'mvn --batch-mode -V -U -e -Dmaven.test.failure.ignore=true clean verify -fae'
        } finally {
            step([$class: 'JUnitResultArchiver', keepLongStdio: true, testResults: '**/target/surefire-reports/TEST-*.xml'])
        }
    }
},
failFast: false
