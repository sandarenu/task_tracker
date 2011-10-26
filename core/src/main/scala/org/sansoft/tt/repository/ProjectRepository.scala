package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class ProjectRepository {
  val projectsCollection = MongoConnector.mongoConnection()("projects")

  def createNewProject(project: MongoDBObject) = {
    validate(project)
    projectsCollection.save(project)
  }

  def isProjectIdAvailable(projectId: String) = {
    val result = projectsCollection.find(MongoDBObject(projectIdK -> projectId))
    result.size == 0
  }

  private def validate(project: MongoDBObject) = {
    project foreach ((t2) => if (t2._2 == null) throw new IllegalArgumentException("Null value for [" + t2._1 + "]"))

    val projectId = project.getAs[String](projectIdK) match {
      case None => throw new IllegalArgumentException("ProjectId cannot be empty")
      case Some(x) => x
    }
    println("ProjectId:" + projectId)
    println("Id available:" + isProjectIdAvailable(projectId))
    if (!isProjectIdAvailable(projectId)) {
      throw new IllegalArgumentException("ProjectId[" + projectId + "] already taken.")
    }
  }

  private def isEmpty(value: String) = {
    if (value == null || value.isEmpty()) {
      true
    } else {
      false
    }
  }

}
