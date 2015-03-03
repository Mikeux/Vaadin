package com.example.cv_catalog.components;

import com.example.cv_catalog.model.EgyebKeszsegek;
import com.example.cv_catalog.model.Oneletrajz;
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
import com.vaadin.ui.VerticalLayout;

public class EgyebKeszsegekComponent  extends CustomComponent {
	
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<EgyebKeszsegek> egyebkeszsegek;
	private Table egyebkeszsegekTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextArea leiras;
	
	private Button addButton, deleteButton,rogzitButton;
	private DateField kezdete,vege;
	
	private Oneletrajz cv;
	
	public EgyebKeszsegekComponent(Oneletrajz cv){
		this.cv = cv;

		egyebkeszsegek = JPAContainerFactory.make(EgyebKeszsegek.class, "CV_Catalog");
		egyebkeszsegek.addNestedContainerProperty("oneletrajz.id");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		egyebkeszsegek.addContainerFilter(filter);
		
		egyebkeszsegekTable = new Table("Egyéb készségek nyílvántartása", egyebkeszsegek);
		egyebkeszsegekTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = egyebkeszsegekTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(egyebkeszsegekTable.getItem(id));
				}
			}
		});
		egyebkeszsegekTable.setVisibleColumns("leiras");
		egyebkeszsegekTable.setColumnHeader("leiras", "Tevékenység leírása");	
		//egyebkeszsegekTable.setColumnWidth("leiras", 700);
		//egyebkeszsegekTable.setColumnExpandRatio("leiras", 1);
		egyebkeszsegekTable.setSelectable(true);
		egyebkeszsegekTable.setImmediate(true); 
		egyebkeszsegekTable.setPageLength(5);
		layout.addComponent(egyebkeszsegekTable);
      		
		addButton = new Button("Hozzáad");
		deleteButton = new Button("Töröl");
		rogzitButton = new Button("Rögzít");
		layout.addComponents(addButton,deleteButton,rogzitButton);
		
		leiras = new TextArea("Tevékenység leírása:");	
		editFields.addComponents(leiras);
		leiras.setColumns(30);
		leiras.setMaxLength(1024);
		editorFields.bind(leiras, "leiras");		
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeFull();
        layout.setSizeFull();
        egyebkeszsegekTable.setSizeFull();
    
        setCompositionRoot(layout);
        
        if(egyebkeszsegek.size() > 0) egyebkeszsegekTable.select(egyebkeszsegek.getIdByIndex(0));
        else {
        	leiras.setEnabled(false);
        }
        
        init();
	}
	
	public void init(){
		addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				egyebkeszsegek.addEntity(new EgyebKeszsegek(cv));
				egyebkeszsegekTable.select(egyebkeszsegek.getIdByIndex(egyebkeszsegek.size()-1));		
				egyebkeszsegekTable.setCurrentPageFirstItemIndex(egyebkeszsegek.size()-1);
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = egyebkeszsegekTable.getValue();
				egyebkeszsegekTable.removeItem(contactId);
				if(egyebkeszsegek.size()  > 0) egyebkeszsegekTable.select(egyebkeszsegek.getIdByIndex(0));
				else
				{
					leiras.setEnabled(false);
				}
			}
		});
		
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				egyebkeszsegek.commit();
				egyebkeszsegekTable.refreshRowCache();
			}
		});
	}
	
}