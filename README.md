# mowersApplication

MowersApplication controls the mowers in a SEAT plant. It needs the coordinates of the terrain (plateau) and the position and movements for two mowers.


## How to compile
It is a maven project, so in the terminal write:

```bash
mvn clean install
```
## How to run
The principal class is Main.class so you can run this class and then the console will ask you about mowers information: the plateau coordinates, the position of two mowers and the movements.
```bash
5 5   //plateau coordinates
1 2 N   //example of a position
LMLMLMLMM   //movements
3 3 E 
MMRMMRMRRM
```
