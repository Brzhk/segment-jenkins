import hudson.model.Saveable
import hudson.tasks.Maven
import hudson.tasks.Maven.MavenInstallation
import hudson.tools.InstallSourceProperty
import hudson.tools.ToolInstallation
import hudson.tools.ToolProperty
import hudson.tools.ToolPropertyDescriptor
import hudson.util.DescribableList
import jenkins.model.Jenkins

Maven.DescriptorImpl mavenDesc = Jenkins.instance.getExtensionList(Maven.DescriptorImpl.class)[0]

Maven.MavenInstaller autoInstaller = new Maven.MavenInstaller("3.5.0")
InstallSourceProperty isp = new InstallSourceProperty([autoInstaller])
isp.installers.add(autoInstaller)

DescribableList propertyList = new DescribableList<ToolProperty<? extends ToolInstallation>, ToolPropertyDescriptor>(Saveable.NOOP, [isp])

MavenInstallation installation = new MavenInstallation("maven-3.5.0", "", propertyList)

mavenDesc.setInstallations(installation)
mavenDesc.save()