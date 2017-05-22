/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;

public class main extends PApplet {
    int gridSize = 40;
    Dragon dragon = new Dragon (this, gridSize);
    Link link = new Link(this, gridSize);
    Field field = new Field(this, gridSize);
    int[][] colliders = new int[10][2];

    public static void main(String[] args){
        PApplet.main("main", args);
    }

    public void settings(){
        size(600,400);
    }

    public void setup(){
        colliders = field.makeCollider();
        background(255);
    }

    public void draw(){
        clear();

        stroke(0);
        field.paint();
        link.paint(colliders);
        dragon.paint(colliders);
    }
}
