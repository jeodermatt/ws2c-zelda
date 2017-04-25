/**
 * Created by Vithu on 24.04.17.
 */
import processing.core.PApplet;

public class Field extends PApplet{
    PApplet field;
    int gridSize = 40;

    public Field(PApplet field){
        this.field = field;
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

}
