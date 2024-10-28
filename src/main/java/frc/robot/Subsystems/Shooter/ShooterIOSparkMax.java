// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;

/** Add your docs here. */
public class ShooterIOSparkMax implements ShooterIO{
    CANSparkMax Shooter;
    CANSparkMax Feeder;
    RelativeEncoder ShooterEncoder;
    RelativeEncoder FeederEncoder;
    
    public ShooterIOSparkMax() {
        Shooter = new CANSparkMax(Constants.ShooterMotorID, MotorType.kBrushless);
        Feeder = new CANSparkMax(Constants.FeederID, MotorType.kBrushless);
        ShooterEncoder = Shooter.getEncoder();
        FeederEncoder = Shooter.getEncoder();

        Shooter.restoreFactoryDefaults();
        Feeder.restoreFactoryDefaults();
        
        Shooter.setIdleMode(IdleMode.kCoast);
        Feeder.setIdleMode(IdleMode.kCoast);
        
        Shooter.setInverted(true);
        Feeder.setInverted(true);

        Shooter.burnFlash();
        Feeder.burnFlash();
    }
    @Override
    public void setFeederVolts(double volts) {
        Feeder.setVoltage(volts);
    }
    @Override
    public void setShooterVolts(double volts) {
        Shooter.setVoltage(volts);
    }
    @Override
    public void getData(ShooterData data) {
        data.ShooterVelocity = ShooterEncoder.getVelocity();
        data.FeederVelocity = FeederEncoder.getVelocity();

        data.ShooterOutput = Shooter.getAppliedOutput();
        data.FeederOutput = Feeder.getAppliedOutput();

        data.ShooterAMPs = Shooter.getOutputCurrent();
        data.FeederAMPs = Feeder.getOutputCurrent();

        data.ShooterTemp = Shooter.getMotorTemperature();
        data.FeederTemp = Feeder.getMotorTemperature();
    }

}
