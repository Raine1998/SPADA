import ecs100.*;

public class Pickup {
    /* Fields representing the state of a CartoonCharacter */
    double pickUpX = -100;   // top left corner of character
    double pickUpY = -100;

    int mood = 5;

    /* Fields containing dimensions of CartoonCharacters */

    double characterHeight = 32;
    double characterWidth = 32;

    public Pickup( int mood){
        this.pickUpX = 800 + randInt(500);
        this.pickUpY = randInt(400) + 25;
        this.mood = randInt(10);
        this.draw();
    }

    public void setX(double x){
        this.pickUpX = x;
    }

    public double getX()
    {
        return this.pickUpX;
    }

    public void setwidth(double w){
        this.characterWidth = w;
    }

    public double getWidth()
    {
        return this.characterWidth;
    }

    public double getHeight()
    {
        return this.characterHeight;

    }

    public void setHeight(double h){
        this.characterHeight = h;
    }

    public void setY(double y){
        this.pickUpY = y;
    }

    public double getY()
    {	
        return this.pickUpY;
    }

    public void setMood(int Mood){
        this.mood = mood;
    }

    public int getMood(){
        return this.mood;
    }

    public void erase() {
        UI.eraseRect(this.pickUpX, this.pickUpY, this.characterWidth, this.characterHeight);
    }

    public void draw(){
        String fname = null;
        fname = "Pickup" + "/" + mood +".png"; 
        UI.drawImage(fname, this.pickUpX, this.pickUpY, this.characterWidth, this.characterHeight);
    }

    public void reDraw(){
        this.pickUpX = 800 + randInt(500);
        this.pickUpY = randInt(400) + 25;
        this.mood = randInt(10);
 
        this.draw();}

    public int randInt(int max) {
        return ( (int)(Math.random() * max ) );
    }
}
