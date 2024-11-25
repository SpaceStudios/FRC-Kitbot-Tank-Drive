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
    public static final int drivetrainLeftFollowSparkID = 3;
    public static final int drivetrainRightSparkID = 2;
    public static final int drivetrainRightFollowSparkID = 4;
    //Talon SRX IDs
    public static final int driveTrainLFTalon = 1;
    public static final int driveTrainLBTalon = 3;
    public static final int driveTrainRFTalon = 2;
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

    public static enum RobotType {
        Kitbot,
        Monty
    }

    public static final RobotType CurrentType = RobotType.Kitbot;

    // Shooter Constants
    public static final int FeederID = 11;
    public static final int ShooterMotorID = 12;
    public static final double ShooterStrength = 0.5;

    //Monty Constants
    public static final int MainIntakeID = 11;
    public static final int LeftIntakeID = 12;
    public static final int RightIntakeID = 13;

    public static final int LeftLauncherID = 21;
    public static final int RightLauncherID = 22;
    public static final int MainFeederID = 23;

    public static final int HoodID = 9;
    public static final int IntakeID = 8;
    public static final double IntakeForce = 0.5;
    public static final double FeederForce = 1;
    public static final double LauncherForce = 0.5;
}
