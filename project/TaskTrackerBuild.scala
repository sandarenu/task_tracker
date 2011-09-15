
import sbt._
import Keys._

/**
 * Main sbt build file for the task-tracker project.
 *
 */
object TicketingCoreProject extends Build {
  val ticketingVersion = "1.0.0-SNAPSHOT"
  val Organization = "org.sansoft"
  val ScalaVersion = "2.9.0-1"

  val jodaTime = "joda-time" % "joda-time" % "1.6"
  val scalaTime = "org.scala-tools.time" % "time_2.8.0" % "0.2"

  val squeryl = "org.squeryl" %% "squeryl" % "0.9.4"
  val mysqlDriver = "mysql" % "mysql-connector-java" % "5.1.10"

  val Slf4jLog4jDep = "org.slf4j" % "slf4j-log4j12" % "1.6.1"

  val ScalaCheckDep = "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test"
  val JUnitDep = "junit" % "junit" % "4.8.2" % "test"
  val scalaTesting = "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"

  val repositories = Seq(
    ScalaToolsSnapshots,
    "typesafe releases" at "http://repo.typesafe.com/typesafe/releases",
    "typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots")

  def publishToRepository = Some(Resolver.file("Local Maven Repository", Path.userHome / ".m2" / "repository" asFile))

  lazy val baseSettings = Defaults.defaultSettings ++ scctSettings ++ Seq(
    version := ticketingVersion,
    organization := Organization,
    scalaVersion := ScalaVersion,
    publishMavenStyle := true,
    publishTo := publishToRepository,
    resolvers ++= repositories,
    checksums := Nil
  )

  lazy val parent = Project("taskTrackerParent", file("."),
    settings = baseSettings ++ Seq(
      name := "task-tracker"
    )) aggregate(core)

  lazy val core = Project("core", file("core"),
    settings = baseSettings ++ Seq(
      name := "core",
      libraryDependencies ++= Seq(
        jodaTime,
        scalaTime,
        scalaTesting,
        ScalaCheckDep)))


}
