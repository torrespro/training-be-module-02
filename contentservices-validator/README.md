# Backbase Training Exercises

## Custom Content Services Validator 
In this tutorial you will develop custom Content Services validator which allows to upload files of `bb:image` document type having *.png,.jpg or .jpeg* extension.

### Installation & Configuration


### Build & Run

- **Copy contentservices-validator from training-modules into *exercises-environment/services/*.**

- **Include contentservices-validator module to the build.** Open `pom.xml` from *exercises-environment/services/*. Add `<module>contentservices-validator</module>` into  `<modules>` section
	```xml
	    <modules>
	        ...	    
	        <module>contentservices-validator</module>
	        ...
	    </modules>
	```	
	Re-compile *exercises-environment/services/* executing `mvn clean install` command.

- **Add newly created module into Content Services application.** Add the following dependency to your `contentservices/pom.xml` file in `<dependencies>` section:

	```xml
	    <dependency>
	        <groupId>com.backbase.expert.training</groupId>
	        <artifactId>contentservices-validator</artifactId>
	        <version>1.0-SNAPSHOT</version>
	    </dependency>
	```

	Copy `web.xml` file from `contentservices/target/contentservices/WEB-INF/web.xml` to `contentservices/src/main/webapp/WEB-INF` directory and change following section:
	
	```
		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>
				classpath:/META-INF/spring/bb-contentservices.xml
				classpath:/META-INF/spring/bb-validators.xml
			</param-value>
		</context-param>
	```

	Re-complie Content Services application by running `mvn clean install` command from *contentservices* module.     


- **Register new validator in Content Services properties.** Edit `configuration/backbase.properties` file by addition of the following property specifying path to the file system directory which will be monitored for file system operations. 
    
    ```    
    # Validators (comma separated, order is important)
	contentservices.validators=com.backbase.portal.contentservices.validator.impl.RepositorySchemaValidator,com.backbase.portal.contentservices.validator.impl.CustomValidator
    ```
    Re-complie configuration module by running `mvn clean install` command from *configuration* module.    

### Build & Run

- Start Content Services application with executing `mvn jetty:run` command from *contentservices* directory.
- Try to upload to Content Services repository any `.txt` file using CMIS client and specifying document type with `bb:image`.
- Make sure upload will fail because of validation. 
	
	
	
	

