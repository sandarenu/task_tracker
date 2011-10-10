/**
 *
 */
package org.sansoft.tt.web

import com.vaadin.Application
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
/**
 * @author sandarenu
 *
 */
class MainApp extends AbstractApplicationServlet {

  override def init(servletConfig: ServletConfig) = super.init(servletConfig)

  def getNewApplication(httpServletRequest: HttpServletRequest) = new TaskTrackerApplication()

  def getApplicationClass() = classOf[TaskTrackerApplication]

}