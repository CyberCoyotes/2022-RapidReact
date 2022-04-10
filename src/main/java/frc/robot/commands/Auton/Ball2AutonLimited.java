// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LaunchAutonBall2;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 2 Ball
 *   
 *   This command shoots the pre-loaded cargo ball, then robot drives out of the tarmac and picks
 *   up a 2nd ball and shoots the seconds cargo ball, and then drives further away from hub/tarmac
*/

public class Ball2AutonLimited extends SequentialCommandGroup {
  public Ball2AutonLimited(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      /** Shoots preloaded ball from inside the tarmac
       *  Starts drivetrain and subsystems to collect the second cargo
      */
      new Ball1Auton(indexMotors, intakeMotor, launcher, drivetrain),
      // Launches second cargo
      new PreLaunch(launcher).withTimeout(0.75),
      new LaunchAutonBall2(launcher).withTimeout(0.75).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)),

      // Drive further away from goal after launch
      new ParallelDeadlineGroup(
        new WaitCommand(0.6), // Changed from 1.2 to .6
        new DriveCommand(drivetrain, () -> {return 2.0;}, () -> {return 0.0;}, () -> {return 0.0;})) // Changed from 1.0 to 2.0  
    ); // End of addCommands
  } // end of sequential group
} //end of file
