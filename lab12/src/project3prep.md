# Project 3 Prep

**What distinguishes a hallway from a room? How are they similar?**

Answer: Hallways are purely present to connect rooms. We can distinguish them because rooms are generally rectangular, and
hallways only have a width of 1, with a length of whatever it needs to be to connect the prior room to the next room.
They're both similar in that they need to be traversable and have walls that are distinguishable from the floors.

**Can you think of an analogy between the process of 
drawing a knight world and randomly generating a world 
using rooms and hallways?**

Answer: Drawing a knight world is like baking a macaron where we need to follow all the same steps in order to get a "pie"; 
if the algorithm is off (like too much egg white or flour) then the proportions are off and the textures are off, and it's no longer
a macaron.
However, drawing a randomly generated world with rooms and hallways is like making beef stew. You can add a lot of beef with few
potatoes or a lot of potatoes with a little beef; at the end of the day it's still going to be and taste like beef stew. 

**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually 
get to the full knight world.**

Answer: Starting from the ground up, I would first work on first creating the desired size of the world. Then, I would work
on generating the rooms of random size in random parts of the world. I would lastly focus on creating random bridge points
between the rooms with straight or pivoting hallways.

**This question is open-ended. Write some classes 
and methods that might be useful for Project 3. Think 
about what helper methods and classes you used for the lab!**

Answer: We will probably need a class that we can delegate the room generation process to. It'll dictate the boundaries of room
size and room shape. We will also probably need a class that handles hallways similar to that we can call after we create the rooms.
We can create a helper method to call within our constructor to fill in the empty world with rooms and hallways.
