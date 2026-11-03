# Homework 9: Structural Design Patterns (Flyweight, Proxy)

A Java project using the Flyweight and Proxy patterns, tested with JUnit 5 and Maven.

## Requirements

- Java 17 or newer
- Maven 3.9 or newer

## Run the tests

```
mvn test
```

In VS Code you can also run them from the Testing panel.
The `SongServer` tests take a few seconds because every real server search waits 1 second.

## Run the drivers

```
mvn -q compile
java -cp target/classes flyweight.FlyweightDriver
java -cp target/classes proxy.ProxyDriver
```

## Project structure

```
src/main/java/flyweight/
  CharacterProperties.java          flyweight (shared font, size, color)
  CharacterPropertiesFactory.java   flyweight factory (hands out shared objects)
  TextCharacter.java                one letter + a reference to its shared properties
  Document.java                     list of characters; add, edit, save, load, print
  FlyweightDriver.java              saves "HelloWorldCS5800" with 4+ property variations

src/main/java/proxy/
  SongService.java                  subject interface (given in the assignment)
  Song.java                         song metadata
  SongServer.java                   real subject (slow: 1-second delay per search)
  SongServiceProxy.java             proxy (caches results in memory)
  ProxyDriver.java

src/test/java/flyweight/            one test class per class above
src/test/java/proxy/                one test class per class above
  FakeSongServer.java               fast fake server that counts calls (test helper)
```
