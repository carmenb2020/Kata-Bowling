package org.example.Game;
import java.util.ArrayList;
import java.util.List;
public class Game {
    private int score;
    public List framesGame = new ArrayList<>();
    public boolean strike,spare = false;
    public void Roll(int pins,int rolls){
        int scoreFrame;
        int frame=framesGame.size();
        if (frame==0 && rolls == 1){
            framesGame.add(pins);
            scoreFrame= pins;
            if(pins == 10){
                strike=true;
            }
        }else if(frame==0 && rolls==2){
            scoreFrame = (int) framesGame.get(0);
            framesGame.set(0,scoreFrame+pins);
            scoreFrame = (int) framesGame.get(0);
            if (scoreFrame==10){
                    spare= true;
            }
        }else if(frame>0 && frame<=11 && rolls == 1){
            framesGame.add(pins);
            int i= framesGame.size()-1;
            if (spare){
                int j = i-1;
                int scoreSpare = (int)framesGame.get(j);
                framesGame.set(j,scoreSpare+pins);
                spare=false;
            }
            if(pins == 10 && i<10){
                strike=true;
            }
        }else if(frame>0 && frame<=11 && rolls==2){
            int i= framesGame.size()-1;
            scoreFrame = (int)framesGame.get(i);
            if (scoreFrame <=10){
                framesGame.set(i,scoreFrame+pins);
                scoreFrame = (int)framesGame.get(i);
                if(strike){
                    int j = i-1;
                    int scoreStrike = (int)framesGame.get(j);
                    framesGame.set(j,scoreStrike+scoreFrame);
                    strike=false;
                }
                if (scoreFrame==10 && i<10){
                    spare= true;
                }
            }
        }
        if (framesGame.size() == 10 && !strike && !spare){
            this.Score();
        }
    }

    public int Score(){
        int scoreFrame = 0;
        for (int i = 0; i<=10;i++){
            scoreFrame = scoreFrame + (int)framesGame.get(i);
        }
        score = scoreFrame;
        return score;
    }

    public List getFramesGame() {
        return framesGame;
    }

    public void setFramesGame(List frames) {
        this.framesGame = frames;
    }

}
