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
      new Ball2Auton(drivetrain, indexMotors, intakeMotor, launcher),
      // Back up towards hub
       new ParallelDeadlineGroup(
        new WaitCommand(0.55), // 0.5 -> 0.25
        new DriveCommand(drivetrain, () -> {return -1.0;}, () -> {return 0.0;}, () -> {return 0.0;})), // 1.0 -> 2.0

      // Do a "backup turn" to the right towards ball 3
      new TurnToDegrees(drivetrain, -70), // was -90. Close enough pre-event

      // Drive towards ball 3 with intake running
      new ParallelDeadlineGroup(
        new WaitCommand(1.7), // 3.25 -> 1.625
        new IntakeSpeed(intakeMotor, 0.5),
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return -2.0;}, () -> {return 0.0;})), // 1.0 -> 2.00
        
        new IntakeSpeed(intakeMotor, 0.5).withTimeout(1),

      // Launch Autononmous Ball 3
      new TurnToDegrees(drivetrain, -54), // -49 overturned, changed to -54     // Launches ball 3
      new PreLaunch(launcher).withTimeout(0.75),
      new LaunchAutonBall3(launcher).withTimeout(0.75).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)),

      //Turn robot towards goal, i.e. "Forward" field orientation, and reset gyro in preperation for auton
      new SequentialCommandGroup(
        new WaitCommand(0.5), // Change from 1 to 0.5
        new TurnToDegrees(drivetrain, 130),
        new ResetGyro(drivetrain))        
        ); // End of commands
  }

}
