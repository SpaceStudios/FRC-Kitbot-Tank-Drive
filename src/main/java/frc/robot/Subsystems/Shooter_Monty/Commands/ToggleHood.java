// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter_Monty.MontySubsystem;

public class ToggleHood extends Command {
  public final MontySubsystem mainSubsystem;
  /** Creates a new ToggleHood. */
  public ToggleHood(MontySubsystem mainMontySubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    mainSubsystem = mainMontySubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mainSubsystem.setHoodState(!mainSubsystem.getHoodState());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
