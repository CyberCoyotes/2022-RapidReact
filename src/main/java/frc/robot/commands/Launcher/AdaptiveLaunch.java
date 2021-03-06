// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Interpolator;
import frc.robot.Limelight;
import frc.robot.subsystems.Launcher;

public class AdaptiveLaunch extends CommandBase {
  private Launcher subsystem;
  private Limelight limelight;

  /** Creates a new LaunchAutomatic. */
  public AdaptiveLaunch(Launcher subsystem, Limelight limelight) {
    this.subsystem = subsystem;
    this.limelight = limelight;
    // Use addRequirements() here to declare subsystem dependencies
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  // adjusting range to include 2.0 but still using default power output 
  @Override
  public void execute() {

    if(limelight.hasValidTarget()) {
      double yAngle = limelight.getY();
      double speedFront = Interpolator.getFrontSpeed(yAngle);
      System.out.println("Front launch output" + speedFront);
      double speedBack = Interpolator.getBackSpeed(yAngle);
      System.out.println("Back launch output" + speedBack);
      subsystem.setLauncherSpeed(speedFront, speedBack);
    } else {
      subsystem.setLauncherSpeed(0.35, 0.40); // Split the tape launching, seems to be defaulting to this so that's good
    }
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