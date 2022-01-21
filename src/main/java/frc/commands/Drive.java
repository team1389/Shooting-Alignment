package frc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;

public class Drive extends CommandBase {
    private Drivetrain drivetrain = null;

    public Drive() {
        drivetrain = Robot.drivetrain;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double throttle = Robot.oi.driveLeftY();
        double rotation = -(Robot.oi.driveRightX());
        boolean isQuickTurn = Robot.oi.driveLeftBumper();

        Robot.drivetrain.drive(throttle, rotation, isQuickTurn);

    }
}
