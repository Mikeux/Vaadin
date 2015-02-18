package com.example.cv_catalog.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.SzakmaiTapasztalat;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SzakmaiTapasztalatComponent extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<SzakmaiTapasztalat> szakmaiTapasztalat;
	private Table szakmaiTapasztalatTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	private TextArea munkaadoNeve,pozicioNeve,leiras;
	
	private Button addButton, deleteButton, rogzitButton;
	private DateField kezdete,vege;
	
	private Oneletrajz cv;
	
	public SzakmaiTapasztalatComponent(Oneletrajz cv){
		this.cv = cv;
		
		/*Form  countryForm  = new Form();
		FieldFactory fieldFactory = new FieldFactory();
		countryForm.setFormFieldFactory(fieldFactory);
		countryForm.setItemDataSource(szakmaiTapasztalatTable.getItem(id));
        countryForm.setEnabled(true);	
        layout.addComponent(countryForm);*/
		
		szakmaiTapasztalat = JPAContainerFactory.make(SzakmaiTapasztalat.class, "CV_Catalog");       
		szakmaiTapasztalat.addNestedContainerProperty("oneletrajz.id");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		szakmaiTapasztalat.addContainerFilter(filter);
		
		
		szakmaiTapasztalatTable = new Table("Szakmai tapasztalatok nyílvántartása", szakmaiTapasztalat);
		szakmaiTapasztalatTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = szakmaiTapasztalatTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(szakmaiTapasztalatTable.getItem(id));					
				}
				id_field.setReadOnly(true);
			}
		});
		szakmaiTapasztalatTable.setVisibleColumns("id", "munkaadoNeve","kezdete","vege","leiras","pozicioNeve");
		//tanulmanyAdatokTable.setColumnHeader("felhasznalok.nev", "Készítette");
		//tanulmanyAdatokTable.setColumnHeader("hozzaadva", "Hozzáadva");
		szakmaiTapasztalatTable.setSelectable(true);
		szakmaiTapasztalatTable.setImmediate(true); 
		szakmaiTapasztalatTable.setPageLength(5);
		layout.addComponent(szakmaiTapasztalatTable);
        
		addButton = new Button("Hozzáad");
		deleteButton = new Button("Töröl");
		rogzitButton = new Button("Rögzít");
		layout.addComponents(addButton,deleteButton,rogzitButton);
		
		id_field = new TextField("Id:");	
		editFields.addComponents(id_field);
		editorFields.bind(id_field, "id");
		
		kezdete = new DateField("Kezdete:");	
		editFields.addComponents(kezdete);
		editorFields.bind(kezdete, "kezdete");
		
		vege = new DateField("Vége:");	
		editFields.addComponents(vege);
		editorFields.bind(vege, "vege");			
		
		munkaadoNeve = new TextArea("Munkaadó neve:");	
		editFields.addComponents(munkaadoNeve);
		munkaadoNeve.setColumns(25);
		munkaadoNeve.setMaxLength(500);
		editorFields.bind(munkaadoNeve, "munkaadoNeve");
		
		leiras = new TextArea("Leírás:");	
		editFields.addComponents(leiras);
		leiras.setColumns(30);
		leiras.setMaxLength(500);
		editorFields.bind(leiras, "leiras");
		
		
		pozicioNeve = new TextArea("Pozíció neve:");	
		editFields.addComponents(pozicioNeve);
		pozicioNeve.setColumns(30);
		pozicioNeve.setMaxLength(500);
		editorFields.bind(pozicioNeve, "pozicioNeve");
						
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeFull();
        layout.setSizeFull();
        szakmaiTapasztalatTable.setSizeFull();

        setCompositionRoot(layout);
        
        //if(tanulmanyAdatok.size() == 0) tanulmanyAdatok.addEntity(new TanulmanyAdatok(this.cv));
        if(szakmaiTapasztalatTable.size() > 0) szakmaiTapasztalatTable.select(szakmaiTapasztalat.getIdByIndex(0));
        else {
        	munkaadoNeve.setEnabled(false);
        	leiras.setEnabled(false);
        	pozicioNeve.setEnabled(false);
        	kezdete.setEnabled(false);
        	vege.setEnabled(false);
        	
        }
		
        init();
        //layout.addComponent(new Label());
	}
	
	public void init(){
		szakmaiTapasztalatTable.setConverter("kezdete", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });
		
		szakmaiTapasztalatTable.setConverter("vege", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });
		
		addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				szakmaiTapasztalat.addEntity(new SzakmaiTapasztalat(cv));
				szakmaiTapasztalatTable.select(szakmaiTapasztalat.getIdByIndex(szakmaiTapasztalat.size()-1));		
				szakmaiTapasztalatTable.setCurrentPageFirstItemIndex(szakmaiTapasztalat.size()-1);
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = szakmaiTapasztalatTable.getValue();
				szakmaiTapasztalatTable.removeItem(contactId);
				if(szakmaiTapasztalat.size()  > 0) szakmaiTapasztalatTable.select(szakmaiTapasztalat.getIdByIndex(0));
				else
				{
		        	munkaadoNeve.setEnabled(false);
		        	leiras.setEnabled(false);
		        	pozicioNeve.setEnabled(false);
		        	kezdete.setEnabled(false);
		        	vege.setEnabled(false);
				}
			}
		});	
		
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				szakmaiTapasztalat.commit();
			}
		});
	
	}
	
}