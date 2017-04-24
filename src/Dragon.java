/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;

public class Dragon extends PApplet{
    public static void main(String[] args){
        PApplet.main("Dragon", args);
    }

    public void settings(){
        size(600,400);
    }

    public void setup(){

        background(255);
    }

    public void draw(){
        stroke(0);
        fill(255,0,0);
        ellipse(width/2, height/2, 100, 100);
    }

}
