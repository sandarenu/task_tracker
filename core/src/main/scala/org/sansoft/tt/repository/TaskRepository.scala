/**
 *
 */
package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._

/**
 * @author sandarenu
 *
 */
class TaskRepository {

  val mongoConn = MongoConnection("localhost", 27017)

  val storyColl = MongoConnector.mongoConnection()("user_stories")

  def addStory(story: MongoDBObject) = storyColl.save(story)
}