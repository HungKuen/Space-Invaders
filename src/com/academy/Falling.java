/*package com.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Falling extends Shape{
    public Point start;
    public Point end;

    public Falling(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public List<Point> draw() {
        List<Point> points = new ArrayList<>();
        int xStart = start.getX();
        int yStart = 0;

        for (int i = 0; i <= 80*24; i++) {
            if (yStart<22) {
                points.add(new Point(xStart, yStart++));
            }else{
                Random random = new Random();
                xStart = random.nextInt(80);
            }
        }
        return points;
    }
}
*/