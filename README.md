# Code Soccer

Code Soccer is a project where I tried to programme and evaluation system of a game Paper Soccer and implement AI algorithms. You can learn the rules on [Wiki](https://en.wikipedia.org/wiki/Paper_soccer) or in my [dissertation work report](https://github.com/StudentTraineeCenter/code-soccer/blob/master/Wodecki_AbsolventskaPrace.pdf) which has been written in Czech.

## Life cycle of a game of Code Soccer
1. _method Main_ calls _method setupGame_ of _GameSetup.java_.
2. Instance of _SoccerGame.java_ is created with designated number of rows and columns. Move history is prepared and ball is set at the center.
3. After the initialization proccess _method play_ of _GameEvaluation.java_ is called which starts the game.
4. When game is finished, the winner is printed out in the console.

## How to implement your own AI
1. Fork this repo
2. In _opponents_ package create your own opponent. **This opponet has to implement interface _Opponent.java_ located at _stc/soccer/opponents/Opponent.java:13_**
3. Add your own type of AI to _OpponentType.java_ enum located at _stc.soccer.opponents.types.OpponentType#NAIVEAI_
4. Pass your AI to the constructor of the evaluation system in _GameSetup.java_ located at _stc/soccer/core/GameSetup.java:26_. **Both passed opponents must have different values of _GoalLocationType_, otherwise an _IllegaleStatecException_ is thrown.**
5. Start your game and look at the results!
