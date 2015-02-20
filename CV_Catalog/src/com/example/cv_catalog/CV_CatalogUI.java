package com.example.cv_catalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.example.cv_catalog.views.LoginView;
import com.example.cv_catalog.views.LoginView2;
import com.example.cv_catalog.views.OneletrajzEdit;
import com.example.cv_catalog.views.Oneletrajzok;
import com.example.cv_catalog.views._MainView;
import com.example.cv_catalog.views.MainView;
import com.example.cv_catalog.views.StartView;
import com.example.cv_catalog.views.szotar_kepzes_szint;
import com.example.cv_catalog.views.szotar_nyelvek;
import com.example.cv_catalog.views.szotar_nyelvi_szint;
import com.example.cv_catalog.views.szotar_orszag;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
						messages.setCommunicationErrorURL("http://sg.hu");
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
        
        // Create and register the views
        //navigator.addView("", new StartView());
		//navigator.addView("login2", LoginView2.class); //http://localhost:8080/CV_Catalog/#!login2
		//navigator.addView("_main", _MainView.class);
				        
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
		//getUI().getNavigator().navigateTo("cvs");
		
		//HorizontalLayout upperSection = new HorizontalLayout();
		//upperSection.addComponent(u.MenuKeszites(getUI()));
		
		//setContent(new MainView(getGui));
		
		//setContent(new Label("sdfsdfd"));
		
		
		/*try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//mysqlCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		} catch (Exception ex) {
			u.uzenHiba("Hiba a MySQL JDBC Driver betöltésekor",ex.toString());
			//ex.printStackTrace();
			//layout.addComponent(new Label("Hiba a MySQL JDBC Driver betöltésekor<br/>Oka: <font color='red'>: "+ex.toString()+"</font>",ContentMode.HTML));
		}
					
		u.uzen(u.GetSQLString("SELECT COUNT(*) FROM dokumentum_tipus")+" db ország van.");*/
		
		//setContent(this);
		
		/*layout.addComponent(new Button("Logout", event -> {// Java 8
			// Redirect this page immediately
			getPage().setLocation("/CV_Catalog/logout.html");
			// Close the session
			getSession().close();
		}));
		// Notice quickly if other UIs are closed
		//setPollInterval(3000);
		
		setContent(layout);*/
		
		/*getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
            	
            	//Notification.show(event.getParameters()+" - "+event.toString());
            	
            	//Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof LoginView;

                if (!isLoggedIn) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    //getNavigator().navigateTo(LoginView.NAME);
                	getUI().getNavigator().navigateTo("login");
                    //return false;

                } else  {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                	//getNavigator().navigateTo(LoginView.NAME);
                	getUI().getNavigator().navigateTo("");
                    //return false;
                }
            	
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });*/
		
		
		// Configure the error handler for the UI
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
			// Find the final cause
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