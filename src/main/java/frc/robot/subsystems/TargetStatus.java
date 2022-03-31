// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

/** Add your docs here. */
public class TargetStatus {
 // https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html

 boolean targetStatus = false;
 double tx = 2;


 public void targetLocked()  {  


   if (tx < 5) {
     targetStatus = true;
   } else {
    targetStatus = false;}

   } // end of if conditional

 } // end of class
