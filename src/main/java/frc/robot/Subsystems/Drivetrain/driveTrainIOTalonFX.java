// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;

/** Add your docs here. */
public class driveTrainIOTalonFX implements DrivetrainIO{
    TalonFX TalonLF = new TalonFX(Constants.driveTrainLFTalon);
    TalonFX TalonLB = new TalonFX(Constants.driveTrainLBTalon);
    TalonFX TalonRF = new TalonFX(Constants.driveTrainRFTalon);
    TalonFX TalonRB = new TalonFX(Constants.driveTrainRBTalon);

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftPositionMeters = TalonLF.getPosition().getValueAsDouble();
        inputs.leftCurrentAmps = new double[(int)(TalonLF.getSupplyCurrent().getValueAsDouble())];
        inputs.leftOutputVolts = TalonLF.getMotorVoltage().getValueAsDouble();
        inputs.leftTempCelsius = new double[(int)(TalonLF.getDeviceTemp().getValueAsDouble())];
        inputs.leftVelocityMetersPerSecond = TalonRF.getVelocity().getValueAsDouble();
        inputs.rightPositionMeters = TalonRF.getPosition().getValueAsDouble();
        inputs.rightCurrentAmps = new double[(int)(TalonRF.getSupplyCurrent().getValueAsDouble())];
        inputs.rightOutputVolts = TalonRF.getMotorVoltage().getValueAsDouble();
        inputs.rightTempCelsius = new double[(int)(TalonRF.getDeviceTemp().getValueAsDouble())];
        inputs.rightVelocityMetersPerSecond = TalonRF.getVelocity().getValueAsDouble();
    }

    @Override
    public void setVolts(double left, double right) {
        // TODO Auto-generated method stub
        TalonLF.setVoltage(left);
        TalonLB.setVoltage(left);
        TalonRF.setVoltage(right);
        TalonRB.setVoltage(right);
    }

}
