val Organisation = "bbc.rms"
val Name = "scalambda"
val Version = "0.0.2"

val ScalaVersion = "2.12.4"
val Specs2Version = "3.6.5"

lazy val applicationSettings = Seq(
  name := Name,
  organization := Organisation,
  version := Version,
  scalaVersion := ScalaVersion,
  resolvers ++= Seq(
    Classpaths.typesafeReleases,
    "releases" at "https://oss.sonatype.org/content/repositories/releases"
  ),
  libraryDependencies ++= dependencies
)

lazy val dependencies = Seq(
  "io.github.mkotsur" %% "aws-lambda-scala" % "0.0.8-SNAPSHOT"
)

lazy val `scalambda`: Project = project.in(file("."))
  .settings(applicationSettings)
