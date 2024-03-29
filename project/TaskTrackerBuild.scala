import sbt._
import com.github.siasia._
import WebPlugin._
import PluginKeys._
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
  val casbah = "com.mongodb.casbah" % "casbah_2.9.0-1" % "2.1.5.0"
  val Slf4jLog4jDep = "org.slf4j" % "slf4j-log4j12" % "1.6.1"
  val ScalaCheckDep = "org.scala-tools.testing" %% "scalacheck" % "1.9" % "test"
  val JUnitDep = "junit" % "junit" % "4.8.2" % "test"
  val scalaTesting = "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"
  //val scctSbt = "ch.craven" %% "scct-plugin" % "0.2"
  val vaadin = "com.vaadin" % "vaadin" % "6.7.0"
  val servletApi = "javax.servlet" % "servlet-api" % "2.5"

  val jettyWebApp = "org.eclipse.jetty" % "jetty-webapp" % "6.1.22" % "container"
  val jettyPlus = "org.eclipse.jetty" % "jetty-plus" % "6.1.22" % "container"
  val jetty = "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

  val repositories = Seq(
    ScalaToolsSnapshots,
    "typesafe releases" at "http://repo.typesafe.com/typesafe/releases",
    "typesafe snapshots" at "http://repo.typesafe.com/typesafe/snapshots",
    "scct-repo" at "http://mtkopone.github.com/scct/maven-repo")

  def publishToRepository = Some(Resolver.file("Local Maven Repository", Path.userHome / ".m2" / "repository" asFile))

  lazy val baseSettings = Defaults.defaultSettings ++ Seq(
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
      name := "task-tracker-parent"
    )) 

  lazy val core = Project("core", file("core"),
    settings = baseSettings ++ Seq(
      name := "core",
      libraryDependencies ++= Seq(
        jodaTime,
        scalaTime,
        scalaTesting,
        ScalaCheckDep,
        casbah,
        jodaTime,
    scalaTime)))  

  lazy val web = Project("web", file("web"),
    settings = baseSettings ++ webSettings ++ Seq(
      name := "web",
      libraryDependencies ++= Seq(
        jodaTime,
        scalaTime,
        scalaTesting,
        ScalaCheckDep,
        casbah,
        jodaTime,
        scalaTime,
        vaadin,
        servletApi,
        jetty))) dependsOn(core)

}
