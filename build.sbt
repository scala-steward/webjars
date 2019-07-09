lazy val root = (project in file(".")).enablePlugins(PlayScala)

name := "webjars"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  ws,
  ehcache,
  guice,
  filters,
  "net.spy" % "spymemcached" % "2.12.3",
  "com.lihaoyi" %% "fastparse" % "2.1.3",
  "org.apache.commons" % "commons-compress" % "1.18",
  "org.eclipse.jgit" % "org.eclipse.jgit" % "4.11.0.201803080745-r",
  "org.webjars" %% "webjars-play" % "2.7.3",
  "org.webjars.bower" % "bootstrap" % "3.3.4",
  "org.webjars.bower" % "select2" % "3.5.2",
  "org.webjars.bower" % "highlightjs" % "9.10.0",
  "org.webjars.bower" % "jquery-typewatch" % "2.2.1",
  specs2 % Test
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-unchecked", "-deprecation")

onLoad in Global := (onLoad in Global).value.andThen { state =>
  if (sys.props("java.specification.version") != "1.8") {
    sys.error("Java 8 is required for this project.")
    state.exit(ok = false)
  }
  else {
    state
  }
}

pipelineStages := Seq(gzip, digest)

publishArtifact in (Compile, packageDoc) := false

publishArtifact in packageDoc := false

sources in (Compile,doc) := Seq.empty
