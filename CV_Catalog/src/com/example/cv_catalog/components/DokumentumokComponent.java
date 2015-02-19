package com.example.cv_catalog.components;

import com.example.cv_catalog.Uploader;
import com.example.cv_catalog.model.DokumentumTipus;
import com.example.cv_catalog.model.Dokumentumok;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

public class DokumentumokComponent  extends CustomComponent {
	
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<Dokumentumok> dokumentumok;
	private JPAContainer<DokumentumTipus> dokumentumTipus;
	private Table dokumentumokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextArea leiras;
	private ComboBox dokumentumTipusCombo;
	
	private Button addButton, deleteButton,rogzitButton;
	private DateField kezdete,vege;
	
	private Oneletrajz cv;
	
	public DokumentumokComponent(Oneletrajz cv){
		this.cv = cv;

		dokumentumTipus = JPAContainerFactory.make(DokumentumTipus.class, "CV_Catalog");
		
		dokumentumok = JPAContainerFactory.make(Dokumentumok.class, "CV_Catalog");
		dokumentumok.addNestedContainerProperty("oneletrajz.id");
		dokumentumok.addNestedContainerProperty("dokumentum_tipus.id");
		dokumentumok.addNestedContainerProperty("dokumentum_tipus.megnevezes");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		dokumentumok.addContainerFilter(filter);
		
		dokumentumokTable = new Table("Csatolm�nyok ny�lv�ntart�sa", dokumentumok);
		dokumentumokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = dokumentumokTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(dokumentumokTable.getItem(id));
				}
			}
		});
		dokumentumokTable.setVisibleColumns("fajlNeve","dokumentum_tipus.megnevezes");
		dokumentumokTable.setColumnHeader("fajlNeve", "F�jl neve");	
		dokumentumokTable.setColumnHeader("dokumentum_tipus.megnevezes", "Csatolm�ny t�pusa");	
		dokumentumokTable.setColumnWidth("fajlNeve", 500);
		//dokumentumokTable.setColumnExpandRatio("leiras", 1);
		dokumentumokTable.setSelectable(true);
		dokumentumokTable.setImmediate(true); 
		dokumentumokTable.setPageLength(5);
		layout.addComponent(dokumentumokTable);
        		
		addButton = new Button("Hozz�ad");
		deleteButton = new Button("T�r�l");
		rogzitButton = new Button("R�gz�t");
		layout.addComponents(addButton,deleteButton,rogzitButton);
		
		ProgressBar bar = new ProgressBar();
		bar.setVisible(false);
		bar.setIndeterminate(true);		
		
		Embedded image = new Embedded("Felt�lt�tt k�p");
		image.setVisible(false);
		editFields.addComponents(image);
		
		Uploader receiver = new Uploader();
		Upload upload = new Upload("Csatlom�ny felt�lt�se", receiver);
		upload.setButtonCaption("Felt�lt");
		upload.addSucceededListener(receiver);
		//upload.addStartedListener(listener);
		//upload.addProgressListener(receiver);
		/*Panel panel = new Panel("Cool Image Storage");
		Layout panelContent = new VerticalLayout();
		panelContent.addComponents(upload, image);
		panel.setContent(panelContent);*/
		editFields.addComponents(upload);
				
		dokumentumTipusCombo = new ComboBox("Csaktolm�ny t�pusa:", dokumentumTipus);
		dokumentumTipusCombo.setContainerDataSource(dokumentumTipus);
		dokumentumTipusCombo.setItemCaptionPropertyId("megnevezes");
		dokumentumTipusCombo.setConverter(new SingleSelectConverter(dokumentumTipusCombo));
		editFields.addComponents(dokumentumTipusCombo);
		editorFields.bind(dokumentumTipusCombo, "dokumentumTipus");	
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeFull();
        layout.setSizeFull();
        dokumentumokTable.setSizeFull();

    
        setCompositionRoot(layout);
        
        if(dokumentumok.size() > 0) dokumentumokTable.select(dokumentumok.getIdByIndex(0));
        else {
        	//leiras.setEnabled(false);
        }
        
        init();
	}
	
	public void init(){
		addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				dokumentumok.addEntity(new Dokumentumok(cv));
				dokumentumokTable.select(dokumentumok.getIdByIndex(dokumentumok.size()-1));		
				dokumentumokTable.setCurrentPageFirstItemIndex(dokumentumok.size()-1);
			}
		});
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = dokumentumokTable.getValue();
				dokumentumokTable.removeItem(contactId);
				if(dokumentumok.size()  > 0) dokumentumokTable.select(dokumentumok.getIdByIndex(0));
				else
				{
					//leiras.setEnabled(false);
				}
			}
		});
		
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				dokumentumok.commit();
			}
		});
	}
	
}