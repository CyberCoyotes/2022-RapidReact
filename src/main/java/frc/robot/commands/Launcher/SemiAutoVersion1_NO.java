/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.IndexSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;


public class SemiAutoVersion1_NO extends CommandBase {
    /* This proof of concept is to have the driver manually drive to the correct distance from the goal
     * ty = {10, 14} range 
     * and correctly align left-to-right
     * tx = {-5, 5}
     * When those conditions are met the launcher will launch a cargo balls
     * at our high goal output values (speedFront = 0.40, speedBack = 0.45)
     * The full launch sequence command is "Group2BallsHigh"
     */ 

    // Target Lock default should be false, i.e. not shoot cargo
    boolean targetLock = false;

    // https://docs.limelightvision.io/en/latest/cs_drive_to_goal_2019.html
    // double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
    // double ty = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("ty").getDouble(0);
    double tx = 2;
    // Not sure if the values would come from "private final SwerveModule m_frontLeftModule" states or something else;
    double xSpeed; // Get current speed or velocity of the robot
    double ySpeed; // Get current speed or velocity of the robot
    double rotationSpeed; // Get the current rotational speed or angular velocity of the robot



    public void targetStatus(Index indexMotors, Launcher launcher)  {  
      // if targetLock = true, then run the LaunchBall2 command
      /* Consider adding conditional that angular velocity and robot veloctiy are sufficiently low
      * so robot momententum doesn't become an issue
      * if ((10 < ty || ty < 14) & (-5 < tx || tx <5) & (xSpeed < 0.5) & (ySpeed < 0.5) & (rotationSpeed < 0.5))
      */
     
      // SmartDashboard.putBoolean("Target Status", targetLock);
      // SmartDashboard.putNumber("LimeLight X", tx);
      // SmartDashboard.putNumber("LimeLight Y", ty);

      if (targetLock) {
        //set targetLock to true when tx & ty are within the parameters
        targetLock = true;
        return; 
      } // end of if conditional
      
    }

    public SemiAutoVersion1_NO(Index indexMotors, Launcher launcher){
      //if targetLock equals true, run the launch sequence
      if (targetLock){
          new IndexSpeed(indexMotors, 0.5);
         } else {
          new LaunchSpeed(launcher, 0.5, 0.5);}
    }
} // end of class