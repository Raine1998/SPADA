import ecs100.*;

public class Obstacle{
    /* Fields representing the state of a CartoonCharacter */
    double obsX = -100;   // top left corner of character
    double obsY = -100;

    int mood = 5;

    /* Fields containing dimensions of CartoonCharacters */

    double characterHeight = 100 + randInt(50);
    double characterWidth = 100 + randInt(100);

    public Obstacle( int mood){
        this.obsX = 800 + randInt(500);
        this.obsY = randInt(250)+ 50;
        //cloud.setMood(randInt(10));
        this.draw();
    }

    public void setX(double x){
        this.obsX = x;
    }

    public double getX()
    {
        return this.obsX;
    }

    public void setY(double y){
        this.obsY = y;
    }

    public double getY()
    {	
        return this.obsY;
    }

    public void setwidth(double w){
        this.characterWidth = w;
    }

    public double getWidth()
    {
        return this.characterWidth;
    }

    public void setHeight(double h){
        this.characterHeight = h;
    }

    public double getHeight()
    {
        return this.characterHeight;

    }

    public void setMood(int Mood){
        this.mood = mood;
    }

    public int getMood(){
        return this.mood;
    }

    public void erase() {
        UI.eraseRect(this.obsX, this.obsY, this.characterWidth, this.characterHeight);
    }

    public void draw(){
        String fname = null;
        fname = "obstacle" + "/" + mood +".png"; 
        UI.drawImage(fname, this.obsX, this.obsY, this.characterWidth, this.characterHeight);
    }

    public void reDraw(){
        this.obsX = 800 + randInt(500);
        this.obsY = randInt(250)+ 50;
        //cloud.setMood(randInt(10));
        this.characterHeight = 100 + randInt(50);
        this.characterWidth = 150 + randInt(125);
        this.draw();}

    public int randInt(int max) {
        return ( (int)(Math.random()* max ) );
    }
}
