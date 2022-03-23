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

public class SemiAutomaticLaunch extends SequentialCommandGroup{
    /* This proof of concept is to have the driver manually drive to the correct distance from the goal
     * ty = {10, 14} range 
     * and correctly align left-to-right
     * tx = {-5, 5}
     * When those conditions are met the launcher will launch a cargo balls
     * at our high goal output values (speedFront = 0.40, speedBack = 0.45)
     * The full launch sequence command is "Group2BallsHigh"
     */ 

    // FIXME I know syntax isn't correct

    public static double launchingSolution(Launcher launch) {
            // FIXME command to launch 2 balls when conditions are met. 
            // I'm home with a sick toddler and sleep deprived. Reasoning skills diminished & subpar
        addCommands(
           new TargetStatus(),
           new LaunchBall2(launch));
        }
} // end of class