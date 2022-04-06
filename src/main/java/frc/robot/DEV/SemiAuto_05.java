/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DEV;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;


public class SemiAuto_05 extends CommandBase {
  private static boolean targetLock = false;

  static double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
  
  public static void targetStatus(Index indexMotors, Launcher launcher)  {  
      if (tx < 5) {
        targetLock = true;
        return ; 
      } // end of if conditional
      
    }

  public void semiAutoLaunch(Index indexMotors, Launcher launcher){
    
    if (targetLock == true){
      new IndexSpeed(indexMotors, 0.5);
      } else {
      new LaunchSpeed(launcher, 0.5, 0.5);}
    }
} // end of class