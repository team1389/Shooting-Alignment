package frc.command;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;

public class TestGyroDrive extends CommandBase {
    private Drivetrain drivetrain = null;

   public TestGyroDrive() {
       drivetrain = Robot.drivetrain;
       addRequirements(drivetrain);
   }
    @Override
    public void execute() {
        double error = -drivetrain.ahrs.getRate();
        drivetrain.set(0.5 + drivetrain.kP * error, 0.5 - drivetrain.kP * error);

    }
}
