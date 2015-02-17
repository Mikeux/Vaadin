package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Oneletrajz;
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

public class Oneletrajzok extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private Button editButton = new Button("Szerkeszt");
	private Button removeButton = new Button("T�rl�s");
	
	private JPAContainer<Oneletrajz> oneletrajzok;
	private Table oneletrajzokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	
	public Oneletrajzok(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		oneletrajzok = JPAContainerFactory.make(Oneletrajz.class, "CV_Catalog");
		oneletrajzok.addNestedContainerProperty("felhasznalok.nev");
		oneletrajzokTable = new Table("�n�letrajzok ny�lv�ntart�sa", oneletrajzok);
		oneletrajzokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = oneletrajzokTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(oneletrajzokTable.getItem(id));
				id_field.setReadOnly(true);
			}
		});
		oneletrajzokTable.setVisibleColumns("id", "hozzaadva","felhasznalok.nev");
		oneletrajzokTable.setColumnHeader("felhasznalok.nev", "K�sz�tette");
		oneletrajzokTable.setColumnHeader("hozzaadva", "Hozz�adva");
		oneletrajzokTable.setSelectable(true);
		oneletrajzokTable.setImmediate(true);	
		tableLayout.addComponent(oneletrajzokTable);
			
		id_field = new TextField("Id:");	
		editLayout.addComponents(id_field);
		editorFields.bind(id_field, "id");	
		/*
		megnvezes = new TextField("Megnevez�s:");	
		megnvezes.setSizeFull();
		megnvezes.setColumns(20);
		megnvezes.setMaxLength(100);
		megnvezes.setImmediate(true);
		//megnvezes.setBuffered(true);
		//megnvezes.setRequired(true);
		megnvezes.addValidator(new StringLengthValidator("A megnevez�s hossz�nak 1 �s 100 k�z�tt kell lennie!",1, 100, false));
		editLayout.addComponents(megnvezes);
		editorFields.bind(megnvezes, "megnvezes");	
		*/
		editLayout.addComponents(editButton,removeButton);
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		this.initListeners();
	}
	
	public void initListeners() {
		        
		editButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object Id = oneletrajzokTable.getValue();
				getUI().getNavigator().navigateTo("cv_edit/"+Id);
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
	}
}
