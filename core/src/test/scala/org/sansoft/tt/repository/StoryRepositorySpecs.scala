package org.sansoft.tt.repository

import org.specs.Specification
import com.mongodb.casbah.Imports._

class StoryRepositorySpecs extends Specification {

  "StoryRepository" should {
    val storyRepo = new StoryRepository
    "should not allow to have null values" in {
      val story = MongoDBObject("name" -> "New Story",
        "project" -> null,
        "module" -> "module 1",
        "description" -> "This is a new story")
      storyRepo.addStory(story) must throwAn[IllegalArgumentException]
    }

    "should save story successfully" in {
      val story = MongoDBObject("name" -> "New Story",
        "project" -> "Test project",
        "module" -> "module 1",
        "description" -> "This is a new story")
      storyRepo.addStory(story)
    }
  }
}