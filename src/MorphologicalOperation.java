
import java.awt.image.BufferedImage;
import javafx.geometry.HorizontalDirection;

public interface MorphologicalOperation {
    public enum STRUCTURING_ELEMENT_SHAPE{
        SQUARE, HORIZONTAL_LINE, CIRCLE
    }
           public BufferedImage execute(BufferedImage img);
           
           public int getShapeSize();
    
}
