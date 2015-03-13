package com.example.cv_catalog;

import java.io.ByteArrayOutputStream;
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
	private String Hiba = "";
	private String FileName = "";
		
	public OutputStream receiveUpload(String filename,String mimeType) {
		File Dir = new File(u.basepath + "/Dokumentumok/"+this.oneletrajz.getId());
		FileOutputStream fos = null;
		this.Hiba = "";
		
		if(this.dok_tipus.getId() == 1){
			if(!mimeType.equalsIgnoreCase("image/jpeg") && !mimeType.equalsIgnoreCase("image/png")) {
				this.Hiba = "A kiválasztott kép nem megfelelõ típusú! (jpg,png)";
			}
		}
		
		try {
			if(filename.equals("")){
				this.Hiba = "Nincs fájl kiválasztva!";
			} else if(this.Hiba.equals("")) {
				if(!Dir.exists()) Dir.mkdirs();
				if(dok_tipus != null){
					this.FileName = filename;
					file = new File(u.basepath + "/Dokumentumok/"+this.oneletrajz.getId()+"/" + filename);
					if(file.exists()){
						this.Hiba = "Már létezik a fájl!";
					}else {
						fos = new FileOutputStream(file);
					}
				} else {
					this.Hiba = "A csatolmány típusa nincs megadva!";
				}				
			}
		} catch (Exception e) {
			this.Hiba = "A fájl feltöltése nem sikerült!\r\n"+e.getMessage();
		}
		
		if(this.Hiba.equals("")) {
			return fos; 
		} else {
			u.uzenHiba("Hiba", this.Hiba);
			return new ByteArrayOutputStream();
		}
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		if(this.Hiba == ""){
			new Notification("Sikeres csatolmány feltöltés!",Notification.TYPE_HUMANIZED_MESSAGE).show(Page.getCurrent());
			Dokumentumok dok = new Dokumentumok(this.oneletrajz,dok_tipus,this.FileName);
			u.EM.getTransaction().begin();
			u.EM.persist(dok);
			u.EM.getTransaction().commit();
		}
	}
};