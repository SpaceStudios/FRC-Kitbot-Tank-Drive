// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter_Monty.MontySubsystem;

public class runLauncher extends Command {
  private final MontySubsystem mainSubsystem;
  private final double speed;
  /** Creates a new runLauncher. */
  public runLauncher(MontySubsystem newSubsystem, double newSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    mainSubsystem = newSubsystem;
    speed = newSpeed;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mainSubsystem.setLaunchVolts(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mainSubsystem.setLaunchVolts(0);
  }
}
