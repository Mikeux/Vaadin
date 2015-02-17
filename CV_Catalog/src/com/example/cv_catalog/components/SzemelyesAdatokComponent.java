package com.example.cv_catalog.components;

import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.SzemelyesAdatok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
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

	private JPAContainer<SzemelyesAdatok> szemelyesAdatok;
	private Table szemelyesAdatokTable;
	private FieldGroup editorFields = new FieldGroup();

	private TextField id_field;
	
	private Oneletrajz cv;
	
	//https://vaadin.com/book/-/page/components.customcomponent.html
	public SzemelyesAdatokComponent(Oneletrajz cv){
		this.cv = cv;
		//setSizeFull();
		//layout.setSizeFull();
		
        //Label label = new Label("Általános Adatok");
        //label.setSizeUndefined(); // Shrink
        //layout.addComponent(label);
		
		szemelyesAdatok = JPAContainerFactory.make(SzemelyesAdatok.class, "CV_Catalog");
		szemelyesAdatok.addNestedContainerProperty("oneletrajz.id");
		szemelyesAdatok.addNestedContainerProperty("orszagok.megnevezes");
		szemelyesAdatok.addNestedContainerProperty("nyelvek.nyelv");
		Filter filter = new Compare.Equal("oneletrajz.id", cv.getId());
		szemelyesAdatok.addContainerFilter(filter);
		
		szemelyesAdatokTable = new Table("Személyes adatok nyílvántartása", szemelyesAdatok);
		szemelyesAdatokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = szemelyesAdatokTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(szemelyesAdatokTable.getItem(id));
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
			
		editorFields.setBuffered(false);		
		editFields.setSizeUndefined();
		editFields.setMargin(true);
		layout.addComponent(editFields);
		
        setSizeUndefined();

        setCompositionRoot(layout);
        
        if(szemelyesAdatok.size() == 0) szemelyesAdatok.addEntity(new SzemelyesAdatok(this.cv));
        szemelyesAdatokTable.select(szemelyesAdatok.getIdByIndex(0));
	}
	
}
