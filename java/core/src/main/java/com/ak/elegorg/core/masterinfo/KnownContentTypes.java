package com.ak.elegorg.core.masterinfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.tika.parser.AutoDetectParser;




public enum KnownContentTypes {
	JPEG(new String[] { "image/jpeg" }, new String[] { "Content-Type", "Orientation", "Image Width", "Image Height","Creation-Date","Model","Date/Time" },
			org.apache.tika.parser.jpeg.JpegParser.class),
	MP4(new String[] { "video/mp4" }, new String[] { "Content-Type", "Orientation", "Image Width", "Image Height","Creation-Date","Model","Date/Time" },
			org.apache.tika.parser.mp4.MP4Parser.class),
	PDF(new String[] { "application/pdf" }, new String[] { "Content-Type", "Orientation", "Image Width", "Image Height","Creation-Date","Model","Date/Time" },
			org.apache.tika.parser.pdf.PDFParser.class);

	private HashSet<String> mimeTypes;
	private HashSet<String> metadataTags;
	private org.apache.tika.parser.Parser parser;

	private KnownContentTypes(String[] mimeTypes, String[] metadataTags,
			Class<? extends org.apache.tika.parser.Parser> parser) {
		this.mimeTypes = new HashSet<String>();
		this.mimeTypes.addAll(Arrays.asList(mimeTypes));
		this.metadataTags = new HashSet<String>();
		this.metadataTags.addAll(Arrays.asList(metadataTags));
		try {
			this.parser = (org.apache.tika.parser.Parser) parser.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			this.parser = new AutoDetectParser();
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			this.parser = new AutoDetectParser();
			e.printStackTrace();
		}
	}

	public Set<String> getMetadataTags() {
		return metadataTags;
	}

	public Set<String> getMimeTypes() {
		return mimeTypes;
	}
	
	public org.apache.tika.parser.Parser getParser() {
		return this.parser;
	}

	public static KnownContentTypes findType(String mimeType) {
		KnownContentTypes type = null;

		for (KnownContentTypes t : values()) {
			if (t.getMimeTypes().contains(mimeType)) {
				type = t;
			}
		}

		return type;
	}

}
