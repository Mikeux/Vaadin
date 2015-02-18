package com.example.cv_catalog.views;

import javax.persistence.EntityManager;

import com.example.cv_catalog.u;
import com.example.cv_catalog.components.NyelvismeretComponent;
import com.example.cv_catalog.components.SzakmaiTapasztalatComponent;
import com.example.cv_catalog.components.SzemelyesAdatokComponent;
import com.example.cv_catalog.components.TanulmanyokComponent;
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
	private TanulmanyokComponent tanulmanyok;
	private SzakmaiTapasztalatComponent szakmaitapasztalat;
	private NyelvismeretComponent nyelvismeret;
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
		
		menuTree = new Tree("Önéletrajz adatai");
		menuTree.addItem("Személyes adatok");
		menuTree.addItem("Tanulmányok");
		menuTree.addItem("Szakmai tapasztalat");
		menuTree.addItem("Nyelvismeret");
		menuTree.addItem("Egyéb készségek");
		menuTree.addItem("Dokumentációk");
		menuLayout.addComponent(menuTree);		
		
		this.init();
		this.showBorders();
		
	}
	
	public void init(){
		menuTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				contentLayout.removeAllComponents();
				if(event.getItemId().toString() == "Személyes adatok") {
					altalanosAdatok = new SzemelyesAdatokComponent(cv);
					contentLayout.addComponent(altalanosAdatok);
				} else if(event.getItemId().toString() == "Tanulmányok") {
					tanulmanyok = new TanulmanyokComponent(cv);
					contentLayout.addComponent(tanulmanyok);
				} else if(event.getItemId().toString() == "Szakmai tapasztalat") {
					szakmaitapasztalat = new SzakmaiTapasztalatComponent(cv);
					contentLayout.addComponent(szakmaitapasztalat);
				} else if(event.getItemId().toString() == "Nyelvismeret") {
					nyelvismeret = new NyelvismeretComponent(cv);
					contentLayout.addComponent(nyelvismeret);
				}
				//u.uzen(event.getItemId().toString());				
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		contentLayout.removeAllComponents();
        if (event.getParameters() == null || event.getParameters().isEmpty()) {
        	contentLayout.addComponent(new Label("Nincs paraméter"));
            return;
        } else {
        	this.cv = u.EntityManager.find(Oneletrajz.class,Integer.parseInt(event.getParameters()));	
        }
        
        menuTree.select("Személyes adatok");
		altalanosAdatok = new SzemelyesAdatokComponent(cv);
		contentLayout.addComponent(altalanosAdatok);
	}
	
	private void showBorders() {
		menuLayout.addStyleName("layout_border");
		contentLayout.addStyleName("layout_border");
		upperSection.addStyleName("layout_border");
		lowerSection.addStyleName("layout_border");
	}
}
