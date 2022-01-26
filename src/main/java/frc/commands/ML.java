package frc.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;

public class ML extends CommandBase {
    double kP = 0.5;
    double kI = 0.05;
    double kD = 0;
    PIDController pid;


    public ML() {
        addRequirements(Robot.ml);
        pid = new PIDController(kP, kI, kD);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double error = 0;//Robot.ml.movement();
        double power = pid.calculate(error, 0);

        Robot.drivetrain.set(power, -power);
        SmartDashboard.putNumber("error", error);
        SmartDashboard.putNumber("power", power);
    }
}
