package com.example.cv_catalog.views;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.NyelvSzint;
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

public class szotar_nyelvi_szint extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private Button addNewButton = new Button("�j");
	private Button removeButton = new Button("T�rl�s");
	
	private Table nyelvi_szintTable;
	private JPAContainer<NyelvSzint> nyelvi_szint;
	
	private FieldGroup editorFields = new FieldGroup();
	private TextField id_field, megnevezes;
	
	public szotar_nyelvi_szint(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(20);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		nyelvi_szint = JPAContainerFactory.make(NyelvSzint.class, "CV_Catalog");
		//orszagok.addEntity(new Orszagok("Marie-Louise Meilleur", 117));
		//orszagok.sort(new String[]{"orszag", "megnevezes"},new boolean[]{false, false});
		nyelvi_szintTable = new Table("Nyelvtud�s szintje", nyelvi_szint);
		nyelvi_szintTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = nyelvi_szintTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(nyelvi_szintTable.getItem(id));
				id_field.setReadOnly(true);
			}
		});
		
		
		nyelvi_szintTable.setVisibleColumns("id","megnevezes");
		nyelvi_szintTable.setSelectable(true);
		nyelvi_szintTable.setImmediate(true);	
		tableLayout.addComponent(nyelvi_szintTable);
		
		id_field = new TextField("Id:");	
		editLayout.addComponents(id_field);
		editorFields.bind(id_field, "id");	
		
		megnevezes = new TextField("Megnevez�s:");	
		editLayout.addComponents(megnevezes);
		editorFields.bind(megnevezes, "megnevezes");	
		megnevezes.setMaxLength(100);
		megnevezes.setImmediate(true);		
		megnevezes.addValidator(new StringLengthValidator("A nyelvk�d hossz�nak 1 �s 100 k�z�tt kell lennie!",1, 100, false));

		editLayout.addComponents(addNewButton,removeButton);
		
		editorFields.setBuffered(false);

		addComponent(mainLayout);
		this.initListeners();
	}
	
	public void initListeners() {
		
		addNewButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(!megnevezes.getValue().equals("")) {
					nyelvi_szint.addEntity(new NyelvSzint());
					nyelvi_szintTable.select(nyelvi_szint.getIdByIndex(nyelvi_szint.size()-1));	
					nyelvi_szintTable.setCurrentPageFirstItemIndex(nyelvi_szint.size()-1);
				}
			}
		});
		removeButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = nyelvi_szintTable.getValue();
				nyelvi_szintTable.removeItem(contactId);
				if(nyelvi_szint.size()  > 0) nyelvi_szintTable.select(nyelvi_szint.getIdByIndex(0));
			}
		});		
	}	
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(nyelvi_szint.size()  > 0) nyelvi_szintTable.select(nyelvi_szint.getIdByIndex(0));
	}
}
