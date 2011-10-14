package org.sansoft.tt.web.usecase

import com.vaadin.ui._
import org.sansoft.tt.web.components.SButton
import org.sansoft.tt.web.components.SButton

class UseCaseForm extends Form {


  val txtSummary = new TextField


  def init() : Unit = {
    txtSummary.setCaption("Summary")
    txtSummary.setWidth("300px")

    addField("summary", txtSummary)
    getFooter().addComponent(createButtonBar);
  }


  private def createButtonBar() = {
    val btnSubmit: SButton = new SButton("Submit", _ => println("Submit clicked"))

    val layout : HorizontalLayout = new HorizontalLayout

    layout.addComponent(btnSubmit)

    layout
  }

}