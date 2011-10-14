/**
 *
 */
package org.sansoft.tt.web.components

import com.vaadin.ui._

/**
 * @author sandarenu
 *
 */
class SButton(text: String, action: Button#ClickEvent => Unit) extends Button(text, new SButtonClickListener(action))

class SButtonClickListener(action: Button#ClickEvent => Unit) extends Button.ClickListener {
  def buttonClick(event: Button#ClickEvent): Unit = {
    action(event)
  }
}