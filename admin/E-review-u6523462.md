## Code Review

Reviewed by: Hao Zhang, u6523462

Reviewing code written by: Vincent Tanumihardja, u7145408

Component: Task8

### Comments 

There are two helper methods for solving the task 8:
The first one is a String method (RemovePosition), which removes the player and the coordination of cards in the 
Arboretum. It is used for getting all cards across the deck and hands.
The second one is a boolean method (sCardAdjacentToAnotherCard), which tells whether the card is adjacent to any card, and 
it combined previous methods in task 7.

The best feature of the code is using the helper methods to solve the task rather than copying and pasting repeated codes,
which will be terrible when finding or fixing mistakes.
The task is decomposed well because I can understand it without putting too much effort. However, if I only read the task 
8 without previous knowledge for task 7. Then it's not that easy as helper methods used for this task are four actually
(including the previous two in task 7), and some of them are nested.
The coding style is quite consistent and very neat, but there are no comments; for all helper methods, the only information
is about the name of the method. I have to read through the whole method, then know how the coder addresses the problem, which
is not easy work especially with so many helper methods when they are nested and not in the same place.



