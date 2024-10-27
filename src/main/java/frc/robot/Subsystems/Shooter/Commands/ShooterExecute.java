// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;

public class ShooterExecute extends Command {
  private ShooterSubsystem shooter;
  private double VoltModifier;
  /** Creates a new ShooterExecute. */
  public ShooterExecute(double NewVoltModifier, ShooterSubsystem Shooter_Set) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooter = Shooter_Set;
    this.VoltModifier = NewVoltModifier;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.SetShooterOutput(VoltModifier);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.SetShooterOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
