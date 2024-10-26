// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter;

/** Add your docs here. */
public interface ShooterIO {
    public static class ShooterData{
        public double ShooterVelocity = 0.0;
        public double FeederVelocity = 0.0;
        
        public double ShooterTemp = 0.0;
        public double FeederTemp = 0.0;

        public double ShooterAMPs = 0.0;
        public double FeederAMPs = 0.0;

        public double ShooterOutput = 0.0;
        public double FeederOutput = 0.0;
    }
    public abstract void setFeederVolts(double volts);
    public abstract void setShooterVolts(double volts);
    public abstract void getData(ShooterData data);
}
