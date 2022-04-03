// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Launcher;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SemiAuto_11 extends CommandBase {

  double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
  double ty = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("ty").getDouble(0);

  /** Creates a new SemiAutoV11. */
  public SemiAuto_11() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((10 < ty && ty < 14) & (-5 < tx && tx <5))
    {
      //set targetLock to true when tx & ty are within the parameters
      System.out.println("Target is lock");
      System.out.println(tx);
      System.out.println(ty);
    } else {
      System.out.println("Target is NOT locked");
      System.out.println(tx);
      System.out.println(ty);
    } 
  
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
