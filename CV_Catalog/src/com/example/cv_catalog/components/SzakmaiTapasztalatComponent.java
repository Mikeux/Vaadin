package com.example.cv_catalog.components;

import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.SzakmaiTapasztalat;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
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
		
		munkaadoNeve = new TextArea("Munkaadó neve:");	
		editFields.addComponents(munkaadoNeve);
		//neve.setColumns(20);
		munkaadoNeve.setMaxLength(500);
		editorFields.bind(munkaadoNeve, "munkaadoNeve");
		
		leiras = new TextArea("Leírás:");	
		editFields.addComponents(leiras);
		//neve.setColumns(20);
		leiras.setMaxLength(500);
		editorFields.bind(leiras, "leiras");
		
		
		pozicioNeve = new TextArea("Pozíció neve:");	
		editFields.addComponents(pozicioNeve);
		//neve.setColumns(20);
		pozicioNeve.setMaxLength(500);
		editorFields.bind(pozicioNeve, "pozicioNeve");
				
		
		kezdete = new DateField("Képzés kezdete:");	
		editFields.addComponents(kezdete);
		editorFields.bind(kezdete, "kezdete");
		
		vege = new DateField("Képzés vége:");	
		editFields.addComponents(vege);
		editorFields.bind(vege, "vege");	
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeUndefined();

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