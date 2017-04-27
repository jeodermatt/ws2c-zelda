/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;

public class main extends PApplet {
    Link link = new Link(this);
    int gridSize = 40;
    Field field = new Field(this, gridSize);
    int[][] colliders = new int[10][2];

    public static void main(String[] args){
        PApplet.main("main", args);
    }

    public void settings(){
        size(600,400);
    }

    public void setup(){

        background(255);
        field.paint();
        colliders = field.makeCollider();
    }

    public void draw(){
        //clear();


        stroke(0);
        link.paint();
    }
}
