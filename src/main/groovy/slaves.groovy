#!groovy

import jenkins.model.*
import hudson.security.*
import jenkins.install.*
import jenkins.slaves.JnlpSlaveAgentProtocol4

Jenkins instance = Jenkins.getInstance()

println "--> configuring slave management"

instance.setSlaveAgentPort(5000)
instance.setAgentProtocols([JnlpSlaveAgentProtocol4.name].toSet())

instance.save()

