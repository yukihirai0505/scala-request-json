name := """scala-request-json"""

version := "1.8"

scalaVersion := "2.11.7"

lazy val scalaRequestJson = Project(id = "scala-request-json", base = file("./"))

// Change this to another test framework if you prefer
libraryDependencies ++= {
  Seq(
    "com.typesafe.play" % "play-json_2.11" % "2.5.9",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.4",
    "com.netaporter" %% "scala-uri" % "0.4.16",
    "ai.x" %% "play-json-extensions" % "0.8.0",
    "com.github.tototoshi" %% "play-json-naming" % "1.1.0",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  )
}
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

organization := "com.yukihirai0505"

organizationName := "com.yukihirai0505"

organizationHomepage := Some(url("https://yukihirai0505.github.io"))

description := "A Scala library for the Asynchronous Json Request"

pomExtra :=
  <url>https://github.com/yukihirai0505/scala-request-json</url>
    <licenses>
      <license>
        <name>MIT</name>
        <url>https://github.com/yukihirai0505/scala-request-json/blob/master/LICENSE.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:yukihirai0505/scala-request-json.git</url>
      <connection>scm:git:git@github.com:yukihirai0505/scala-request-json.git</connection>
    </scm>
    <developers>
      <developer>
        <id>yukihirai0505</id>
        <name>Yuki Hirai</name>
        <url>https://yukihirai0505.github.io</url>
      </developer>
    </developers>
