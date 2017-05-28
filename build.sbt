name := """scala-request-json"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= {
  Seq(
    "com.typesafe.play" % "play-json_2.11" % "2.5.9",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
    "com.netaporter" %% "scala-uri" % "0.4.16",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

