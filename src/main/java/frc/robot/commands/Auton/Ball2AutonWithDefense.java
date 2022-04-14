// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ResetGyro;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.TurnToDegrees;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.Launcher.LaunchLow;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 2 Ball + Defense
 *   
 *   This command shoots the pre-loaded cargo ball, 
 *   then robot drives out of the tarmac and picks up and shoots a 2nd cargo ball,
 *   then robot turns right and drives to picks up the opposite alliance's ball,
 *   robot turns 180 degrees, and shoots the opposite alliance's ball into the corner 
 * 
*/

public class Ball2AutonWithDefense extends SequentialCommandGroup {
  public Ball2AutonWithDefense(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      // Two ball auton, pick up third, and lined up for third shot
      new Ball2Auton(drivetrain, indexMotors, intakeMotor, launcher),

      // Do a "backup turn" to the right towards opposite team ball
      new TurnToDegrees(drivetrain, -90), // was -90. Close enough pre-event

      // Drive towards opposite team ball with intake running
      new ParallelDeadlineGroup(
          new WaitCommand(0.60), // Changed from 1.1 to 0.60
          new IntakeSpeed(intakeMotor, 0.6),
          new DriveCommand(drivetrain, () -> {return 2.0;}, () -> {return 0.0;}, () -> {return 0.0;})), // Changed from 1.0 to 2.0
          new IntakeSpeed(intakeMotor, 0.6).withTimeout(1),

      //Turn robot towards goal, i.e. "Forward" field orientation, and reset gyro in preperation for teleop
      new SequentialCommandGroup(
        new WaitCommand(0.5), // Change from 1 to 0.5
        new TurnToDegrees(drivetrain, 130),
        new ResetGyro(drivetrain)),
        
      // Launch other alliance's ball into corner
      new PreLaunch(launcher).withTimeout(0.75),
      new LaunchLow(launcher).withTimeout(0.75).alongWith(new IndexSpeed(indexMotors, 0.6).withTimeout(0.25))

        ); // End of commands
  }

}
