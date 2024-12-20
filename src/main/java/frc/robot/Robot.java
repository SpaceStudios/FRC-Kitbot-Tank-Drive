// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.bluecheese.betterLogger;
import org.bluecheese.betterLogger.LogMode;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends LoggedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  

  @Override
  public void robotInit() {
    
    //Logger.getInstance().addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
    //Logger.getInstance().recordMetadata("ProjectName", "KitbotExample"); // Set a metadata value
    //if (isReal()) {
    //  Logger.getInstance().addDataReceiver(new WPILOGWriter("/media/sda1/")); // Log to a USB stick
    //  new PowerDistribution(1, ModuleType.kRev); // Enables power distribution logging
    //} else {
    //  setUseTiming(false); // Run as fast as possible
    //  String logPath = LogFileUtil.findReplayLog(); // Pull the replay log from AdvantageScope (or prompt the user)
    // Logger.getInstance().setReplaySource(new WPILOGReader(logPath)); // Read replay log
    //  Logger.getInstance().addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim"))); // Save outputs to a new log 
    //}
    // Logger.getInstance().disableDeterministicTimestamps() // See "Deterministic Timestamps" in the "Understanding Data Flow" page
    // Start logging! No more data receivers, replay sources, or metadata values may be added.
    System.out.println("////////////////////");
    System.out.println("// Brunost V1.1.0 //");
    System.out.println("////////////////////");
    switch (Constants.currentMode) {
      case REAL:
        Logger.addDataReceiver(new WPILOGWriter("/media/sda1/")); 
        Logger.addDataReceiver(new NT4Publisher());
        betterLogger.Start(LogMode.ROBOT);
        break;
      case SIM:
        Logger.addDataReceiver(new NT4Publisher());
        betterLogger.Start(LogMode.SIMULATOR);
        break;
      case REPLAY:
        setUseTiming(false); 
        String logPath = LogFileUtil.findReplayLog();
        Logger.setReplaySource(new WPILOGReader(logPath));
        Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));
        break;
      
    }
    Logger.start();
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
