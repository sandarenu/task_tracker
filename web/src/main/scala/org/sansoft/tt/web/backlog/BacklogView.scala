package org.sansoft.tt.web.backlog

import com.vaadin.ui._
import org.sansoft.tt.web.components.{ SButton, STextField }
import org.sansoft.tt.util.KeyBox._
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.repository.ProjectRepository
import com.mongodb.casbah.Imports._

class BacklogView(projectRepo: ProjectRepository) extends VerticalLayout {

  val btnCreateIteration = new SButton(getMessage("backlog.view.create.new.iteration"), _ => createIteration)
  val btnCreateStory = new SButton(getMessage("backlog.view.create.new.story"), _ => createStory)
  val lblHeader = new Label("")
  val cmbProjectList = new ComboBox

  def init = {
    createHeaderComponent
  }

  private def createHeaderComponent = {
    val layout = new HorizontalLayout
    formatProjectListCombo
//    lblHeader.setWidth("50%");
    layout.addComponent(cmbProjectList)
    layout.addComponent(lblHeader)
    layout.addComponent(btnCreateIteration)
    layout.addComponent(btnCreateStory)
    layout.setSizeFull
    layout.setSpacing(true)
    addComponent(layout)
  }

  def formatProjectListCombo() = {
    cmbProjectList.setWidth("300px")
    cmbProjectList.setImmediate(true)
    cmbProjectList.setInputPrompt(getMessage("backlog.view.select.project.prompt"))
    projectRepo.findAllProjects.foreach( prj =>
    	cmbProjectList.addItem(prj.as[String](nameK))
    )
  }

  private def createIteration = {
    println("new iteration")
  }

  private def createStory = {
   removeAllComponents()
    val form = new UseCaseForm
    form.init
    addComponent(form)
  }
}