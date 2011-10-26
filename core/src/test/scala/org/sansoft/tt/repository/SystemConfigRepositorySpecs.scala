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
      id2 mustEqual (id1 + 1)
    }

  }

}