val Organisation = "bbc.rms"
val Name = "scalambda"
val Version = "0.0.4"

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
  "com.amazonaws"           % "aws-lambda-java-core"    % "1.1.0",
  "org.json4s"              %% "json4s-jackson"         % "3.5.0",
  "org.specs2"              %% "specs2-core" % "4.0.0"  % "test",
  "org.specs2"              %% "specs2-mock" % "4.0.0"  % "test"
)

lazy val `scalambda`: Project = project.in(file("."))
  .settings(applicationSettings)
