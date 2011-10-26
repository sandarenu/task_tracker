package org.sansoft.tt.repository

import org.specs.Specification
import org.specs.matcher.PatternMatchers._
import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class ProjectRepositorySpecs extends Specification {
  "ProjectRepository" should {
    val projectsCollection = MongoConnector.mongoConnection()("projects")
    projectsCollection.dropCollection()

    val projectRepo = new ProjectRepository

    "check availablity of project id when project id not yet taken" in {
      projectRepo.isProjectCodeAvailable("PRJ-1") must beTrue
    }

    "check availablity of project id when project id created" in {
      val project = MongoDBObject(idK -> "1",
        projectCodeK -> "PRJ-1",
        nameK -> "ABC-Project")
      projectRepo.createNewProject(project)

      projectRepo.isProjectCodeAvailable("PRJ-1") must beFalse
    }

    "save project" in {
      val project = MongoDBObject(idK -> "1",
        projectCodeK -> "PRJ-2",
        nameK -> "ABC-Project2")
      projectRepo.createNewProject(project) must not throwA
    }

    "not save project when project-id already taken" in {
      val project = MongoDBObject(idK -> "1",
        projectCodeK -> "PRJ-2",
        nameK -> "ABC-Project2")
      projectRepo.createNewProject(project)

      val project1 = MongoDBObject(idK -> "2",
        projectCodeK -> "PRJ-2",
        nameK -> "ABC-Project2")
      projectRepo.createNewProject(project1) must throwA(new IllegalArgumentException("ProjectId[PRJ-2] already taken."))
    }
  }
}