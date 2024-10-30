// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter_Monty.MontySubsystem;

public class runIntake extends Command {
  /** Creates a new setIntake. */
  public final MontySubsystem mainSubsystem;
  public final double speed;
  public runIntake(MontySubsystem mainMontySubsystem,double newSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    mainSubsystem = mainMontySubsystem;
    speed = newSpeed;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mainSubsystem.setIntakeVolts(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mainSubsystem.setIntakeVolts(0);
  }
}
