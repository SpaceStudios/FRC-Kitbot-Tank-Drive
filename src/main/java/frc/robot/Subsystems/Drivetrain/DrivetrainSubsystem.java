// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  DrivetrainIO io;

  DrivetrainIOInputsAutoLogged inputs = new DrivetrainIOInputsAutoLogged();
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(new Rotation2d(), 0, 0);
  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.5);

  private void setVoltages(double left, double right) {
    io.setVolts(left, right);
  }

  public Command setVoltagesCommand(DoubleSupplier left, DoubleSupplier right) {
    return new RunCommand(() -> this.setVoltages(left.getAsDouble(), right.getAsDouble()), this);
  }

  public Command setVoltagesArcadeCommand(DoubleSupplier drive, DoubleSupplier steer) {
    return new RunCommand(() -> {
      var speeds = DifferentialDrive.arcadeDriveIK(drive.getAsDouble(), steer.getAsDouble(), false);
      this.setVoltages(speeds.left * Constants.DriveTrainVoltage, speeds.right * Constants.DriveTrainVoltage);
    }, this);
  }

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    switch (Constants.currentMode) {
      case REAL:
        switch (Constants.currentMotor) {
          case SparkMax:
            io = new DrivetrainIOSparkMaxs();
            break;
          case TalonFX:
            io = new DrivetrainIOSim();
            break;
          case TalonSRX:
            io = new DrivetrainIOTalonSRX();
            break;
        }
        break;
      case SIM:
        io = new DrivetrainIOSim();
        break;
      case REPLAY:
        break;
    }
  }

  public Pose2d getPose2d() {
    return odometry.getPoseMeters();
  }

  public void resetPose2d(Pose2d newPose) {
    odometry.resetPosition(new Rotation2d(), 0, 0, newPose);
  }

  public ChassisSpeeds getCurrentSpeeds() {
    return new ChassisSpeeds((inputs.leftVelocityMetersPerSecond+inputs.rightVelocityMetersPerSecond)/2, 0, inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond);
  }

  public void driveBasedOnChassisSpeeds(ChassisSpeeds setCurrentSpeeds) {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.updateInputs(inputs);
    Logger.processInputs("Drivetrain", inputs);
    odometry.update(
    odometry.getPoseMeters().getRotation()
        // Use differential drive kinematics to find the rotation rate based on the wheel speeds and distance between wheels
        .plus(Rotation2d.fromRadians((inputs.leftVelocityMetersPerSecond - inputs.rightVelocityMetersPerSecond)
            * 0.020 / Units.inchesToMeters(26))),
    inputs.leftPositionMeters, inputs.rightPositionMeters);
    Logger.recordOutput("Drivebase Pose", odometry.getPoseMeters());
  }
}
