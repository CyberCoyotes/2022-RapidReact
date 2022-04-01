// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TargetStatus {
  // https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html

  static boolean targetStatus = false;
  NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-back");//Instantiate the tables
  double tx = limelight.getEntry("tx").getDouble(0.0);
  
public static boolean getTargetStatus(double tx) {
  if (tx < 5) {
    System.out.println("Missile lock-on");
    targetStatus = true; // Don't know if this is needed if call 'getTargetStatus'
    return true;
  } else {
    System.out.println("Still seeking target");
    targetStatus = false; // Don't know if this is needed if call 'getTargetStatus'
    return false;}
 }

 

 public void targetLocked()  {  


   if (tx < 5) {
     targetStatus = true;
   } else {
    targetStatus = false;}

   } // end of if conditional

 } // end of class
