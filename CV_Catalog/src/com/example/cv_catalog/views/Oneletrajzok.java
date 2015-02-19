package com.example.cv_catalog.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.cv_catalog.u;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Orszagok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class Oneletrajzok extends VerticalLayout implements View {
	
	private VerticalLayout mainLayout = new VerticalLayout();
	private HorizontalSplitPanel splitLayout = new HorizontalSplitPanel();
	private HorizontalLayout tableLayout = new HorizontalLayout();
	private FormLayout editLayout = new FormLayout();
	
	private Button editButton = new Button("Szerkeszt");
	private Button removeButton = new Button("Törlés");
	private Button filterButton = new Button("Szûrés");
	private ComboBox orszagokCombo,nyelvekCombo;
	
	private JPAContainer<Nyelvek> nyelvek;
	private JPAContainer<Orszagok> orszagok;
	private JPAContainer<Oneletrajz> oneletrajzok;
	private Table oneletrajzokTable;
	private FieldGroup editorFields = new FieldGroup();

	private List<Filter> filters = new ArrayList<Filter>();
	
	private TextField id_field;
	
	public Oneletrajzok(UI ui){
		mainLayout.setSizeFull();
		mainLayout.addComponent(u.MenuKeszites(ui));
		mainLayout.addComponent(splitLayout);
		splitLayout.addComponents(tableLayout,editLayout);
		splitLayout.setSplitPosition(50);
		splitLayout.setSizeFull();
		tableLayout.setSizeFull();
		editLayout.setSizeFull();
		
		nyelvek = JPAContainerFactory.make(Nyelvek.class, "CV_Catalog");
		orszagok = JPAContainerFactory.make(Orszagok.class, "CV_Catalog");
		
		//https://vaadin.com/forum#!/thread/1076201
		oneletrajzok = JPAContainerFactory.make(Oneletrajz.class, "CV_Catalog");
		oneletrajzok.addNestedContainerProperty("felhasznalok.nev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.vezetekNev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.keresztNev");
		oneletrajzok.addNestedContainerProperty("szemelyesAdatok.szulIdo");		
		oneletrajzokTable = new Table("Önéletrajzok nyílvántartása", oneletrajzok);
		oneletrajzokTable.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object id = oneletrajzokTable.getValue();
				if (id != null)
					editorFields.setItemDataSource(oneletrajzokTable.getItem(id));
				//id_field.setReadOnly(true);
			}
		});
		oneletrajzokTable.setVisibleColumns("felhasznalok.nev", "hozzaadva",
												"szemelyesAdatok.vezetekNev",
												"szemelyesAdatok.keresztNev",
												"szemelyesAdatok.szulIdo");
		oneletrajzokTable.setColumnHeader("felhasznalok.nev", "Létrehozta");
		oneletrajzokTable.setColumnHeader("hozzaadva", "Hozzáadva");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.vezetekNev", "Vezetéknév");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.keresztNev", "Keresztnév");
		oneletrajzokTable.setColumnHeader("szemelyesAdatok.szulIdo", "Születési idõ");
		
		oneletrajzokTable.setSelectable(true);
		oneletrajzokTable.setImmediate(true);	
		tableLayout.addComponent(oneletrajzokTable);
			
		editLayout.addComponents(editButton,removeButton);
		
		Panel panel = new Panel("Szûrési feltételek");
		Layout panelContent = new VerticalLayout();
		//panel.setSizeUndefined();
		//panelContent.setSizeUndefined();
		
		orszagokCombo = new ComboBox("Származási ország:", orszagok);	
		orszagokCombo.setContainerDataSource(orszagok);
		orszagokCombo.setItemCaptionPropertyId("megnevezes");
		orszagokCombo.setConverter(new SingleSelectConverter(orszagokCombo));
		//editLayout.addComponents(orszagokCombo);
				
		nyelvekCombo = new ComboBox("Anyanyelv:", nyelvek);
		nyelvekCombo.setContainerDataSource(nyelvek);
		nyelvekCombo.setItemCaptionPropertyId("nyelv");
		nyelvekCombo.setConverter(new SingleSelectConverter(nyelvekCombo));
		//editLayout.addComponents(nyelvekCombo);		
		panelContent.addComponents(orszagokCombo,nyelvekCombo);
		panel.setContent(panelContent);
		
		editLayout.addComponents(panel);	

		editLayout.addComponents(filterButton);
		
		editorFields.setBuffered(false);

        //setSizeFull();
		//tableLayout.setSizeFull();
		oneletrajzokTable.setSizeFull();
		
		addComponent(mainLayout);
		this.init();
	}
	
	public void init() {
		oneletrajzokTable.setConverter("szemelyesAdatok.szulIdo", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });		        
		
		oneletrajzokTable.setConverter("hozzaadva", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });				
		
		editButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object Id = oneletrajzokTable.getValue();
				getUI().getNavigator().navigateTo("cv_edit/"+Id);
			}
		});
		
		
		filterButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				//Filter filter = new Compare.Equal("felhasznalok.nev", 1);
				//filters.add(filter);
				//oneletrajzok.addContainerFilter(filter);
			}
		});
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
	}
}
