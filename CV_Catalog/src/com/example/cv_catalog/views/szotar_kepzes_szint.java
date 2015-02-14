package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.NyelvSzint;
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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class szotar_kepzes_szint extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField id_field;	
	
	public szotar_kepzes_szint(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(30);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		JPAContainer<KepzesSzint> kepzes_szint = JPAContainerFactory.make(KepzesSzint.class, "CV_Catalog");
		//orszagok.addEntity(new Orszagok("Marie-Louise Meilleur", 117));
		//orszagok.sort(new String[]{"orszag", "megnevezes"},new boolean[]{false, false});
		Table kepzes_szintTable = new Table("Képzés szintje", kepzes_szint);
		kepzes_szintTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = kepzes_szintTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(kepzes_szintTable.getItem(id));
				id_field.setReadOnly(true);
			}
		});
		
		
		kepzes_szintTable.setVisibleColumns("id","megnvezes");
		kepzes_szintTable.setSelectable(true);
		kepzes_szintTable.setImmediate(true);	
		tableLayout.addComponent(kepzes_szintTable);
		
		id_field = new TextField("Id:");	
		editLayout.addComponents(id_field);
		editorFields.bind(id_field, "id");	
		
		TextField megnvezes = new TextField("Megnevezés:");	
		megnvezes.setSizeFull();
		editLayout.addComponents(megnvezes);
		editorFields.bind(megnvezes, "megnvezes");	
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
