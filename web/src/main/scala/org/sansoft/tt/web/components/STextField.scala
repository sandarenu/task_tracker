package org.sansoft.tt.web.components

import com.vaadin.ui._
import com.vaadin.data._

class STextField extends TextField {
  def addListener(action: Property.ValueChangeEvent => Unit): Unit = {
    addListener(new SPropertyValueChangedListner(action))
  }
}

class SPropertyValueChangedListner(action: Property.ValueChangeEvent => Unit) extends Property.ValueChangeListener {
  override def valueChange(valueChangeEvent: Property.ValueChangeEvent): Unit = {
    action(valueChangeEvent)
  }
}