package com.example.cv_catalog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class Uploader implements Receiver, SucceededListener {
	public File file;
	
	public OutputStream receiveUpload(String filename,String mimeType) {
		// Create upload stream
		FileOutputStream fos = null; // Stream to write to
		//File folder = new File ( System.getProperty( "user.home" ) + File.separator + "myfolder" ).mkdir();
				
	try {
		file = new File("/" + filename);
		fos = new FileOutputStream(file);
	} catch (final java.io.FileNotFoundException e) {
		new Notification("A fájl megnyitása nem sikerült<br/>",
				e.getMessage(),
				Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
		return null;
	}
		return fos; // Return the output stream to write to
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// Show the uploaded file in the image viewer
		u.uzen("Kész");
		//image.setVisible(true);
		//image.setSource(new FileResource(file));
	}
};