package com.example.cv_catalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.example.cv_catalog.views.LoginView;
import com.example.cv_catalog.views.MainView;
import com.example.cv_catalog.views.OneletrajzEdit;
import com.example.cv_catalog.views.Oneletrajzok;
import com.example.cv_catalog.views.szotar_kepzes_szint;
import com.example.cv_catalog.views.szotar_nyelvek;
import com.example.cv_catalog.views.szotar_nyelvi_szint;
import com.example.cv_catalog.views.szotar_orszag;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("cv_catalog")
public class CV_CatalogUI extends UI {
	Navigator navigator;
	
	@PreserveOnRefresh()
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CV_CatalogUI.class)
	public static class Servlet extends VaadinServlet implements SessionInitListener, SessionDestroyListener {
		
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();	

			getService().addSessionInitListener(this);
			getService().addSessionDestroyListener(this);
			getService().setSystemMessagesProvider(
				new SystemMessagesProvider() {
					public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
						CustomizedSystemMessages messages = new CustomizedSystemMessages();
						messages.setCommunicationErrorCaption("Kommunikációs hiba!");
						messages.setCommunicationErrorMessage("Ez nem valami jó!");
						messages.setCommunicationErrorNotificationEnabled(true);
						//messages.setCommunicationErrorURL("http://sg.hu");
						messages.setCommunicationErrorURL("http://localhost:8080/CV_Catalog");
						return messages;
					}
				});
		}

		@Override
		public void sessionDestroy(SessionDestroyEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sessionInit(SessionInitEvent event) throws ServiceException {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	protected void init(VaadinRequest request) {	
		navigator = new Navigator(this, this);
		getPage().setTitle("Önéletrajz nyílvántartó");
        				        
		navigator.addView("login", LoginView.class);		
		navigator.addView("main", new MainView(getUI()));
		
		navigator.addView("szotar_orszag", new szotar_orszag(getUI()));
		navigator.addView("szotar_nyelvi_szint", new szotar_nyelvi_szint(getUI()));
		navigator.addView("szotar_kepzes_szint", new szotar_kepzes_szint(getUI()));
		navigator.addView("szotar_nyelvek", new szotar_nyelvek(getUI()));
		
		navigator.addView("cv_edit", new OneletrajzEdit(getUI()));
		navigator.addView("cvs", new Oneletrajzok(getUI()));
		
		boolean isLoggedIn = getSession().getAttribute("user") != null;
		if(!isLoggedIn) getUI().getNavigator().navigateTo("login");
		else  getUI().getNavigator().navigateTo("cvs");
				
		// Configure the error handler for the UI		
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
			String cause = "";
			for (Throwable t = event.getThrowable(); t != null; t = t.getCause())
			if (t.getCause() == null) // We're at final cause
				cause += t.getClass().getName() + "\r\n";
				Notification.show("Hiba",cause, Notification.TYPE_ERROR_MESSAGE);
				//layout.addComponent(new Label(cause, ContentMode.HTML));
				doDefault(event);
			}
		});	
	}

}