package frc.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;

public class DriveWithVision extends CommandBase {
    private Drivetrain drivetrain = null;

    private final double DRIVE_K = 0.03;

  //  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

   /*
    double tv = table.getEntry("tv").getDouble(0);
    double tx = table.getEntry("tx").getDouble(0);
    double ty = table.getEntry("ty").getDouble(0);
    double ta = table.getEntry("ta").getDouble(0);
*/
//    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
//    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
//    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
//    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    private double tv, tx, ty, ta;

    public DriveWithVision() {
        drivetrain = Robot.drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        tv = 0;
        tx = 0;
        ty = 0;
        ta = 0;
    }

    private void fetchValues() {
        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }

    @Override
    public void execute() {
        fetchValues();
        //Only run if the target is in view
        if (tv >= 1) {
            drivetrain.set(-tx * DRIVE_K, tx * DRIVE_K);
            System.out.println(-tx * DRIVE_K + " " + tx * DRIVE_K);
            System.out.println("Found target");
        } else {
            System.out.println("No target, tx is " + Double.toString(tx));
        }
        if (tv == 0 || ty == 0 || tx == 0 || ta == 0) {
            System.out.println("networktables does exist");
        }
    }
}
