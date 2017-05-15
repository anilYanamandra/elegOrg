package com.ak.elegorg.core.handlers;

import java.io.File;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.sanselan.Sanselan;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;
import org.springframework.web.util.UriTemplate;

import com.ak.elegorg.core.constants.ElegOrgConstants;
import com.ak.elegorg.core.properties.OrganizingProperties;

public class JpegFileOrganizationHandler implements BaseOrganizationHandler {

	@Override
	public String getNewFileLocation(File file, OrganizingProperties p) {
		// TODO Auto-generated method stub
		String dtStr = null, cameraMake = null, cameraModel = null, newFilePath = null;
		try {
			Date d1 = new Date();
			System.out.println("Start Time: " + d1.toString());
			JpegImageMetadata meta = ((JpegImageMetadata) Sanselan
					.getMetadata(file));

			TiffImageMetadata imeta = meta.getExif();
			
			TiffField field = imeta
					.findField(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);//ExifTagConstants.EXIF_TAG_CREATE_DATE);
			System.out.println("Date : " + field.getStringValue());

			dtStr = field.getStringValue();

			field = imeta.findField(ExifTagConstants.EXIF_TAG_MAKE);
			cameraMake = field.getStringValue();
			System.out.println("Camera Serial Number: "
					+ field.getStringValue());

			field = imeta.findField(ExifTagConstants.EXIF_TAG_MODEL);
			cameraModel = field.getStringValue();
			
			System.out.println("Camera Serial Number: "
					+ field.getStringValue());
			Date d2 = new Date();
			System.out.println("End Time: " + d2.toString());
			SimpleDateFormat existingFormat = new SimpleDateFormat(
					"yyyy:MM:dd HH:mm:ss");
			SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-YYYY");
			String pathDatePart = null;
			try {
				pathDatePart = newFormat.format(existingFormat.parse(dtStr));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			UriTemplate template = new UriTemplate(p.getImageFileFolderFormat());
			Map<String, String> variables = new HashMap<String,String>();
			
			variables.put(OrganizingProperties.DESTINATION_BASE, p.getDestinationBase());
			variables.put(OrganizingProperties.CREATION_DATE, pathDatePart);
			variables.put(OrganizingProperties.CAMERA_MODEL, cameraMake.trim() + "-" + cameraModel.trim());
			URI folderUri = template.expand(variables);
			 
			
			newFilePath = java.net.URLDecoder.decode(folderUri.toASCIIString(), "UTF-8");
			
//			newFilePath = destinationBase + "/" + p.getImageFolderName() + "/"+  pathDatePart + "/"
//					+ cameraMake.trim() + "-" + cameraModel.trim() + "/";
		} catch (Exception e) {
			e.printStackTrace();
			return ElegOrgConstants.NULL_STRING.getValue();
		}
		System.out.println(file.getName());
		System.out.println(newFilePath);
		return newFilePath;
	}

	@Override
	public boolean canBeHandled(File f, OrganizingProperties p) {
		// TODO Auto-generated method stub
		return false;
	}

}
