package com.example.cv_catalog;

import java.sql.*;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

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
	
JAVA 8-tól
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

//Eseménykezelés
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

Elérési utak
//Find the application directory
String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
//Image as a file resource
FileResource resource = new FileResource(new File(basepath +"/WEB-INF/images/image.png"));
//Show the image in the application
Image image = new Image("Image from file", resource);
//Let the user view the file in browser or download it
Link link = new Link("Link to the image file", resource);


Kép betöltés a "WEB-INF/classes" utból
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

Állapot megõrzés - @PreserveOnRefresh

@Subscribe
public void changePage(ChangePageEvent event) {
    String url = event.getPageName();
    if (event.hasId()) {
        url += "/" + event.getEntityId();
    }
    navigator.navigateTo(url);
}

Hasznos linkek - 
http://demo.vaadin.com/book-examples-vaadin7/book/#application.architecture.globalaccess
https://vaadin.com/tutorial
http://askvikrant.com/vaadin-7-for-beginners-part-2/
https://vaadin.com/book/vaadin7/-/page/advanced.navigator.html
https://vaadin.com/wiki/-/wiki/Main/Creating%20a%20simple%20login%20view
https://vaadin.com/wiki/-/wiki/Main/Using+parameters+with+views

https://vaadin.com/book/vaadin7/-/page/advanced.navigator.html


HTML -> View
new CustomLayout(new ByteArrayInputStream("<b>Template</b>".getBytes()));
vagy 
new CustomLayout(new FileInputStream(file));


PDF megnyitás book of vaadin -> 391
*/

public class u {
	
	public static String db_database = "79.172.252.29/gabtihu1_cv_catalog";
	public static String db_name = "gabtihu1_mikeux";
	public static String db_pass = "motnaf87";
	//public static String db_param = "jdbc:mysql://localhost/etyukod_te2015?user=root&password=rosivrepus02";
	//public static String db_param = "localhost/cv_catalog?user=root&password=";
	//public static String db_param = "79.172.252.29/gabtihu1_cv_catalog?user=gabtihu1_mikeux&password=motnaf87";
	
	public static void uzen(String msg) {
		
		// Notification with default settings for a warning
		Notification notif = new Notification("Információ",msg,Notification.TYPE_WARNING_MESSAGE);
		// Customize it
		notif.setDelayMsec(400);
		notif.setPosition(Position.BOTTOM_RIGHT);
		notif.setStyleName("u_uzen");
		//notif.setIcon(new ThemeResource("img/reindeer.png"));
		// Show it in the page
		notif.show(Page.getCurrent());
	}
	
	public static void uzenHiba(String Title, String uzenet){
		Notification.show(Title,uzenet, Notification.TYPE_ERROR_MESSAGE);		
	}
	
	public static String GetSQLString(String SQL) {
		String Ret = "";
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
        try {
            ///Connection con = DriverManager.getConnection("jdbc:mysql:///" + adatbazis, felhnev, jelszo);
        	Connection con = DriverManager.getConnection("jdbc:mysql://"+db_database,db_name,db_pass);
        	Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            if (rs.next()) {
            	Ret = rs.getString(1);
            }
            con.close();
        } catch (SQLException ex) {
        	uzenHiba("Hiba",ex.toString());
        }		
        return Ret;
	}
	
	public static MenuBar MenuKeszites(UI ui) {
		MenuBar barmenu = new MenuBar();
		//layout.addComponent(barmenu);
		
		// A feedback component
		//final Label selection = new Label("");
		//layout.addComponent(selection);

		// Define a common menu command for all the menu items.
		MenuBar.Command mycommand = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				u.uzen(selectedItem.getText());
				//ui.getNavigator().navigateTo("login");
		        //selection.setValue("Ordered a " + selectedItem.getText() +" from menu.");
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
				}
			}  
		};
		
		MenuBar.Command kezdolap = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				ui.getNavigator().navigateTo("main");
			}  
		};
		
		ui.getNavigator().navigateTo("login");
		        
		// Another top-level item
		MenuItem cvs = barmenu.addItem("Önéletrajzok", null, null);
		cvs.addItem("Összes önéletrajz", null, kezdolap);
		cvs.addItem("Új önéletrajz",  null, mycommand);
		
		MenuItem szotarak = barmenu.addItem("Szótárak kezelése", null, null);
		szotarak.addItem("Ország", null, szotar); 	
		szotarak.addItem("Pénznem", null, szotar); 	
		szotarak.addItem("Nyelv szint", null, szotar); 	
		szotarak.addItem("Képzési szint", null, szotar); 	
		
		MenuItem exit = barmenu.addItem("Kijelentkezés", null, kilepes);
		
		/*
		// A top-level menu item that opens a submenu
		MenuItem drinks = barmenu.addItem("Beverages", null, null);

		// Submenu item with a sub-submenu
		MenuItem hots = drinks.addItem("Hot", null, null);
		hots.addItem("Tea",new ThemeResource("icons/tea-16px.png"),    mycommand);
		hots.addItem("Coffee",new ThemeResource("icons/coffee-16px.png"), mycommand);

		// Another submenu item with a sub-submenu
		MenuItem colds = drinks.addItem("Cold", null, null);
		colds.addItem("Milk",      null, mycommand);
		colds.addItem("Weissbier", null, mycommand);

		// A sub-menu item after a separator
		drinks.addSeparator();
		drinks.addItem("Quit Drinking", null, null);
		*/

		return barmenu;
	}
	
}
