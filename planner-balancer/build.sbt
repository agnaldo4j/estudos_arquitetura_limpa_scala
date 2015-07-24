import sbtassembly.Plugin.AssemblyKeys._

assemblySettings

name := "planner-balancer"

version := "0.1"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.0",
  "org.specs2" %% "specs2-core" % "3.6.2" % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.3.12",
  "com.typesafe.akka" %% "akka-remote" % "2.3.12"
)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => 
  {
  	case PathList("META-INF", xs @ _*) => (xs map {_.toLowerCase}) match {
    	case "notice.txt" :: xs => MergeStrategy.first
    	case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) => MergeStrategy.filterDistinctLines
    	case ("manifest.mf" :: Nil) | ("index.list" :: Nil) | ("dependencies" :: Nil) => MergeStrategy.discard
    	case "spring.tooling" :: xs => MergeStrategy.filterDistinctLines
    	case "license.txt" :: xs => MergeStrategy.discard
        case ps @ (x :: xs) if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") => MergeStrategy.discard
    	case _ => MergeStrategy.first
    }
    case "reference.conf" => MergeStrategy.concat
    case "overview.html" => MergeStrategy.discard
    case "about.html" => MergeStrategy.discard
    case _ => MergeStrategy.first
  }
}