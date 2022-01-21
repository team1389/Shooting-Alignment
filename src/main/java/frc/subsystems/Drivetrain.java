package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
    private TalonSRX leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;

    public double kP = 0.02;

    public Drivetrain() {
        leftFrontMotor = new TalonSRX(RobotMap.LEFT_FRONT);
        leftBackMotor = new TalonSRX(RobotMap.LEFT_BACK);

        rightFrontMotor = new TalonSRX(RobotMap.RIGHT_FRONT);
        rightBackMotor = new TalonSRX(RobotMap.RIGHT_BACK);
        /*configPID(leftBackMotor);
        configPID(leftFrontMotor);
        configPID(rightBackMotor);
        configPID(rightFrontMotor);*/
    }

    public void set(double leftPower, double rightPower) {
        leftFrontMotor.set(ControlMode.PercentOutput, leftPower);
        leftBackMotor.set(ControlMode.PercentOutput, leftPower);

        rightFrontMotor.set(ControlMode.PercentOutput, -rightPower);
        rightBackMotor.set(ControlMode.PercentOutput, -rightPower);
    }

    public void drive(double throttle, double rot, boolean isQuickTurn) {
        //differentialDrive.tankDrive(leftY, rightY);
        leftFrontMotor.set(ControlMode.PercentOutput, throttle - rot);
        leftBackMotor.set(ControlMode.PercentOutput, throttle - rot);

        rightFrontMotor.set(ControlMode.PercentOutput, -throttle - rot);
        rightBackMotor.set(ControlMode.PercentOutput, -throttle - rot);
    }

    /*public void configPID(TalonSRX talon) {
        talon.config_kD();
        talon.config_kF();
        talon.config_kI();
        talon.config_kP();
    }*/
}
