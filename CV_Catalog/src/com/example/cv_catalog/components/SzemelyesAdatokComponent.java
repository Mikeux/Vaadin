package com.example.cv_catalog.components;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Orszagok;
import com.example.cv_catalog.model.SzemelyesAdatok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SzemelyesAdatokComponent extends CustomComponent {
	private FormLayout editFields = new FormLayout();
	private VerticalLayout layout = new VerticalLayout();

	private JPAContainer<Nyelvek> nyelvek;
	private JPAContainer<Orszagok> orszagok;
	private JPAContainer<SzemelyesAdatok> szemelyesAdatok;
	private Table szemelyesAdatokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	private ComboBox orszagokCombo,nyelvekCombo;
	
	private Oneletrajz cv;
	
	//https://vaadin.com/book/-/page/components.customcomponent.html
	public SzemelyesAdatokComponent(Oneletrajz cv){
		this.cv = cv;
		//setSizeFull();
		//layout.setSizeFull();
		
        //Label label = new Label("Általános Adatok");
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
		
		szemelyesAdatokTable = new Table("Személyes adatok nyílvántartása", szemelyesAdatok);
		szemelyesAdatokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = szemelyesAdatokTable.getValue();
				if (id != null) {
					editorFields.setItemDataSource(szemelyesAdatokTable.getItem(id));
					//orszagokCombo.setValue(szemelyesAdatok.getContainerProperty(szemelyesAdatokTable.getValue(), "orszagok.id").getValue());
					//nyelvekCombo.setValue(szemelyesAdatok.getContainerProperty(szemelyesAdatokTable.getValue(), "nyelvek.id").getValue());
				}
				id_field.setReadOnly(true);
			}
		});
		szemelyesAdatokTable.setVisibleColumns("id", "vezetekNev","keresztNev","szulIdo","telefonszam","orszagok.megnevezes","nyelvek.nyelv");
		//szemelyesAdatokTable.setColumnHeader("felhasznalok.nev", "Készítette");
		//szemelyesAdatokTable.setColumnHeader("hozzaadva", "Hozzáadva");
		szemelyesAdatokTable.setSelectable(true);
		szemelyesAdatokTable.setImmediate(true); 
		szemelyesAdatokTable.setPageLength(1);
		layout.addComponent(szemelyesAdatokTable);
        
		id_field = new TextField("Id:");	
		editFields.addComponents(id_field);
		editorFields.bind(id_field, "id");
		
		TextField vezetekNev = new TextField("Vezeték név:");	
		editFields.addComponents(vezetekNev);
		editorFields.bind(vezetekNev, "vezetekNev");
		
		TextField keresztNev = new TextField("Kereszt név:");	
		editFields.addComponents(keresztNev);
		editorFields.bind(keresztNev, "keresztNev");
		
		DateField szulIdo = new DateField("Születési idõ:");	
		editFields.addComponents(szulIdo);
		editorFields.bind(szulIdo, "szulIdo");	
		
		TextField telefonszam = new TextField("Telefonszám:");	
		editFields.addComponents(telefonszam);
		editorFields.bind(telefonszam, "telefonszam");	
		
		orszagokCombo = new ComboBox("Származási ország:", orszagok);
		//orszagokCombo.setItemCaptionMode(ItemCaptionMode.ITEM_CAPTION_MODE_PROPERTY);
		orszagokCombo.setContainerDataSource(orszagok);
		orszagokCombo.setItemCaptionPropertyId("megnevezes");
		//orszagokCombo.setFilteringMode(FilteringMode.CONTAINS);
		//orszagokCombo.setConverter(new SingleSelectConverterCorrected(orszagok));
		//orszagokCombo.setConverter(new SingleSelectConverter(orszagokCombo));
		orszagokCombo.setImmediate(true);
		orszagokCombo.setBuffered(false);
		editFields.addComponents(orszagokCombo);
				
		nyelvekCombo = new ComboBox("Anyanyelv:", nyelvek);
		nyelvekCombo.setContainerDataSource(nyelvek);
		nyelvekCombo.setItemCaptionPropertyId("nyelv");
		nyelvekCombo.setImmediate(true);
		nyelvekCombo.setBuffered(false);
		nyelvekCombo.setConverter(new SingleSelectConverter(nyelvekCombo));
		editFields.addComponents(nyelvekCombo);
		editorFields.bind(nyelvekCombo, "nyelvek");	
		
		
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeUndefined();

        setCompositionRoot(layout);
        
        if(szemelyesAdatok.size() == 0) szemelyesAdatok.addEntity(new SzemelyesAdatok(this.cv));
        szemelyesAdatokTable.select(szemelyesAdatok.getIdByIndex(0));
        
        init();
        
        //layout.addComponent(new Label());
	}
	
	public void init(){
		orszagokCombo.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				//https://vaadin.com/book/-/page/datamodel.container.html
				
				/*
				https://vaadin.com/forum#!/thread/997794
				https://vaadin.com/forum#!/thread/19674
				https://vaadin.com/book/vaadin7/-/page/jpacontainer.fieldfactory.html
				
				https://vaadin.com/forum/#!/thread/587861/1189437
				https://vaadin.com/book/-/page/components.table.html
				http://stackoverflow.com/questions/15972920/vaadin-sqlcontainer-reference-how-to-implement-foreign-key-relation
				*/
				//szemelyesAdatok.getContainerProperty(szemelyesAdatokTable.getValue(), "orszagok.id")
				Orszagok o = orszagok.getItem(event.getProperty().getValue()).getEntity();
				//Item item = szemelyesAdatok.getItem(szemelyesAdatokTable.getValue());
				//SzemelyesAdatok sza= szemelyesAdatok.getItem(szemelyesAdatokTable.getValue()).getEntity();
				//u.uzen(item.getItemProperty("telefonszam").getValue().toString());
				//u.uzen(sza.getKeresztNev());
				
				szemelyesAdatokTable.setBuffered(true);
				
				szemelyesAdatok.getItem(szemelyesAdatokTable.getValue()).getEntity().setOrszagok(o);
				
				u.uzen(szemelyesAdatok.getItem(szemelyesAdatokTable.getValue()).isModified()+"");
				//item.getItemProperty("orszagok.id").setValue(o.getId());
				//Orszagok o = orszagok.getItem(event.getProperty().getValue()).getEntity();
				//u.uzen(o.getNyelvkod());
				//u.uzen(szemelyesAdatok.getIdByIndex(0)+"");
				//szemelyesAdatok.getItem(szemelyesAdatok.getIdByIndex(0)).getEntity().setOrszagok(o);
				//szemelyesAdatokTable.setContainerDataSource();
				
				//new SingleSelectConverter<ProductType>(cbType)
				
				szemelyesAdatok.commit();
				szemelyesAdatok.refresh();
				szemelyesAdatokTable.commit();
				szemelyesAdatokTable.setBuffered(false);
			}			
		});
		
	}
	
}
