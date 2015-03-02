package com.example.cv_catalog.components;

import java.io.File;

import javax.persistence.TypedQuery;

import com.example.cv_catalog.Uploader;
import com.example.cv_catalog.u;
import com.example.cv_catalog.model.DokumentumTipus;
import com.example.cv_catalog.model.Dokumentumok;
import com.example.cv_catalog.model.Oneletrajz;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
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
	private Upload upload;
	
	private Button addButton, deleteButton,rogzitButton, letoltButton;
	private DateField kezdete,vege;
	
	private Uploader receiver;
	
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
		
		dokumentumokTable = new Table("Csatolmányok nyílvántartása", dokumentumok);
		dokumentumokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = dokumentumokTable.getValue();
				if (id != null) {			
					//u.uzen(dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.id")+" => "+dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.megnevezes"));
					//dokumentumTipusCombo.setData(dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.id"));
					//dokumentumTipusCombo.select(dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.megnevezes"));
					//u.uzen(dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.id")+"");
					editorFields.setItemDataSource(dokumentumokTable.getItem(id));
					//if(dokumentumokTable.getItem(id).getItemProperty("dokumentum_tipus.megnevezes"))
				}
			}
		});
		dokumentumokTable.setVisibleColumns("fajlNeve","dokumentum_tipus.megnevezes");
		dokumentumokTable.setColumnHeader("fajlNeve", "Fájl neve");	
		dokumentumokTable.setColumnHeader("dokumentum_tipus.megnevezes", "Csatolmány típusa");	
		dokumentumokTable.setColumnWidth("fajlNeve", 500);
		//dokumentumokTable.setColumnExpandRatio("leiras", 1);
		dokumentumokTable.setSelectable(true);
		dokumentumokTable.setImmediate(true); 
		dokumentumokTable.setPageLength(5);
		layout.addComponent(dokumentumokTable);
        		
		//addButton = new Button("Hozzáad");
		deleteButton = new Button("Töröl");
		//rogzitButton = new Button("Rögzít");
		letoltButton = new Button("Letölt");
		
		layout.addComponents(new HorizontalLayout(deleteButton,letoltButton));
		//layout.addComponent(letoltButton);
		
		/*ProgressBar bar = new ProgressBar();
		bar.setVisible(false);
		bar.setIndeterminate(true);		
		
		Embedded image = new Embedded("Feltöltött kép");
		image.setVisible(false);
		editFields.addComponents(image);*/
		
		receiver = new Uploader();
		receiver.oneletrajz = cv;
		upload = new Upload("Csatolmány feltöltése", receiver);
		upload.setButtonCaption("Feltölt");
		upload.addSucceededListener(receiver);
		//upload.addStartedListener(listener);
		//upload.addProgressListener(receiver);
		/*Panel panel = new Panel("Cool Image Storage");
		Layout panelContent = new VerticalLayout();
		panelContent.addComponents(upload, image);
		panel.setContent(panelContent);*/
		editFields.addComponents(upload);
				
		dokumentumTipusCombo = new ComboBox("Csatolmány típusa:", dokumentumTipus);
		dokumentumTipusCombo.setContainerDataSource(dokumentumTipus);
		dokumentumTipusCombo.setItemCaptionPropertyId("megnevezes");
		dokumentumTipusCombo.setConverter(new SingleSelectConverter(dokumentumTipusCombo));
		editFields.addComponents(dokumentumTipusCombo);
		editorFields.bind(dokumentumTipusCombo, "dokumentum_tipus");	
		
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
        	deleteButton.setEnabled(false);
        	letoltButton.setEnabled(false);
        	//upload.setEnabled(false);
        }
        
        init();
	}
	
	public void init(){
		
		letoltButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object Id = dokumentumokTable.getValue();
				Item currentItem = dokumentumokTable.getItem(Id);
				File fajl = new File(u.basepath + 
						"/Dokumentumok/"+currentItem.getItemProperty("id").getValue()+"/"+
						currentItem.getItemProperty("fajlNeve").getValue());
				Resource res = new FileResource(fajl);
				Page.getCurrent().open(res, null, false);
				//FileDownloader fd = new FileDownloader(res);				
				//fd.extend(letoltButton);
			}
		});
		
		dokumentumTipusCombo.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				TypedQuery<DokumentumTipus> query = u.EM.createQuery("SELECT dt FROM DokumentumTipus dt WHERE dt.id="+dokumentumTipusCombo.getValue().toString(), DokumentumTipus.class);
				receiver.dok_tipus = query.getSingleResult();
				//receiver.Tipus = dokumentumTipusCombo.getValue().toString();
				//u.uzen(dokumentumTipusCombo.getValue().toString());
			}
			
		});
				
		/*addButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				dokumentumok.addEntity(new Dokumentumok());
				dokumentumokTable.select(dokumentumok.getIdByIndex(dokumentumok.size()-1));		
				dokumentumokTable.setCurrentPageFirstItemIndex(dokumentumok.size()-1);
			}
		});*/
		
		deleteButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				boolean ok = true;
				Object contactId = dokumentumokTable.getValue();
				Item currentItem = dokumentumokTable.getItem(contactId);
				
				File fajl = new File(u.basepath + 
						"/Dokumentumok/"+currentItem.getItemProperty("id").getValue()+"/"+
						currentItem.getItemProperty("fajlNeve").getValue());
				ok = fajl.delete();
				
				if(ok) {
					dokumentumokTable.removeItem(contactId);
				}
				else {
					u.uzenHiba("Hiba", "Nem sikerült a csatolmány törlése!");
				}
				
				if(dokumentumok.size()  > 0) {
					dokumentumokTable.select(dokumentumok.getIdByIndex(0));
				}
				else
				{
		        	letoltButton.setEnabled(false);
		        	//upload.setEnabled(false);
		        	deleteButton.setEnabled(false);
				}
			}
		});
		
		/*rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				dokumentumok.commit();
			}
		});*/
	}
	
}