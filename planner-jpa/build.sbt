name := "planner-jpa"

version := "0.1"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  "com.typesafe" % "config" % "1.3.0",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "org.springframework.data" % "spring-data-jpa" % "1.8.2.RELEASE",
  "org.specs2" %% "specs2-core" % "3.6.2" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)