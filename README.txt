Last edit by: Jon Bradley 6/15/2014


This project is a personal attempt at creating a prototype game engine.
It was designed around creating a tower-defense style game using Java Swing.
Development was halted at the end of summer '13 by the beginning of my final time at undergrad.

In its current state, the engine loads and renders a hard-coded map with passable and impassable tiles in a grid format.
Upon map creation, a pathfinding algorithm is run to determine pathing for the mob AI
AI zombies are then spawned in and use the stored path to navigate the map

The art assets are not currently publicly available and the engine will throw null errors without them.
However, the engine is capable of using any size of image file, as long as each 'tile' is the same pixel x pixel.
The engine will also accept any size of image for AI mobs.  We used 21 x 21 for tiles, and 9 x 15 for mobs
The engine is hard-coded to scale everything by 2, but this number is easily changed in Game.java


#########################################################
This is a tower defense game.

To do:
	Come up with a Map file format for custom maps
	Add towers and mobs
	Complete the tick() for each game component
	write a graphics handler for mobs, towers, and bullets
	Implement UI
	Create in-game currency
	
Completed:
	Add a map pathfinder



#########################################################
	The below older format will no longer be used
#########################################################

#########################################################
# This document will contain more detailed descriptions	#
#  of what has been changed when merging a project or	#
#  pushing to the GitHub website.			#
#########################################################

7/18/2013 	Entry #2
	New:	td\map\Map.java
		td\map\Tile.java
		td\graphics\Screen.java
		td\graphics\Sprite.java
	Mod:	td\Game.java
		td\entity\Entity.java
		.gitignore
	Rmv:	N/A

	File:	td\map\Map.java
	Change:	Created file
		Added methods: Map(), getHeight(), getWidth(), tick(), render(), createBufferedImage(), and changeTile()
		Added File Description at top of file

	File:	td\map\Tile.java
	Change:	Created file
		Added methods: Tile(), getID(), getHeight(), getWidth(), getImage()
		Added File Description at top of file

	File:	td\graphics\Screen.java
	Change:	Created file
		Added File Description at top of file

	File:	td\graphics\Sprite.java
		Created file
		Added methods: Sprite(), getHeight(), getWidth, getImage()
		Added File Description at top of file

	File:	td\entity\Entity.java
	Change:	Added File Description at top of file

	File:	td\Game.java
	Change:	Added small functionality to use a new map
		Added File Description at top of file

	File:	.gitignore
	Change:	Added an ignore for photoshop file types: .psb, .psd
		MOVED RESOURCES TO NEW FOLDER OUTSIDE OF GIT REPO SCOPE

#########################################################

7/17/2013	Entry #1
	New: 	README.txt
		.gitignore
		td\Game.java
		td\entity\Entity.java
	Mod:	N/A
	Rmv:	N/A

	File:	README.TXT
	Change:	Created file
		Wrote out the text you see here
		No need to keep meta updates - final entry for README.txt

	File:	.gitignore
	Change:	Created file
		Added git ignores for four picture types: jpeg, jpg, gif, and png

	File:	td\Game.java
	Change:	Created file
		Added methods: run(), tick(), render(), and main()

	File:	\td\entity\Entity.java
	Change: Created file
		Added methods: tick(), render(), init(), remove(), and move()
