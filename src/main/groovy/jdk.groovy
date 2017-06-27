import hudson.model.JDK
import hudson.model.Saveable
import hudson.tools.InstallSourceProperty
import hudson.tools.JDKInstaller
import hudson.tools.ToolInstallation
import hudson.tools.ToolProperty
import hudson.tools.ToolPropertyDescriptor
import hudson.util.DescribableList
import jenkins.model.Jenkins

JDK.DescriptorImpl jdkDesc = Jenkins.instance.getExtensionList(JDK.DescriptorImpl.class)[0]
JDKInstaller autoInstaller = new JDKInstaller("jdk-8u131", true)
autoInstaller.descriptor.doPostCredential("berzehk@gmail.com", "TuC3GuUYpy5u")
InstallSourceProperty isp = new InstallSourceProperty([autoInstaller])
DescribableList propertyList = new DescribableList<ToolProperty<? extends ToolInstallation>, ToolPropertyDescriptor>(Saveable.NOOP, [isp])
JDK installation = new JDK("jdk-8u131", "", propertyList)
jdkDesc.setInstallations(installation)
jdkDesc.save()