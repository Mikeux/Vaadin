package com.example.cv_catalog;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/*
//Set the default locale of the UI
UI.getCurrent().setLocale(new Locale("en"));
//Set the page title (window or tab caption)
Page.getCurrent().setTitle("My Page");
//Set a session attribute
VaadinSession.getCurrent().setAttribute("myattrib", "hello");
//Access the HTTP service parameters
File baseDir = VaadinService.getCurrent().getBaseDirectory();
You can get the page and the session also from a UI with getPage() and getSession() and

--------------------------------------------------------------------

class MyView extends VerticalLayout {
	TextField entry = new TextField("Enter this");
	Label display = new Label("See this");
	Button click = new Button("Click This");
	public MyView() {
		addComponent(entry);
		addComponent(display);
		addComponent(click);
		// Configure it a bit
		setSizeFull();
		addStyleName("myview");
	}
}
//Use it
Layout myview = new MyView();

--------------------------------------------------------------------

class MyView extends CustomComponent {
	TextField entry = new TextField("Enter this");
	Label display = new Label("See this");
	Button click = new Button("Click This");
	public MyView() {
		Layout layout = new VerticalLayout();
		layout.addComponent(entry);
		layout.addComponent(display);
		layout.addComponent(click);
		setCompositionRoot(layout);
		setSizeFull();
	}
}
//Use it
MyView myview = new MyView();

------------------------------------------------------------------------

DesignContext design = Design.read(getClass().getResourceAsStream("MyDeclarativeUI.html"),null);
setContent(design.getRootComponent());
//Get the components from the design
mytree = (Tree) design.getComponentByLocalId("mytree");
mytable = (Table) design.getComponentByLocalId("mytable");
//Bind the data display components to (example) data
mytree.setContainerDataSource(TreeExample.createTreeContent());
mytable.setContainerDataSource(TableExample.generateContent());

------------------------------------------------------------------------

//Have a component that fires click events
final Button button = new Button("Click Me!");

//Handle the events with an anonymous class
button.addClickListener(new Button.ClickListener() {
	public void buttonClick(ClickEvent event) {
		button.setCaption("You made me click!");
	}
});

final Button button = new Button("Click It!",
	new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
		event.getButton().setCaption("Done!");
		}
	});
	
JAVA 8-t�l
layout.addComponent(new Button("Click Me!",event -> event.getButton().setCaption("You made click!")));

public class Java8Buttons extends CustomComponent {
	public Java8Buttons() {
	setCompositionRoot(new HorizontalLayout(
		new Button("OK", this::ok),
		new Button("Cancel", this::cancel)));
	}
	public void ok(ClickEvent event) {
		event.getButton().setCaption ("OK!");
	}
	public void cancel(ClickEvent event) {
		event.getButton().setCaption ("Not OK!");
	}
}

//Esem�nykezel�s
public class TheButtons extends CustomComponent implements Button.ClickListener {
	Button onebutton;
	Button toobutton;
	public TheButtons() {
		onebutton = new Button("Button One", this);
		toobutton = new Button("A Button Too", this);
		// Put them in some layout
		Layout root = new HorizontalLayout();
		root.addComponent(onebutton);
		root.addComponent(toobutton);
		setCompositionRoot(root);
	}
	@Override
	public void buttonClick(ClickEvent event) {
		// Differentiate targets by event source
		if (event.getButton() == onebutton)
		onebutton.setCaption ("Pushed one");
		else if (event.getButton() == toobutton)
		toobutton.setCaption ("Pushed too");
	}
}

El�r�si utak
//Find the application directory
String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
//Image as a file resource
FileResource resource = new FileResource(new File(basepath +"/WEB-INF/images/image.png"));
//Show the image in the application
Image image = new Image("Image from file", resource);
//Let the user view the file in browser or download it
Link link = new Link("Link to the image file", resource);


K�p bet�lt�s a "WEB-INF/classes" utb�l
layout.addComponent(new Image(null,new ClassResource("smiley.jpg")));

//A theme resource in the current theme ("mytheme")
//Located in: VAADIN/themes/mytheme/img/themeimage.png
ThemeResource resource = new ThemeResource("img/themeimage.png");
//Use the resource
Image image = new Image("My Theme Image", resource);


Errors
textfield.setComponentError(new UserError("Bad value"));
button.setComponentError(new UserError("Bad click"));

getService().setSystemMessagesProvider(
	new SystemMessagesProvider() {
	@Override
	public SystemMessages getSystemMessages(
		SystemMessagesInfo systemMessagesInfo) {
		CustomizedSystemMessages messages =
		new CustomizedSystemMessages();
		messages.setCommunicationErrorCaption("Comm Err");
		messages.setCommunicationErrorMessage("This is bad.");
		messages.setCommunicationErrorNotificationEnabled(true);
		messages.setCommunicationErrorURL("http://vaadin.com/");
		return messages;
	}
});

�llapot meg�rz�s - @PreserveOnRefresh

*/


public class u {

		public static void uzen(String msg) {
			
			// Notification with default settings for a warning
			Notification notif = new Notification("Inform�ci�",msg,Notification.TYPE_WARNING_MESSAGE);
			// Customize it
			notif.setDelayMsec(400);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setStyleName("u_uzen");
			//notif.setIcon(new ThemeResource("img/reindeer.png"));
			// Show it in the page
			notif.show(Page.getCurrent());
		}
	
}
