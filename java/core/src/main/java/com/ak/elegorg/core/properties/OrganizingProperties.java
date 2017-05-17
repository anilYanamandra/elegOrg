package com.ak.elegorg.core.properties;

public class OrganizingProperties {
	
	public static String DESTINATION_BASE	= "DESTINATION_BASE";
	public static String CAMERA_MODEL		= "CAMERA_MODEL";
	public static String CREATION_DATE		= "CREATION_DATE";
	
	String destinationBase;
	String baseDir;
	String errorDir;
	String[] supportedExtensions = { "jpeg", "jpg", "JPG", "JPEG", "mp4", "MOV", "pdf" };
	String[] selectedExtensions;
	String imageFolderName = "images";
	String videoFolderName = "videos";
	String imageFileFolderFormat = "{DESTINATION_BASE}/{CREATION_DATE}/images/{CAMERA_MODEL}/";
	String videoFileFolderFormat = "{DESTINATION_BASE}/{CREATION_DATE}/videos/";
	

	
	
	public String getImageFileFolderFormat() {
		return imageFileFolderFormat;
	}

	public void setImageFileFolderFormat(String imageFileFolderFormat) {
		this.imageFileFolderFormat = imageFileFolderFormat;
	}

	public String getVideoFileFolderFormat() {
		return videoFileFolderFormat;
	}

	public void setVideoFileFolderFormat(String videoFileFolderFormat) {
		this.videoFileFolderFormat = videoFileFolderFormat;
	}

	
	
	public String getImageFolderName() {
		return imageFolderName;
	}

	public void setImageFolderName(String imageFolderName) {
		this.imageFolderName = imageFolderName;
	}

	public String getVideoFolderName() {
		return videoFolderName;
	}

	public void setVideoFolderName(String videoFolderName) {
		this.videoFolderName = videoFolderName;
	}



	
	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String[] getSupportedExtensions() {
		return supportedExtensions;
	}

	public void setSupportedExtensions(String[] supportedExtensions) {
		this.supportedExtensions = supportedExtensions;
	}

	public String[] getSelectedExtensions() {
		return selectedExtensions;
	}

	public void setSelectedExtensions(String[] selectedExtensions) {
		this.selectedExtensions = selectedExtensions;
	}
	public String getDestinationBase() {
		return destinationBase;
	}

	public void setDestinationBase(String destinationBase) {
		this.destinationBase = destinationBase;
	}
	public String getErrorDir() {
		return destinationBase+"/"+"errordir";
		//return errorDir;
	}

	public void setErrorDir(String errorDir) {
		this.errorDir = errorDir;
	}

	
}
