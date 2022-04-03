/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Launcher;

import java.util.Set;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Launcher;

public class SemiAuto_01_2 implements Command {
    /* This proof of concept is to have the driver manually drive to the correct distance from the goal
     * ty = {10, 14} range 
     * and correctly align left-to-right
     * tx = {-5, 5}
     * When those conditions are met the launcher will launch a cargo balls
     * at our high goal output values (speedFront = 0.40, speedBack = 0.45)
     * The full launch sequence command is "Group2BallsHigh"
     */ 

    // Target Lock default should be false, i.e. not shoot cargo
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tx = table.getEntry("tx");
    boolean targetLock = false;

    public boolean targetStatus(double ty, double tx, Launcher launch)  {  
      // if targetLock = true, then run the LaunchBall2 command
      /* Consider adding conditional that angular velocity and robot veloctiy are sufficiently low
      * so robot momententum doesn't become an issue
      * if ((10 < ty || ty < 14) & (-5 < tx || tx <5) & (xSpeed < 0.5) & (ySpeed < 0.5) & (rotationSpeed < 0.5))
      */

      if ((10 < ty || ty < 14) & (-5 < tx || tx <5)) {
        targetLock = true;
        new LaunchBall2(launch);
        return targetLock; 
      } // end of if conditional
      else{
        return false;
      } // end of else

    // Move these to RobotContainer ?
      // SmartDashboard.putNumber("Limelight x", tx);
      // SmartDashboard.putNumber("Limelight y", ty);
    }



	@Override
	public Set<Subsystem> getRequirements() {
		return null;
	}
} // end of class