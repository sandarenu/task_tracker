/**
 *
 */
package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._
/**
 * @author sandarenu
 *
 */
object MongoConnector {

  def mongoConnection() = MongoConnection("localhost", 27017)("task_tracker")
}