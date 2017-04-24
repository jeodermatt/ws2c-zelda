/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;

public class main extends PApplet {
    public static void main(String[] args){
        PApplet.main("main", args);
    }

    public void settings(){
        size(800,600);
    }

    public void setup(){
        background(255);
    }

    public void draw(){
        stroke(0);
        fill(255,0,255);
        ellipse(width/2, height/2, 100, 100);
    }

}
