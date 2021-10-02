# Game
When a player starts, it incepts a random (whole) number and sends it to the second 
player as an approach of starting the game. The receiving player can now always choose 
between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. The 
resulting whole number is then sent back to the original sender.

# Technologies
Java and Spring-boot

# How to run it
- Clone the main project by this command:
 `git clone https://github.com/w-salloum/game`
- From the terminal go to ./game and use maven to build the project `mvn clean install`
- Run the player server by `java -jar ./player/target/player-0.0.1.jar ` , it will run it on the port 8082 ( you can change the port from application.properties)
- Run the game manager server by `java -jar gamemanager/target/gamemanager-0.0.1.jar` , it will run it on the port 8081 ( you can change the port from application.properties)
- Go to http://localhost:8081/game/start to create an instance from the game manager and make it ready to receive players
- To create an automatic player  http://localhost:8082/player/new , use another browser to create another player, while you need 2 players to play the game.
- To create a manual player ( who will not be able to play automatically) http://localhost:8082/player/new?auto=false
- To allow the manual player to play http://localhost:8082/player/play ( you will not be able to use it for the automatic player)
- To check the moves http://localhost:8081/game/moves 

# How it works
- We use the player server to create automatic and manual players
- When a player is created, an event will be created to ask the game manager for invitation
- When the player server get an invitation for players to play, it will send players IDs who are waiting to play.
- The players can play by APIs
- We can use start API to start the game by a player ( to send a random number) 
- Player server has a waiting queue of the players who are created and have not started playing yet.
- Game manager will create an instance of the game and will be waiting for players.
- When we create an instance of the game manager, an event will be created to invite players to join the game from the player server.
- When 2 players join the game, it will be started automatically, and the 2 players will be communicated by APIs.
- There is only one instance of the game manager, and when the game is finished, it will be archived with the moves, and a new game manager instance will be created and be ready to receive new players again ( the code is extendable to use threads and allow more than one instance)

