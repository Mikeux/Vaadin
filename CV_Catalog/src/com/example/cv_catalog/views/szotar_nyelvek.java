package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Orszagok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class szotar_nyelvek  extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	private Table nyelvekTable;
	private JPAContainer<Nyelvek> nyelvek;
	
	private Button addNewButton = new Button("Új");
	private Button removeButton = new Button("Törlés");
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField id,nyelvkod;	
	
	public szotar_nyelvek(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		nyelvek = JPAContainerFactory.make(Nyelvek.class, "CV_Catalog");
		nyelvekTable = new Table("Nyelvek kezelése", nyelvek);
		nyelvekTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object o = nyelvekTable.getValue();
				if (o != null)
					editorFields.setItemDataSource(nyelvekTable.getItem(o));
				id.setReadOnly(true);
			}
		});
		
		
		nyelvekTable.setVisibleColumns("id","nyelvkod","nyelv","karakterKeszlet");
		nyelvekTable.setSelectable(true);
		nyelvekTable.setImmediate(true);	
		tableLayout.addComponent(nyelvekTable);
				
		id = new TextField("Nyelvkód id:");	
		editLayout.addComponents(id);
		editorFields.bind(id, "id");		
		
		nyelvkod = new TextField("Nyelvkód kód:");	
		editLayout.addComponents(nyelvkod);
		editorFields.bind(nyelvkod, "nyelvkod");	
		nyelvkod.setMaxLength(4);
		nyelvkod.setImmediate(true);		
		nyelvkod.addValidator(new StringLengthValidator("A nyelvkód hosszának 1 és 4 között kell lennie!",1, 4, false));
		
		TextField nyelv = new TextField("Megnevezés:");
		editLayout.addComponents(nyelv);
		editorFields.bind(nyelv, "nyelv");	
		nyelv.setMaxLength(15);
		nyelv.setImmediate(true);		
		nyelv.addValidator(new StringLengthValidator("A nyelv megnevezésének hosszának 1 és 15 között kell lennie!",1, 15, false));
		
		
		TextField karakterKeszlet = new TextField("Karakterkészlet:");
		editLayout.addComponents(karakterKeszlet);
		editorFields.bind(karakterKeszlet, "karakterKeszlet");	
		karakterKeszlet.setMaxLength(15);
		karakterKeszlet.setImmediate(true);		
		//karakterKeszlet.addValidator(new StringLengthValidator("A karakterkészlet hosszának 1 és 15 között kell lennie!",1, 15, false));
		
		editLayout.addComponents(addNewButton,removeButton);
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		this.initListeners();
		
	}
	
	public void initListeners() {
		
		addNewButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(!nyelvkod.getValue().equals("")) {
					nyelvek.addEntity(new Nyelvek());
					nyelvekTable.select(nyelvek.getIdByIndex(nyelvek.size()-1));	
					nyelvekTable.setCurrentPageFirstItemIndex(nyelvek.size()-1);
				}
			}
		});
		removeButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = nyelvekTable.getValue();
				nyelvekTable.removeItem(contactId);
				if(nyelvek.size()  > 0) nyelvekTable.select(nyelvek.getIdByIndex(0));
			}
		});		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(nyelvek.size()  > 0) nyelvekTable.select(nyelvek.getIdByIndex(0));		
	}
}
