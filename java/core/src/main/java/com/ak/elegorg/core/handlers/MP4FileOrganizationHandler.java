package com.ak.elegorg.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.springframework.web.util.UriTemplate;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.ak.elegorg.core.constants.ElegOrgConstants;
import com.ak.elegorg.core.properties.OrganizingProperties;

public class MP4FileOrganizationHandler implements BaseOrganizationHandler {

	@Override
	public String getNewFileLocation(File file, OrganizingProperties p) {
		// TODO Auto-generated method stub
		try {
			InputStream stream = new FileInputStream(file);
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new MP4Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(stream, handler, metadata, parseCtx);
			stream.close();
			String[] metadataNames = metadata.names();
			for (String name : metadataNames) {
				System.out.println(name + ": " + metadata.get(name));
			}

			String xmlDate = metadata.get("meta:creation-date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date date = sdf.parse(xmlDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-YYYY");
			
			System.out.println("Direct Printing Date : " + newFormat.format(date));
			UriTemplate template = new UriTemplate(p.getVideoFileFolderFormat());
			
			Map<String, String> variables = new HashMap<String,String>();
			
			variables.put(OrganizingProperties.DESTINATION_BASE, p.getDestinationBase());
			variables.put(OrganizingProperties.CREATION_DATE, newFormat.format(date));
			URI folderUri = template.expand(variables);
			 
			
			String folderPath = java.net.URLDecoder.decode(folderUri.toASCIIString(), "UTF-8");//folderUri.toASCIIString();//p.getDestinationBase() + "/" + p.getVideoFolderName() +"/" +  newFormat.format(date) ; 
					
			System.out.println(folderPath);
			return folderPath;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ElegOrgConstants.NULL_STRING.getValue();
		}
		
	}

	@Override
	public boolean canBeHandled(File f, OrganizingProperties p) {
		// TODO Auto-generated method stub
		return false;
	}

}
