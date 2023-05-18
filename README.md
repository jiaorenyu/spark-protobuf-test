# Test spark 3.4.0 protobuf support, see [details](https://spark.apache.org/docs/latest/sql-data-sources-protobuf.html)

## run notebook(scala kernel) locally

we are using scala jupyter kernel [Almond.sh]() to run spark locally.
Assume we already have `couriser` installed locally (we should have if we correctly setup our dev environment when company onboarding), in case you don't have it, please follow the [steps in details](https://almond.sh/docs/quick-start-install).

### install Almond
```bash
$ cs launch --fork almond --scala 2.12 -- --install
```

### build shaded uber jar
since there would be a `protobuf-java` conflict with spark itself, we need to shade `com.google.protobuf.**`, see [guideline](https://sourcegraph.com/github.com/apache/spark/-/blob/python/pyspark/sql/protobuf/functions.py?L47-51).
```scala
ThisBuild / assemblyShadeRules := Seq(
  ShadeRule.rename("com.google.protobuf.**" -> "org.sparkproject.spark_protobuf.protobuf.@1").inAll
)
```
publish to local repo
```bash
$ sbt publishLocal
```

### play within notebook test.ipynb
