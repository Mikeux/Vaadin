package com.example.cv_catalog.views;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {

	private VerticalLayout mainSection = new VerticalLayout();
	private HorizontalLayout upperSection = new HorizontalLayout();
	private HorizontalSplitPanel lowerSection = new	HorizontalSplitPanel();
	private VerticalLayout menuLayout = new VerticalLayout();
	private VerticalLayout contentLayout = new VerticalLayout();
	
	private Tree menuTree;
	
	public MainView(UI ui){
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
		contentLayout.setSizeFull();
		
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
		
		//this.addMenuOption("Option 1", new Label("Component 1"));
		//this.addMenuOption("Option 2", new Label("Component 2"));
		init();
		
		this.showBorders();
	}
	
	private void init(){
		menuTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
		        // Pick only left mouse clicks
		        if (event.getButton() == ItemClickEvent.BUTTON_LEFT)
		            Notification.show("Left click",Notification.Type.HUMANIZED_MESSAGE);
		    }
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit("CV_Catalog");
		
		/*em.getTransaction().begin();
		//em.createQuery("DELETE FROM Person p").executeUpdate();
		Oneletrajz ol = new Oneletrajz();
		ol.setfk_felhasznalok(11);
		ol.setHozzaadva(new Date());
		em.persist(ol);
		em.getTransaction().commit();*/
		
		/*TypedQuery<Object[]> q = em.createQuery(
			    "SELECT c.id, count(p.id) " +
			    "FROM Product p LEFT JOIN p.category c " +
			    "WHERE p.seller.id = :id " +
			    "GROUP BY c.id", Object[].class).setParameter("id", 1);		
		List<Object[]> resultList = q.getResultList();
		Map<String, Long> resultMap = new HashMap<String, Long>(resultList.size());
		for (Object[] result : resultList)
		  resultMap.put((String)result[0], (Long)result[1]);*/	
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
