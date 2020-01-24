package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.command.DriveWithVision;

public class OI {
    public DriveWithVision driveWithVision = new DriveWithVision();

    public XboxController driveController, manipController;

    public OI() {
        initControllers();

        Robot.drivetrain.setDefaultCommand(driveWithVision);
    }

    /**
     * Initialize JoystickButtons and Controllers
     */
    private void initControllers() {
        driveController = new XboxController(0);
        manipController = new XboxController(1);

    }


}