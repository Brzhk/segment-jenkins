import hudson.model.Saveable
import org.jenkinsci.plugins.docker.commons.tools.DockerTool
import hudson.tools.InstallSourceProperty
import hudson.tools.ToolInstallation
import hudson.tools.ToolProperty
import hudson.tools.ToolPropertyDescriptor
import hudson.util.DescribableList
import jenkins.model.Jenkins
import org.jenkinsci.plugins.docker.commons.tools.DockerToolInstaller

Docker.DescriptorImpl dockerDesc = Jenkins.instance.getExtensionList(Docker.DescriptorImpl.class)[0]

DockerToolInstaller autoInstaller = new DockerToolInstaller("3.5.0")
InstallSourceProperty isp = new InstallSourceProperty([autoInstaller])
isp.installers.add(autoInstaller)

DescribableList propertyList = new DescribableList<ToolProperty<? extends ToolInstallation>, ToolPropertyDescriptor>(Saveable.NOOP, [isp])

DockerTool installation = new DockerTool("docker-3.5.0", "", propertyList)

dockerDesc.setInstallations(installation)
dockerDesc.save()