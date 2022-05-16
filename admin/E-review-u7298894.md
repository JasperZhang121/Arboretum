## Code Review

Reviewed by: Samuel Barilaro, u7298894

Reviewing code written by: Hao Zhang, u6523462

Component: task 6

### Comments 

task 6 is clear and concise, the function of the two methods are easy to understand and each visual element is clearly 
defined. It uses one helper function to find the location of a card relative to the centre in
a format more practical to use than placement substring format outlined in the task 4 description. 
the coding style is consistent throughout the task mostly follows java naming conventions though some variable names 
do not use camel case and one line exceeds 80 characters. 
Though the task is easy to understand, there is a lack of documentation.
The primary method of the task is efficient though there are 4 unused variables defined.
The helper function is also quite efficient though it returns a string when the role of the function only requires void,
this string is used when testing, and I can understand the choice made here as writing a test for a void java fx method 
cant be done in the way a regular test is done and has not yet been taught.



