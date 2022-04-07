// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.TurnToDegrees;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.Launcher.LaunchAutonBall3;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 3 Ball
 *   
 *   This command shoots the pre-loaded cargo ball, 
 *   then robot drives out of the tarmac and picks up and shoots a 2nd cargo ball,
 *   then robot turns right and drives to picks up and shoots a 3rd cargo ball
*/

public class Ball3Auton extends SequentialCommandGroup {
  public Ball3Auton(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      // Two ball auton, pick up third, and lined up for third shot
      new Ball2PlusAuton(drivetrain, indexMotors, intakeMotor, launcher),
      // Launches ball 3
      new PreLaunch(launcher).withTimeout(0.75),
      new LaunchAutonBall3(launcher).withTimeout(0.75).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)),
      
      // After shot, turn robot correct orientation and reset gyro automatically in auton
      new TurnToDegrees(drivetrain, 90) // was 180
      // FIXME Add command to reset gyro
      // new Drivetrain.zeroGyroscope(drivetrain)
      ); // End of commands
  }

}
