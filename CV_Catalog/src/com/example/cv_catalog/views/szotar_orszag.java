package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Orszagok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class szotar_orszag extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private JPAContainer<Orszagok> orszagok;
	private Table orszagokTable;
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField id;
	
	public szotar_orszag(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		orszagok = JPAContainerFactory.make(Orszagok.class, "CV_Catalog");
		//orszagok.addEntity(new Orszagok("Marie-Louise Meilleur", 117));
		//orszagok.sort(new String[]{"orszag", "megnevezes"},new boolean[]{false, false});
		orszagokTable = new Table("Országok kezelése", orszagok);
		orszagokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object orszagkod = orszagokTable.getValue();
				if (orszagkod != null)
					editorFields.setItemDataSource(orszagokTable.getItem(orszagkod));
					id.setReadOnly(true);
			}
		});
		
		
		orszagokTable.setVisibleColumns("id","orszag","megnevezes","tipus","penznem","nyelvkod");
		orszagokTable.setSelectable(true);
		orszagokTable.setImmediate(true);	
		tableLayout.addComponent(orszagokTable);
		
		id = new TextField("Ország id:");	
		editLayout.addComponents(id);
		editorFields.bind(id, "id");	
		
		TextField orszag = new TextField("Ország kód:");	
		editLayout.addComponents(orszag);
		editorFields.bind(orszag, "orszag");	
		
		TextField megnevezes = new TextField("Ország neve:");
		editLayout.addComponents(megnevezes);
		editorFields.bind(megnevezes, "megnevezes");	
		
		TextField tipus = new TextField("Ország tipus:");
		editLayout.addComponents(tipus);
		editorFields.bind(tipus, "tipus");	
		
		TextField penznem = new TextField("Ország pénzneme:");
		editLayout.addComponents(penznem);
		editorFields.bind(penznem, "penznem");	

		TextField nyelvkod = new TextField("Ország nyelvkód:");
		editLayout.addComponents(nyelvkod);
		editorFields.bind(nyelvkod, "nyelvkod");			
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(orszagok.size()  > 0) orszagokTable.select(orszagok.getIdByIndex(0));				
	}
}
