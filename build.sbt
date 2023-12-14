// See README.md for license details.
ThisBuild / crossScalaVersions  := Seq("2.13.10")
ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.josephpopo"

val chiselVersion = "6.0.0-M3"
val firrtlVersion = "6.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "Chisel_Formal",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "edu.berkeley.cs" %% "firrtl2" % firrtlVersion,
      "org.scalatest" %% "scalatest" % "3.2.17",
      "net.java.dev.jna" % "jna" % "5.13.0",
      "edu.berkeley.cs" %% "chiseltest" % "6.0-SNAPSHOT",
      compilerPlugin(("org.chipsalliance" % "chisel-plugin" % chiselVersion).cross(CrossVersion.full))
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations",
    ),
    resolvers ++= Resolver.sonatypeOssRepos("snapshots"),
    resolvers ++= Resolver.sonatypeOssRepos("releases")
)
lazy val publishSettings = Seq(
  publishMavenStyle := true,
  Test / publishArtifact := false,
  pomIncludeRepository := { x => false },
  // scm is set by sbt-ci-release
  pomExtra :=
    <url>https://github.com/ucb-bar/chiseltest/</url>
      <licenses>
        <license>
          <name>Apache-2.0</name>
          <url>https://opensource.org/licenses/Apache-2.0</url>
          <distribution>repo</distribution>
        </license>
        <license>
          <name>BSD-3-Clause</name>
          <url>https://opensource.org/licenses/BSD-3-Clause</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <developers>
        <developer>
          <id>ekiwi</id>
          <name>Kevin Laeufer</name>
          <email>laeufer@berkeley.edu</email>
        </developer>
        <developer>
          <id>ducky64</id>
          <name>Richard Lin</name>
        </developer>
      </developers>,
  publishTo := {
    val v = version.value
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT")) {
      Some("snapshots".at(nexus + "content/repositories/snapshots"))
    } else {
      Some("releases".at(nexus + "service/local/staging/deploy/maven2"))
    }
  }
)