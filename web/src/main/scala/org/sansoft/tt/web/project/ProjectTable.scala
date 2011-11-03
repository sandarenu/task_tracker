package org.sansoft.tt.web.project

import com.vaadin.ui._
import com.vaadin.data._
import com.vaadin.data.util.IndexedContainer
import org.sansoft.tt.web.components.SButton
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.repository.ProjectRepository
import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class ProjectTable(projectRepo: ProjectRepository) extends VerticalLayout {

  val PROJECT_CODE = "Code"
  val PROJECT_NAME = "Name"
  val START_DATE = "Start_Date"
  val END_DATE = "End_Date"
  val OWNER = "Owner"
  val projectTable = new Table

  def init() = {
    createTable
    addComponent(projectTable)
  }

  def createTable() = {
    projectTable.setContainerDataSource(fillIso3166Container)
    projectTable.setColumnHeaders(Array(getMessage("project.table.header.code"),
      getMessage("project.table.header.name"),
      getMessage("project.table.header.start.date"),
      getMessage("project.table.header.end.date"),
      getMessage("project.table.header.owner")));
    projectTable.setWidth("100%");
    projectTable.setHeight("170px");

  }

  private def fillIso3166Container() = {
    val container = new IndexedContainer
    container.addContainerProperty(PROJECT_CODE, classOf[String],
      null);
    container.addContainerProperty(PROJECT_NAME, classOf[String],
      null);
    container.addContainerProperty(START_DATE, classOf[String],
      null);
    container.addContainerProperty(END_DATE, classOf[String],
      null);
    container.addContainerProperty(OWNER, classOf[String],
      null);

    projectRepo.findAllProjects.foreach { prj =>
      println("Found Project[" + prj + "]")
      val item: Item = container.addItem(prj.as[String](idK));
      item.getItemProperty(PROJECT_CODE).setValue(prj.as[String](projectCodeK));
      item.getItemProperty(PROJECT_NAME).setValue(prj.as[String](nameK));
      item.getItemProperty(START_DATE).setValue(prj.as[String](startDateK));
      item.getItemProperty(END_DATE).setValue(prj.as[String](endDateK));
      item.getItemProperty(OWNER).setValue(prj.as[String](ownerIdK));
    }

    container
  }

}