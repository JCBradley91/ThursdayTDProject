This is a tower defense game.

To do:
	Come up with a Map file format for custom maps
	Add towers and mobs
	Add a map pathfinder
	Complete the tick() for each game component
	write a graphics handler for mobs, towers, and bullets
	Implement UI
	Create in-game currency



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