package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoginView2 extends VerticalLayout implements View {

	public static final String NAME = "login2";
	TextField jelszo,nev;
	Button belepes;
	
	public LoginView2(){
		setSizeFull();
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		Panel panel = new Panel("Bejelentkezés2");
		panel.addStyleName("login_panel");

		//panel.setSizeFull();
		panel.setSizeUndefined(); // Shrink to fit content
		layout.addComponent(panel);
		        
		// Create the content
		FormLayout content = new FormLayout();
		//content.addStyleName(Reindeer.LAYOUT_BLACK);
		nev = new TextField("Név2:");
		nev.setIcon(FontAwesome.USER);
		nev.setRequired(true);
		//nev.focus();
		
		jelszo = new TextField("Jelszó:");
		jelszo.setIcon(FontAwesome.LOCK);
		jelszo.setRequired(true);
		
		belepes = new Button("Belépés2");
	
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
				u.uzen("Belépés???");
				Notification.show("Sikeres belépés");
				//getUI().getPage().setLocation("http://www.sg.hu");
				//layout.addComponent(new Label("Thank you for clicking"));
				//close();
				
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		nev.focus();
	  if(event.getParameters() != null) {
            String id = event.getParameters();
            u.uzen("Parameter => "+id);
        }
	}
	
}
