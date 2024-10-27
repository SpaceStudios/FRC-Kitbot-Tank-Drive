// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.Subsystems.Shooter.ShooterIO.ShooterData;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  ShooterIO io;
  CommandXboxController controller = new CommandXboxController(0);
  ShooterData MainData = new ShooterData();

  public ShooterSubsystem() {
    switch (Constants.currentMode) {
      case REAL:
        io = new ShooterIOSparkMax();
        break;
      case SIM:
        io = new ShooterIOSIM();
        break;
      default:
        break;
    }
  }

  public void SetFeederOutput(double volts) {
    io.setFeederVolts(volts);
  }

  public void SetShooterOutput(double volts) {
    io.setShooterVolts(volts);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.getData(MainData);
    Logger.recordOutput("Shooter Speed", MainData.ShooterVelocity);
    Logger.recordOutput("Shooter Output Volts", MainData.ShooterOutput);
    Logger.recordOutput("Feeder Speed", MainData.FeederVelocity);
    Logger.recordOutput("Feeder Output Volts", MainData.FeederOutput);
  }
}
