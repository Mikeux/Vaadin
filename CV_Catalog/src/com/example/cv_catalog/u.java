package com.example.cv_catalog;

import java.sql.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.example.cv_catalog.model.Felhasznalok;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.views.Oneletrajzok;
import com.example.cv_catalog.views.szotar_nyelvek;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class u {
	
	public static Felhasznalok LoginFelhasznalo;
	public static EntityManager EM = JPAContainerFactory.createEntityManagerForPersistenceUnit("CV_Catalog");
	public static String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	
	public static void uzen(String msg) {
		
		// Notification with default settings for a warning
		Notification notif = new Notification("Információ",msg,Notification.TYPE_WARNING_MESSAGE);
		// Customize it
		notif.setDelayMsec(2000);
		notif.setPosition(Position.BOTTOM_RIGHT);
		notif.setStyleName("u_uzen");
		//notif.setIcon(new ThemeResource("img/reindeer.png"));
		// Show it in the page
		notif.show(Page.getCurrent());
	}
	
	public static void uzenHiba(String Title, String uzenet){
		Notification.show(Title,uzenet, Notification.TYPE_ERROR_MESSAGE);		
	}
		
	public static MenuBar MenuKeszites(UI ui) {
		MenuBar barmenu = new MenuBar();

		MenuBar.Command mycommand = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				u.uzen(selectedItem.getText());
			}  
		};
		
		MenuBar.Command kilepes = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				ui.getSession().setAttribute("user", null);
				ui.getNavigator().navigateTo("login");
			}  
		};
		
		MenuBar.Command szotar = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if(selectedItem.getText().equals("Ország")) {
					ui.getNavigator().navigateTo("szotar_orszag");
				} else if(selectedItem.getText().equals("Nyelv szint")) {
					ui.getNavigator().navigateTo("szotar_nyelvi_szint");
				} else if(selectedItem.getText().equals("Képzési szint")) {
					ui.getNavigator().navigateTo("szotar_kepzes_szint");
				} else if(selectedItem.getText().equals("Nyelvek")) {
					ui.getNavigator().navigateTo("szotar_nyelvek");
				}
			}  
		};
		
		MenuBar.Command kezdolap = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				ui.getNavigator().removeView("cvs");
				ui.getNavigator().addView("cvs", new Oneletrajzok(ui));						
				ui.getNavigator().navigateTo("cvs");
			}  
		};
		
		//http://www.objectdb.com/java/jpa/persistence/crud
		MenuBar.Command cvedit = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				if(u.LoginFelhasznalo != null) {
					Oneletrajz cv = new Oneletrajz();
					cv.setFelhasznalok(u.LoginFelhasznalo);
					cv.setHozzaadva(new java.util.Date());
					u.EM.getTransaction().begin();
					u.EM.persist(cv);
					u.EM.getTransaction().commit();			
					ui.getNavigator().navigateTo("cv_edit/"+cv.getId());
					
				} else {
					u.uzen("Nincs felhasználó bejelentkezve!");
				}
			}  
		};
		
		ui.getNavigator().navigateTo("login");
		        
		// Another top-level item
		MenuItem cvs = barmenu.addItem("Önéletrajzok", null, null);
		cvs.addItem("Összes önéletrajz", null, kezdolap);
		cvs.addItem("Új önéletrajz",  null, cvedit);
		
		MenuItem szotarak = barmenu.addItem("Szótárak kezelése", null, null);
		szotarak.addItem("Ország", null, szotar); 	
		szotarak.addItem("Nyelvek", null, szotar); 	
		szotarak.addItem("Nyelv szint", null, szotar); 	
		szotarak.addItem("Képzési szint", null, szotar); 	
		
		MenuItem exit = barmenu.addItem("Kijelentkezés", null, kilepes);
		
		return barmenu;
	}
	
}
