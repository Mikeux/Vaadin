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
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField orszag;
	
	public szotar_orszag(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		JPAContainer<Orszagok> orszagok = JPAContainerFactory.make(Orszagok.class, "CV_Catalog");
		//orszagok.addEntity(new Orszagok("Marie-Louise Meilleur", 117));
		//orszagok.sort(new String[]{"orszag", "megnevezes"},new boolean[]{false, false});
		Table orszagokTable = new Table("Orsz�gok kezel�se", orszagok);
		orszagokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object orszagkod = orszagokTable.getValue();
				if (orszagkod != null)
					editorFields.setItemDataSource(orszagokTable.getItem(orszagkod));
					orszag.setReadOnly(true);
			}
		});
		
		
		orszagokTable.setVisibleColumns("orszag","megnevezes","tipus","penznem","nyelvkod");
		orszagokTable.setSelectable(true);
		orszagokTable.setImmediate(true);	
		tableLayout.addComponent(orszagokTable);
		
		orszag = new TextField("Orsz�g k�d:");	
		editLayout.addComponents(orszag);
		editorFields.bind(orszag, "orszag");	
		
		TextField megnevezes = new TextField("Orsz�g neve:");
		editLayout.addComponents(megnevezes);
		editorFields.bind(megnevezes, "megnevezes");	
		
		TextField tipus = new TextField("Orsz�g tipus:");
		editLayout.addComponents(tipus);
		editorFields.bind(tipus, "tipus");	
		
		TextField penznem = new TextField("Orsz�g p�nzneme:");
		editLayout.addComponents(penznem);
		editorFields.bind(penznem, "penznem");	

		TextField nyelvkod = new TextField("Orsz�g nyelvk�d:");
		editLayout.addComponents(nyelvkod);
		editorFields.bind(nyelvkod, "nyelvkod");			
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
