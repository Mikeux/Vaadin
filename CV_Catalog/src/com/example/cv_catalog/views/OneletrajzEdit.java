package com.example.cv_catalog.views;

import javax.persistence.EntityManager;

import com.example.cv_catalog.u;
import com.example.cv_catalog.components.SzemelyesAdatokComponent;
import com.example.cv_catalog.model.Felhasznalok;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OneletrajzEdit extends VerticalLayout implements View {
	private VerticalLayout mainSection = new VerticalLayout();
	private HorizontalLayout upperSection = new HorizontalLayout();
	private HorizontalSplitPanel lowerSection = new	HorizontalSplitPanel();
	private VerticalLayout menuLayout = new VerticalLayout();
	private VerticalLayout contentLayout = new VerticalLayout();
	
	private Oneletrajz cv;
	private SzemelyesAdatokComponent altalanosAdatok;
	private Tree menuTree;
	
	public OneletrajzEdit(UI ui){
		setSizeFull();		
		mainSection.setSizeFull();	
		
		//upperSection.addComponent(new Label("Header"));
		upperSection.addComponent(u.MenuKeszites(ui));
		//menuLayout.addComponent(new Label("Menu"));
		
		contentLayout.addComponent(new Label("Content"));
		
		lowerSection.addComponents(menuLayout,contentLayout);
		
		lowerSection.setSplitPosition(20);
		
		//upperSection.setSizeFull();
		//setExpandRatio(lowerSection, 0.1f);
		
		lowerSection.setSizeFull();
		//menuLayout.setSizeFull();
		//contentLayout.setSizeFull();
		
		mainSection.addComponent(upperSection);
		mainSection.addComponent(lowerSection);
		
		addComponent(mainSection);
		
		mainSection.setExpandRatio(lowerSection, 1);
		
		menuTree = new Tree("�n�letrajz adatai");
		menuTree.addItem("Szem�lyes adatok");
		menuTree.addItem("Tanulm�nyok");
		menuTree.addItem("Szakmai tapasztalat");
		menuTree.addItem("Nyelvismeret");
		menuTree.addItem("Egy�b k�szs�gek");
		menuTree.addItem("Dokument�ci�k");
		menuLayout.addComponent(menuTree);		
		
		//this.addMenuOption("Option 1", new Label("Component 1"));
		//this.addMenuOption("Option 2", new Label("Component 2"));
		this.init();
		this.showBorders();
		
	}
	
	public void init(){
		menuTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				contentLayout.removeAllComponents();
				if(event.getItemId().toString() == "Szem�lyes adatok") {
					altalanosAdatok = new SzemelyesAdatokComponent(cv);
					contentLayout.addComponent(altalanosAdatok);
				}
				//u.uzen(event.getItemId().toString());				
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		contentLayout.removeAllComponents();
        if (event.getParameters() == null || event.getParameters().isEmpty()) {
        	contentLayout.addComponent(new Label("Nincs param�ter"));
            return;
        } else {
        	//u.uzen(event.getParameters());
        	//contentLayout.addComponent(new Label(event.getParameters()));
        	this.cv = u.EntityManager.find(Oneletrajz.class,Integer.parseInt(event.getParameters()));	
        }
        
        this.altalanosAdatok = new SzemelyesAdatokComponent(this.cv);
        contentLayout.addComponent(altalanosAdatok);
        
        /*contentLayout.removeComponent(altalanosAdatok);
        
		JPAContainer<Oneletrajz> cvs = JPAContainerFactory.make(Oneletrajz.class, "CV_Catalog");
		cvs.addNestedContainerProperty("felhasznalok.nev");
		Table cvTable = new Table("�n�letrajzok ny�lv�ntart�sa", cvs);
		cvTable.setVisibleColumns("id", "hozzaadva","felhasznalok.nev");
		cvTable.setColumnHeader("felhasznalok.nev", "K�sz�tette");
		cvTable.setColumnHeader("hozzaadva", "Hozz�adva");
		contentLayout.addComponent(cvTable);*/
				
		//cvs.getItem(0).getEntity().setFelhasznalok();
		/*
		Filter filter = new Compare.Greater("age", 117);
		persons.addContainerFilter(filter);
		*/
	}
	
	private void showBorders() {
		menuLayout.addStyleName("layout_border");
		contentLayout.addStyleName("layout_border");
		upperSection.addStyleName("layout_border");
		lowerSection.addStyleName("layout_border");
	}
}
