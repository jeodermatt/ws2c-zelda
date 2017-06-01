/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Dragon extends PApplet{

    int hearts = 1;
    final int SPEED = 40;
    int x = 500;
    int y = 200;
    int cooldown = 0;
    int gridSize;
    PApplet field;
    ArrayList< Fire > fireballs = new ArrayList<Fire>();



    public Dragon (PApplet field, int gridSize) {
        this.field = field;
        this.gridSize = gridSize;
    }

    /**
     * shoot fireball
     */
    public void attack(){

        if( cooldown == 0 ){
            if(main.level>2) {
                Fire fireballU = new Fire(x,y, Direction.UP);
                fireballs.add(fireballU);
                Fire fireballD = new Fire(x,y, Direction.DOWN);
                fireballs.add(fireballD);
            }
            Fire fireball = new Fire(x,y, Direction.CENTER);
            fireballs.add(fireball);
            cooldown = 100;
        }
    }

    /**
     * check if dragon hits colliders
     * @param colliders
     * @param key
     * @return
     */
    public boolean checkCollider(int[][] colliders, int key) {
        boolean freeToWalk = true;
        for(int i = 0; i < colliders.length; i++) {
            if (key == UP && y > colliders[i][1]+gridSize && y < colliders[i][1]+gridSize*2 && x > colliders[i][0] && x < colliders[i][0]+gridSize) {
                freeToWalk = false;
            }
            if (key == DOWN && y < colliders[i][1] && y > colliders[i][1]-gridSize && x > colliders[i][0] && x < colliders[i][0]+gridSize) {
                freeToWalk = false;
            }
            if (key == LEFT && x > colliders[i][0]+gridSize && x < colliders[i][0]+gridSize*2 && y > colliders[i][1] && y < colliders[i][1]+gridSize) {
                freeToWalk = false;
            }
            if (key == RIGHT && x < colliders[i][0] && x > colliders[i][0]-gridSize && y > colliders[i][1] && y < colliders[i][1]+gridSize) {
                freeToWalk = false;
            }
        }

        return freeToWalk;
    }

    /**
     * check if fire hits colliders
     * @param colliders
     */
    public void checkFireCollision(int[][] colliders) {
        Iterator<Fire> fireball = fireballs.iterator();

        while (fireball.hasNext()) {
            Fire f = fireball.next();
            int y = f.y;
            int x = f.x;
            for (int i = 0; i < colliders.length; i++) {
                if (y < colliders[i][1] + gridSize && y > colliders[i][1] - gridSize && x > colliders[i][0] - gridSize && x < colliders[i][0] + gridSize) {
                    fireball.remove();
                    break;
                }

                if (x < 0) {
                    fireball.remove();
                    break;
                }
            }
        }
    }


    /**
     * paint the dragon
     * @param colliders
     */
    public void paint(int[][] colliders) {
        // Random Movement: up = 0, right = 1, down = 2, left = 3
        Random random = new Random();

        int n = random.nextInt(100);
        switch (n) {
            case 0:
                if (0 < y - SPEED && checkCollider(colliders, UP)) y = y - SPEED;
                break;
            case 1:
                if (field.width-50 > x + SPEED && checkCollider(colliders, DOWN)) x = x + SPEED;
                break;
            case 2:
                if (field.height-50 > y + SPEED && checkCollider(colliders, RIGHT)) y = y + SPEED;
                break;
            case 3:
                if (400 < x - SPEED && checkCollider(colliders, LEFT)) x = x - SPEED;
                break;
        }
        // random attack: attack = 1, no attack = 0
        int m = random.nextInt(20 );
        if(main.level==2 || main.level==4){
            m = random.nextInt(5);
        }
        switch (m) {
            case 1:
                attack();
        }
        checkFireCollision(colliders);
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
        if(main.level==1) {
            field.fill(34, 139, 34);
        }
        if(main.level==2) {
            field.fill(43, 0, 255);
        }
        if(main.level==3) {
            field.fill(255, 205, 0);
        }
        if(main.level==4) {
            field.fill(255, 0, 0);
        }

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

    public enum Direction{
        CENTER,
        UP,
        DOWN
    }

    /**
     * fireball of dragon
     */
    public class Fire{
        int x;
        int y;
        boolean alreadyHit;
        Direction dir = Direction.CENTER;

        public Fire (int x, int y, Direction dir){
            this.x=x;
            this.y=y;
            this.dir = dir;
        }
        public void paint() {

            x -= 5;
            int rs = 10;

            // fire skin
            field.fill(255, 0, 0);

            switch(dir) {
                case CENTER:
                    field.ellipse(x - rs, y, rs, rs);
                    break;
                case UP:
                    y += 1;
                    field.ellipse(x - rs, y, rs, rs);
                    break;
                case DOWN:
                    y -= 1;
                    field.ellipse(x - rs, y, rs, rs);
                    break;
            }
        }

    }

}

