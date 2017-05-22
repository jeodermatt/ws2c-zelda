/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;

public class Dragon extends PApplet{

    int hearts = 7;
    final int SPEED = 40;
    int x = 500;
    int y = 200;
    int cooldown = 0;
    PApplet field;
    ArrayList< Fire > fireballs = new ArrayList<Fire>();


    public Dragon (PApplet field) {
        this.field = field;
    }

    public void attack(){
        if( cooldown == 0 ){
            Fire fireball = new Fire(x,y);
            fireballs.add(fireball);
            cooldown = 100;
        }
    }


    public void paint() {
        // Random Movement: up = 0, right = 1, down = 2, left = 3
        Random random = new Random();
        //Random zahlrange habe ich auf 50 gestellt um quasi ein Timeout zu kreieren  => muss gefixet werden.
        int n = random.nextInt(100);
        switch (n) {
            case 0:
                if (0 < y - SPEED) y = y - SPEED;
                break;
            case 1:
                if (field.width-50 > x + SPEED) x = x + SPEED;
                break;
            case 2:
                if (field.height-50 > y + SPEED) y = y + SPEED;
                break;

            case 3:
                if (400 < x - SPEED) x = x - SPEED;
                break;
        }
        // random attack: attack = 1, no attack = 0
        int m = random.nextInt(1);
        switch (n) {
            case 1:
                attack();
        }

        for (Fire fireball : fireballs) {
            fireball.paint();
        }
        if (cooldown > 0) cooldown--;
        hearts();
        body();
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
            field.rect(field.width-(i*hx), hy, rs, rs);
            field.rect(field.width-i*hx, hy + rs, rs, rs);
            field.rect(field.width-i*hx, hy - rs, rs, rs);
            field.rect(field.width-i*hx + rs, hy - rs, rs, rs);
            field.rect(field.width-i*hx - rs, hy - rs, rs, rs);
            field.rect(field.width-i*hx + rs, hy - 2*rs, rs, rs);
            field.rect(field.width-i*hx - rs, hy - 2*rs, rs, rs);
            field.rect(field.width-i*hx + 2*rs, hy - rs, rs, rs);
            field.rect(field.width-i*hx - 2*rs, hy - rs, rs, rs);
            field.rect(field.width-i*hx + rs, hy, rs, rs);
            field.rect(field.width-i*hx - rs, hy, rs, rs);
        }
    }


    /**
     * draw body
     */
    public void body(){
        // size of pixels
        int rs = 5;

        // color of clothing
        field.fill(34,139,34);

        // upper body
        field.rect(x,y,rs,rs);
        field.rect(x+rs,y,rs,rs);
        //2.zeile
        field.rect(x-rs*2,y+rs,rs,rs);
        field.rect(x-rs,y+rs,rs,rs);
        field.rect(x,y+rs,rs,rs);
        field.rect(x+rs,y+rs,rs,rs);
        field.rect(x,y+rs*2,rs,rs);
        //3. Zeile
        field.rect(x,y+rs*3,rs,rs);
        field.rect(x+rs,y+rs*3,rs,rs);
        field.rect(x+rs*7,y+rs*3,rs,rs);

        //4. Zeile
        field.rect(x,y+rs*4,rs,rs);
        field.rect(x+rs,y+rs*4,rs,rs);
        field.rect(x+rs*2,y+rs*4,rs,rs);
        field.rect(x+rs*3,y+rs*4,rs,rs);
        field.rect(x+rs*4,y+rs*4,rs,rs);
        field.rect(x+rs*5,y+rs*4,rs,rs);
        field.rect(x+rs*6,y+rs*4,rs,rs);
        field.rect(x+rs*7,y+rs*4,rs,rs);

        //5. Zeile
        field.rect(x,y+rs*5,rs,rs);
        field.rect(x+rs,y+rs*5,rs,rs);
        field.rect(x+rs*2,y+rs*5,rs,rs);
        field.rect(x+rs*3,y+rs*5,rs,rs);
        field.rect(x+rs*4,y+rs*5,rs,rs);
        field.rect(x+rs*5,y+rs*5,rs,rs);

        //6. Zeile
        field.rect(x+rs*3,y+rs*6,rs,rs);
        field.rect(x+rs*4,y+rs*6,rs,rs);

        //7. Zeile
        field.rect(x+rs,y+rs*7,rs,rs);
        field.rect(x+rs*2,y+rs*7,rs,rs);
        field.rect(x+rs*3,y+rs*7,rs,rs);

        //flügel

        field.fill(238,130,238);

        //2. Zeile
        field.rect(x+rs*3,y+rs,rs,rs);
        field.rect(x+rs*4,y+rs,rs,rs);

        //3. Zeile
        field.rect(x+rs,y+rs*2,rs,rs);
        field.rect(x+rs*2,y+rs*2,rs,rs);

    }


    public class Fire extends Thread{
        int x;
        int y;
        boolean alreadyHit;

        public Fire (int x, int y){
            this.x=x;
            this.y=y;
        }
        public void paint(){

            x-=5;
            int rs=10;

            // fire skin
            field.fill(255,0,0);

            field.ellipse(x-rs,y,rs,rs);
            field.ellipse(x-rs,y,rs,rs);
            field.ellipse(x-rs,y,rs,rs);



        }
    }

}

