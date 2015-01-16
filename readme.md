Viking Game

* Your ship
** Has a crew
** Has an 'Infamy' score
** Has cargo space

* Procedurally Generated World
** Coastline
** Islands
** Settlements
** Other raiding ships on the sea

* Home Settlement
** Stores your loot, acting as your 'score'
** Storing loot visually upgrades your home settlement over time

* Other Settlements
** Can have different levels of fortifications
** Can have different amounts of defenders
** Has a leader
*** Name randomly generated based on a Markov chain generator
*** Has a 'Bravery' score

* Confrontations
** Shows you the leader's name, the level of fortifications, and the amount of defenders
** Gives you some basic options to interact with the enemy leader
*** Attack
**** Starts combat phase
*** Threaten
**** Pits your 'infamy' score against their 'bravery' score
****
*** Disengage
**** If their bravery is higher than your infamy, they will attack you when you disengage
***** They lose the fortification bonus when doing this though
** Combat is based on your crew vs. (their crew + their fortifications)
*** Small chance percentage, critical hits, etc.?



#TODO:
* load gamescreen in the background while title menuscreen is up?
* asset loader class?


Systems
* movement
* render
* input
* ui
* collision


Components
* graphic
* particle
* position
* velocity
* health
* damage
* player
* ai
* collided?