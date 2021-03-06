package com.example.cv_catalog.components;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.cv_catalog.ConfirmationDialog;
import com.example.cv_catalog.ConfirmationDialog.ConfirmationDialogCallback;
import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Dokumentumok;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Orszagok;
import com.example.cv_catalog.model.SzemelyesAdatok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SzemelyesAdatokComponent extends CustomComponent {
	private FormLayout editFields = new FormLayout();
	private FormLayout ImageFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout layout2 = new HorizontalLayout();
	
	private JPAContainer<Nyelvek> nyelvek;
	private JPAContainer<Orszagok> orszagok;
	private JPAContainer<SzemelyesAdatok> szemelyesAdatok;
	private Table szemelyesAdatokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	private ComboBox orszagokCombo,nyelvekCombo;
	private Button rogzitButton;
	
	private Oneletrajz cv;
	
	//https://vaadin.com/book/-/page/components.customcomponent.html
	public SzemelyesAdatokComponent(Oneletrajz cv){
		this.cv = cv;
		//setSizeFull();
		//layout.setSizeFull();
		
        //Label label = new Label("�ltal�nos Adatok");
        //label.setSizeUndefined(); // Shrink
        //layout.addComponent(label);
		
		nyelvek = JPAContainerFactory.make(Nyelvek.class, "CV_Catalog");
		orszagok = JPAContainerFactory.make(Orszagok.class, "CV_Catalog");
		
		szemelyesAdatok = JPAContainerFactory.make(SzemelyesAdatok.class, "CV_Catalog");
		szemelyesAdatok.addNestedContainerProperty("oneletrajz.id");
		szemelyesAdatok.addNestedContainerProperty("orszagok.id");
		szemelyesAdatok.addNestedContainerProperty("orszagok.megnevezes");
		szemelyesAdatok.addNestedContainerProperty("nyelvek.id");
		szemelyesAdatok.addNestedContainerProperty("nyelvek.nyelv");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		szemelyesAdatok.addContainerFilter(filter);
		
		szemelyesAdatokTable = new Table("Szem�lyes adatok ny�lv�ntart�sa", szemelyesAdatok);
		szemelyesAdatokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = szemelyesAdatokTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(szemelyesAdatokTable.getItem(id));
					//orszagokCombo.setValue(szemelyesAdatok.getContainerProperty(szemelyesAdatokTable.getValue(), "orszagok.id").getValue());
					//nyelvekCombo.setValue(szemelyesAdatok.getContainerProperty(szemelyesAdatokTable.getValue(), "nyelvek.id").getValue());
				}
				//id_field.setReadOnly(true);
			}
		});
		szemelyesAdatokTable.setVisibleColumns("vezetekNev","keresztNev","szulIdo","telefonszam","orszagok.megnevezes","nyelvek.nyelv");
		szemelyesAdatokTable.setColumnHeader("vezetekNev", "Vezet�k n�v");
		szemelyesAdatokTable.setColumnHeader("keresztNev", "Vezet�k n�v");
		szemelyesAdatokTable.setColumnHeader("szulIdo", "Sz�let�si id�");
		szemelyesAdatokTable.setColumnHeader("telefonszam", "Telefonsz�m");
		szemelyesAdatokTable.setColumnHeader("orszagok.megnevezes", "Sz�rmaz�si orsz�g");
		szemelyesAdatokTable.setColumnHeader("nyelvek.nyelv", "Anyanyelv");
		
		szemelyesAdatokTable.setSelectable(true);
		szemelyesAdatokTable.setImmediate(true); 
		szemelyesAdatokTable.setPageLength(1);
		layout.addComponent(szemelyesAdatokTable);
        
		rogzitButton = new Button("R�gz�t");
		layout.addComponents(rogzitButton);
		
		/*id_field = new TextField("Id:");	
		editFields.addComponents(id_field);
		editorFields.bind(id_field, "id");*/
		
		TextField vezetekNev = new TextField("Vezet�k n�v:");	
		editFields.addComponents(vezetekNev);
		editorFields.bind(vezetekNev, "vezetekNev");
		
		TextField keresztNev = new TextField("Kereszt n�v:");	
		editFields.addComponents(keresztNev);
		editorFields.bind(keresztNev, "keresztNev");
		
		DateField szulIdo = new DateField("Sz�let�si id�:");	
		editFields.addComponents(szulIdo);
		editorFields.bind(szulIdo, "szulIdo");	
		
		TextField telefonszam = new TextField("Telefonsz�m:");	
		editFields.addComponents(telefonszam);
		editorFields.bind(telefonszam, "telefonszam");	
		
		orszagokCombo = new ComboBox("Sz�rmaz�si orsz�g:", orszagok);
		//orszagokCombo.setItemCaptionMode(ItemCaptionMode.ITEM_CAPTION_MODE_PROPERTY);
		orszagokCombo.setContainerDataSource(orszagok);
		orszagokCombo.setItemCaptionPropertyId("megnevezes");
		//orszagokCombo.setFilteringMode(FilteringMode.CONTAINS);
		//orszagokCombo.setConverter(new SingleSelectConverterCorrected(orszagok));
		//orszagokCombo.setConverter(new SingleSelectConverter(orszagokCombo));
		//orszagokCombo.setImmediate(true);
		//orszagokCombo.setBuffered(false);
		orszagokCombo.setConverter(new SingleSelectConverter(orszagokCombo));
		editFields.addComponents(orszagokCombo);
		editorFields.bind(orszagokCombo, "orszagok");	
				
		nyelvekCombo = new ComboBox("Anyanyelv:", nyelvek);
		nyelvekCombo.setContainerDataSource(nyelvek);
		nyelvekCombo.setItemCaptionPropertyId("nyelv");
		//nyelvekCombo.setImmediate(true);
		//nyelvekCombo.setBuffered(false);
		nyelvekCombo.setConverter(new SingleSelectConverter(nyelvekCombo));
		editFields.addComponents(nyelvekCombo);
		editorFields.bind(nyelvekCombo, "nyelvek");	
		
		ImageFields.setSizeFull();
		ImageFields.setMargin(false);
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		//layout.addComponents(editFields,ImageFields);
		layout2.addComponents(editFields,ImageFields);
		layout.addComponent(layout2);
		
        setSizeFull();
        layout.setSizeFull();
        szemelyesAdatokTable.setSizeFull();

        setCompositionRoot(layout);
        
        if(szemelyesAdatok.size() == 0) szemelyesAdatok.addEntity(new SzemelyesAdatok(this.cv));
        szemelyesAdatokTable.select(szemelyesAdatok.getIdByIndex(0));
        
        init();
        
        //layout.addComponent(new Label());
	}
	
	public void init() {
		
		//Els� f�nyk�p bet�lt�se
		String file = u.basepath+"/WEB-INF/default_kep/hu.jpg";
		JPAContainer<Dokumentumok> dokumentumok = JPAContainerFactory.make(Dokumentumok.class, "CV_Catalog");
		dokumentumok.addNestedContainerProperty("oneletrajz.id");
		dokumentumok.addNestedContainerProperty("dokumentum_tipus.id");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		Filter filter2 = new Compare.Equal("dokumentum_tipus.id", 1);
		dokumentumok.addContainerFilter(filter);		
		dokumentumok.addContainerFilter(filter2);	
		if(dokumentumok.size() > 0){
			file = u.basepath+"/Dokumentumok/"+this.cv.getId()+"/"+
					dokumentumok.getContainerProperty(dokumentumok.getIdByIndex(0), "fajlNeve").getValue();
		}
		FileResource resource = new FileResource(new File(file));
		Embedded image = new Embedded("", resource);
		image.addStyleName("fenykep_img");
		image.setType(Embedded.TYPE_IMAGE);
		ImageFields.addComponent(image);
		
		szemelyesAdatokTable.setConverter("szulIdo", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });
		
		rogzitButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				szemelyesAdatok.commit();
				szemelyesAdatokTable.refreshRowCache();

		        //cd.bringToFront();
			}
		});
		
	}
	
}
