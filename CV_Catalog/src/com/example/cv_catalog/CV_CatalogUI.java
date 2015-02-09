package com.example.cv_catalog;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DefaultErrorHandler;
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
	public static class Servlet extends VaadinServlet {
		
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
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
		
		// Configure the error handler for the UI
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
			// Find the final cause
			String cause = "<b>The click failed because:</b><br/>";
			for (Throwable t = event.getThrowable(); t != null;
			t = t.getCause())
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