FROM jenkins

USER root
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y sudo && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers

RUN apt-get update
RUN apt-get install git jq python-pip -y
RUN pip install awscli
RUN pip install boto3
RUN pip install virtualenv

USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY src/main/groovy/tz.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY src/main/groovy/slaves.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY src/main/groovy/security.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY src/main/groovy/executors.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY src/main/groovy/maven.groovy /usr/share/jenkins/ref/init.groovy.d/

COPY src/main/resources/plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

#COPY src/main/resources/gitconfig /var/jenkins_home/.gitconfig
#COPY src/main/resources/credentials /var/jenkins_home/.aws/credentials