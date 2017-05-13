# Sierpinski

This application uses an algorithm for interesting results.  Given a set of initial vertices, this algorithm will draw a dot in a random location.  Then it will ranomly choose a vertex and place a dot halfway between the selected vertex and the previous dot.  The result is interesting.  For three points (placed anywhere, doesn't have to be equilateral), the result is a Sierpinski trianle.

This is my implementation of [The Chaos Game](https://en.wikipedia.org/wiki/Chaos_game).

FRACTALS!

## To build

```
$ javac -d bin src/*.java
```

## To run
```
$ cd bin
$ java Main
```

## Screenshots

![Screenshot1](./img/screenshot1.png?raw=true "Screenshot 1")
![Screenshot2](./img/screenshot2.png?raw=true "Screenshot 2")

## Extensions to do

- It would be fun to define a set of additional rules to The Chaos Game:
 -- You can't select the same vertex twice.
 -- You can't select the next clockwise vertex.
 -- You can't select an ajacent vertex (5 or larger)
 -- You can't select a vertex opposite (n/2 distance) from the previous one.
 -- You can't select a vertex 1 or n-1 spots away from the previous two selected vertices respectively. (Where n is the number of vertices)
- Imagine if we could change this so that the user could define their own rules without a need to recompile!
