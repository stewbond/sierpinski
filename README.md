# Sierpinski

This application uses a set of simple algorithms mixed with a degree of chaotic randomness.  The result is usually more interesting than a simple pleasant pattern.  The result is usually an infinitely recurring pattern known as a fractal.  Several algorithms and a few variations on initial conditions are available to let you play as much as you like with the patterns and see what you can make.  

This is my implementation of [The Chaos Game](https://en.wikipedia.org/wiki/Chaos_game).
![Screenshot](./img/scrnsht.png?raw=true "Screenshot")
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

## The Algorithms

### Vanilla
#### Initial condition
The vanilla algorithms is fairly simple: Draw a set of `m` points where `m > 2`. We will call this set `A`.  Then place a point `p0`somewhere on the plane (it doesn't need to be between the points).  

#### Algorithm
Draw a dot halfway between the previous dot and a randomly selected starting point.
```
r = random() % m
p(n+1) = (A[r] - pn) / 2 + pn
```
![Vanilla](./img/vanilla.png?raw=true "Vanilla")

If you start with three points the result is a **Sierpinsky triangle**. Four points isn't terribly interesting, but things get fun again at five-plus.

### No Duplicates
#### Initial condition
The initial condition is the same as the Vanilla setup. Draw a set of `m` points where `m > 2`. We will call this set `A`.  Then place a point `p0`somewhere on the plane (it doesn't need to be between the points).  
#### Algorithm
Draw a dot halfway between the previous dot and a randomly selected starting point.  **The randomly selected starting point may not be the same as the previous point**.
```
do 
  r = random() % m
while ( r == r(n-1) )
p(n+1) = (A[r] - pn) / 2 + pn
```
![NoDups](./img/nodups.png?raw=true "No Duplicates")

### Clockwise
#### Initial condition
The initial condition is similar to the Vanilla setup. Draw a set of `m` points where `m > 3`. We will call this set `A`.  Then place a point `p0`somewhere on the plane (it doesn't need to be between the points).  
#### Algorithm
Draw a dot halfway between the previous dot and a randomly selected starting point.  The randomly selected starting point may not be the same as the previous point **or the next point clockwise to the previous point**.
```
do 
  r = random() % m
while ( r == r(n-1) || r == r(n-1) + 1 )
p(n+1) = (A[r] - pn) / 2 + pn
```
Of course, ensure that r wraps around correctly when checking for the next clockwise point.
![Clockwise](./img/clockwise.png?raw=true "Clockwise")

As you try this with more points, things feel less geometric and more detailed.
### No Adjacent
#### Initial condition
The initial condition is similar to the Vanilla setup. Draw a set of `m` points where `m > 4`. We will call this set `A`.  Then place a point `p0`somewhere on the plane (it doesn't need to be between the points).  
#### Algorithm
Draw a dot halfway between the previous dot and a randomly selected starting point.  The randomly selected starting point may not be the same as the previous point **or adjacent to the previous point**.
```
do 
  r = random() % m
while ( r == r(n-1) || r == r(n-1) + 1 || r == r(n-1) -1 )
p(n+1) = (A[r] - pn) / 2 + pn
```
Of course, ensure that r wraps around correctly when checking for the next clockwise point.
![NoAdjacent](./img/noadj.png?raw=true "No Adjacent")

Sometimes you can find the perfect number of points for each algorithm to give a very simple fractal.
### No Adjacent (Ex)
#### Initial condition
The initial condition is similar to the Vanilla setup. Draw a set of `m` points where `m > 3`. We will call this set `A`.  Then place a point `p0`somewhere on the plane (it doesn't need to be between the points).
#### Algorithm
Draw a dot halfway between the previous dot and a randomly selected starting point.  The randomly selected starting point may not be **adjacent to the previous point, but may be the previous point**.
```
do
  r = random() % m
while ( r == r(n-1) + 1 || r == r(n-1) -1 )
p(n+1) = (A[r] - pn) / 2 + pn
```
Of course, ensure that r wraps around correctly when checking for the next clockwise point.
![NoAdjacentEx](./img/noadjex.png?raw=true "No Adjacent (Ex)")

This algorithm is nearly identical to the *No Adjacent* algorithm with the exception that the previous starting point is a valid direction. In this case 4 points is a valid position, but you'll notice that it simply results in a line. This algorithm is interesting because it uses start patterns between all dots.
### Barnsley Fern
This algorithm is also quite simple but is significantly different from the others. It follows the equation `fm`:

```
P(n+1) = fm(Pn) = | a  b | * Pn + |e|
                  | c  d |        |f|
```
Where Matrix `A` is a 2x2 matrix with constant elements `a,b,c,d`, Vector `V` is a `1x2` vector with constant elements `e,f` and point `P` is a 1x2 vector with elements `x,y`.
There are four sets of constants `a,b,c,d,e,f`, each one pertaining to a different variation of the equation.  The variation is randomly selected using a weighted probability `p`. 

The initial condition, `P0`, is at `[0,0]`.  The constants and the weighted probability is shown in the table below: 

| function | a    | b    | c    | d    | e    | f    | p  |
|----------|------|------|------|------|------|------|----|
| f0(Pn)   | 0.00 | 0.00 | 0.00 | 0.16 | 0.00 | 0.00 |0.01|
| f1(Pn)   | 0.85 | 0.04 | -.04 | 0.85 | 0.00 | 1.60 |0.85|
| f2(Pn)   | 0.20 | -.26 | 0.23 | 0.22 | 0.00 | 1.60 |0.07|
| f3(Pn)   | -.15 | 0.28 | 0.26 | 0.24 | 0.00 | 0.44 |0.07|

#### Algorithm

```
r = randomSelect(p[])
Px = a[r] * Px_prev + b[r] * Py_prev + e[r]
Py = c[r] * Px_prev + d[r] * Py_prev + f[r]
```

![Fern](./img/fern.png?raw=true "Fern")

You can tweak these numbers to make different shaped ferns.  Some configurations exist which actually match specific species of fern. See some other variations [here](http://www.home.aone.net.au/~byzantium/ferns/fractal.html).

## Ideas for extensions to do

- **Additional Rules**:
    - You can't select a vertex opposite (n/2 distance) from the previous one.
    - You can't select a vertex 1 or n-1 spots away from the previous two selected vertices respectively. (Where n is the number of vertices)
    - You can't select adjacent vertices from the previous vertex, but you can select the previous one.
- **Performance**:
    - div/0 exceptions are thrown once in a while.
    - If we press "start" again, I think we spawn a new thread and leak the old one.
- **GUI and features**:
    - Add fern constants to the GUI so they can be configured
    - Add a few variations to the fern constants.
    - Add a "Randomize" setting to randomly select staring points
    - Separate GUI options from algorithmic options
    - Greyscale : Instead of drawing a black pixel, just increment the darkness of that pixel.
    - Figure out how to go 3D.
    - Scroll and Zoom
- **Refactoring**
    - Split OptionsStruct into something for the GUI (not exposed to Algorithms) and something for the `Algorithm`.
    - Reduce the amount of work new Algorithms need to do to be created.  Make them module and plugin-able.

## Implementing Algorithms
The application isn't totally optimized for adding algorithms.  You need to change a few files.  Here's what you need to do: 

- `OptionsStruct.java`: Add `public final static int ALGO_XXXX` to the `OptionsStruct` class.
- `Canvas.java`: Add a switch case to the `go` method to instantiate your algorithm.
- `LeftPane.java`: Add a name for your algorithm to `String[] algorithms` in the constructor.  The index of the string corresponds to the value you gave the `static int` in the `OptionsStruct`.
- `MyAlgorithm.java`: Make your own class. It must extend `Algorithm`.  You may instead extend one of the other algorithms if your class is similar and you can re-use the initialization from someone else.

### Instructions to Extend `Algorithm`
You need to implement two functions:
```java
class MyAlgorithm extends Algorithm {
  public Vector<Point> Initialize(OptionsStruct os) { ... }
  public Point Step(OptionsStruct os) { ... }
}
```
The first method, `Initialize`, will define initial conditions.  You may return as many points as you like for the initial drawing.  Points are in the pixel axis.  The width and height of the canvas are available in `OptionsStruct.width` and `OptionsStruct.height`.  The number of requested starting points is availabl in `OptionsStruct.startpoints`.  If you don't need that, then just don't use it.

The second method, `Step`, will be called every iteration.  You are expected to calculate and return one new point to draw.

The following function is available to you.  This may be used to generate the location of the starting points for a simple symmetrical polygon.
```java
protected Vector<Point> genericPolygon(OptionsStruct os)
```
Take a look at `Vanilla.java`, `NoDups.java`, and `Fern.java` for a few examples or check out stewbond@8ce0d6c282accc25b449c3fa7ccef6e45b6b9a4f for the simple implementation that was needed to create the [No Adjacent (Ex)](./README.md#No-Adjacent-\(Ex\)) algorithm.
