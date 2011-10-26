package org.sansoft.tt.repository

import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class SystemConfigRepository {

  val idCollection = MongoConnector.mongoConnection()("unique_id_collection")

  def getNextId(idType: String) = {
    val query = MongoDBObject("_id" -> idType)
    val update = $inc(valueK -> 1.0)
    idCollection.findAndModify(query, MongoDBObject.empty, MongoDBObject.empty, false, update, true, true)
  }
}