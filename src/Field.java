/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;

public class Field extends PApplet{
    main field;
    int gridSize;
    int numberOfColliders = 2;
    int[][] colliders = new int[numberOfColliders][2];

    public Field(main field, int gridSize){
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

        int offset = 5;
        int max = gridSize/offset;
        field.noStroke();
        for (int i = 0; i < colliders.length; i++) {

            for(int h = 0; h < max; h++) { //height
                for (int w = 0; w < max; w++) { //width
                    if (h == 0 || w == 0) {
                        field.fill(200);
                    } else if(h == max-1 || w == max-1) {
                        field.fill(0);
                    }
                    else {
                        field.fill(100);
                    }

                    field.rect(colliders[i][0] + (offset * w), colliders[i][1] + (offset * h), offset, offset);
                }
            }
        }
    }

    public int[][] makeCollider() {
        colliders = new int[numberOfColliders][2];
        for (int i = 0; i < colliders.length; i++) {
            colliders[i][0] = ((int)random(0, field.width/gridSize))*gridSize;
            colliders[i][1] = ((int)random(0, field.width/gridSize))*gridSize;
        }
        return this.colliders;
    }
}
