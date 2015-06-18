1. Copy contentservices-validator from training-modules into exercises-environment/services/

2. Open pom.xml from exercises-environment/services/
	 Add <module>contentservices-validator</module> into  <modules> section
```
	<modules>
        <module>camel-player-management</module>
	    <module>contentservices-validator</module>
    </modules>
```

	Re-Compile exercises-environment/services/ (mvn clean install / Make sure the Training Server is running)

3. You can find backbase.propertise on following path exercises-environment/configuration/src/main/resources
	Open this file and at the end of it add lines:
	
	# Validators (comma separated, order is important)
	contentservices.validators=com.backbase.portal.contentservices.validator.impl.RepositorySchemaValidator,com.backbase.portal.contentservices.validator.impl.CustomValidator

	Re-Compile exercises-environment/configuration	(mvn clean install)
	
4. Open pom.xml from exercises-environment/contentservices/

	<!-- Statics sub-modules-->
	<dependency>
		<groupId>com.backbase.expert.training</groupId>
		<artifactId>contentservices-validator</artifactId>
		<version>1.0-SNAPSHOT</version>
	</dependency>
	
	Open web.xml from exercises-environment/contentservices/
	
	find value:
	
	<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            classpath:/META-INF/spring/bb-contentservices.xml
        </param-value>
    </context-param>
	
	Extend with:
	
   <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value><![CDATA[
            classpath:/META-INF/spring/bb-contentservices.xml
            classpath:/META-INF/spring/bb-validators.xml
        ]]></param-value>
    </context-param>


	
	
	
	

