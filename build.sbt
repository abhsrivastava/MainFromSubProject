
lazy val publishSettings =  Seq(
   licenses ++= Seq(("MIT", url("http://opensource.org/licenses/MIT"))),
   bintrayOrganization := Some("abhishes"),
   bintrayRepository := "AbhiTestRepo",
   bintrayPackageLabels := Seq("foo", "bar")
)

lazy val commonSettings = Seq(
   organization := "com.abhi",
   version := "1.0",
   scalaVersion := "2.12.3"
)


lazy val project1 = (project in file("SubProject1")).settings(commonSettings)
   .settings(publishSettings)
   .settings(
      name := "SubProject1",
      mainClass in Compile := Some("com.abhi.sub1.SubMain1")
   )

lazy val project2 = (project in file("SubProject2")).settings(commonSettings)
   .settings(publishSettings)
   .settings(
      name := "SubProject2",
      mainClass in Compile := Some("com.abhi.sub1.SubMain1")
   )

lazy val rootProject = (project in file("."))
   .settings(commonSettings)
   .settings(publishSettings)
   .settings(
      name := "MyScalaProject",
      mainClass in Compile := (mainClass in Compile in project1).value
).dependsOn(project1, project2)