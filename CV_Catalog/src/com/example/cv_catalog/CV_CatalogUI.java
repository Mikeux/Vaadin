package com.example.cv_catalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
@Theme("cv_catalog")
public class CV_CatalogUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = CV_CatalogUI.class)
	public static class Servlet extends VaadinServlet implements SessionInitListener, SessionDestroyListener {
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();	
			getService().addSessionInitListener(this);
			getService().addSessionDestroyListener(this);
			getService().setSystemMessagesProvider(
					new SystemMessagesProvider() {
					public SystemMessages getSystemMessages(
						SystemMessagesInfo systemMessagesInfo) {
						CustomizedSystemMessages messages =
						new CustomizedSystemMessages();
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
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
				
		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				u.uzen("12312321");
				getUI().getPage().setLocation("http://www.sg.hu");
				layout.addComponent(new Label("Thank you for clicking"));
				//close();
				
			}
		});
		layout.addComponent(button);

		
		layout.addComponent(new Button("Logout", event -> {// Java 8
			// Redirect this page immediately
			getPage().setLocation("/myapp/logout.html");
			// Close the session
			getSession().close();
		}));
		// Notice quickly if other UIs are closed
		setPollInterval(3000);
		
		// Configure the error handler for the UI
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
			// Find the final cause
			String cause = "<b>The click failed because:</b><br/>";
			for (Throwable t = event.getThrowable(); t != null; t = t.getCause())
			if (t.getCause() == null) // We're at final cause
				cause += t.getClass().getName() + "<br/>";
				// Display the error message in a custom fashion
				layout.addComponent(new Label(cause, ContentMode.HTML));
				// Do the default error handling (optional)
				doDefault(event);
			}
		});	
	}

}