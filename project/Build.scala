import sbt._

object ModelBuild extends Build {
    lazy val root = Project(id = "root", base = file(".")) aggregate(
      plannerJDBC,
      plannerUsecases,
      plannerSignature,
      plannerRestAPI,
      plannerRestAPIHarness,
      plannerBalancer,
      plannerExecutor
    )

    lazy val plannerJDBC = Project(id="jdbc", base = file("planner-jdbc"))

    lazy val plannerUsecases = Project(id="usecases", base = file("planner-usecases"))

    lazy val plannerSignature = Project(id="signature", base = file("planner-signature"))

    lazy val plannerRestAPI = Project(id="restAPI", base = file("planner-restapi"))dependsOn(plannerSignature, plannerJDBC)

    lazy val plannerRestAPIHarness = Project(id="restAPIHarness", base = file("planner-restapi-harness"))dependsOn(plannerSignature)

    lazy val plannerBalancer = Project(id="balancer", base = file("planner-balancer"))

    lazy val plannerExecutor = Project(id="executor", base = file("planner-executor"))dependsOn(plannerSignature, plannerJDBC)
}