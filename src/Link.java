import processing.core.PApplet;

/**
 * Created by Mathieu on 24.04.2017.
 */
public class Link extends PApplet{
    int hearts = 3; // remaining lives of link
    final int SPEED = 5; // speed of link
    int x = 100; // x-coordinate
    int y = 100; // y-coordinate
    PApplet field;


    public Link(PApplet field){
        this.field = field;
    }

    /**
     *
     */
    public void attack(){
        System.out.println();
        Arrow arr = new Arrow(field, x, y);
        arr.start();
        System.out.println("start");
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
    }

    public class Arrow extends Thread{
        int x;
        int y;
        PApplet fieldArr;

        public Arrow(PApplet field, int x, int y){
            this.fieldArr = field;
            this.x = x;
            this.y = y;
        }

        public void run(){
            System.out.println("run");
            x += 15;
            fieldArr.ellipse(x, y, 10, 10);
        }
    }
}

