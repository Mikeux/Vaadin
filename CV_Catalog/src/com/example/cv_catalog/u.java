package com.example.cv_catalog;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class u {

		public static void uzen(String msg) {
			
			// Notification with default settings for a warning
			Notification notif = new Notification("Információ",msg,Notification.TYPE_WARNING_MESSAGE);
			// Customize it
			notif.setDelayMsec(400);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setStyleName("u_uzen");
			//notif.setIcon(new ThemeResource("img/reindeer.png"));
			// Show it in the page
			notif.show(Page.getCurrent());
		}
	
}
