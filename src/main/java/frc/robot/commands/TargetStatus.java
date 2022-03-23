/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CommandGroups.Group2BallsHigh;
import frc.robot.commands.Launcher.LaunchBall2;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;

public class TargetStatus {
    /* This proof of concept is to have the driver manually drive to the correct distance from the goal
     * ty = {10, 14} range 
     * and correctly align left-to-right
     * tx = {-5, 5}
     * When those conditions are met the launcher will launch a cargo balls
     * at our high goal output values (speedFront = 0.40, speedBack = 0.45)
     * The full launch sequence command is "Group2BallsHigh"
     */ 

    // FIXME I know syntax isn't correct    
    // Target Lock default should be false, i.e. not shoot cargo
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tx = table.getEntry("tx");

    public static boolean targetStatus(double ty, double tx)  {  
      if ((10 < ty || ty < 14) & (-5 < tx || tx <5)) {
        return true;
            
        SmartDashboard.getNumber("Limelight x", tx);
        SmartDashboard.putNumber("Limelight y", ty);
        SmartDashboard.putBoolean("Target Status", targetStatus);
        } // end of if conditional
    }
} // end of class