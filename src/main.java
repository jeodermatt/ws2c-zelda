/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;
import processing.core.PImage;

public class main extends PApplet {
    int gridSize = 40;
    Dragon dragon = new Dragon (this, gridSize);
    Link link = new Link(this, gridSize);
    Field field = new Field(this, gridSize);
    int[][] colliders = new int[10][2];
    public static int level = 1;
    boolean gameOver = false;
    PImage img;
    PImage img2;
    boolean start = false;


    public static void main(String[] args){
        PApplet.main("main", args);
    }

    public void settings(){
        size(600,400);
    }

    public void setup() {
        colliders = field.makeCollider();
        background(255);
        frameRate(60);
        img = loadImage("Image.png");
        img2 = loadImage("win.png");

    }

    public void draw() {
        if (!start) {
            image(img, 0, 0);
            textSize(26);
            textAlign(CENTER);
            text("Press Spacebar to start the game", 300, 350);
            switch (key) {
                case ' ': start=true;


            }
        } else {
            clear();
            if (!gameOver) {
                if (level == 4) {
                    image(img2,0,0);
                    textSize(26);
                    textAlign(CENTER);
                    text("You won! \nPress Spacebar to Restart", 300, 350);
                    switch (key) {
                        case ' ':
                            level = 1;
                            reset();
                    }
                } else {
                    stroke(0);
                    field.paint();
                    link.paint(colliders);
                    dragon.paint(colliders);
                    textSize(26);
                    textAlign(CENTER);
                    fill(255);
                    text("Level " + level, 300, 50);
                    checkIfHit();
                }
            } else {
                image(img, 0, 0);
                textSize(26);
                textAlign(CENTER);
                text("Game Over \nPress Spacebar to Restart", 300, 300);

                switch (key) {
                    case ' ':
                        level = 1;
                        reset();
                }
            }
        }
    }

    public void reset(){
        link.hearts = 3;
        dragon.hearts = (int) Math.pow(2, level);
        link.x = 100;
        link.y = 100;
        link.arrows.clear();
        link.cooldown = 0;
        dragon.x = 500;
        dragon.y = 200;
        dragon.cooldown = 0;
        dragon.fireballs.clear();
        if(level == 1){
            field.numberOfColliders = (int) Math.pow(3, 3);
        }else if(level ==2){
            field.numberOfColliders = (int) Math.pow(3, 2);
        }else if(level ==3){
            field.numberOfColliders = (int) Math.pow(3, 1);
        }

        colliders = field.makeCollider();
        gameOver = false;
    }

    public void checkIfHit(){
        // check if dragon hit by arrow
        for(Link.Arrow arr : link.arrows){
            if(arr.x > (dragon.x - 25) && arr.x < (dragon.x + 25)
                && arr.y > (dragon.y-10) && (arr.y < dragon.y + 40) && !arr.alreadyHit){
                // decrease dragon hearts
                dragon.hearts--;
                // arrow hit dragon
                arr.alreadyHit = true;
            }
        }

        // check if link hit by fire
        for(Dragon.Fire fire : dragon.fireballs){
            if(fire.x > (link.x - 25) && fire.x < (link.x + 25)
                    && fire.y > (link.y - 20) && (fire.y < link.y + 25) && !fire.alreadyHit){
                // decrease dragon hearts
                link.hearts--;
                // arrow hit dragon
                fire.alreadyHit = true;
            }
        }

        if(link.hearts <= 0) gameOver = true;
        if(dragon.hearts == 0){
            level++;
            reset();
        }


    }
}
