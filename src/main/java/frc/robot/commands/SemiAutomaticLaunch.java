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
import frc.robot.commands.CommandGroups.Group2BallsHigh;
import frc.robot.commands.Launcher.LaunchBall2;

public class SemiAutomaticLaunch {
    /* This proof of concept is to have the driver manually drive to the correct distance from the goal
     * ty = {10, 14} range 
     * and correctly align left-to-right
     * tx = {-5, 5}
     * When those conditions are met the launcher will launch a cargo balls
     * at our high goal output values (speedFront = 0.40, speedBack = 0.45)
     * The full launch sequence command is "Group2BallsHigh"
     */ 

    // FIXME I know syntax isn't correct

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tx = table.getEntry("tx");

    private static boolean targetLock = false; // Target Lock default should be false, i.e. not shoot cargo

    public static double launchingSolution(double ty, double tx) {

        
        if ((10 < ty || ty < 14) & (-5 < tx || tx <5)) {
         targetLock = true;
            // FIXME command to launch 2 balls when conditions are met. 
            // I'm home with a sick toddler and sleep deprived. Reasoning skills diminished & subpar
            return new Group2BallsHigh(Launcher launcher, Intake intakeMotor, Index indexMotors);
            
        SmartDashboard.putNumber("Limelight x", tx);
        SmartDashboard.putNumber("Limelight y", ty);
        SmartDashboard.putBoolean("Target Status", targetLock);
        } // end of if conditional
    }
} // end of class