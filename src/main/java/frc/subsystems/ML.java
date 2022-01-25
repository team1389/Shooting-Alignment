package frc.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.utils.BoundingBox;

import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class ML extends SubsystemBase {
    NetworkTable ml,camera;
    NetworkTableEntry boxes, objectClasses, res;
//    String string;
    double middleX, middleY;


    public ML() {
        ml = NetworkTableInstance.getDefault().getTable("ML");
        boxes = ml.getEntry("detections");
//        objectClasses = ml.getEntry("object_classes");
//        camera =  NetworkTableInstance.getDefault().getTable("ML"+ NetworkTable.PATH_SEPARATOR+"MLOut");
//        res = camera.getEntry("mode");
//        string = res.getString("320x240 MJPEG 30 fps");
        middleX = 160/2;
        middleY = 120/2;
    }
    public double movement() {

        double[] inputCords = getDoubleArray();
        BoundingBox[] boxes = new BoundingBox[inputCords.length/4];
        for (int i = 1; i <= inputCords.length/4; i++) {
            boxes[i-1] = new BoundingBox(inputCords[(i*4)-4], inputCords[(i*4)-3], inputCords[(i*4)-2], inputCords[(i*4)-1]);
        }
        BoundingBox largest = new BoundingBox(middleX, middleY, middleX, middleY);
        for (BoundingBox x : boxes) {
            if (x.area > largest.area) {
                largest = x;
            }
        }
        return (((largest.x1 +largest.x2)/2) - middleX) / middleX;
    }

    public double[] getDoubleArray() {
        String boxesString = boxes.getString("[]");

        String[] numsString = boxesString.substring(1,boxesString.length()-2).split(", ");
        double[] nums = new double[numsString.length];
        for (int x = 0; x < nums.length; x++) {
            nums[x] = Double.parseDouble(numsString[x]);
        }
        return nums;
//        Scanner scanner = new Scanner(boxesString);
//        scanner.next("\\[");
//        double[] array = new double[(int) boxesString.chars().filter(ch -> ch == ',').count() + 1];
//        for (int x = 0; x < array.length; x++) {
//            array[x] = scanner.nextDouble();
//            scanner.next(", ");
//        }
//        return array;

    }

    double kP = 0.5;
    double kI = 0.05;
    double kD = 0;
    PIDController pid;

    public void turn() {

        pid = new PIDController(kP, kI, kD);

        double error = Robot.ml.movement();
        double power = pid.calculate(error, 0);

        SmartDashboard.putNumber("power", power);
        SmartDashboard.putNumber("error", error);

        Robot.drivetrain.set(power, -power);
    }
}




