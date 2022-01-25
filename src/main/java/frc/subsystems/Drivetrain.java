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
        rightFront.set(ControlMode.PercentOutput, rightPower);
    }

    public void drive(double throttle, double rot, boolean isQuickTurn) {
        double leftPower = throttle - rot;
        double rightPower = throttle + rot;
        if (Math.abs(throttle)+Math.abs(rot) > 1) {
            double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            leftPower = leftPower/max;
            rightPower = rightPower/max;
        }
        leftFront.set(ControlMode.PercentOutput, leftPower);
        rightFront.set(ControlMode.PercentOutput, rightPower);
        leftBack.set(ControlMode.PercentOutput,  leftPower);
        rightBack.set(ControlMode.PercentOutput, rightPower);
    }

}
