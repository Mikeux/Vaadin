package com.example.cv_catalog.views;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Felhasznalok;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoginView extends VerticalLayout implements View {

	public static final String NAME = "login";
	TextField nev;
	PasswordField jelszo;
	Button belepes;
	
	public LoginView(){
		setSizeFull();
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		Panel panel = new Panel("Bejelentkezés");
		panel.addStyleName("login_panel");
		//panel.setSizeFull();
		panel.setSizeUndefined(); // Shrink to fit content
		layout.addComponent(panel);
		        
		// Create the content
		FormLayout content = new FormLayout();
		//content.addStyleName(Reindeer.LAYOUT_BLACK);
		nev = new TextField("Név:");
		nev.setIcon(FontAwesome.USER);
		nev.setRequired(true);
		//nev.focus();
		
		jelszo = new PasswordField("Jelszó:");
		jelszo.setIcon(FontAwesome.LOCK);
		jelszo.setRequired(true);
		
		belepes = new Button("Belépés");
	
		content.addComponent(nev);
		content.addComponent(jelszo);
		content.addComponent(belepes);
		
		content.setSizeUndefined(); // Shrink to fit
		content.setMargin(true);
		panel.setContent(content);		
		layout.setMargin(true);
		
		addComponent(layout);
		
		belepes.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				TypedQuery<Felhasznalok> query = u.EM.createQuery("SELECT f FROM Felhasznalok f WHERE f.nev='"+nev.getValue()+"' AND f.jelszo='"+jelszo.getValue()+"'", Felhasznalok.class);
				try {
					u.LoginFelhasznalo  = query.getSingleResult();
					u.uzen("Sikeres bejelentkezés! ("+u.LoginFelhasznalo.getNev()+")");
					getSession().setAttribute("user",nev.getValue());
					getUI().getNavigator().navigateTo("cvs");
				}catch (Exception ex) {
					Notification.show("Hibás név/jelszó!",Notification.TYPE_ERROR_MESSAGE);
					//u.uzen("Hibás név/jelszó!");
				}
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		nev.focus();
	}
	
}
