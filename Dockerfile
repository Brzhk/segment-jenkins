FROM jenkins:latest

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

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt
COPY resources/gitconfig /var/jenkins_home/.gitconfig
COPY resources/credentials /var/jenkins_home/.aws/credentials