package gui.Button;
import java.awt.BasicStroke;
public class MyDashStroke extends BasicStroke{
	private static float pattern1[]= {10,15};
	private static float pattern2[]= {10,2};
	
	public MyDashStroke(int lineWidth,int patternType) {
		super(lineWidth,CAP_BUTT,JOIN_BEVEL,1.0f,getPattern(patternType),0);
	}
	private static float[] getPattern(int patternType) {
        switch (patternType) {
            case 1:
                return pattern1;
            case 2:
                return pattern2;
            default:
                return pattern1;
        }
    }
}