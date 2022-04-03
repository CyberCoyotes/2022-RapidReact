// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants.LimelightRange;

public class TargetStatus {
  // https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html

  static boolean targetLock = false;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-back");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  double TX = tx.getDouble(0.0);
  double TY = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  
public static boolean getTargetStatus(double TX, double TY) {
  if ((LimelightRange.txMin< TX && TX < LimelightRange.txMax) & (LimelightRange.tyMin< TY && TY < LimelightRange.tyMax)) {
    
    System.out.println("Missile lock-on");
    return targetLock == true;

  } else {

    System.out.println("Still seeking target");
    return targetLock == false;
    }
 }

 

 public boolean targetLocked(double TX)  {  


   if (TX < 5) {
     return targetLock == true;

   } else {
     
    return targetLock == false;}

   } // end of if conditional

 } // end of class
