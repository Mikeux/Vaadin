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
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
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
	
	private Button addButton, deleteButton,rogzitButton, letoltButton, feltoltButton;
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
					editorFields.setItemDataSource(dokumentumokTable.getItem(id));
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
				
		Panel panel = new Panel("Új csatolmány hozzáadása");
		Layout panelContent = new VerticalLayout();
		//panel.setSizeUndefined();
		//panelContent.setSizeUndefined();
		
		receiver = new Uploader();
		receiver.oneletrajz = cv;
		upload = new Upload("", receiver);
		//upload.setButtonCaption("Feltölt");
		upload.setButtonCaption(null);
		upload.addSucceededListener(receiver);
		upload.setImmediate(false);
		//upload.submitUpload();
						
		dokumentumTipusCombo = new ComboBox("Csatolmány típusa:", dokumentumTipus);
		dokumentumTipusCombo.setContainerDataSource(dokumentumTipus);
		dokumentumTipusCombo.setItemCaptionPropertyId("megnevezes");
		dokumentumTipusCombo.setConverter(new SingleSelectConverter(dokumentumTipusCombo));
		//editFields.addComponents(dokumentumTipusCombo);
		//editorFields.bind(dokumentumTipusCombo, "dokumentum_tipus");	
		
		editorFields.setBuffered(false);	

		feltoltButton = new Button("Feltölt");
		
		panelContent.addComponents(upload,feltoltButton,dokumentumTipusCombo);
		panel.setContent(panelContent);
		layout.addComponent(panel);
		
		
		//editFields.setSizeUndefined();
		//editFields.setMargin(true);
		//layout.addComponent(editFields);
		
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
		
		feltoltButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object Id = dokumentumTipusCombo.getValue();
				if(Id == null) u.uzenHiba("Hiba", "A dokumentum típusának megadása kötelezõ!");
				else {
					upload.submitUpload();
					try {
						Thread.sleep(2000);
						dokumentumTipusCombo.setData(null);
						dokumentumokTable.refreshRowCache();	
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}
		});
		
		letoltButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object Id = dokumentumokTable.getValue();
				Item currentItem = dokumentumokTable.getItem(Id);
				try {
					File fajl = new File(u.basepath + 
					"/Dokumentumok/"+cv.getId()+"/"+currentItem.getItemProperty("fajlNeve").getValue());
					Resource res = new FileResource(fajl);
					Page.getCurrent().open(res, null, false);
				}catch (Exception ex) {
					u.uzenHiba("Hiba", "A csatolmány letöltése nem sikerült! "+ex.getMessage());
				}
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
				
				//ConfirmationDialogPopupWindow confirmationPopup = new ConfirmationDialogPopupWindow("File already exists.Do you want to replace?");
				
				boolean ok = true;
				Object contactId = dokumentumokTable.getValue();
				Item currentItem = dokumentumokTable.getItem(contactId);
				
				File fajl = new File(u.basepath + 
						"/Dokumentumok/"+cv.getId()+"/"+currentItem.getItemProperty("fajlNeve").getValue());
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