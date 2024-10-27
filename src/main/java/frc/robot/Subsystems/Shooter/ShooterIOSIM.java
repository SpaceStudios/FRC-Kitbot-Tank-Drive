// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import frc.robot.Constants;

/** Add your docs here. */
public class ShooterIOSIM implements ShooterIO{

    TalonFX ShooterMotor;
    TalonFX FeederMotor;

    VoltageOut ShooterVoltage;
    VoltageOut FeederVoltage;

    public ShooterIOSIM() {
        ShooterMotor = new TalonFX(Constants.ShooterMotorID);
        FeederMotor = new TalonFX(Constants.FeederID);

        ShooterMotor.setInverted(true);
        FeederMotor.setInverted(true);
        
        ShooterVoltage = new VoltageOut(0.0);
        FeederVoltage = new VoltageOut(0.0);
    }

    @Override
    public void setFeederVolts(double volts) {
        FeederMotor.setControl(FeederVoltage.withOutput(volts));
    }

    @Override
    public void setShooterVolts(double volts) {
        ShooterMotor.setControl(ShooterVoltage.withOutput(volts));
    }

    @Override
    public void getData(ShooterData data) {
        // Getting Sim State
        var ShooterSIMState = ShooterMotor.getSimState();
        var FeederSIMState = FeederMotor.getSimState();

        ShooterSIMState.setSupplyVoltage(RoboRioSim.getVInVoltage());
        FeederSIMState.setSupplyVoltage(RoboRioSim.getVInVoltage());

        data.ShooterOutput = ShooterSIMState.getMotorVoltage();
        data.ShooterAMPs = ShooterSIMState.getTorqueCurrent();
        data.ShooterTemp = 0.0;
        data.ShooterVelocity = 0.0;

        data.FeederOutput = FeederSIMState.getMotorVoltage();
        data.FeederAMPs = FeederSIMState.getTorqueCurrent();
        data.FeederTemp = 0.0;
        data.FeederVelocity = 0.0;
    }

}
