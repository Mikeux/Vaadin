package com.example.cv_catalog.views;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import com.example.cv_catalog.ConfirmationDialog;
import com.example.cv_catalog.u;
import com.example.cv_catalog.ConfirmationDialog.ConfirmationDialogCallback;
import com.example.cv_catalog.model.Felhasznalok;
import com.example.cv_catalog.model.KepzesSzint;
import com.example.cv_catalog.model.Nyelvek;
import com.example.cv_catalog.model.Oneletrajz;
import com.example.cv_catalog.model.Orszagok;
import com.example.cv_catalog.model.SzakmaiTapasztalat;
import com.example.cv_catalog.model.SzemelyesAdatok;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.StringToDateConverter;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
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
	private Button defaultButton = new Button("Alaphelyzet");
	private ComboBox orszagokCombo,nyelvekCombo,kepzesSzintCombo;
	
	private JPAContainer<Nyelvek> nyelvek;
	private JPAContainer<Orszagok> orszagok;
	private JPAContainer<KepzesSzint> kepzesSzint;
	private IndexedContainer oneletrajzok;
	private Table oneletrajzokTable;

	private TextField nev;
	
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
		kepzesSzint = JPAContainerFactory.make(KepzesSzint.class, "CV_Catalog");
		
		this.adatotOsszeallit();
		oneletrajzokTable = new Table("Önéletrajzok nyílvántartása", oneletrajzok);
		oneletrajzokTable.setVisibleColumns("letrehozta","vezeteknev","keresztnev","szulido");
        oneletrajzokTable.setColumnHeader("letrehozta", "Hozzáadta");
        oneletrajzokTable.setColumnHeader("vezeteknev", "Vezetéknév");
        oneletrajzokTable.setColumnHeader("keresztnev", "Keresztnév");        
        oneletrajzokTable.setColumnHeader("szulido", "Születési idõ");
		
		oneletrajzokTable.setSelectable(true);
		oneletrajzokTable.setImmediate(true);	
		tableLayout.addComponent(oneletrajzokTable);
			
		editLayout.addComponents(new HorizontalLayout(editButton,removeButton));
		
		Panel panel = new Panel("Szûrési feltételek");
		Layout panelContent = new VerticalLayout();
		panel.setSizeUndefined();
		panelContent.setSizeUndefined();
		
		nev = new TextField("Név:");	
		//v_nev.setColumns(20);
		nev.setMaxLength(100);

		orszagokCombo = new ComboBox("Származási ország:", orszagok);	
		orszagokCombo.setContainerDataSource(orszagok);
		orszagokCombo.setItemCaptionPropertyId("megnevezes");
		orszagokCombo.setConverter(new SingleSelectConverter(orszagokCombo));
		//editLayout.addComponents(orszagokCombo);
				
		nyelvekCombo = new ComboBox("Nyelvismeret:", nyelvek);
		nyelvekCombo.setContainerDataSource(nyelvek);
		nyelvekCombo.setItemCaptionPropertyId("nyelv");
		nyelvekCombo.setConverter(new SingleSelectConverter(nyelvekCombo));
		//editLayout.addComponents(nyelvekCombo);		
		
		kepzesSzintCombo = new ComboBox("Képesítés szintje:", kepzesSzint);	
		kepzesSzintCombo.setContainerDataSource(kepzesSzint);
		kepzesSzintCombo.setItemCaptionPropertyId("megnvezes");
		kepzesSzintCombo.setConverter(new SingleSelectConverter(kepzesSzintCombo));		
		
		panelContent.addComponents(nev,orszagokCombo,nyelvekCombo,kepzesSzintCombo);
		panel.setContent(panelContent);
		
		editLayout.addComponents(panel);	


		editLayout.addComponents(new HorizontalLayout(filterButton,defaultButton));
		
        //setSizeFull();
		//tableLayout.setSizeFull();
		oneletrajzokTable.setSizeFull();
		
		addComponent(mainLayout);
		this.init();
	}
	
	public void adatotOsszeallit(){
		String where = "";
		if(orszagokCombo != null) {
			if(orszagokCombo.getValue() != null) where += "Orszagok.id = "+orszagokCombo.getValue().toString();
		}
		
		if(nyelvekCombo != null) {
			if(nyelvekCombo.getValue() != null) {
				if(where != "") where += " AND ";
				where += "Nyelvek.id = "+nyelvekCombo.getValue().toString();
			}
		}		
		
		if(nev != null) {
			if(nev.getValue() != null) {
				if(where != "") where += " AND ";
				where += "(sza.vezetekNev LIKE '%"+nev.getValue().toString()+"%' OR sza.keresztNev LIKE '%"+nev.getValue().toString()+"%')";
			}
		}	
		
		if(kepzesSzintCombo != null) {
			if(kepzesSzintCombo.getValue() != null) {
				if(where != "") where += " AND ";
				where += "KepzesSzint.id = "+kepzesSzintCombo.getValue().toString();
			}
		}	
				
		List<Object[]> result = u.EM.createQuery("Select f,o,sza "+
				" from Oneletrajz AS o "+
				" JOIN Felhasznalok f ON f.oneletrajzok=o "+
				" JOIN SzemelyesAdatok sza ON o.szemelyesAdatok=sza "+
				" LEFT OUTER JOIN sza.orszagok Orszagok "+ 
				" LEFT OUTER JOIN o.nyelvismeret Nyelvismeret "+
				" LEFT OUTER JOIN Nyelvismeret.nyelvek Nyelvek "+
				" LEFT OUTER JOIN o.tanulmanyok Tanulmanyok "+
				" LEFT OUTER JOIN Tanulmanyok.kepzesSzint KepzesSzint "+
				((where != "") ? " WHERE " + where :"")+
				" GROUP BY o").getResultList();		
		oneletrajzok  = new IndexedContainer();
		oneletrajzok.addContainerProperty("id", Integer.class, "");
		oneletrajzok.addContainerProperty("letrehozta", String.class, "");
		oneletrajzok.addContainerProperty("vezeteknev", String.class, "");
		oneletrajzok.addContainerProperty("keresztnev", String.class, "");
		oneletrajzok.addContainerProperty("szulido", Date.class, "");		
		
		for (Object[] row: result) {
			Felhasznalok f = (Felhasznalok) row[0];
			Oneletrajz or = (Oneletrajz) row[1];
			SzemelyesAdatok sza = (SzemelyesAdatok) row[2];
			Item newItem = oneletrajzok.getItem(oneletrajzok.addItem());
			newItem.getItemProperty("id").setValue(or.getId());
			newItem.getItemProperty("letrehozta").setValue(f.getNev());
			newItem.getItemProperty("vezeteknev").setValue(sza.getVezetekNev());
			newItem.getItemProperty("keresztnev").setValue(sza.getKeresztNev());
			newItem.getItemProperty("szulido").setValue(sza.getSzulIdo());
		}		
	}
	
	public void init() {
		oneletrajzokTable.setConverter("szulido", new StringToDateConverter() {
            @Override
            protected DateFormat getFormat(Locale locale) {
            	return new SimpleDateFormat("yyyy.MM.dd");
            }
        });		        
		
		
		editButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(oneletrajzokTable.getValue() != null){
					Object sor = (int)oneletrajzokTable.getValue();
					if(sor != null) {
						getUI().getNavigator().navigateTo("cv_edit/"+oneletrajzok.getItem(sor).getItemProperty("id").getValue());
					}else  {
						u.uzen("Nincs kiválasztott elem!");
					}
				}
			}
		});
		
		removeButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {				
				Object sor = oneletrajzokTable.getValue();
				if(sor != null) {				
					ConfirmationDialog cd = new ConfirmationDialog("Törlés", "Valóban törölni akarj az önéletrajzot?","Igen", "Nem", new ConfirmationDialogCallback(){
						@Override
						public void response(boolean ok) {
							if(ok){
								try {			
									Object id = oneletrajzok.getItem(sor).getItemProperty("id").getValue();
									u.EM.getTransaction().begin();
									u.EM.createQuery("DELETE FROM Oneletrajz o WHERE o.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM Nyelvismeret nyi WHERE nyi.oneletrajz.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM SzemelyesAdatok sza WHERE sza.oneletrajz.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM SzakmaiTapasztalat szt WHERE szt.oneletrajz.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM Tanulmanyok ta WHERE ta.oneletrajz.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM EgyebKeszsegek ek WHERE ek.oneletrajz.id="+id).executeUpdate();
									u.EM.createQuery("DELETE FROM Dokumentumok ek WHERE ek.oneletrajz.id="+id).executeUpdate();
									u.EM.getTransaction().commit();
									adatotOsszeallit();
									oneletrajzokTable.setContainerDataSource(oneletrajzok);
									if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
									u.uzen("Sikeres törlés!");
								} catch(Exception ex) {
									Notification.show("Törlése nem sikerült", Notification.TYPE_ERROR_MESSAGE);
								}
							}
						}});
					getUI().addWindow(cd);

				}
				else {
					u.uzen("Nincs kiválasztott elem!");
				}
			}
		});
		
		filterButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				adatotOsszeallit();
				oneletrajzokTable.setContainerDataSource(oneletrajzok);
				if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
				u.uzen("Adatok leszûrve");
			}
		});
		
		defaultButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				nev.setValue("");
				orszagokCombo.setValue(null);
				nyelvekCombo.setValue(null);
				kepzesSzintCombo.setValue(null);
				adatotOsszeallit();
				oneletrajzokTable.setContainerDataSource(oneletrajzok);
				if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
				u.uzen("Összes adat megjelenítése");
			}
		});		
	}
		
	@Override
	public void enter(ViewChangeEvent event) {
		if(oneletrajzok.size()  > 0) oneletrajzokTable.select(oneletrajzok.getIdByIndex(0));	
	}
	
}
