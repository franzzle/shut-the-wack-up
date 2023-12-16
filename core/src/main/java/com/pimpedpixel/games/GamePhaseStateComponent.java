package com.pimpedpixel.games;

public class GamePhaseStateComponent {
    private GamePhaseState gamePhaseState = GamePhaseState.ATTRACT_SCREEN;

    void transitionToNextState(){
        switch (gamePhaseState){
            case ATTRACT_SCREEN:
                gamePhaseState = GamePhaseState.GAME_RUNNING;
                break;
            case GAME_RUNNING:
                gamePhaseState = GamePhaseState.GAME_OVER;
                break;
            case GAME_OVER:
                gamePhaseState = GamePhaseState.ATTRACT_SCREEN;
                break;
        }
    }
}
