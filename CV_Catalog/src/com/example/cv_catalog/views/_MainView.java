package com.example.cv_catalog.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class _MainView extends VerticalLayout implements View {

	public _MainView(){
		setSizeFull();
		
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull(); // Use entire window
		// Add some component
		content.addComponent(new Label("Hello!"));
		// Layout inside layout
		HorizontalLayout hor = new HorizontalLayout();
		hor.setSizeFull(); // Use all available space
		// Couple of horizontally laid out components
		Tree tree = new Tree("Menü");
		tree.addItem("Önéletrajzok");
		tree.addItem("Új önéletrajz");
		tree.addItem("Kilépés");
		// Expand all items that can be
		for (Object itemId: tree.getItemIds())
		    tree.expandItem(itemId);
		
		hor.addComponent(tree);
		
		Table table = new Table("Adatok");
		/*
		// Table with a component column in non-editable mode
		final Table table = new Table("My Table");
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Description", TextArea.class, null);
		table.addContainerProperty("Delete", CheckBox.class, null);
		        
		// Insert this data
		String people[][] = {{"Galileo",  "Liked to go around the Sun"},
		                     {"Monnier",  "Liked star charts"},
		                     {"Väisälä",  "Liked optics"},
		                     {"Oterma",   "Liked comets"},
		                     {"Valtaoja", "Likes cosmology and still "+
		                         "lives unlike the others above"},
		                     };
		        
		// Insert the data and the additional component column
		for (int i=0; i<people.length; i++) {
		    TextArea area = new TextArea(null, people[i][1]);
		    area.setRows(2);
		    
		    // Add an item with two components
		    Object obj[] = {people[i][0], area, new CheckBox()};
		    table.addItem(obj, new Integer(i));
		}
		table.setPageLength(table.size());*/
		
		table.setSizeFull();
		hor.addComponent(table);
		hor.setExpandRatio(table, 1); // Expand to fill
		content.addComponent(hor);
		content.setExpandRatio(hor, 1); // Expand to fill		
		
		addComponent(content);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
