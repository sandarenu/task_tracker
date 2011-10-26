/**
 *
 */
package org.sansoft.tt.repository

import org.specs.Specification
import com.mongodb.casbah.Imports._
/**
 * @author sandarenu
 *
 */
class TaskRepositorySpecs extends Specification {

  "TaskRepository" should {
    val taskRepo = new TaskRepository
    "save user story" in {
      val story = MongoDBObject("name" -> "New Story",
        "project" -> "ABC-Project",
        "module" -> "module 1",
        "description" -> "This is a new story")
        taskRepo.addStory(story)
    }
  }

}