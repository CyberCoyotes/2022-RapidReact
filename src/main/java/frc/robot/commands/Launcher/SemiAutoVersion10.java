
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// FRC 3603 Launch High
package frc.robot.commands.Launcher;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;


public class SemiAutoVersion10 extends CommandBase {
    private final Launcher launcher;
  
    // https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html
    
    static boolean targetStatus = false;
    NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-back");//Instantiate the tables
    double tx = limelight.getEntry("tx").getDouble(0.0);

  public SemiAutoVersion10(Launcher launch) {

    launcher = launch;
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (tx < 5) {
      launcher.setLaunch2();
      System.out.println("Missile lock-on");
    } else {
      System.out.println("Still seeking target");
      launcher.stopLauncher();
   }  
    
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