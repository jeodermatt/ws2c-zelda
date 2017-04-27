/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;

public class Field extends PApplet{
    PApplet field;
    int gridSize;
    int[][] colliders = new int[10][2];

    public Field(PApplet field, int gridSize){
        this.field = field;
        this.gridSize = gridSize;
    }

    /**
     *
     * draw map
     *
     */
    public void paint(){
        field.fill(0);
        field.stroke(60);
        for (int i = 0; i < field.width/gridSize; i++) {
            for (int k = 0; k < field.height/gridSize; k++) {
                field.rect(i*gridSize, k*gridSize, gridSize, gridSize);
            }
        }
    }

    public int[][] makeCollider() {
        for (int i = 0; i < colliders.length; i++) {
            colliders[i][0] = ((int)random(0, field.width/gridSize))*gridSize;
            colliders[i][1] = ((int)random(0, field.width/gridSize))*gridSize;;
            field.fill(255);
            field.rect(colliders[i][0],colliders[i][1], gridSize, gridSize);
        }
        return this.colliders;
    }
}
