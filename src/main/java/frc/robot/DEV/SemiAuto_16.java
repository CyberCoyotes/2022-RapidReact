// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DEV;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionRange;
// import frc.robot.Constants.Launcher;
import frc.robot.subsystems.Launcher;


public class SemiAuto_16 extends CommandBase {

  private final Launcher launcher;


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

  
  public SemiAuto_16(Launcher launch) {
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

    if ((VisionRange.txMin< TX && TX < VisionRange.txMax) && (VisionRange.tyMin< TY && TY < VisionRange.tyMax)) {
      //Sets targetLock to true when tx & ty are within the parameters
      launcher.setLaunch2();

    } else {
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
