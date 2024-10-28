// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.RobotController;
import frc.robot.Constants;

/** Add your docs here. */
public class DrivetrainIOTalonSRX implements DrivetrainIO {

    TalonSRX TalonLF = new TalonSRX(Constants.driveTrainLFTalon);
    TalonSRX TalonLB = new TalonSRX(Constants.driveTrainLBTalon);
    TalonSRX TalonRF = new TalonSRX(Constants.driveTrainRFTalon);
    TalonSRX TalonRB = new TalonSRX(Constants.driveTrainRBTalon);

    public DrivetrainIOTalonSRX() {
        TalonLB.follow(TalonLF);
        TalonRB.follow(TalonRF);

        TalonLF.setNeutralMode(NeutralMode.Brake);
        TalonRF.setNeutralMode(NeutralMode.Brake);

        TalonLF.setInverted(true);
    }

    @Override
    public void updateInputs(DrivetrainIOInputs inputs) {
        inputs.leftOutputVolts = TalonLF.getMotorOutputVoltage();
        inputs.leftTempCelsius = new double[] {(TalonLF.getTemperature())};
        inputs.leftCurrentAmps = new double[] {(TalonLF.getSupplyCurrent())};
        inputs.rightOutputVolts = TalonRF.getMotorOutputVoltage();
        inputs.rightTempCelsius = new double[] {(TalonRF.getTemperature())};
        inputs.rightCurrentAmps = new double[] {(TalonRF.getSupplyCurrent())};
    }

    @Override
    public void setVolts(double left, double right) {
        TalonLF.set(TalonSRXControlMode.PercentOutput, left/RobotController.getInputVoltage());
        TalonLB.set(TalonSRXControlMode.PercentOutput, left/RobotController.getInputVoltage());
        TalonRF.set(TalonSRXControlMode.PercentOutput, right/RobotController.getInputVoltage());
        TalonRB.set(TalonSRXControlMode.PercentOutput, right/RobotController.getInputVoltage());
    }

}
