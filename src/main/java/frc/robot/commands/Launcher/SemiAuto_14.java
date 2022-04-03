// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants.Launcher;
import frc.robot.subsystems.Launcher;


public class SemiAuto_14 extends CommandBase {

  private final Launcher launcher;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-back");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  


  public SemiAuto_14(Launcher launch) {
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
    //read values periodically
    double TX = tx.getDouble(0.0);
    double TY = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    SmartDashboard.putNumber("LimelightX", TX);
    SmartDashboard.putNumber("LimelightY", TY);
    SmartDashboard.putNumber("LimelightArea", area);

    if((5 < TY && TY < 14) & (-5 < TX && TX <5))
    {
      //set targetLock to true when tx & ty are within the parameters
      System.out.println("Target is lock");
      System.out.println(TX);
      System.out.println(TY);
      System.out.println(area);
      launcher.setLaunch2();

    } else {
      System.out.println("Target is NOT locked");
      System.out.println(TX);
      System.out.println(TY);
      System.out.println(area);
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
