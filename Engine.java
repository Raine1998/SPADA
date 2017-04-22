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
    
    int highScore = 0;

    boolean up = false;    // Boolean Value to determine movement direction
    int birdState = 1;

    Character bird = new Character(50, 50);
    Pickup pUp = new Pickup();
    Obstacle cloudA = new Obstacle(mood);
    Obstacle cloudB = new Obstacle(mood);
    Sound music= new Sound("BGMusic.wav");

    public Engine()
    {
        UI.initialise();
        UI.setWindowSize(1200, 700);
        UI.setDivider(0.0);

        UI.setKeyListener(this::direction);   
        UI.setFontSize(20);
        UI.setDivider(0.0);
        UI.addButton( "Play", this::playGame);
        UI.addButton( "Quit", UI::quit);
    }

    public void direction(String dir){
        if(dir.equals("Up")){up = true;}
        else if(dir.equals("Down")){up = false;}
    }

    public void playGame()
    {                       
        reset();

        while( state.equals("play")){
            music.loop();
            
            processCollisions();
            drawAssets();
            updatePositions();

            score += speed;

            if ( mood < 1 || mood > 10){ state = "game over";}
        }

        if ( state.equals("game over"))
        {
            music.stop();
            drawBG(5, 0, 0);
            UI.setFontSize(40);
            UI.drawString("GAME OVER", 350, 100 );
            UI.setFontSize(20);
            
            if( score > highScore)
                { highScore = score;}
            UI.drawString("High Score: " + highScore, 400, 200);    
            UI.drawString("Your Score: " + score, 400, 400);

        }
    }

    public void drawBG(int mood, double x, double y)
    {
        String fname = null;
        fname = "Scene" + "/" +  mood + ".png"; 
        UI.drawImage(fname, 0, 0, 1200, 500);
    }

    public void updatePositions(){
        pUp.setX( pUp.getX() - speed * 2);
        cloudA.setMood(mood);
        cloudA.setX( cloudA.getX() - speed * 2);
        cloudB.setMood(mood);
        cloudB.setX( cloudB.getX() - speed * 2);
        if(up && bird.getY() > 0){bird.setY(bird.getY() - 15) ;}
        else if (bird.getY() < 490 - bird.getHeight()){bird.setY(bird.getY() + 15) ;}

    }

    public void drawAssets(){

        drawBG(mood, 0, 0);
        UI.drawString("Score: " + score, 700, 50);
        UI.drawString("Mood: " + mood, 700, 150);
        if (pUp.getX() < (0 - pUp.getWidth())){ pUp.reDraw();} else{pUp.draw();}
        if (cloudA.getX() < (0 - cloudA.getWidth())){cloudA.reDraw();}else{cloudA.draw();}
        if (cloudB.getX() < (0 - cloudB.getWidth())){cloudB.reDraw();}else{cloudB.draw();}
        if(birdState == 0){bird.drawA(); birdState = 1;}
        else if(birdState == 1){bird.drawB(); birdState = 2;}
        else if(birdState == 2){bird.drawC(); birdState = 3;}
        else if(birdState == 3){bird.drawC(); birdState = 0;}
    }

    public void processCollisions(){
        if ( ( pUp.getX()  <= bird.getX() + bird.getWidth()) 
        && ( pUp.getY() + pUp.getHeight() > bird.getY() 
            &&  pUp.getY() < bird.getY() + bird.getHeight()))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);

            if(pUp.getPos()){
                mood++;
                speed += 2.5;
            }
            else {
                mood--;
                speed--;

            }

            pUp.reDraw();
        }

        if ( ( cloudA.getX()  <= bird.getX() + bird.getWidth() && cloudA.getX() + cloudA.getWidth() - 25 >= bird.getX()) 
        && ( cloudA.getY() + cloudA.getHeight() >= bird.getY() + bird.getHeight()  
            &&  cloudA.getY() <= bird.getY()))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);
            state = "game over";
        }

        if ( ( cloudB.getX()  <= bird.getX() + bird.getWidth() && cloudB.getX() + cloudB.getWidth()- 25 >= bird.getX()) 
        && ( cloudB.getY() + cloudB.getHeight() >= bird.getY() + bird.getHeight() 
            &&  cloudB.getY() <= bird.getY()  ))
        {
            UI.sleep(50);
            drawBG(5, 0, 0);
            state = "game over";
        }

        if ( ( pUp.getX() + 50  >= cloudA.getX() && pUp.getX()  <= cloudA.getX() + cloudA.getWidth()) 
        && ( pUp.getY()+ pUp.getHeight() > cloudA.getY() - 50
            &&  pUp.getY() + 50 < cloudA.getY() + cloudA.getHeight()))
        {
            pUp.reDraw();
        }

        if ( ( pUp.getX()+ 50  >= cloudB.getX() &&  pUp.getX()  <= cloudB.getX() + cloudB.getWidth()) 
        && ( pUp.getY()+ pUp.getHeight() > cloudB.getY() - 50 
            &&  pUp.getY() + 50 < cloudB.getY() + cloudB.getHeight()))
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


    public void reset(){
        state = "play"; // game state "play", "game over" or "menu" (not implemented yet);
        score = 0;
        speed = 10;   // Movement speed of Obstacles and pick ups
        mood = 5;
        Character bird = new Character( 50, 50);
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
