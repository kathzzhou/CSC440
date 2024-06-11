# CSC440
Project 1 for Intro to AI
Saanya Jauhri, Kathleen Zhou

Data and Analysis: 
1) Explain the design and algorithm for your Bot 4, being as specific as possible as to what your bot is actually doing. How does your bot factor in the available information to make more informed decisions about what to do next?

For our Bot 4, we decided on using an A* search which builds off of bot 3. We designed a heuristic using the probability a fire-adjacent cell will catch on fire as specified in the assignment, which we called probability P, and the Manhattan distance from the node to the button, M. Specifically, we find the shortest path from the node to the button avoiding all cells on fire AND all adjacent cells with P values > 0.5 (we can change this arbitrarily set value later after we run some tests, and then explain our testing process). In other words, h(n) is calculated as the M(node) that avoids all fire and fire-adjacent cells with P values > 0.5. We know that this heuristic is at least consistent, because in potentially taking shorter, fire-adjacent cell routes instead of going around all of them, h(n) <= C*(node, node') + h(node'). We believe that this bot is better informed than Bot 3, as we take into account the current spread of the fire like Bot 3 does, but we also attempt to assess risk of fire-adjacent cells instead of avoiding all of them altogether. 



2) For each bot, repeatedly generate test environments and evaluate the performance of the bot, for many values
of q between 0 and 1. Graph the probability (or average frequency, rather) of the bot successfully putting out
the fire. Be sure to repeat the experiment enough times to get accurate results for each bot, and each tested
value of q.



3) When bots fail or get trapped by the fire, why do they fail? Was there a better decision they could have made that would have saved them? Why or why not? Support your conclusions.



4) Speculate on how you might construct the ideal bot. What information would it use, what information would it compute, and how?

The ideal bot would take into account the current spread of the fire and the flammability of each cell, like in our Bot 4. Optimally, it would also take into account the risk of future paths being intercepted by fire; in essence, taking into account P values not just for the current fire spread but for predicted fire spread after next few timesteps. For example, if Bot 4 avoids all cells with P values > 0.5 but accidentally ends up being cornered by the fire because the node after the next node has a high P value and thus caught fire in the timesteps Bot 4 had taken to reach that node, then the ideal bot would avoid this situation by registering the probability of the next few nodes in the path and their predicted P value after the next few timesteps. In other words, the bot would extrapolate for P based off the continuation of time. 

I will think more on this
