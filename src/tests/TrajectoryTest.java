//package tests;
//
//import rndf.Waypoint;
//import trajectory.Trajectory;
//import trajectory.TrajectoryShifter;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
///**
// * Created by Gregory on 1/4/17.
// */
//public class TrajectoryTest {
//
//    public static void main(String[] args) throws IOException {
//        Trajectory trajectory = new Trajectory();
//        trajectory.addWaypoint(new Waypoint(10, 10));
//        trajectory.addWaypoint(new Waypoint(40, 10));
//        trajectory.addWaypoint(new Waypoint(70, 30));
//        trajectory.addWaypoint(new Waypoint(90, 50));
//        long st = System.currentTimeMillis();
//        Trajectory shiftedTrajectory = TrajectoryShifter.generatedShiftedTrajectory(trajectory, 1);
//        System.out.println(System.currentTimeMillis() - st);
//        System.out.println(trajectory.toString());
//        System.out.println("-----------------------");
//        System.out.println(shiftedTrajectory.toString());
//
//        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D ig2 = bi.createGraphics();
//        ig2.setColor(Color.BLACK);
//        ig2.fillRect(0, 0, 100, 100);
//        ig2.setColor(Color.BLUE);
//        for(int i = 0; i < trajectory.getWaypoints().size(); i++) {
//            ig2.fillRect((int)trajectory.getWaypoints().get(i).getX(),(int)trajectory.getWaypoints().get(i).getY(), 1, 1);
//            if(trajectory.getWaypoints().size() -1 > i) {
//                ig2.drawLine((int)trajectory.getWaypoints().get(i).getX(),(int)trajectory.getWaypoints().get(i).getY(), (int)trajectory.getWaypoints().get(i+1).getX(),(int)trajectory.getWaypoints().get(i+1).getY());
//            }
//        }
//        ig2.setColor(Color.RED);
//        for(int i = 0; i < shiftedTrajectory.getWaypoints().size(); i++) {
//            ig2.fillRect((int)shiftedTrajectory.getWaypoints().get(i).getX(),(int)shiftedTrajectory.getWaypoints().get(i).getY(), 1, 1);
//            if(shiftedTrajectory.getWaypoints().size() -1 > i) {
//                ig2.drawLine((int)shiftedTrajectory.getWaypoints().get(i).getX(),(int)shiftedTrajectory.getWaypoints().get(i).getY(), (int)shiftedTrajectory.getWaypoints().get(i+1).getX(),(int)shiftedTrajectory.getWaypoints().get(i+1).getY());
//            }
//        }
//        ImageIO.write(bi, "PNG", new File("data.png"));
//    }
//
//}
