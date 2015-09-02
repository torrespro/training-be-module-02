package com.backbase.portal.contentservices.validator.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.data.Properties;
import org.apache.chemistry.opencmis.commons.definitions.TypeDefinition;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNotSupportedException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backbase.portal.contentservices.validator.RepositoryValidator;

/**
 * We can use this class to check all CMIS object that we have defined.
 * Before each CRUD operation you can add some logic to check your object
 * */
public class CustomValidator implements RepositoryValidator {

	private static final Logger LOG = LoggerFactory.getLogger(CustomValidator.class);

	private static final String BB_TYPE_DEFINITON = "bb:image";

	private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<String>(Arrays.asList(".jpg", ".jpeg", ".png"));
	private static final Set<String> ALLOWED_MIMETYPES = new HashSet<String>(Arrays.asList("image/jpeg", "image/pjpeg", "image/png"));

	@Override
	public void validateCreate(TypeDefinition typeDefinition,Properties properties, ContentStream contentStream)	throws CmisBaseException {
		validateImageExtension(typeDefinition, contentStream);
	}

	@Override
	public void validateDelete(TypeDefinition typeDefinition) throws CmisBaseException {

	}

	@Override
	public void validateMove(TypeDefinition typeDefinition)	throws CmisBaseException {

	}

	@Override
	public void validateUpdateContentStream(TypeDefinition typeDefinition,	ContentStream contentStream) throws CmisBaseException {
		validateImageExtension(typeDefinition, contentStream);
	}

	@Override
	public void validateUpdateProperties(TypeDefinition typeDefinition,	Properties properties) throws CmisBaseException {

	}

	/** 								
	 * This method verifies the image that has been uploaded as a type Definition of bb:image , defined extensions and stream's length
	 * 
	 * @param typeDefinition
	 * @param contentStream
	 * */
	protected void validateImageExtension(TypeDefinition typeDefinition, ContentStream contentStream) {
		if (StringUtils.isNotBlank(typeDefinition.getId())
				&& typeDefinition.getId().equals(BB_TYPE_DEFINITON)
				&& (contentStream != null) && (contentStream.getLength() > 0L)
				&& (!isAllowedExtensions(contentStream))) {
			LOG.debug("Image has an invalid extensions");
			throw new CmisNotSupportedException("Document with type definition " + typeDefinition.getDisplayName()+ " has an invalid extensions");
		}

	}

	/** 
	 * Check if contentStream is upload will have valid extensions
	 * We have predefined set of allowed extensions.
	 * 
	 * @param contentStream
	 * */
	protected boolean isAllowedExtensions(ContentStream contentStream) {
		boolean result = false;
		Iterator<String> iterator = ALLOWED_EXTENSIONS.iterator();
        if (contentStream.getFileName() != null) {
            while (iterator.hasNext()) {
                String element = iterator.next();
                if (contentStream.getFileName().endsWith(element)) {
                    result = true;
                    break;
                }
            }
        } else {
            if(ALLOWED_MIMETYPES.contains(contentStream.getMimeType())) {
                result = true;
            }
        }
        return result;
	}

}
