package org.sansoft.tt.web.backlog

import com.vaadin.ui._
import org.sansoft.tt.web.components.SButton
import org.sansoft.tt.web.i18n.I18nSupport._

class UseCaseForm extends VerticalLayout {

  val lblCaption = new Label
  val txtSummary = new TextField
  val cmbPriority = new ComboBox
  val cmbIteration = new ComboBox
  val txtDesctiption = new RichTextArea
  val txtTags = new TextField
  val cmbOwner = new ComboBox
  val cmbAcceptingUser = new ComboBox
  val cmbEffort = new ComboBox
  val frmUseCase = new Form

  def init(): Unit = {
    formatCaptionLable
    formatSummayField
    formatPriorityCombo
    formatIterationCombo
    formatDescriptionText
    formatTagsField
    formatOwnerCombo
    formatAcceptingUserCombo
    formatEffortCombo
    frmUseCase.addField("summary", txtSummary)
    frmUseCase.addField("priority", cmbPriority)
    frmUseCase.addField("iteration", cmbIteration)
    frmUseCase.addField("description", txtDesctiption)
    frmUseCase.addField("tags", txtTags)
    frmUseCase.addField("owner", cmbOwner)
    frmUseCase.addField("acceptinUser", cmbAcceptingUser)
    frmUseCase.addField("estimation", cmbEffort)
    frmUseCase.getFooter().addComponent(createButtonBar);
    addComponent(lblCaption)
    addComponent(new Label("<hr />", Label.CONTENT_XHTML))
    addComponent(frmUseCase)
  }

  def formatCaptionLable() = {
    lblCaption.setCaption(getMessage("usecase.form.heading"))
    lblCaption.setContentMode(Label.CONTENT_XHTML);
  }

  def formatSummayField() = {
    txtSummary.setCaption(getMessage("usecase.form.field.summary"))
    txtSummary.setWidth("600px")
  }

  def formatPriorityCombo() = {
    cmbPriority.setCaption(getMessage("usecase.form.field.priority"))
    cmbPriority.setWidth("300px")
    cmbPriority.addItem("Imediate")
  }

  def formatIterationCombo() = {
    cmbIteration.setCaption(getMessage("usecase.form.field.iteration"))
    cmbIteration.setWidth("300px")
    cmbIteration.addItem("Iteration 1")
  }

  def formatDescriptionText() = {
    txtDesctiption.setCaption(getMessage("usecase.form.field.description"))
    txtDesctiption.setWidth("600px")
  }

  def formatTagsField() = {
    txtTags.setCaption(getMessage("usecase.form.field.tags"))
    txtTags.setWidth("600px")
  }

  def formatOwnerCombo() = {
    cmbOwner.setCaption(getMessage("usecase.form.field.owner"))
    cmbOwner.setWidth("300px")
    cmbOwner.addItem("Sandarenu")
  }

  def formatAcceptingUserCombo() = {
    cmbAcceptingUser.setCaption(getMessage("usecase.form.field.accepting.user"))
    cmbAcceptingUser.setWidth("300px")
    cmbAcceptingUser.addItem("Sandarenu")
  }

  def formatEffortCombo() = {
    cmbEffort.setCaption(getMessage("usecase.form.field.effort"))
    cmbEffort.setWidth("300px")
    cmbEffort.addItem("1")
  }

  private def createButtonBar() = {
    val btnSave: SButton = new SButton(getMessage("usecase.form.button.save"), _ => println("Submit clicked"))
    val btnSaveAndCreate: SButton = new SButton(getMessage("usecase.form.button.save.and.next"), _ => println("Submit clicked"))
    val btnCancel: SButton = new SButton(getMessage("usecase.form.button.cancel"), _ => println("Submit clicked"))

    val layout: HorizontalLayout = new HorizontalLayout
    layout.setSpacing(true)
    layout.addComponent(btnSave)
    layout.addComponent(btnSaveAndCreate)
    layout.addComponent(btnCancel)

    layout
  }

}