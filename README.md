## Welcome

Welcome to PacMan FX, created by Keenan Johnson, Josh Weeks, and Conner Young. 

## Folder Structure

The folders of main significance are:

- `src`: the folder which contains the source code and art assets
- `lib`: the folder to maintain JavaFX dependencies
- `bin`: the folder in which compiled output files are generated

## Design Choices

- The map of the game is created on a 2D matrix, in which each cell represents a wall or open space with or without a pickup.
- Polymorphism is used in the model ArrayList pickups, which contains two different subtypes of pickup

## Known Bugs

- Ghosts can sometimes stack on top of each other
- Ghosts typically remain still if the player remains still
