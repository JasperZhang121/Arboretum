## Code Review

Reviewed by: Vincent Tanumihardja, u7145408

Reviewing code written by: Samuel Barilaro, u7298894

Component: Task 7 (isPlacementValid)

### Comments 

The task was decomposed quite nicely, so it is understandable for the reader. 
There are 2 helper functions used to solve the task. 
The first one is adjustSubCoordinate which converts the coordinates into C,N,S,E,W based on the coordinates given.
This is useful to convert the coordinates appropriately for adjacentLocations.
The second helper function produces the adjacent coordinates given a card placement.
This helper function then help to simplify the main task as it creates a list for the main task to check whether the placement is valid.
Placement is only valid when the location appears in the list of adjacent locations.
Unfortunately, the code lacks documentation.
Even though the function names are quite clear but documentation is still required so that the reader can know the thought process when solving the task.
However, the names do follow the Java code convention and the style is consistent throughout the task.
But, I think it would be better to put new statements on a new line to make it look tidier.


