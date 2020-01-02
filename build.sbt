Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion := "2.12.10"

lazy val root = project
  .in(file("."))
  .settings(name := "memeid")
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect"       % "2.0.0",
      "org.specs2"    %% "specs2-scalacheck" % "4.8.1" % Test,
      "org.specs2"    %% "specs2-cats"       % "4.8.1" % Test,
      "org.typelevel" %% "cats-laws"         % "2.1.0" % Test,
      "org.typelevel" %% "discipline-specs2" % "1.0.0" % Test
    )
  )

lazy val literal = project
  .dependsOn(root)
  .settings(name := "memeid-literal")
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
      "com.chuusai"    %% "shapeless"    % "2.3.3"            % Test,
      "org.specs2"     %% "specs2-core"  % "4.8.1"            % Test
    )
  )

lazy val doobie = project
  .dependsOn(root)
  .settings(name := "memeid-doobie")
  .settings(
    libraryDependencies ++= Seq(
      "org.tpolecat" %% "doobie-core"   % "0.8.8",
      "org.tpolecat" %% "doobie-specs2" % "0.8.8" % Test,
      "org.specs2"   %% "specs2-cats"   % "4.8.1" % Test,
      "org.tpolecat" %% "doobie-h2"     % "0.8.8" % Test
    )
  )

lazy val circe = project
  .dependsOn(root)
  .settings(name := "memeid-circe")
  .settings(
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.12.3"
    )
  )
