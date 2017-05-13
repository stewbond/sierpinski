# sierpinski

This application uses an algorithm for interesting results.  Given a set of initial vertices, this algorithm will draw a dot in a random location.  Then it will ranomly choose a vertex and place a dot halfway between the selected vertex and the previous dot.  The result is interesting.  For three points (placed anywhere, doesn't have to be equilateral), the result is a Sierpinski trianle.

FRACTALS!

## Things I'd like to do:
- Make all hard-coded parameters optional and configurable in the GUI.  This can be should in the "Left Pane".  
- Generalize the number of vertices.  3 Verticies are hard-coded.  I also tested with 5 vertices (you can see them commented out).  I'd like to be able to select an arbitrary number of vertices from the left pane.   

## To build

```
$ javac -d bin src/*.java
```

## To run
```
$ cd bin
$ java Main
```

## Screenshot

![Screenshot](./img/screenshot.png?raw=true "Screenshot")
