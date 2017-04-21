import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.event.*;

public class Engine {
    String state = "play"; // game state "play", "game over" or "menu" (not implemented yet);
    int score = 0;
    double speed = 10;   // Movement speed of Obstacles and pick ups
    int mood = 5;
    boolean up = false;    // Boolean Value to determine movement direction

    Character bird = new Character( 5, 50, 50);
    Pickup pUp = new Pickup(randInt(10));
    Obstacle cloudA = new Obstacle(5);
    Obstacle cloudB = new Obstacle(5);

    public Engine()
    {
        UI.setDivider(0.0);
        UI.initialise();
        UI.setKeyListener(this::direction);   
        UI.setFontSize(20);
        UI.setDivider(0.0);
        UI.addButton( "Play", this::playGame);
        UI.addButton( "Quit", UI::quit);
    }

    public void direction(String dir){
        if(dir.equals("w")){up = true;}
        else if(dir.equals("s")){up = false;}
    }

    public void playGame()
    {                       
        reset();

        while( state.equals("play")){
            processCollisions();
            updatePositions();
            drawAssets();

            score += speed;
        }

        if ( state.equals("game over"))
        {
            drawBG(5, 0, 0);
            UI.setFontSize(40);
            UI.drawString("GAME OVER", 300, 100 );
            UI.setFontSize(20);
            UI.drawString("Score: " + score, 300, 200);
        }
    }

    public void drawBG(int mood, double x, double y)
    {
        String fname = null;
        fname = "Scene" + "/" +  mood + ".png"; 
        UI.drawImage(fname, 0, 0, 18000, 500);
    }

    public void updatePositions(){
        pUp.setX( pUp.getX() - speed * 2);
        cloudA.setX( cloudA.getX() - speed * 2);
        cloudB.setX( cloudB.getX() - speed * 2);
        if(up && bird.getY() > 0){bird.setY(bird.getY() - 15) ;}
        else if (bird.getY() < 452){bird.setY(bird.getY() + 15) ;}

    }

    public void drawAssets(){
        drawBG(mood, 0, 0);
        UI.drawString("Score: " + score, 700, 50);
        if (pUp.getX() < (0 - pUp.getWidth())){ pUp.reDraw();} else{pUp.draw();}
        if (cloudA.getX() < (0 - cloudA.getWidth())){cloudA.reDraw();}else{cloudA.draw();}
        if (cloudB.getX() < (0 - cloudB.getWidth())){cloudB.reDraw();}else{cloudB.draw();}
        bird.draw();
    }

    public void processCollisions(){
        if ( ( pUp.getX()  <= bird.getX() + bird.getWidth()) 
        && ( pUp.getY() + pUp.getHeight() > bird.getY() 
            &&  pUp.getY() < bird.getY() + bird.getHeight()))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);
            pUp.reDraw();
            setGlobalSpeed( pUp.getMood());   
        }

        if ( ( cloudA.getX()  <= bird.getX() + bird.getWidth()) 
        && ( cloudA.getY() + cloudA.getHeight() > bird.getY() + bird.getHeight()  
            &&  cloudA.getY() < bird.getY()))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);
            state = "game over";
        }

        if ( ( cloudB.getX()  <= bird.getX() + bird.getWidth()) 
        && ( cloudB.getY() + cloudB.getHeight() > bird.getY() + bird.getHeight() 
            &&  cloudB.getY() < bird.getY()  ))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);
            state = "game over";
        }

        if ( ( pUp.getX()  >= cloudA.getX() && pUp.getX()  <= cloudA.getX() + cloudA.getWidth()) 
        && ( pUp.getY()+ pUp.getHeight() > cloudA.getY() 
            &&  pUp.getY() < cloudA.getY() + cloudA.getHeight()))
        {
            pUp.reDraw();
        }

        if ( ( pUp.getX()  >= cloudB.getX() && pUp.getX()  <= cloudB.getX() + cloudB.getWidth()) 
        && ( pUp.getY()+ pUp.getHeight() > cloudB.getY() 
            &&  pUp.getY() < cloudB.getY() + cloudB.getHeight()))
        {
            pUp.reDraw();
        }

        if ( ( cloudB.getX()  >= cloudA.getX() && cloudB.getX()  <= cloudA.getX() + cloudA.getWidth()) 
        && ( cloudB.getY()+ cloudB.getHeight() > cloudA.getY() 
            && cloudB.getY() < cloudA.getY() + cloudA.getHeight()))
        {
            cloudB.reDraw();
        }
    }

    public void setGlobalSpeed(int m){
        speed += m / 2;
        //bird.setMood(m);
        pUp.setMood(m);
        //cloud.setMood(m);

    }

    public void reset(){

        state = "play"; // game state "play", "game over" or "menu" (not implemented yet);
        score = 0;
        speed = 10;   // Movement speed of Obstacles and pick ups
        mood = 5;
        Character bird = new Character( 5, 50, 50);
        pUp.reDraw();
        cloudA.reDraw();
        cloudB.reDraw();

    }

    public int randInt(int max) {
        return ( (int)(Math.random()* max ) );
    }

    public static void main(String[] args){
        Engine obj = new Engine();
    }

}
