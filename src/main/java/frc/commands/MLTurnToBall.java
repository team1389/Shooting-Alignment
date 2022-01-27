package frc.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.subsystems.Drivetrain;

public class MLTurnToBall extends CommandBase {
    double kP = 0.5;

    double kI = 0.05;
    double kD = 0;
    PIDController pid;


    public MLTurnToBall() {
        addRequirements(Robot.ml);
        SmartDashboard.putNumber("kP", kP);
        SmartDashboard.putNumber("kI", kI);
        SmartDashboard.putNumber("kD", kD);

        pid = new PIDController(kP, kI, kD);


    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        kP = SmartDashboard.getNumber("kP", kP);

        pid.setP(kP);

        kI = SmartDashboard.getNumber("kI", kI);

        pid.setI(kI);

        kD = SmartDashboard.getNumber("kD", kD);

        pid.setD(kD);

        double error = Robot.ml.movement();
        double power = pid.calculate(error, 0);

        Robot.drivetrain.set(power, -power);
        SmartDashboard.putNumber("error", error);
        SmartDashboard.putNumber("power", power);
    }
    @Override
    public void end(boolean interrupted) {
        
    }
}
