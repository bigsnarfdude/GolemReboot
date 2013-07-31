import AssemblyKeys._

assemblySettings

name := "Golem"

version := "1.0"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases",
  "Typesafe repo" at "http://repo.typesafe.com/typesafe/repo",
  "Sonatype Snapshot" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Release" at "https://oss.sonatype.org/content/repositories/releases",
  "Spray Repo" at "http://repo.spray.io/"
)

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.0.2",
  "com.typesafe.akka" %% "akka-actor" % "2.2.0",
  "org.specs2" %% "specs2" % "2.1-SNAPSHOT" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "io.spray" %% "spray-json" % "1.2.5",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
//  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "mysql" % "mysql-connector-java" % "5.1.22",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0",
  "io.spray" %%  "spray-json" % "1.2.5",
  "org.igniterealtime.smack" % "smack" % "3.2.1",
  "org.igniterealtime.smack" % "smackx" % "3.2.1"
)

scalacOptions ++= Seq("-feature")