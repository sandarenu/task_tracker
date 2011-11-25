package org.sansoft.tt.web.components

import com.vaadin.ui._
import com.vaadin.terminal._
import com.vaadin.ui.themes.BaseTheme
import org.sansoft.tt.web.backlog.UseCaseForm
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.web.backlog.BacklogView

class MainMenu(mainPanel: MainPanel) extends HorizontalLayout with ComponentRegistry {

  val btnDashBoard: SButton = new SButton(getMessage("mainmenu.dashboard"), _ => btnDashBoardClicked)
  val btnBackLog: SButton = new SButton(getMessage("mainmenu.backlog"), _ => btnBackLogClicked)
  val btnTimeSheet: SButton = new SButton(getMessage("mainmenu.timesheet"), _ => btnTimeSheetClicked)

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
    val backlog = new BacklogView(projectRepository)
    backlog.init

    mainPanel.changeBody(backlog)
  }

  private def btnTimeSheetClicked() = {
    println("DashBoard Clicked")
  }

}