/**
 *
 */
package org.sansoft.tt.web

import com.vaadin.Application
import com.vaadin.ui._
import org.sansoft.tt.web.components.SButton
import org.sansoft.tt.web.usecase._
/**
 * @author sandarenu
 *
 */
class WindowOpener(private val mainWindow: Window) extends CustomComponent {
  var subWindow: Window = null
  var openbutton: Button = new SButton("Open Window", _ => createSubWindow)
  var closebutton: Button = null
  var explanation: Label = new Label("Explanation")
  val layout = new VerticalLayout()
  layout.addComponent(openbutton)
  layout.addComponent(explanation)
  setCompositionRoot(layout)

  private def createSubWindow(): Unit = {
    subWindow = new Window("My Dialog")
    subWindow.setPositionX(200)
    subWindow.setPositionY(100)
    subWindow.addComponent(new Label("A text label in the window."))
    subWindow.addComponent(new SButton("Close", _ => closeSubWindow))
    subWindow.addListener(new SWindowCloseListener(_ => onSubWindowClose))
    val form: UseCaseForm = new UseCaseForm
    form.init()
    subWindow.addComponent(form)

    mainWindow.addWindow(subWindow)

    openbutton.setEnabled(false)
    explanation.setValue("Window opened")
  }

  private def closeSubWindow(): Unit = {
    mainWindow.removeWindow(subWindow)
    openbutton.setEnabled(true)
    explanation.setValue("Closed with button")
  }

  private def onSubWindowClose(): Unit = {
    openbutton.setEnabled(true)
    explanation.setValue("Closed with window controls")
  }
}

class SWindowCloseListener(action: Window#CloseEvent => Unit) extends Window.CloseListener {
  def windowClose(event: Window#CloseEvent) = {
    action(event)
  }
}