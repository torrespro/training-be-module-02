1. Copy camel-cmis-uploader from training-modules into exercises-environment/services/

2. Open pom.xml from exercises-environment/services/
	 Add <module>camel-cmis-uploader</module> into  <modules> section
```
	<modules>
        <module>camel-player-management</module>
	    <module>contentservices-validator</module>
        <module>camel-cmis-uploader</module>
    </modules>
```
	Re-Compile exercises-environment/services/ (mvn clean install / Make sure the Training Server is running)

3. Rename the file backbase-mashup-service.xml -> basckbase-integration-service.xml
	
4. Open the recently renamed file and search for:

```
    <routeContext id="com.backbase.mashup.service.cmis.cmisimporter">
```
and replace with

```
    <routeContext id="com.backbase.portal.integration.service.cmis.cmisimporter">
```

