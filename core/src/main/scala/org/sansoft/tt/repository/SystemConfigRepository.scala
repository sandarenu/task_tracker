package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class SystemConfigRepository {

  val idCollection = MongoConnector.mongoConnection()("unique_id_collection")

  def getNextId(idType: String) : Double = {
    val query = MongoDBObject(idK -> idType)
    val update = $inc(valueK -> 1.0)
    val idObject: MongoDBObject = idCollection.findAndModify(query, MongoDBObject.empty, MongoDBObject.empty, false, update, true, true) match {
      case None => throw new IllegalStateException("ID object cannot be none")
      case Some(x) => x
    }

    idObject.getAs[Double](valueK) match {
      case None => throw new IllegalStateException("ID object cannot be none")
      case Some(x) => x
    }
  }
}