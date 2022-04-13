
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// FRC 3603 Launch High
package frc.robot.commands.launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;

public class PreLaunch extends CommandBase {
    private final Launcher launcher;

  
  public PreLaunch(Launcher launch) {

    launcher = launch;
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    launcher.setPreLaunch();
  }

  // Called once the command ends or is interrupted.
   @Override
  public void end(boolean interrupted) {
    launcher.stopLauncher();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
