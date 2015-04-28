name := "planner-jdbc"

version := "0.1"

scalaVersion := "2.11.6"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  "com.typesafe" % "config" % "1.2.1",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "org.springframework.data" % "spring-data-jdbc-core" % "1.0.0.RELEASE",
  "org.springframework.data" % "spring-data-oracle" % "1.0.0.RELEASE",
  "org.specs2" %% "specs2-core" % "3.5" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)