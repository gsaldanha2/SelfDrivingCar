package math;

import java.awt.geom.Line2D;

/**
 * Created by Gregory on 11/16/16.
 */
public class QuickMath {

    public static final float PI = 3.141592f;

    public static float MPHToFeetPerMillis(float mph) {
        return (mph * 5280) / 3600000;
    }

    public static float angleBetweenPoints(float x1, float y1, float x2, float y2) {
        return (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
    }

    public static float distanceFrom(float x, float y, float x1, float y1) {
        return (float) Math.hypot(x1 - x, y1 - y);
    }

    public static float cos(float degrees) {
        return (float) Math.cos(Math.toRadians(degrees));
    }

    public static float sin(float degrees) {
        return (float) Math.sin(Math.toRadians(degrees));
    }

    public static Vec2 computeIntersectionOfLines(Line2D.Float l1, Line2D.Float l2) {
        float d = (l2.y2 - l2.y1) * (l1.x2 - l1.x1) - (l2.x2 - l2.x1) * (l1.y2 - l1.y1);
        if (d == 0) {
            return null;
        }
        float ua = ((l2.x2 - l2.x1) * (l1.y1 - l2.y1) - (l2.y2 - l2.y1) * (l1.x1 - l2.x1)) / d;
        return new Vec2(l1.x1 + (l1.x2 - l1.x1) * ua, l1.y1 + (l1.y2 - l1.y1) * ua);
    }

    public static float tan(float degrees) {
        return (float) Math.tan(Math.toRadians(degrees));
    }
}
