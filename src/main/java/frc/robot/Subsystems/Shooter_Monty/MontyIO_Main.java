// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

/** Add your docs here. */
public class MontyIO_Main implements MontyIO {

    TalonFX LeftLauncher;
    TalonFX RightLauncher;
    TalonSRX feedMotor;

    TalonSRX leftIntake;
    TalonSRX rightIntake;
    TalonSRX mainIntake;


    Solenoid Hood;
    Solenoid Intake;

    public MontyIO_Main(PneumaticHub hub) {
        Hood = hub.makeSolenoid(Constants.HoodID);
        Intake = hub.makeSolenoid(Constants.IntakeID);
        // Setting up Launch System
        LeftLauncher = new TalonFX(Constants.LeftLauncherID);
        RightLauncher = new TalonFX(Constants.RightLauncherID);
        feedMotor = new TalonSRX(Constants.MainFeederID);

        // Setting up Intake System
        leftIntake = new TalonSRX(Constants.LeftIntakeID);
        rightIntake = new TalonSRX(Constants.RightIntakeID);
        mainIntake = new TalonSRX(Constants.MainIntakeID);

        leftIntake.configFactoryDefault();
        rightIntake.configFactoryDefault();
        mainIntake.configFactoryDefault();

        leftIntake.follow(mainIntake);
        rightIntake.follow(mainIntake);
        LeftLauncher.getConfigurator().apply(new TalonFXConfiguration());
        RightLauncher.getConfigurator().apply(new TalonFXConfiguration());
        feedMotor.configFactoryDefault();

        RightLauncher.setControl(new Follower(LeftLauncher.getDeviceID(),true));

        Intake.set(false);
        Hood.set(true);
    }

    @Override
    public void setIntakeState(boolean newintakeState) {
        Intake.set(newintakeState);
    }

    @Override
    public void setHoodState(boolean newhoodState) {
        Hood.set(newhoodState);
    }

    @Override
    public void setFeederVolts(double volts) {
        feedMotor.set(ControlMode.PercentOutput, volts*Constants.FeederForce);
    }

    @Override
    public void setIntakeVolts(double volts) {
        mainIntake.set(ControlMode.PercentOutput, volts*Constants.IntakeForce);
    }

    @Override
    public void setLaunchVolts(double volts) {
        LeftLauncher.set(volts*Constants.LauncherForce);
    }

    @Override
    public void getData(MontyIOData data) {
        data.LauncherVolts = LeftLauncher.getMotorVoltage().getValueAsDouble();
        data.FeederVolts = feedMotor.getMotorOutputVoltage();
        data.IntakeVolts = mainIntake.getMotorOutputVoltage();

        data.LauncherAMPs = LeftLauncher.getTorqueCurrent().getValueAsDouble();
        data.FeederAMPs = feedMotor.getSupplyCurrent();
        data.IntakeAMPs = feedMotor.getSupplyCurrent();

        data.intakeState = Intake.get();
        data.hoodState = Hood.get();
    }

}
