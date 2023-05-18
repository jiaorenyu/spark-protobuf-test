import sbtassembly.AssemblyPlugin.autoImport._

organization := "com.tubitv"
name := "zy-test-protobuf"
version := "1.0-SNAPSHOT"
crossPaths := false

libraryDependencies ++= Seq(
    "com.google.protobuf" % "protobuf-java" % "3.21.12",
)

Compile / PB.targets := Seq(
  PB.gens.java -> (Compile / sourceManaged).value
)

ThisBuild / assemblyShadeRules := Seq(
  ShadeRule.rename("com.google.protobuf.**" -> "org.sparkproject.spark_protobuf.protobuf.@1").inAll
)

assemblyPackageScala / assembleArtifact := false

assembly / artifact := {
  val art = (assembly / artifact).value
  art.withClassifier(Some("assembly"))
}

addArtifact(assembly / artifact, assembly)
