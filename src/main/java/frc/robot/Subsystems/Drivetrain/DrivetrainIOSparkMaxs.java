// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;

import frc.robot.Constants;

/** Add your docs here. */
public class DrivetrainIOSparkMaxs implements DrivetrainIO{
    CANSparkMax leftSpark = new CANSparkMax(Constants.drivetrainLeftSparkID, MotorType.kBrushless);
    CANSparkMax rightSpark = new CANSparkMax(Constants.drivetrainRightSparkID, MotorType.kBrushless);
    CANSparkMax LeftFollow = new CANSparkMax(Constants.drivetrainLeftFollowSparkID, MotorType.kBrushless);
    CANSparkMax RightFollow = new CANSparkMax(Constants.drivetrainRightFollowSparkID, MotorType.kBrushless);
    RelativeEncoder LeftEncoder = leftSpark.getEncoder();
    RelativeEncoder RightEncoder = rightSpark.getEncoder();

    // SparkPIDController LeftPid = leftSpark.getPIDController();
    // SparkPIDController RightPid = leftSpark.getPIDController();

    public DrivetrainIOSparkMaxs() {
        leftSpark.restoreFactoryDefaults();
        LeftFollow.restoreFactoryDefaults();
        RightFollow.restoreFactoryDefaults();
        rightSpark.restoreFactoryDefaults();

        leftSpark.setCANTimeout(250);
        LeftFollow.setCANTimeout(250);
        rightSpark.setCANTimeout(250);
        RightFollow.setCANTimeout(250);
        
        leftSpark.setInverted(true);
        rightSpark.setInverted(false);

        LeftFollow.follow(leftSpark);
        RightFollow.follow(rightSpark);

        leftSpark.enableVoltageCompensation(12.0);
        rightSpark.enableVoltageCompensation(12.0);

        leftSpark.setSmartCurrentLimit(40);
        rightSpark.setSmartCurrentLimit(40);

        leftSpark.setIdleMode(IdleMode.kBrake);
        rightSpark.setIdleMode(IdleMode.kBrake);

        leftSpark.burnFlash();
        LeftFollow.burnFlash();
        rightSpark.burnFlash();
        RightFollow.burnFlash();
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftPositionMeters = LeftEncoder.getPosition();
        inputs.leftVelocityMetersPerSecond = LeftEncoder.getVelocity();
        inputs.leftCurrentAmps = new double[] {leftSpark.getOutputCurrent()};
        inputs.leftTempCelsius = new double[] {leftSpark.getMotorTemperature()};
        inputs.rightPositionMeters = RightEncoder.getPosition();
        inputs.rightVelocityMetersPerSecond = RightEncoder.getVelocity();
        inputs.rightCurrentAmps = new double[] {rightSpark.getOutputCurrent()};
        inputs.rightTempCelsius = new double[] {rightSpark.getMotorTemperature()};
    }

    @Override
    public void setVolts(double left, double right) {
        leftSpark.setVoltage(left);
        rightSpark.setVoltage(right);
    }
}
