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