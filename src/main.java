/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;

public class main extends PApplet {
    Link link = new Link(this);
    Field field = new Field(this);

    public static void main(String[] args){
        PApplet.main("main", args);
    }

    public void settings(){
        size(600,400);
    }

    public void setup(){
        background(255);
    }

    public void draw(){
        clear();

        field.paint();
        stroke(0);
        link.paint();
    }
}
