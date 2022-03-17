
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// FRC 3603 Launch High
package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;

public class LaunchTarmac extends CommandBase {
    private final Launcher launcher;

  
  public LaunchTarmac(Launcher launch) {

    launcher = launch;
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    launcher.setTarmacLaunch();
  }

  /** 
  new LaunchSpeed(launcher, 0.30, 0.35).withTimeout(0.50),
  // Index the ball #1 into the running Launcher
    new IndexSpeed(indexMotors, 0.5).withTimeout(0.5), 
      new ParallelDeadlineGroup(
        new WaitCommand(4),
        // Maintain Launcher speed
        new LaunchSpeed(launcher, 0.36, 0.42),
*/

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
