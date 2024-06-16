# CSC440
Project 1 for Intro to AI
Saanya Jauhri, Kathleen Zhou

Data and Analysis: 
1) Explain the design and algorithm for your Bot 4, being as specific as possible as to what your bot is actually doing. How does your bot factor in the available information to make more informed decisions about what to do next?

For our Bot 4, we wanted to utilize not just the locations of the fire and fire-adjacent cells but also the spreadability of the fire. As the assignment specifies, each fire-adjacent cell catches on fire with the probability of 1 - (1 - q)^K, which we will call probability P. We thought that instead of avoiding all fire-adjacent cells like in bot 3, we could look at the probability of spread to our potential paths and decide which fire-adjacent cells it is worth the risk to cross into. Just like our bot 3, we are using a Breadth-First Search algorithm to find the shortest path to the button by creating a fringe to add new nodes onto and exploring the oldest ones first. However, at each time step, we stipulate that the only valid children to explore will be open cells that aren't on fire AND fire-adjacent open cells that are below a certain threshold for P. After (x) amount of trials, we determined that a threshold of (y) worked the best in terms of success rate and step amount. We believe that this bot is better informed than Bot 3, as we take into account the current spread of the fire like Bot 3 does, but we also attempt to assess risk of fire-adjacent cells instead of avoiding all of them altogether. 


2) For each bot, repeatedly generate test environments and evaluate the performance of the bot, for many values
of q between 0 and 1. Graph the probability (or average frequency, rather) of the bot successfully putting out
the fire. Be sure to repeat the experiment enough times to get accurate results for each bot, and each tested
value of q.



3) When bots fail or get trapped by the fire, why do they fail? Was there a better decision they could have made that would have saved them? Why or why not? Support your conclusions.



4) Speculate on how you might construct the ideal bot. What information would it use, what information would it compute, and how?

The ideal bot would take into account the current spread of the fire and the flammability of each cell, like in our Bot 4. Optimally, it would also take into account the risk of future paths being intercepted by fire; in essence, taking into account P values not just for the current fire spread but for predicted fire spread after next few timesteps. For example, if Bot 4 avoids all cells with P values > 0.5 but accidentally ends up being cornered by the fire because the node after the next node has a high P value and thus caught fire in the timesteps Bot 4 had taken to reach that node, then the ideal bot would avoid this situation by registering the probability of the next few nodes in the path and their predicted P value after the next few timesteps. In other words, the bot would extrapolate for P based off the continuation of time. 

I will think more on this
