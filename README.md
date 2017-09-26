## Prerequisites

Scala 2.11.+ is supported.

## Setup

### sbt

If you don't have it already, make sure you add the Maven Central as resolver in your SBT settings:

```scala
resolvers += Resolver.sonatypeRepo("releases")
```

Also, you need to include the library as your dependency:

```scala
libraryDependencies += "com.yukihirai0505" % "scala-request-json_2.11" % "1.5"
```

https://search.maven.org/#artifactdetails%7Ccom.yukihirai0505%7Cscala-request-json_2.11%7C1.5%7Cjar

## Submodule usage

Add submodule

```
$ git submodule add git@github.com:yukihirai0505/scala-request-json.git git-submodules/scala-request-json
```

Update submodules

```
$ git submodule update --init --recursive
```

```
$ git submodule foreach git pull origin master
```
