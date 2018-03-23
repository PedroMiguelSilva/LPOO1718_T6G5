# LPOO1718_T6G5

TODO LIST:
	
	- generalize the movement of entities for all entities
			(or just  movable ones since
			interactive objects do not move)
			
	- change map layout to an matrix of abstract classes CELLS with an array of objects
			to prevent problems from two objects beeing in the same position at the given time
			(to print the map, the fucntion would only print the symbol of the object in the
			position 0 of the object array) all the cells in the matrix would be initialized
			with the begining of the map values or just with Clear objects yet to be
			implemented
			
			NOTE: this can be accomplished by a two layer layout with basis of fixed objects and interactives and then movable object 			on top
			
	- constructor of the game must take as input the values given in the gui
	
	- get rid of enemy abstract class and implement an interface to force movement to movable classes such as hero ogre guard etc	
			
	- move the game logic of the levels "Level1" and "Level2" up to the abstract class "Level"
			to prevent repetition of logic
			FOR EXAMPLE: every level has a hero moving, end game detection, enemy movement
							and then eng game detection, the only thing that changes is the 
							condition to end the level and the array of npcs and enemies, however
							those are saved in the level class therefore the class "Level" has 
							acess to it  