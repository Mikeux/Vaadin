package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {

	private VerticalLayout mainSection = new VerticalLayout();
	private HorizontalLayout upperSection = new HorizontalLayout();
	private HorizontalSplitPanel lowerSection = new	HorizontalSplitPanel();
	private VerticalLayout menuLayout = new VerticalLayout();
	private VerticalLayout contentLayout = new VerticalLayout();
	
	public MainView(UI ui){
		setSizeFull();		
		mainSection.setSizeFull();	
		
		//upperSection.addComponent(new Label("Header"));
		upperSection.addComponent(u.MenuKeszites(ui));
		menuLayout.addComponent(new Label("Menu"));
		contentLayout.addComponent(new Label("Content"));
		lowerSection.addComponents(menuLayout,contentLayout);
				
		
		lowerSection.setSplitPosition(20);
		
		//upperSection.setSizeFull();
		//setExpandRatio(lowerSection, 0.1f);
		
		lowerSection.setSizeFull();
		//menuLayout.setSizeFull();
		contentLayout.setSizeFull();
		
		mainSection.addComponent(upperSection);
		mainSection.addComponent(lowerSection);
		
		addComponent(mainSection);
		
		mainSection.setExpandRatio(lowerSection, 1);
		
		Tree tree = new Tree("Menü");
		tree.addItem("Önéletrajzok");
		tree.addItem("Új önéletrajz");
		tree.addItem("Kilépés");
		menuLayout.addComponent(tree);		
		
		//this.addMenuOption("Option 1", new Label("Component 1"));
		//this.addMenuOption("Option 2", new Label("Component 2"));
		
		this.showBorders();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		
	}
	
	public void addMenuOption(String caption, final Component component) {
		Button button = new Button(caption);
		menuLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				contentLayout.removeAllComponents();
				contentLayout.addComponent(component);
			}
		});
	}
	
	private void showBorders() {
		menuLayout.addStyleName("layout_border");
		contentLayout.addStyleName("layout_border");
		upperSection.addStyleName("layout_border");
		lowerSection.addStyleName("layout_border");
	}
}
