package com.example.cv_catalog.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToDateConverter;
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
	private Button removeButton = new Button("Törlés");
	
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
		
		//https://vaadin.com/forum#!/thread/1076201
		oneletrajzok = JPAContainerFactory.make(Oneletrajz.class, "CV_Catalog");
		oneletrajzok.addNestedContainerProperty("felhasznalok.nev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.vezetekNev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.keresztNev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.szulIdo");
		oneletrajzokTable = new Table("Önéletrajzok nyílvántartása", oneletrajzok);
		oneletrajzokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = oneletrajzokTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(oneletrajzokTable.getItem(id));
				//id_field.setReadOnly(true);
			}
		});
		oneletrajzokTable.setVisibleColumns("felhasznalok.nev", "hozzaadva",
												"szemelyesAdatok.vezetekNev",
												"szemelyesAdatok.keresztNev",
												"szemelyesAdatok.szulIdo");
		oneletrajzokTable.setColumnHeader("felhasznalok.nev", "Létrehozta");
		oneletrajzokTable.setColumnHeader("hozzaadva", "Hozzáadva");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.vezetekNev", "Vezetéknév");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.keresztNev", "Keresztnév");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.szulIdo", "Születési idõ");
		
		oneletrajzokTable.setSelectable(true);
		oneletrajzokTable.setImmediate(true);	
		tableLayout.addComponent(oneletrajzokTable);
			
		/*id_field = new TextField("Id:");	
		editLayout.addComponents(id_field);
		editorFields.bind(id_field, "id");	
		
		megnvezes = new TextField("Megnevezés:");	
		megnvezes.setSizeFull();
		megnvezes.setColumns(20);
		megnvezes.setMaxLength(100);
		megnvezes.setImmediate(true);
		//megnvezes.setBuffered(true);
		//megnvezes.setRequired(true);
		megnvezes.addValidator(new StringLengthValidator("A megnevezés hosszának 1 és 100 között kell lennie!",1, 100, false));
		editLayout.addComponents(megnvezes);
		editorFields.bind(megnvezes, "megnvezes");	
		*/
		editLayout.addComponents(editButton,removeButton);
		
		editorFields.setBuffered(false);

        //setSizeFull();
		//tableLayout.setSizeFull();
		oneletrajzokTable.setSizeFull();
		
		addComponent(mainLayout);
		this.init();
	}
	
	public void init() {
		oneletrajzokTable.setConverter("szemelyesAdatok.szulIdo", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });		        
		
		oneletrajzokTable.setConverter("hozzaadva", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });				
		
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
