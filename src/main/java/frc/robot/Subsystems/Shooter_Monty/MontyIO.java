// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shooter_Monty;

/** Add your docs here. */
public interface MontyIO {

    public static class MontyIOData {
        public double LauncherVolts;
        public double FeederVolts;
        public double IntakeVolts;

        public double LauncherAMPs;
        public double FeederAMPs;
        public double IntakeAMPs;

        public boolean hoodState;
        public boolean intakeState;
    }

    public abstract void setIntakeState(boolean newintakeState);
    public abstract void setHoodState(boolean newhoodState);
    public abstract void setFeederVolts(double volts);
    public abstract void setIntakeVolts(double volts);
    public abstract void setLaunchVolts(double volts);
    public abstract boolean getIntakeState();
    public abstract boolean getHoodState();
    public abstract void getData(MontyIOData data);
}
