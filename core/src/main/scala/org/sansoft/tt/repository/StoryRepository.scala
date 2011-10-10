/**
 *
 */
package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._
/**
 * @author sandarenu
 *
 */
class StoryRepository {
  val storyCollection = MongoConnector.mongoConnection()("user_stories")

  def addStory(story: MongoDBObject) = {
    validate(story)
    storyCollection.save(story)
  }

  private def validate(story: MongoDBObject) = {
    story foreach ((t2) => if (t2._2 == null) throw new IllegalArgumentException("Null value for [" + t2._1 + "]"))
  }

  private def isEmpty(value: String) = {
    if (value == null || value.isEmpty()) {
      true
    } else {
      false
    }
  }

}