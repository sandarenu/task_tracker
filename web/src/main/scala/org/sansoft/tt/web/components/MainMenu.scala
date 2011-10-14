package org.sansoft.tt.web.components

import com.vaadin.ui._
import com.vaadin.terminal._
import com.vaadin.ui.themes.BaseTheme
import org.sansoft.tt.web.usecase.UseCaseForm

class MainMenu(mainPanel : MainPanel) extends HorizontalLayout {

  val btnDashBoard: SButton = new SButton("DashBoard", _ => btnDashBoardClicked)
  val btnBackLog: SButton = new SButton("BackLog", _ => btnBackLogClicked)
  val btnTimeSheet: SButton = new SButton("TimeSheet", _ => btnTimeSheetClicked)

  def init() = {
    addComponent(formatButton(btnDashBoard))
    addComponent(createSeparator)
    addComponent(formatButton(btnBackLog))
    addComponent(createSeparator)
    addComponent(formatButton(btnTimeSheet))
    fillFreeSpace
    setSizeFull
  }

  private def formatButton(button: SButton) = {
    button.setStyleName(BaseTheme.BUTTON_LINK);
    button.setWidth("150px");
    button
  }

  private def createSeparator() = {
    var lbl: Label = new Label("|")
    lbl.setWidth("10px")
    lbl
  }

  private def fillFreeSpace() = {
    var lbl: Label = new Label("")
    lbl.setWidth("100%");
    addComponent(lbl);
    setExpandRatio(lbl, 1.0f);

  }

  private def btnDashBoardClicked() = {
    println("DashBoard Clicked")
  }

  private def btnBackLogClicked() = {
    println("DashBoard Clicked")
    val form : UseCaseForm = new UseCaseForm
    form.init

    mainPanel.changeBody(form)
  }

  private def btnTimeSheetClicked() = {
    println("DashBoard Clicked")
  }

}