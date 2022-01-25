package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.Drive;
import frc.commands.ML;
//import frc.commands.MLTurnToBall;


public class OI {
    public XboxController driveController, manipController;
    Drive drive = new Drive();


    JoystickButton aBtn, bBtn, yBtn, xBtn;



    public OI() {
        initControllers();

        aBtn = new JoystickButton(driveController, XboxController.Button.kA.value);
        aBtn.whenPressed(new InstantCommand(() -> Robot.ml.turn()));

        //Robot.drivetrain.setDefaultCommand(drive);
    }
    /**
     * Initialize JoystickButtons and Controllers
     */
    private void initControllers() {
        driveController = new XboxController(0);
        //manipController = new XboxController(1);
    }

    public double driveLeftY() {
        return -driveController.getLeftY();
    }

    public double driveRightX() {
        return driveController.getRightX();
    }

    public double manipRightTrigger() {
        return manipController.getRightTriggerAxis();
    }

    public double mainpLeftTrigger() {
        return manipController.getLeftTriggerAxis();
    }


    public boolean driveLeftBumper() {
        return driveController.getLeftBumper();
    }

    public boolean manipAButton() {
        return manipController.getAButtonPressed();
    }

    public boolean manipStartButton() {
        return manipController.getStartButtonPressed();
    }

    public boolean manipBackButton() {
        return manipController.getBackButtonPressed();
    }


    public double manipLeftTrigger() {
        return manipController.getLeftTriggerAxis();
    }
}