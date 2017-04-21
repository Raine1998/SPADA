import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.awt.event.*;

public class Character   {

    double characterX = -100;  
    double characterY = -100;
    
    int mood = 5;

    double characterHeight = 48;
    double characterWidth = 48;

    public Character(int mood, double x, double y){
        this.characterX = x;
        this.characterY = y;
        this.mood = mood;
    }

    public void setX(double x){
        this.characterX = x;
    }

    public double getX()
    {
        return this.characterX;
    }

    public void setY(double y){
        this.characterY = y;
    }

    public double getY()
    {   
        return this.characterY;
    }

    public void setwidth(double w){
        this.characterWidth = w;
    }

    public double getWidth()
    {
        return this.characterWidth;}

    public double getHeight()
    {
        return this.characterHeight;

    }

    public void setHeight(double h){
        this.characterHeight = h;
    }

    public int getMood(){
        return this.mood;
    }

    public void setMood(int Mood){
        this.mood = mood;
    }

    public void erase() {
        UI.eraseRect(this.characterX, this.characterY, this.characterWidth+1, this.characterHeight+1);
    }

    public void draw(){
        String fname = null;

        fname = "Character" + "/" + mood + "a"+".png"; 
        UI.drawImage(fname, this.characterX, this.characterY, this.characterWidth, this.characterHeight);
        UI.sleep(25);
        this.erase();

        fname = "Character" + "/" + mood + "b"+".png"; 
        UI.drawImage(fname, this.characterX, this.characterY, this.characterWidth, this.characterHeight);
        UI.sleep(25);
        this.erase();

        fname = "Character" + "/" + mood + "c"+".png"; 
        UI.drawImage(fname, this.characterX, this.characterY, this.characterWidth, this.characterHeight);
        UI.sleep(25);
        this.erase();
    }
}
