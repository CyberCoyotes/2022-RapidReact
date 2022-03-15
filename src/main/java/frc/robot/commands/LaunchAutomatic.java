// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Interpolator;
import frc.robot.Limelight;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Launcher;

public class LaunchAutomatic extends CommandBase {
  private Launcher subsystem;
  private Limelight limelight;

  /** Creates a new LaunchAutomatic. */
  public LaunchAutomatic(Launcher subsystem, Limelight limelight) {
    this.subsystem = subsystem;
    this.limelight = limelight;
    // Use addRequirements() here to declare subsystem dependencies
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yAngle = limelight.getY();
    double speedFront = Interpolator.getFrontSpeed(yAngle);
    double speedBack = Interpolator.getBackSpeed(yAngle);
    subsystem.setLauncherSpeed(speedFront, speedBack);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.stopLauncher();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
