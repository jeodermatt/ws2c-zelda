import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by Mathieu on 24.04.2017.
 */
public class Link extends PApplet{
    int hearts = 3; // remaining lives of link
    final int SPEED = 15; // speed of link
    int x = 100; // x-coordinate
    int y = 100; // y-coordinate
    int cooldown = 0;
    PApplet field;
    ArrayList<Arrow> arrows = new ArrayList<>();


    public Link(PApplet field){
        this.field = field;
    }

    /**
     *
     */
    public void attack(){
        if(cooldown == 0) {
            Arrow arr = new Arrow(x, y);
            arrows.add(arr);
            cooldown = 100;
        }
    }

    /**
     *
     *
     */
    public void paint(){
        switch(field.keyCode){
            case UP:    y = y - SPEED;
                        break;
            case DOWN:  y = y + SPEED;
                        break;
            case RIGHT: x = x + SPEED;
                        break;
            case LEFT:  x = x - SPEED;
                        break;
        }

        switch(field.key){
            case ' ': attack();
        }
        field.keyCode = 0;
        field.key = 0;
        field.ellipse(x, y, 10, 10);
        for (Arrow arr : arrows){
            arr.paint();
        }
        if(cooldown > 0) cooldown--;
        field.text("Cooldown: "+cooldown, 10,20);
        field.text("Hearts: "+hearts, 10,40);
    }

    public class Arrow extends Thread{
        int x;
        int y;

        public Arrow(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void paint() {
            x += 15;
            field.ellipse(x, y, 10, 10);
        }
    }
}

