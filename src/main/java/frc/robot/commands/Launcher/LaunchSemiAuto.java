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
import frc.robot.Constants.VisionRange;
// import frc.robot.Constants.Launcher;
import frc.robot.subsystems.Launcher;


public class LaunchSemiAuto extends CommandBase {

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

  double TX = tx.getDouble(0.0);
  double TY = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);

  ShuffleboardTab visionTab = Shuffleboard.getTab("Vision");

  /** 
  Shuffleboard.getTab("Vision")
    .add("tx", tx)
    .add("ty", ty);
  */

  
  public LaunchSemiAuto(Launcher launch) {
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

    SmartDashboard.putNumber(("tX"), TX);
    SmartDashboard.putNumber(("tY"), TY);
    SmartDashboard.putBoolean("Target Status", targetLock);

    if ((VisionRange.txMin< TX && TX < VisionRange.txMax) & (VisionRange.tyMin< TY && TY < VisionRange.tyMax)) {
      //Sets targetLock to true when tx & ty are within the parameters
      System.out.println("++ Target LOCKED ++ " + "(" + TX + "," + TY + ")" + " Area:" + area);

      // TODO Replace with Group2BallsHigh once intake is fixed
      launcher.setLaunch2();
      targetLock = true;

    } else {
      System.out.println("-- Target NOT locked -- " + "(" + TX + "," + TY + ")" + " Area:" + area);
      launcher.stopLauncher();
      targetLock = false;
    } 
  
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {      
      launcher.stopLauncher();}
      // FIXME Do TX and TY need to be reset to zero to prevent false positives with next attempt at a shot?
      // double TX = tx.getDouble(0.0);
      // double TY = ty.getDouble(0.0);
      // double area = ta.getDouble(0.0);

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
