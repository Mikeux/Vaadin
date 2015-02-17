package com.example.cv_catalog.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class AltalanosAdatok extends CustomComponent {
	private VerticalLayout layout = new VerticalLayout();

	//https://vaadin.com/book/-/page/components.customcomponent.html
	
	public AltalanosAdatok(){
		 // A layout structure used for composition
        //Panel panel = new Panel("My Custom Component");
        //panel.setContent(new VerticalLayout());
        
        // Compose from multiple components
        Label label = new Label("Általános Adatok");
        label.setSizeUndefined(); // Shrink
        layout.addComponent(label);
        layout.addComponent(new Button("Ok"));
        
        // Set the size as undefined at all levels
        //panel.getContent().setSizeUndefined();
        //panel.setSizeUndefined();
        setSizeUndefined();

        // The composition root MUST be set
        setCompositionRoot(layout);
	}
	
}
