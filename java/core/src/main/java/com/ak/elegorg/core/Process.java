package com.ak.elegorg.core;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.ak.elegorg.core.constants.ElegOrgConstants;
import com.ak.elegorg.core.handlers.BaseOrganizationHandler;
import com.ak.elegorg.core.handlers.JpegFileOrganizationHandler;
import com.ak.elegorg.core.handlers.MP4FileOrganizationHandler;
import com.ak.elegorg.core.properties.OrganizingProperties;

public class Process {

	public static void main(String[] ar) {
		OrganizingProperties props = new OrganizingProperties();

		if (ar != null && ar.length > 0) {
			props.setBaseDir(ar[0]);
		} else {
			System.out.println("Invalid parameters passed.");
			System.exit(1);
		}

		if (ar != null && ar.length > 1) {
			props.setDestinationBase(ar[1]);
		} else {
			System.out.println("Invalid Destination Address.");
			System.exit(1);
		}
		
		startProcess(props);



	}
	
	public static void startProcess(OrganizingProperties p) {
		
		File baseDir = new File(p.getBaseDir());
		Collection<File> files = FileUtils.listFiles(baseDir, p.getSupportedExtensions(), true);
		
		System.out.println("Start of Process : " + new java.util.Date().toString());
		System.out.println("Number of Files being Handled : "+ files.size());
		for (File file : files) {
			BaseOrganizationHandler handler = new JpegFileOrganizationHandler();
			String newFileLocation = handler.getNewFileLocation(file, p);
			if((ElegOrgConstants.isElegOrgConstants(ElegOrgConstants.NULL_STRING, newFileLocation))) {
				handler = new MP4FileOrganizationHandler();
				newFileLocation = handler.getNewFileLocation(file, p);
			}
			
			if(!(ElegOrgConstants.isElegOrgConstants(ElegOrgConstants.NULL_STRING, newFileLocation))) {
				File newFile = new File(newFileLocation);
				newFile.mkdirs();
				String fileLoc = newFileLocation.endsWith("/")? newFileLocation + file.getName() : newFileLocation + "/" + file.getName();
				file.renameTo(new File(fileLoc));
				
			}
			else {
				String errorLocation = p.getErrorDir();
				File newFile = new File(errorLocation);
				newFile.mkdirs();
				String fileLoc = errorLocation.endsWith("/")? errorLocation +file.getName() : errorLocation + "/" + file.getName();
				file.renameTo(new File(fileLoc));

			}
			
			
		}
		System.out.println("End of Process : "+ files.size() + " Files :  " + new java.util.Date().toString());
	}

}
