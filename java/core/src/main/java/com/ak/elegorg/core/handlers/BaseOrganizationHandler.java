/**
 * 
 */
package com.ak.elegorg.core.handlers;

import java.io.File;

import com.ak.elegorg.core.properties.OrganizingProperties;

/**
 * @author Anil Kumar Yanamandra
 *
 */
public interface BaseOrganizationHandler {
	
	public String getNewFileLocation(File f, OrganizingProperties p);
	public boolean canBeHandled(File f, OrganizingProperties p);

}
