// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Subsystems.Shooter.Commands.FeederExecute;
import frc.robot.Subsystems.Shooter.Commands.ShooterExecute;

public class RobotContainer {
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    switch (Constants.currentControl) {
      case Keyboard:
        drivetrainSubsystem.setDefaultCommand(
          drivetrainSubsystem.setVoltagesArcadeCommand(
            () -> modifyJoystick(-controller.getLeftY()),
            () -> modifyJoystick(controller.getLeftX())));
        break;
      case Controller:
        drivetrainSubsystem.setDefaultCommand(
          drivetrainSubsystem.setVoltagesArcadeCommand(
            () -> modifyJoystick(-controller.getLeftY()),
            () -> modifyJoystick(controller.getRightX())));
        controller.rightTrigger().whileTrue(new ShooterExecute(-1, Shooter));
        controller.leftTrigger().whileTrue(new FeederExecute(-1, Shooter));
        break;
      case cursedController:
        drivetrainSubsystem.setDefaultCommand(
          drivetrainSubsystem.setVoltagesArcadeCommand(
            () -> modifyJoystick(-controller.getLeftY()),
            () -> modifyJoystick(controller.getRightY())));
        break;
    }
    //drivetrainSubsystem.setDefaultCommand(
    //  drivetrainSubsystem.setVoltagesArcadeCommand(
    //    () -> modifyJoystick(-controller.getLeftY()),
    //    () -> modifyJoystick(controller.getLeftX())));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  CommandXboxController controller = new CommandXboxController(0);

  DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  ShooterSubsystem Shooter = new ShooterSubsystem();

  private double modifyJoystick(double in) {
    if (Math.abs(in) < 0.05) {
      return 0;
    }
    return in * in * Math.signum(in);
  }
}
