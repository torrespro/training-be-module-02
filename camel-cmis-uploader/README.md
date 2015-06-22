# Backbase Training Exercises

## Camel CMIS Uploader 
In this tutorial you will develop a Camel based component which listens to the specified directory on file system and automatically uploads file copied to this directory to the Content Services repository.

### Installation & Configuration

- **Copy camel-cmis-uploader from training-modules into the services folder of your Launchpad 0.12.x project.**

- **Include camel-cmis-uploader module to the build.** Open `pom.xml` from *exercises-environment/services/*. Add `<module>camel-cmis-uploader</module>` into  `<modules>` section
	```xml
	    <modules>
	        ...	    
	        <module>camel-cmis-uploader</module>
	        ...
	    </modules>
	```	
	Re-compile *exercises-environment/services/* executing `mvn clean install` command.
	
- **Enable newly created module in Portal application.** Add the following dependency to your `portal/pom.xml` file in `<dependencies>` section:

	```xml
	    <dependency>
	        <groupId>com.backbase.expert.training</groupId>
	        <artifactId>camel-cmis-uploader</artifactId>
	        <version>1.0-SNAPSHOT</version>
	    </dependency>
	```

- **Configure module properties.** Edit `configuration/backbase.properties` file by addition of the following property specifying path to the file system directory which will be monitored for file system operations. 
    `training.services.cmis.import.dir=absolute_path_to_monitored_folder`
Re-complie configuration module by running `mvn clean install` command from *configuration* module.     

- **Configure logging (optional).** Add the following line to your *logback.xml*
	```xml
	    <logger name="com.backbase.training" level="DEBUG"/>
	```

### Build & Run

- Build Portal module with executing `mvn clean install` command from *portal* directory.
- Start Portal application with executing `mvn jetty:run` command from *portal* directory.
- Start Content Services application with executing `mvn jetty:run` command from *contentservices* directory.
- Place some file to the folder configured for monitoring. 
- Open CXP Manager Assets and make sure that newly uploaded file appears there.
