# LPOO1718_T6G5

TODO LIST:
			
	- refactor values taken from level construtor to initialize map and interactables		
			
	- constructor of the game must take as input the values given in the gui
	
	- get rid of enemy abstract class and implement an interface to force movement to movable classes such as hero ogre guard etc	
			
	- move the game logic of the levels "Level1" and "Level2" up to the abstract class "Level"
			to prevent repetition of logic
			FOR EXAMPLE: every level has a hero moving, end game detection, enemy movement
							and then eng game detection, the only thing that changes is the 
							condition to end the level and the array of npcs and enemies, however
							those are saved in the level class therefore the class "Level" has 
							acess to it  