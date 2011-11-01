package org.sansoft.tt.web.project

import com.vaadin.ui._
import com.vaadin.data._
import org.sansoft.tt.web.components.SButton
import org.sansoft.tt.web.i18n.I18nSupport._
import org.sansoft.tt.repository.ProjectRepository
import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._
import com.vaadin.terminal.UserError
import org.sansoft.tt.util.Util._

class ProjectForm(projectRepo: ProjectRepository, id: Double) extends VerticalLayout {

  val lblCaption = new Label
  val txtId = new TextField
  val txtCode = new TextField
  val txtName = new TextField
  val dfStartDate = new PopupDateField
  val dfEndDate = new PopupDateField
  val txtDesctiption = new RichTextArea
  val cmbOwner = new ComboBox
  val frmproject = new Form

  def init(): Unit = {
    formatCaptionLable
    formatIdField
    formatCodeField
    formatNameField
    formatStartDatePicker
    formatEndDatePicker
    formatDescriptionText
    formatOwnerCombo
    frmproject.addField("id", txtId)
    frmproject.addField("code", txtCode)
    frmproject.addField("name", txtName)
    frmproject.addField("startDate", dfStartDate)
    frmproject.addField("endDate", dfEndDate)
    frmproject.addField("description", txtDesctiption)
    frmproject.addField("owner", cmbOwner)
    frmproject.getFooter().addComponent(createButtonBar);
    addComponent(lblCaption)
    addComponent(new Label("<hr />", Label.CONTENT_XHTML))
    addComponent(frmproject)
  }

  def formatCaptionLable() = {
    lblCaption.setCaption(getMessage("project.form.heading"))
    lblCaption.setContentMode(Label.CONTENT_XHTML);
  }

  def formatIdField() = {
    txtId.setCaption(getMessage("project.form.field.id"))
    txtId.setWidth("200px")
    txtId.setValue(id)
    txtId.setReadOnly(true);
  }

  def formatCodeField() = {
    txtCode.setCaption(getMessage("project.form.field.code"))
    txtCode.setWidth("200px")
    txtCode.setRequired(true)
    txtCode.setRequiredError(getMessage("project.form.project.code.required.error"))
    txtCode.setImmediate(true)
    txtCode.addListener(new Property.ValueChangeListener() {
      override def valueChange(valueChangeEvent: Property.ValueChangeEvent) {
        try {
          if (!projectRepo.isProjectCodeAvailable(txtCode.getValue().asInstanceOf[String])) {
            txtCode.setComponentError(new UserError(getMessage("project.form.project.code.already.taken.error")));
            throw new Validator.InvalidValueException(getMessage("project.form.project.code.already.taken.error"));
          } else {
            txtCode.setComponentError(null)
            frmproject.setComponentError(null)
          }
        } catch {
          case e: Validator.InvalidValueException => frmproject.setComponentError(new UserError(e.getMessage))
          case e: Exception => println("Unexpected error occurred while validating a field ")
        }

      }
    })
  }

  def formatNameField() = {
    txtName.setCaption(getMessage("project.form.field.name"))
    txtName.setWidth("600px")
    txtName.setRequired(true)
    txtName.setRequiredError(getMessage("project.form.project.name.required.error"))
  }

  def formatStartDatePicker() = {
    dfStartDate.setInputPrompt(getMessage("project.form.field.start.date"))
    dfStartDate.setResolution(DateField.RESOLUTION_DAY);
    dfStartDate.setCaption(getMessage("project.form.field.start.date"))
    dfStartDate.setRequired(true)
    dfStartDate.setRequiredError(getMessage("project.form.start.date.required.error"))
  }

  def formatEndDatePicker() = {
    dfEndDate.setInputPrompt(getMessage("project.form.field.end.date"))
    dfEndDate.setResolution(DateField.RESOLUTION_DAY);
    dfEndDate.setCaption(getMessage("project.form.field.end.date"))
    dfEndDate.setRequired(true)
    dfEndDate.setRequiredError(getMessage("project.form.end.date.required.error"))
  }

  def formatDescriptionText() = {
    txtDesctiption.setCaption(getMessage("project.form.field.description"))
    txtDesctiption.setWidth("600px")
  }

  def formatOwnerCombo() = {
    cmbOwner.setCaption(getMessage("project.form.field.owner"))
    cmbOwner.setWidth("300px")
    cmbOwner.addItem("Sandarenu")
  }

  private def createButtonBar() = {
    val btnSave: SButton = new SButton(getMessage("project.form.button.save"), _ => saveProject)
    val btnCancel: SButton = new SButton(getMessage("project.form.button.cancel"), _ => println("Submit clicked"))

    val layout: HorizontalLayout = new HorizontalLayout
    layout.setSpacing(true)
    layout.addComponent(btnSave)
    layout.addComponent(btnCancel)

    layout
  }

  private def saveProject() = {
    try {
      frmproject.setComponentError(null)
      frmproject.commit
      val projectData: MongoDBObject = createProjectFromUserData
      println(projectData)
      projectRepo.createNewProject(projectData)
      getWindow().showNotification(richFormat(getMessage("project.form.project.save.success"), Map("#{0}" -> txtName.getValue)))
    } catch {
      case e: Exception => frmproject.setComponentError(new UserError(e.getMessage))
    }

  }

  private def createProjectFromUserData(): MongoDBObject = {
    MongoDBObject(idK -> id,
      projectCodeK -> txtCode.getValue,
      nameK -> txtName.getValue,
      descriptionK -> txtDesctiption.getValue,
      startDateK -> dfStartDate.getValue,
      endDateK -> dfEndDate.getValue,
      ownerIdK -> cmbOwner.getValue)
  }
}
