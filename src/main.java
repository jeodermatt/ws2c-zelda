/**
 * Created by Mathieu on 24.04.2017.
 */
import processing.core.PApplet;

public class main extends PApplet {
    Link link = new Link(this);
    Dragon dragon = new Dragon (this);
    int gridSize = 40;
    Field field = new Field(this, gridSize);
    int[][] colliders = new int[10][2];
    int level = 1;
    boolean gameOver = false;

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
        if(!gameOver) {
            if(level == 1) {
                stroke(0);
                field.paint();
                link.paint();
                dragon.paint();
                textSize(26);
                textAlign(CENTER);
                fill(255);
                text("Level " + level, 300, 50);
                checkIfHit();
            }else{ // todo: next level!
                fill(255);
                textSize(26);
                textAlign(CENTER);
                text("You won! \nPress Spacebar to Restart", 300, 200);
                switch(key){
                    case ' ': reset();
                }
            }
        }else{
            fill(255);
            textSize(26);
            textAlign(CENTER);
            text("Game Over \nPress Spacebar to Restart", 300, 200);
            switch(key){
                case ' ': reset();
            }
        }
    }

    public void reset(){
        link.hearts = 3;
        dragon.hearts = 7;
        link.x = 100;
        link.y = 100;
        link.arrows.clear();
        link.cooldown = 0;
        dragon.x = 500;
        dragon.y = 200;
        dragon.cooldown = 0;
        dragon.fireballs.clear();
        colliders = field.makeCollider();
        gameOver = false;
        level--;
    }

    public void checkIfHit(){
        // check if dragon hit by arrow
        for(Link.Arrow arr : link.arrows){
            if(arr.x > (dragon.x - 25) && arr.x < (dragon.x + 25)
                && arr.y > (dragon.y) && (arr.y < dragon.y + 25) && !arr.alreadyHit){
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

        if(link.hearts == 0) gameOver = true;
        if(dragon.hearts == 0) level++;
    }
}
