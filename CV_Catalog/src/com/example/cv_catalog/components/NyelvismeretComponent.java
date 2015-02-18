package com.example.cv_catalog.components;

import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.NyelvSzint;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Nyelvismeret;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Orszagok;
import com.example.cv_catalog.model.Tanulmanyok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Property;
import com.vaadin.data.Container.Filter;
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

public class NyelvismeretComponent  extends CustomComponent {
	
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<Nyelvismeret> nyelvismeret;
	private JPAContainer<Nyelvek> nyelvek;
	private JPAContainer<NyelvSzint> nyelvszint;
	private Table nyelvismeretTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextArea neve;
	private ComboBox nyelvCombo,nyelvszintCombo;
	
	private Button addButton, deleteButton,rogzitButton;
	private DateField kezdete,vege;
	
	private Oneletrajz cv;
	
	public NyelvismeretComponent(Oneletrajz cv){
		this.cv = cv;
		
		nyelvek = JPAContainerFactory.make(Nyelvek.class, "CV_Catalog");
		nyelvszint = JPAContainerFactory.make(NyelvSzint.class, "CV_Catalog");
		
		nyelvismeret = JPAContainerFactory.make(Nyelvismeret.class, "CV_Catalog");
		nyelvismeret.addNestedContainerProperty("oneletrajz.id");
		nyelvismeret.addNestedContainerProperty("nyelvek.id");
		nyelvismeret.addNestedContainerProperty("nyelvek.nyelv");		
		nyelvismeret.addNestedContainerProperty("nyelvszint.id");
		nyelvismeret.addNestedContainerProperty("nyelvszint.megnevezes");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		nyelvismeret.addContainerFilter(filter);
		
		nyelvismeretTable = new Table("Nyelvi ismeretek nyílvántartása", nyelvismeret);
		nyelvismeretTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = nyelvismeretTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(nyelvismeretTable.getItem(id));
				}
			}
		});
		nyelvismeretTable.setVisibleColumns("nyelvek.nyelv","nyelvszint.megnevezes");
		nyelvismeretTable.setColumnHeader("nyelvek.nyelv", "Nyelv");
		nyelvismeretTable.setColumnHeader("nyelvszint.megnevezes", "Szint");		
		nyelvismeretTable.setSelectable(true);
		nyelvismeretTable.setImmediate(true); 
		nyelvismeretTable.setPageLength(5);
		layout.addComponent(nyelvismeretTable);
        		
		addButton = new Button("Hozzáad");
		deleteButton = new Button("Töröl");
		rogzitButton = new Button("Rögzít");
		layout.addComponents(addButton,deleteButton,rogzitButton);
		
		nyelvCombo = new ComboBox("Képzési szint:", nyelvek);
		nyelvCombo.setContainerDataSource(nyelvek);
		nyelvCombo.setItemCaptionPropertyId("nyelv");
		nyelvCombo.setConverter(new SingleSelectConverter(nyelvCombo));
		editFields.addComponents(nyelvCombo);
		editorFields.bind(nyelvCombo, "nyelvek");	
		
		nyelvszintCombo = new ComboBox("Képzési szint:", nyelvszint);
		nyelvszintCombo.setContainerDataSource(nyelvszint);
		nyelvszintCombo.setItemCaptionPropertyId("megnevezes");
		nyelvszintCombo.setConverter(new SingleSelectConverter(nyelvszintCombo));
		editFields.addComponents(nyelvszintCombo);
		editorFields.bind(nyelvszintCombo, "nyelvszint");	
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeFull();
        layout.setSizeFull();
        nyelvismeretTable.setSizeFull();


        setCompositionRoot(layout);
        
        //if(tanulmanyAdatok.size() == 0) tanulmanyAdatok.addEntity(new TanulmanyAdatok(this.cv));
        if(nyelvismeret.size() > 0) nyelvismeretTable.select(nyelvismeret.getIdByIndex(0));
        else {
        	nyelvCombo.setEnabled(false);
        	nyelvszintCombo.setEnabled(false);
        }
        
        init();
	}
	
	public void init(){
		addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				nyelvismeret.addEntity(new Nyelvismeret(cv));
				nyelvismeretTable.select(nyelvismeret.getIdByIndex(nyelvismeret.size()-1));		
				nyelvismeretTable.setCurrentPageFirstItemIndex(nyelvismeret.size()-1);
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = nyelvismeretTable.getValue();
				nyelvismeretTable.removeItem(contactId);
				if(nyelvismeret.size()  > 0) nyelvismeretTable.select(nyelvismeret.getIdByIndex(0));
				else
				{
        	nyelvCombo.setEnabled(false);
        	nyelvszintCombo.setEnabled(false);
				}
			}
		});
		
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				nyelvismeret.commit();
			}
		});
	}
	
}