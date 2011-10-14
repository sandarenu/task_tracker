package org.sansoft.tt.web

import com.vaadin.Application
import com.vaadin.ui._
import org.sansoft.tt.web.components._

class TaskTrackerApplication extends Application {
/*  def init = {
	val mainWindow = new Window("hello")
	mainWindow.addComponent(new Label("hello scala!"))
	setMainWindow(mainWindow)
  }
  */

  override def init: Unit = {
     setTheme("custom");
        val main = new Window("The Main Window")
        setMainWindow(main)

        val mainPanel:MainPanel = new MainPanel
        mainPanel.init
        main.addComponent(mainPanel)
   }
}
