package org.sansoft.tt.web.components

import org.sansoft.tt.repository._

trait ComponentRegistry {
	val projectRepository = new ProjectRepository

	val sysConfigRepo = new SystemConfigRepository
}