package org.sansoft.tt.web.components

import com.vaadin.ui._
import com.vaadin.terminal._
import com.vaadin.ui.themes.BaseTheme
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.web.project._
import org.sansoft.tt.repository.ProjectRepository

class MainPanel extends VerticalLayout {
  val header: HeaderPanel = new HeaderPanel(this)
  val mainMenu: MainMenu = new MainMenu(this)
  val contentPanel: Panel = new Panel

  def init(): Unit = {
    header.init
    addComponent(header)
    setComponentAlignment(header, Alignment.TOP_CENTER)

    mainMenu.init
    addComponent(mainMenu)
    setComponentAlignment(header, Alignment.MIDDLE_CENTER)

    initContentPanel
    addComponent(contentPanel)
    setComponentAlignment(header, Alignment.BOTTOM_CENTER)
  }

  def changeBody(component: Component) = {
    contentPanel.removeAllComponents();
    contentPanel.addComponent(component);
  }

  private def initContentPanel() = {
    contentPanel.setWidth("100%");
    contentPanel.setHeight("100%")
  }
}

class HeaderPanel(mainPanel: MainPanel) extends HorizontalLayout  with ComponentRegistry {

  def init(): Unit = {
    createHearderBanner()
    createHeaderMenu()
    setSizeFull
  }

  private def createHearderBanner(): Unit = {
    val headerImage: Embedded = new Embedded("", new ThemeResource("images/header-banner.gif"))
    headerImage.setWidth("180px")
    headerImage.setHeight("50px")
    addComponent(headerImage)
    setComponentAlignment(headerImage, Alignment.TOP_LEFT)

  }

  private def createHeaderMenu() = {
    val layout: HorizontalLayout = new HorizontalLayout

    val btnProjects: SButton = new SButton(getMessage("headermenu.projects"), _ => btnProjectsClicked)
    btnProjects.setStyleName(BaseTheme.BUTTON_LINK);
    layout.addComponent(btnProjects)

    layout.addComponent(new Label("|"))

    val btnHelp: SButton = new SButton(getMessage("headermenu.help"), _ => btnHelpClicked)
    btnHelp.setStyleName(BaseTheme.BUTTON_LINK);
    layout.addComponent(btnHelp)

    addComponent(layout)
    setComponentAlignment(layout, Alignment.TOP_RIGHT)
  }

  private def btnHelpClicked() = {
    println("Help clicked")
  }

  private def btnProjectsClicked() = {
    val projectTable : AllProjectView = new AllProjectView(projectRepository, sysConfigRepo)
    projectTable.init
    mainPanel.changeBody(projectTable)
  }

}