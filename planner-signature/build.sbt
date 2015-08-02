name := "planner-signature"

version := "0.1"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.0",
  "commons-codec" % "commons-codec" % "1.9",
  "com.github.nscala-time" %% "nscala-time" % "1.2.0",
  "org.specs2" %% "specs2-core" % "3.6.2" % "test"
)