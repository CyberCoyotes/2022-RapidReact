/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.dev;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.setLaunchSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;


public class SemiAuto_01_1 extends CommandBase {
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

    double tx = 2;

    public void targetStatus(Index indexMotors, Launcher launcher)  {  
      // if targetLock = true, then run the LaunchBall2 command
      /* Consider adding conditional that angular velocity and robot veloctiy are sufficiently low
      * so robot momententum doesn't become an issue
      * if ((10 < ty && ty < 14) & (-5 < tx && tx <5) & (xSpeed < 0.5) & (ySpeed < 0.5) & (rotationSpeed < 0.5))
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

    public SemiAuto_01_1(Index indexMotors, Launcher launcher){
      //if targetLock equals true, run the launch sequence
      if (targetLock){
          new IndexSpeed(indexMotors, 0.5);
         } else {
          new setLaunchSpeed(launcher, 0.5, 0.5);}
    }
} // end of class