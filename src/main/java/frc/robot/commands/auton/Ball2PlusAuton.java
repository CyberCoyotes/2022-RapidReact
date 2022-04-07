// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.TurnToDegrees;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
// import frc.robot.Limelight;


/**  Auton file name for Auton Chooser = 2 Ball + Pickup 3rd Turned RIGHT
 *   
 *   This command shoots the pre-loaded cargo ball, 
 *   then robot drives out of the tarmac and picks up and shoots a 2nd cargo ball,
 *   then robot turns right and drives to pickup a 3rd cargo ball
*/


public class Ball2PlusAuton extends SequentialCommandGroup {
  public Ball2PlusAuton(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      new Ball2DriveStraightAuton(drivetrain, indexMotors, intakeMotor, launcher),
      // Back up to Goal
       new ParallelDeadlineGroup(
        new WaitCommand(0.5),
        new DriveCommand(drivetrain, () -> {return -1.0;}, () -> {return 0.0;}, () -> {return 0.0;})),

      // Turn right 90 degrees
       // TODO Confirm experimentally that values are correct
      new TurnToDegrees(drivetrain, -90), // was -90

      // Turn on Intake and Drive towards ball 3
      new ParallelDeadlineGroup(
        new WaitCommand(3.25),
        new IntakeSpeed(intakeMotor, 0.5),
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return -1.0;}, () -> {return 0.0;})),

      // Turn Launcher towards the goal
      // TODO Confirm experimentally that values are correct
      new TurnToDegrees(drivetrain, 45)

      // Back up to Goal - commenting out for now 
      // new ParallelDeadlineGroup(
         // new WaitCommand(0.35),
         // new DriveCommand(drivetrain, () -> {return -1.0;}, () -> {return 0.0;}, () -> {return 0.0;}))

   ); //end of addCommands
  }
}
