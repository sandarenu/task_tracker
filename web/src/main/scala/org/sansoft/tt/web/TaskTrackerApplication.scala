package org.sansoft.tt.web

import com.vaadin.Application
import com.vaadin.ui._

class Hello extends Application {
  def init = {
	val mainWindow = new Window("hello")
	mainWindow.addComponent(new Label("hello scala!"))
	setMainWindow(mainWindow)
  }
}
