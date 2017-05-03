package com.ak.elegorg.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import com.ak.elegorg.core.masterinfo.KnownContentTypes;
public class Organizer {
	public static void main(String ar[]) {
		try {
			parseExample(new File("C:\\ak\\personal\\testdata\\elegOrg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void parseExample(final File folder) throws IOException, SAXException, TikaException {
	    AutoDetectParser parser = new AutoDetectParser();
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();
	    Metadata metadata2 = new Metadata();
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	           //System.out.println(fileEntry.getName());
	   	    	try (InputStream stream = new FileInputStream(fileEntry) ) {
	   	    	parser.parse(stream, handler, metadata);
		        //System.out.println(handler.toString());
	   	    	
	   	    	String contentType = metadata.get("Content-Type");
	   	    	System.out.println(fileEntry.getName());
	   	    	KnownContentTypes type = KnownContentTypes.findType(contentType);
	   	    	org.apache.tika.parser.Parser secondParser = null;
	   	    	ParseContext pcontext = new ParseContext();
	   	    	if(type!=null) {
	   	    	secondParser = type.getParser();
	   	    	}
	   	    	if(secondParser !=null) {
	   	    		FileInputStream inputstream = new FileInputStream(fileEntry); 
	   	    		secondParser.parse(inputstream, handler, metadata2,pcontext);
	   	    	}
	   	    	String[] metadataNames = metadata2.names();
	   	    	if(type!=null) {
		   	    	System.out.println("-----------------------------------------------------------------------------------------");
	   	    		System.out.println("File Name :"+ fileEntry.getName());
	   	    		System.out.println("File System Date :"+ new java.util.Date(fileEntry.lastModified()).toLocaleString());
	   	    		//for(String name : type.getMetadataTags()) { 		
	   	    		for(String name : metadataNames) {
						System.out.println(name + ": " + metadata2.get(name));
			   	    	}

			   	    	System.out.println("-----------------------------------------------------------------------------------------");
	   	    		
	   	    	}
		    }	

	        }
	    }
	}

}
