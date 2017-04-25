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
     * paint Link
     *
     */
    public void paint(){
        switch(field.keyCode) {
            case UP:
                if(0 < y - SPEED) y = y - SPEED;
                break;
            case DOWN:
                if(field.height > y + SPEED) y = y + SPEED;
                break;
            case RIGHT:
                if(field.width > x + SPEED) x = x + SPEED;
                break;
            case LEFT:
                if(0 < x - SPEED) x = x - SPEED;
                break;
        }

        switch(field.key){
            case ' ': attack();
        }
        field.keyCode = 0;
        field.key = 0;
        for (Arrow arr : arrows){
            arr.paint();
        }
        if(cooldown > 0) cooldown--;
        cooldown();
        hearts();
        body();
    }

    /**
     * draw cooldown
     */
    public void cooldown(){
        int start = 15;
        int cx = 5;
        int cy = 10;
        int rs = 5;

        for(int i = 1; i < cooldown/rs; i++){
            field.rect(start+cx*i, cy, rs, rs);
        }
    }

    /**
     * draw hearts
     */
    public void hearts(){
        field.fill(255,0,0);
        int hx = 30;
        int hy = 40;
        int rs = 5;
        for(int i = 1; i <= hearts; i++){
            field.rect(i*hx, hy, rs, rs);
            field.rect(i*hx, hy + rs, rs, rs);
            field.rect(i*hx, hy - rs, rs, rs);
            field.rect(i*hx + rs, hy - rs, rs, rs);
            field.rect(i*hx - rs, hy - rs, rs, rs);
            field.rect(i*hx + rs, hy - 2*rs, rs, rs);
            field.rect(i*hx - rs, hy - 2*rs, rs, rs);
            field.rect(i*hx + 2*rs, hy - rs, rs, rs);
            field.rect(i*hx - 2*rs, hy - rs, rs, rs);
            field.rect(i*hx + rs, hy, rs, rs);
            field.rect(i*hx - rs, hy, rs, rs);
        }
    }

    /**
     * draw body
     */
    public void body(){
        // size of pixels
        int rs = 5;

        // color of clothing
        field.fill(0,200,0);

        // upper body
        field.rect(x, y, rs, rs);
        field.rect(x-rs, y, rs, rs);
        field.rect(x+rs, y+rs, rs, rs);
        field.rect(x, y+rs, rs, rs);
        field.rect(x-rs, y+rs, rs, rs);
        field.rect(x-rs*2, y+rs, rs, rs);
        field.rect(x, y+rs*2, rs, rs);
        field.rect(x-rs, y+rs*2, rs, rs);
        field.rect(x, y+rs*3, rs, rs);
        field.rect(x-rs, y+rs*3, rs, rs);


        //hat
        field.rect(x-rs*2, y-rs*4, rs, rs);
        field.rect(x-rs, y-rs*4, rs, rs);
        field.rect(x-rs*2, y-rs*3, rs, rs);
        field.rect(x-rs*2, y-rs*2, rs, rs);

        // color of hair
        field.fill(200,100,0);
        field.rect(x-rs, y-rs*3, rs, rs);
        field.rect(x, y-rs*3, rs, rs);

        // color of skin
        field.fill(200,150,0);
        field.rect(x-rs, y-rs, rs, rs);
        field.rect(x, y-rs, rs, rs);
        field.rect(x+rs, y-rs*2, rs, rs);
        field.rect(x-rs, y-rs*2, rs, rs);
        field.rect(x, y-rs*2, rs, rs);
        // hands
        field.rect(x+rs*2, y+rs, rs, rs);
        field.rect(x+rs*2, y, rs, rs);
        field.rect(x-rs*3, y+rs, rs, rs);
        field.rect(x-rs*3, y+rs*2, rs, rs);

        //shoes
        field.fill(200,50,0);
        field.rect(x, y+rs*4, rs, rs);
        field.rect(x-rs, y+rs*4, rs, rs);
        field.rect(x+rs, y+rs*5, rs, rs);
        field.rect(x-rs, y+rs*5, rs, rs);
        field.rect(x+rs, y+rs*6, rs, rs);
        field.rect(x-rs, y+rs*6, rs, rs);
    }

    public class Arrow extends Thread{
        int x;
        int y;

        public Arrow(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void paint() {
            x += 5;
            int rs = 5;

            // arrow skin
            field.fill(200,100,0);
            for(int i = 1; i <= 5; i++) {
                field.rect(x+rs*i, y, rs, rs);
            }
            field.fill(155,100,100);
            field.rect(x+rs*2, y, rs, rs);
            field.rect(x+rs*2, y-rs, rs, rs);
            field.rect(x+rs*2, y+rs, rs, rs);
        }
    }
}

