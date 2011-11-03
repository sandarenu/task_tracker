package org.sansoft.tt.web.project

import com.vaadin.ui._
import org.sansoft.tt.web.components._
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.repository.ProjectRepository
import org.sansoft.tt.repository.SystemConfigRepository
import com.vaadin.terminal.ThemeResource
import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._
import org.sansoft.tt.util.Util._
import java.text.MessageFormat

class AllProjectView(projectRepo: ProjectRepository, sysConfigRepo: SystemConfigRepository) extends VerticalLayout with FormActionListner {

  val projectTable = new ProjectTable(projectRepo)
  val btnCreateNewProject = new SButton("Create New Project", _ => btnCreateNewProjectClicked)
  val lblHeader = new Label("Projects")
  val lblSubmitSuccess = new Label

  def init() = {
    initTableView
    createSuccessSubmitLable
    setSizeFull
    setSpacing(true)
  }

  override def formSubmitSuccess(savedObject: Any) {
    removeAllComponents()
    val project = savedObject.asInstanceOf[MongoDBObject]
    lblSubmitSuccess.setCaption(MessageFormat.format(getMessage("project.form.project.save.success"), project.as[String](nameK)))
    addComponent(lblSubmitSuccess)
    setComponentAlignment(lblSubmitSuccess, Alignment.TOP_LEFT)
    initTableView
  }

  override def formCancled() {
    removeAllComponents()
    initTableView
  }

  private def btnCreateNewProjectClicked() = {
    removeAllComponents()
    val form: ProjectForm = new ProjectForm(projectRepo, sysConfigRepo.getNextId("project"), this)
    form.init
    addComponent(form)

  }

  private def initTableView() {
    projectTable.init
    addComponent(lblHeader)
    setComponentAlignment(lblHeader, Alignment.TOP_LEFT)
    addComponent(new Label("<hr />", Label.CONTENT_XHTML))
    addComponent(projectTable)
    addComponent(btnCreateNewProject)
    setComponentAlignment(btnCreateNewProject, Alignment.TOP_RIGHT)
  }

  private def createSuccessSubmitLable() = {
    lblSubmitSuccess.setIcon(new ThemeResource("icons/accept.png"))
    lblSubmitSuccess.setHeight("20px");
    lblSubmitSuccess.setWidth("100%");
    lblSubmitSuccess.setStyleName("messages-area");
  }
}