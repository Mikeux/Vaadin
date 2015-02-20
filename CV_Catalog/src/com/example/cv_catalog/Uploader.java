package com.example.cv_catalog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.example.cv_catalog.model.DokumentumTipus;
import com.example.cv_catalog.model.Dokumentumok;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class Uploader implements Receiver, SucceededListener {
	public File file;
	public String Tipus;
	public Oneletrajz oneletrajz;
	
	
	public OutputStream receiveUpload(String filename,String mimeType) {
		
		//https://vaadin.com/book/vaadin6/-/page/application.resources.html
		//https://vaadin.com/book/-/page/application.resources.html
		
		// Find the application directory
				
		// Image as a file resource
		//FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/image.png"));
		
		// Show the image in the application
		//Image image = new Image("Image from file", resource);
		        
		// Let the user view the file in browser or download it
		//Link link = new Link("Link to the image file", resource);
		
		/*Oneletrajz cv = new Oneletrajz();
		cv.setFelhasznalok(u.LoginFelhasznalo);
		cv.setHozzaadva(new java.util.Date());
		u.EM.getTransaction().begin();
		u.EM.persist(cv);
		u.EM.getTransaction().commit();	*/	
		
		//u.uzen("PATH => "+VaadinService.getCurrent().getBaseDirectory().getAbsolutePath());
		
		FileOutputStream fos = null; // Stream to write to
		//File folder = new File ( System.getProperty( "user.home" ) + File.separator + "myfolder" ).mkdir();
				
		try {
			file = new File(u.basepath + "/" + filename);
			fos = new FileOutputStream(file);

			
		} catch (final java.io.FileNotFoundException e) {
			new Notification("A fájl feltöltése nem sikerült<br/>",
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
		//DokumentumTipus dt = new Dok
		/*System.out.println(u.basepath + "/" + filename);
		Dokumentumok dok = new Dokumentumok(this.oneletrajz,new DokumentumTipus(),u.basepath + "/" + filename);
		
		u.EM.getTransaction().begin();
		u.EM.persist(dok);
		u.EM.getTransaction().commit();	*/
	}
};