// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;


public class SemiAuto_15 extends CommandBase {

  private final Launcher launcher;
  boolean targetLock = false;


  //Shuffleboard.selectTab("Vision");

  /* NetworkTableEntry tx = Shuffleboard.getTab("Vision")
    .add("TX")
    .withWidget("TX Value")
    .getEntry("tx");
  */
   
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-back");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  ShuffleboardTab visionTab = Shuffleboard.getTab("Vision");

  /** 
  Shuffleboard.getTab("Vision")
    .add("tx", tx)
    .add("ty", ty);
  */

  
  public SemiAuto_15(Launcher launch) {
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
    // read values periodically
    double TX = tx.getDouble(0.0);
    double TY = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    SmartDashboard.putNumber(("tX"), TX); // Added since v.12
    SmartDashboard.putNumber(("tY"), TY); // Added since v.12
    SmartDashboard.putBoolean("Target Status", targetLock);

    if((5 < TY && TY < 14) & (-5 < TX && TX <5))
    {
      //Sets targetLock to true when tx & ty are within the parameters
      System.out.println("++ Target LOCKED ++ " + "(" + TX + "," + TY + ")" + " Area:" + area); // Revised since v.12
      launcher.setLaunch2();
      targetLock = true; // Added since v.12

    } else {
      System.out.println("-- Target NOT locked --" + "(" + TX + "," + TY + ")" + " Area:" + area);
      launcher.stopLauncher();
      targetLock = false; // Added since v.12
    } 
  
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {      
      launcher.stopLauncher();}
      // double TX = tx.getDouble(0.0);
      // double TY = ty.getDouble(0.0);
      // double area = ta.getDouble(0.0);

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
