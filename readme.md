# Jaskell Java8

Jaskell Java8 is Java8 Edition of [Jaskell Core library](https://github.com/MarchLiu/jaskell-core). 
It rewritten completed by Java 8. So easy to use in Java8 projects.

It includes a parsec combinators library , a SQL expressions library and a arithmetic expressions parser.  


[![Maven Central](https://img.shields.io/maven-central/v/io.github.marchliu/jaskell-java8.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.marchliu%22%20AND%20a:%22jaskell-java8%22)

## INSTALLATION

### Maven

```xml
<dependency>
  <groupId>io.github.marchliu</groupId>
  <artifactId>jaskell-java8</artifactId>
  <version>2.1.3</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.marchliu:jaskell-java8:1.5.1'
```

### Gradle Kotlin

```
implementation("io.github.marchliu:jaskell-java8:1.5.1")
```

### SBT

```sbtshell
libraryDependencies += "io.github.marchliu" % "jaskell-java8" % "1.5.1"
```

### Apache Ivy

```xml
<dependency org="io.github.marchliu" name="jaskell-java8" rev="1.5.1" />
```

### Groovy Grap

```groovy
@Grapes(
  @Grab(group='io.github.marchliu', module='jaskell-java8', version='1.5.1')
)

```

### Leiningen

```clojure
[io.github.marchliu/jaskell-java8 "2.0.1"]
```

### Apache Bluildr

```
'io.github.marchliu:jaskell-java8:jar:1.5.1'
```

### Maven Central Badge

```
[![Maven Central](https://img.shields.io/maven-central/v/io.github.marchliu/jaskell-java8.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.marchliu%22%20AND%20a:%22jaskell-java8%22)
```

### PURL

```
pkg:maven/io.github.marchliu/jaskell-java8@1.5.1
```

### Bazel

```
maven_jar(
    name = "jaskell-java8",
    artifact = "io.github.marchliu:jaskell-java8:1.5.1",
    sha1 = "6a405e182efbd5bf1923b5b83cbbc1890329e759",
)
```

## Update Log

### 1.1

 - SQL add sum function
 - make sum and count functions as literal, so them could have alias
 - add parsec common namespace for common jobs
 - make parsec combinators easier to use 
 - make group/order by accept name list
 - add choice(list of parsecs) function
 - make name could escape
 - make parameters of sql more clean
 
### 1.2.2

1.2 着重 SQL 组件重构

 - add text state constructor
 - add int tests
 - bugs in int parser fixed
 - all SQL statements generate upper case words
 
### 1.3
 
 - add expression parser library
 - bugs fixed

### 1.4
 
 - parsec can parse a Seq or String once by shortcut method
 - add scientific number parser
 - add a test include scientific number new test
 - bug fixed
 
#### 1.4.1

 - where could order and group
 
#### 1.4.2

 - add parameter expression
 
### 1.5

 - make sql components easier to used.
 - add distinct
 - add select list
 - add select function
 - add order by and group by list
 - add in predicate
 - bug fixed, Select.From now is CouldFrom
 
### 1.5.7

 - add more default methods sugar for parsec functions
 - make binary public
 
### 1.5.8

 - having now is a query
 
### 1.5.9
 
  - add could having
  
### 1.6.0

 - add weak expression sample mix boolean and arithmetic expression
 
### 1.6.1

 - add could order, limit offset for having
 
### 1.6.2

 - add could union for query
 - add could returning
 
### 1.6.3

 - add predicate parser

### 2.0.0

 - formal sync as jaskell-dotty

### 2.0.1

 - try now named attempt
 - create a try type like scala try

### 2.1.0

 - reflections of Try

### 2.1.1

 - add map and recover for try
