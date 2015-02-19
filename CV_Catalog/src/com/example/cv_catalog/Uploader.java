package com.example.cv_catalog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class Uploader implements Receiver, SucceededListener {
	public File file;
	
	public OutputStream receiveUpload(String filename,String mimeType) {
		
		/*
		https://vaadin.com/book/vaadin6/-/page/application.resources.html
		https://vaadin.com/book/-/page/application.resources.html
		
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		
		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                        "/WEB-INF/images/image.png"));
		
		// Show the image in the application
		Image image = new Image("Image from file", resource);
		        
		// Let the user view the file in browser or download it
		Link link = new Link("Link to the image file", resource);
		*/
		
		u.uzen("PATH => "+VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
		
		FileOutputStream fos = null; // Stream to write to
		//File folder = new File ( System.getProperty( "user.home" ) + File.separator + "myfolder" ).mkdir();
				
		return null;
		/*try {
			file = new File("/" + filename);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			new Notification("A fájl megnyitása nem sikerült<br/>",
					e.getMessage(),
					Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
			return null;
		}
			return fos; // Return the output stream to write to
		*/
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// Show the uploaded file in the image viewer
		u.uzen("Kész");
		//image.setVisible(true);
		//image.setSource(new FileResource(file));
	}
};