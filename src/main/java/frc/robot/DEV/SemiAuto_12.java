// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DEV;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants.Launcher;
import frc.robot.subsystems.Launcher;

public class SemiAuto_12 extends CommandBase {

  private final Launcher launcher;

  double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
  double ty = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("ty").getDouble(0);

  public SemiAuto_12(Launcher launch) {
    // Use addRequirements() here to declare subsystem dependencies.
    launcher = launch;
    addRequirements(launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if((5 < ty && ty < 14) & (-5 < tx && tx <5))
    {
      //set targetLock to true when tx & ty are within the parameters
      System.out.println("Target is lock");
      System.out.println(tx);
      System.out.println(ty);
      launcher.setHighLaunch();

    } else {
      System.out.println("Target is NOT locked");
      System.out.println(tx);
      System.out.println(ty);

      launcher.stopLauncher();
    } 
  
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {      
      launcher.stopLauncher();}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
