// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class Constants {
    // Drive Train Constants
    // Falcon IDs
    public static final int drivetrainLeftFalconID = 0;
    public static final int drivetrainRightFalconID = 1;
    // Spark IDs
    public static final int drivetrainLeftSparkID = 1;
    public static final int drivetrainLeftFollowSparkID = 2;
    public static final int drivetrainRightSparkID = 3;
    public static final int drivetrainRightFollowSparkID = 4;
    //Talon SRX IDs
    public static final int driveTrainLFTalon = 1;
    public static final int driveTrainLBTalon = 2;
    public static final int driveTrainRFTalon = 3;
    public static final int driveTrainRBTalon = 4;

    public static final Mode currentMode = Mode.SIM;
    public static final ControlMode currentControl = ControlMode.Controller;
    public static final MotorID currentMotor = MotorID.SparkMax;

    public static final double JoystickDriftControl = 0.05;

    public static final int DriveTrainVoltage = 12;

    public static enum Mode {
        REAL,
        SIM,
        REPLAY
    }

    public static enum MotorID {
        SparkMax,
        TalonFX,
        TalonSRX
    }

    public static enum ControlMode {
        Keyboard,
        Controller,
        cursedController
    }

    // Shooter Constants
    public static final int FeederID = 11;
    public static final int ShooterMotorID = 12;
    public static final double ShooterStrength = 0.5;

    //Monty Constants
    public static final int FeederMotorID = 11;
    public static final int LeftFeederID = 12;
    public static final int RightFeederID = 13;

    public static final int LeftShooterID = 21;
    
}
