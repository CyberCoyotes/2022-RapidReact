// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.launcher.LaunchAutonBall2;
import frc.robot.commands.launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 2 Ball
 *   
 *   This command shoots the pre-loaded cargo ball, then robot drives out of the tarmac and picks
 *   up a 2nd ball and shoots the seconds cargo ball as well
*/

public class Ball2Auton extends SequentialCommandGroup {
  public Ball2Auton(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      new Ball1Auton(indexMotors, intakeMotor, launcher, drivetrain),
      // Launches Ball 2 
      new PreLaunch(launcher).withTimeout(0.5),
      new LaunchAutonBall2(launcher).withTimeout(0.5).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25))
    ); // End of addCommands
  }
}
