package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
    TalonSRX leftFront, rightFront,leftBack,rightBack;

    public Drivetrain() {
        leftFront = new TalonSRX(RobotMap.LEFT_FRONT);
        rightFront = new TalonSRX(RobotMap.RIGHT_FRONT);
        rightFront.setInverted(true);
        leftBack = new TalonSRX(RobotMap.LEFT_BACK);
        rightBack = new TalonSRX(RobotMap.RIGHT_BACK);
        rightBack.setInverted(true);

    }

    public void set(double leftPower, double rightPower) {

    }

    public void drive(double throttle, double rot, boolean isQuickTurn) {
        leftFront.set(ControlMode.PercentOutput,throttle - rot);
        rightFront.set(ControlMode.PercentOutput,-throttle - rot);
        leftBack.set(ControlMode.PercentOutput, throttle - rot);
        rightBack.set(ControlMode.PercentOutput,-throttle -rot);
    }

}
