package com.example.cv_catalog.components;

import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Tanulmanyok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class TanulmanyokComponent extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<KepzesSzint> kepzesSzint;
	private JPAContainer<Tanulmanyok> tanulmanyAdatok;
	private Table tanulmanyAdatokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	private TextArea neve;
	private ComboBox kepzes_szintCombo;
	
	private Button addButton, deleteButton,rogzitButton;
	private DateField kezdete,vege;
	
	private Oneletrajz cv;
	
	public TanulmanyokComponent(Oneletrajz cv){
		this.cv = cv;
		
		kepzesSzint = JPAContainerFactory.make(KepzesSzint.class, "CV_Catalog");
		
		tanulmanyAdatok = JPAContainerFactory.make(Tanulmanyok.class, "CV_Catalog");
		tanulmanyAdatok.addNestedContainerProperty("oneletrajz.id");
		tanulmanyAdatok.addNestedContainerProperty("kepzesSzint.id");
		tanulmanyAdatok.addNestedContainerProperty("kepzesSzint.megnvezes");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		tanulmanyAdatok.addContainerFilter(filter);
		
		tanulmanyAdatokTable = new Table("Személyes adatok nyílvántartása", tanulmanyAdatok);
		tanulmanyAdatokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = 		tanulmanyAdatokTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(tanulmanyAdatokTable.getItem(id));
				}
				id_field.setReadOnly(true);
			}
		});
		tanulmanyAdatokTable.setVisibleColumns("id", "neve","kezdete","vege","kepzesSzint.megnvezes");
		//tanulmanyAdatokTable.setColumnHeader("felhasznalok.nev", "Készítette");
		//tanulmanyAdatokTable.setColumnHeader("hozzaadva", "Hozzáadva");
		tanulmanyAdatokTable.setSelectable(true);
		tanulmanyAdatokTable.setImmediate(true); 
		tanulmanyAdatokTable.setPageLength(5);
		layout.addComponent(tanulmanyAdatokTable);
        
		
		
		addButton = new Button("Hozzáad");
		deleteButton = new Button("Töröl");
		layout.addComponents(addButton,deleteButton,rogzitButton);
		
		id_field = new TextField("Id:");	
		editFields.addComponents(id_field);
		editorFields.bind(id_field, "id");
		
		neve = new TextArea("Képzés neve:");	
		editFields.addComponents(neve);
		//neve.setColumns(20);
		neve.setMaxLength(500);
		editorFields.bind(neve, "neve");
		
		kezdete = new DateField("Képzés kezdete:");	
		editFields.addComponents(kezdete);
		editorFields.bind(kezdete, "kezdete");
		
		vege = new DateField("Képzés vége:");	
		editFields.addComponents(vege);
		editorFields.bind(vege, "vege");	
						
		kepzes_szintCombo = new ComboBox("Képzési szint:", kepzesSzint);
		kepzes_szintCombo.setContainerDataSource(kepzesSzint);
		kepzes_szintCombo.setItemCaptionPropertyId("megnvezes");
		//nyelvekCombo.setImmediate(true);
		//nyelvekCombo.setBuffered(false);
		kepzes_szintCombo.setConverter(new SingleSelectConverter(kepzes_szintCombo));
		editFields.addComponents(kepzes_szintCombo);
		editorFields.bind(kepzes_szintCombo, "kepzesSzint");	
		
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeUndefined();

        setCompositionRoot(layout);
        
        //if(tanulmanyAdatok.size() == 0) tanulmanyAdatok.addEntity(new TanulmanyAdatok(this.cv));
        if(tanulmanyAdatok.size() > 0) tanulmanyAdatokTable.select(tanulmanyAdatok.getIdByIndex(0));
        else {
        	neve.setEnabled(false);
        	kezdete.setEnabled(false);
        	vege.setEnabled(false);
        	kepzes_szintCombo.setEnabled(false);
        }
        
        init();
        //layout.addComponent(new Label());
	}
	
	public void init(){
		addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				tanulmanyAdatok.addEntity(new Tanulmanyok(cv));
				tanulmanyAdatokTable.select(tanulmanyAdatok.getIdByIndex(tanulmanyAdatok.size()-1));		
				tanulmanyAdatokTable.setCurrentPageFirstItemIndex(tanulmanyAdatok.size()-1);
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = tanulmanyAdatokTable.getValue();
				tanulmanyAdatokTable.removeItem(contactId);
				if(tanulmanyAdatok.size()  > 0) tanulmanyAdatokTable.select(tanulmanyAdatok.getIdByIndex(0));
				else
				{
		        	neve.setEnabled(false);
		        	kezdete.setEnabled(false);
		        	vege.setEnabled(false);
		        	kepzes_szintCombo.setEnabled(false);
		        	neve.setValue("");
				}
			}
		});			
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				tanulmanyAdatok.commit();
			}
		});
	}
	
}