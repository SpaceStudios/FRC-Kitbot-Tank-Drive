// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Shooter_Monty.MontyIO.MontyIOData;

public class MontySubsystem extends SubsystemBase {
  /** Creates a new MontyShooter. */
  MontyIO io;
  MontyIOData MainData = new MontyIOData();
  public MontySubsystem(PneumaticHub hub) {
    io = new MontyIO_Main(hub);
  }

  public void setLaunchVolts(double volts) {
    io.setLaunchVolts(volts);
  }
  
  public void setFeederVolts(double volts) {
    io.setFeederVolts(volts);
  }

  public void setIntakeVolts(double volts) {
    io.setIntakeVolts(volts);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    io.getData(MainData);
    Logger.recordOutput("Launcher Volts", MainData.LauncherVolts);
    Logger.recordOutput("Feeder Volts", MainData.FeederVolts);
    Logger.recordOutput("Intake Volts", MainData.IntakeVolts);

    Logger.recordOutput("Launcher AMPs", MainData.LauncherAMPs);
    Logger.recordOutput("Feeder AMPs", MainData.FeederAMPs);
    Logger.recordOutput("Intake AMPs", MainData.IntakeAMPs);

    Logger.recordOutput("Intake State", MainData.intakeState);
    Logger.recordOutput("Hood State", MainData.hoodState);
  }
}
