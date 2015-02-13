package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Orszagok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class szotar_orszag extends VerticalLayout implements View {
	private VerticalLayout mainSection = new VerticalLayout();
	private FieldGroup editorFields = new FieldGroup();
	
	public szotar_orszag(UI ui){
		mainSection.addComponent(u.MenuKeszites(ui));
		JPAContainer<Orszagok> orszagok = JPAContainerFactory.make(Orszagok.class, "CV_Catalog");
		//orszagok.addEntity(new Orszagok("Marie-Louise Meilleur", 117));
		//orszagok.sort(new String[]{"orszag", "megnevezes"},new boolean[]{false, false});
		Table orszagokTable = new Table("Országok kezelése", orszagok);
		
		orszagokTable.setVisibleColumns("orszag","megnevezes","tipus","penznem","nyelvkod");
		orszagokTable.setSelectable(true);
		orszagokTable.setImmediate(true);	
		mainSection.addComponent(orszagokTable);
		
		TextField orszag = new TextField("orszag");
		mainSection.addComponents(orszag);
		editorFields.bind(orszag, "orszag");	
		editorFields.setBuffered(false);

		editorFields.setItemDataSource(orszagokTable.getItem("orszag"));
		
		addComponent(mainSection);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
