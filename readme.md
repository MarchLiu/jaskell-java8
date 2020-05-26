# Jaskell Java8

一个特化的 Java 8 版 Jaskell ，专为在 Java 8 环境下的项目准备的一个纯 
Java 版本。不带 Clojure 依赖，并且尽可能不依赖任何标准库之外的组件。

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
