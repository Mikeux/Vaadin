package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Nyelvek;
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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class szotar_nyelvek  extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField nyelvkod;
	
	public szotar_nyelvek(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		JPAContainer<Nyelvek> nyelvek = JPAContainerFactory.make(Nyelvek.class, "CV_Catalog");
		Table nyelvekTable = new Table("Nyelvek kezel�se", nyelvek);
		nyelvekTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object o = nyelvekTable.getValue();
				if (o != null)
					editorFields.setItemDataSource(nyelvekTable.getItem(o));
				nyelvkod.setReadOnly(true);
			}
		});
		
		
		nyelvekTable.setVisibleColumns("nyelvkod","nyelv","karakterKeszlet");
		nyelvekTable.setSelectable(true);
		nyelvekTable.setImmediate(true);	
		tableLayout.addComponent(nyelvekTable);
		
		nyelvkod = new TextField("Nyelvk�d k�d:");	
		editLayout.addComponents(nyelvkod);
		editorFields.bind(nyelvkod, "nyelvkod");	
		
		TextField nyelv = new TextField("Megnevez�s:");
		editLayout.addComponents(nyelv);
		editorFields.bind(nyelv, "nyelv");	
		
		TextField karakterKeszlet = new TextField("Karakterk�szlet:");
		editLayout.addComponents(karakterKeszlet);
		editorFields.bind(karakterKeszlet, "karakterKeszlet");	
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
