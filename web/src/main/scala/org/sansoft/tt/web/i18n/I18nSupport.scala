package org.sansoft.tt.web.i18n
import org.sansoft.tt.web.i18n._
import java.util.Locale
import java.util.MissingResourceException

object I18nSupport {

  var messages: Messages = new Messages(Locale.US)

  def setLocale(locale: Locale): Unit = {
    messages = new Messages(locale)
  }

  def getMessage(key: String): String = {
    try {
      messages(key)
    } catch {
      case e: MissingResourceException => "!" + key
    }
  }
}