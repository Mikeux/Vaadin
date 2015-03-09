package com.example.cv_catalog;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public final class ConfirmationDialog extends Window implements Button.ClickListener {
    private static final int CONFIRMATION_DIALOG_HEIGHT = 140;
    private static final int CONFIRMATION_DIALOG_WIDTH = 250;
    private static final int ONE_HUNDRED_PERCENT = 100;
    private static final long serialVersionUID = 1L;
    private final ConfirmationDialogCallback callback;
    private final Button okButton;
    private final Button cancelButton;

    public ConfirmationDialog(final String caption, final String question,
    		final String okLabel, final String cancelLabel, final ConfirmationDialogCallback callback) {
        super(caption);
        setWidth(CONFIRMATION_DIALOG_WIDTH, ConfirmationDialog.UNITS_PIXELS);
        setHeight(CONFIRMATION_DIALOG_HEIGHT, ConfirmationDialog.UNITS_PIXELS);
        okButton = new Button(okLabel, this);
        cancelButton = new Button(cancelLabel, this);
        setModal(true);

        this.callback = callback;

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setStyleName("mainLayout");
        
        if (question != null) { 
        	mainLayout.addComponent(new Label(question));    
        }

        final HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setStyleName("gombok");
        buttonLayout.setSpacing(true);
        buttonLayout.addComponent(okButton);
        buttonLayout.addComponent(cancelButton);
                
        mainLayout.addComponent(buttonLayout);
        mainLayout.setHeight(ONE_HUNDRED_PERCENT, ConfirmationDialog.UNITS_PERCENTAGE);
        mainLayout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_CENTER);       
        this.setContent(mainLayout);
    }

    /**
     * Event handler for button clicks.
     * @param event the click event.
     */
    public void buttonClick(final ClickEvent event) {
        if (getParent() != null) { 
        	getUI().removeWindow(this);
        }
        callback.response(event.getSource() == okButton);
    }

    /**
     * Interface for confirmation dialog callbacks.
     */
    public interface ConfirmationDialogCallback {
        /**
         * The user response.
         * @param ok True if user clicked ok.
         */
        void response(boolean ok);
    }

}