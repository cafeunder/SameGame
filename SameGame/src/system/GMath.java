package system;

public class GMath {
	public static float calcAngle(float x1, float y1, float x2, float y2){
		float dx = x1-x2;
		float dy = y1-y2;
		return (float)(Math.atan2(dy, dx));
	}
}
