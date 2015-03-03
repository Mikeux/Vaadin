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
	public Oneletrajz oneletrajz;
	public DokumentumTipus dok_tipus;
	
	private String FileName = "";
		
	public OutputStream receiveUpload(String filename,String mimeType) {
		File Dir = new File(u.basepath + "/Dokumentumok/"+this.oneletrajz.getId());
		FileOutputStream fos = null;
				
		try {
			if(!Dir.exists()) Dir.mkdirs();
			
			if(dok_tipus != null){
				this.FileName = filename;
				file = new File(u.basepath + "/Dokumentumok/"+this.oneletrajz.getId()+"/" + filename);
				fos = new FileOutputStream(file);
			} else {
				u.uzen("A csatolmány típusa nincs megadva!");
			}
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
		new Notification("Sikeres csatolmány feltöltés!",Notification.TYPE_HUMANIZED_MESSAGE).show(Page.getCurrent());
		Dokumentumok dok = new Dokumentumok(this.oneletrajz,dok_tipus,this.FileName);
		u.EM.getTransaction().begin();
		u.EM.persist(dok);
		u.EM.getTransaction().commit();
	}
};