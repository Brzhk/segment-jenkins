import jenkins.model.*

Jenkins.instance.setNumExecutors(5)
Jenkins.instance.save()
System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', 'America/New_York')