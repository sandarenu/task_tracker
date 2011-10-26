package org.sansoft.tt.repository

import org.specs.Specification
import org.specs.matcher.PatternMatchers._
import com.mongodb.casbah.Imports._
import org.sansoft.tt.util.KeyBox._

class SystemConfigRepositorySpecs extends Specification {

  "SystemConfigRepository" should {
    val sysConfigRepo = new SystemConfigRepository
    "create new id" in {
      val id1 = sysConfigRepo.getNextId("project")
      val id2 = sysConfigRepo.getNextId("project")

      val id1DbObj: MongoDBObject = id1 match {
        case None => fail("ID1 cannot be none")
        case Some(x) => x
      }

      val id2DbObj: MongoDBObject = id2 match {
        case None => fail("ID2 cannot be none")
        case Some(x) => x
      }

      id1 must not beNone

      id2 must not beNone

      (id1DbObj.getAs[String]("_id") match {
        case None => fail("ID1 cannot be none")
        case Some(x) => x
      }) must be matching ("project")

      val firstId = id1DbObj.getAs[Double](valueK) match {
        case None => fail("ID1 value cannot be none")
        case Some(x) => x
      }

      val secondId = id2DbObj.getAs[Double](valueK) match {
        case None => fail("ID2 value cannot be none")
        case Some(x) => x
      }

      secondId mustEqual (firstId + 1)

    }

  }

}