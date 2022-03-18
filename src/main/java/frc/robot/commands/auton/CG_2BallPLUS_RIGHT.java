// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IntakeSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
// import frc.robot.Limelight;


/**  Auton file name for Auton Chooser = 2 Ball + Pickup 3rd Turned RIGHT
 *   
 *   This command shoots the pre-loaded cargo ball, 
 *   then robot drives out of the tarmac and picks up and shoots a 2nd cargo ball,
 *   then robot turns right and drives to pickup a 3rd cargo ball
*/


public class CG_2BallPLUS_RIGHT extends SequentialCommandGroup {
  public CG_2BallPLUS_RIGHT(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      new CG_2Ball(drivetrain, indexMotors, intakeMotor, launcher),

       // Back up to Goal
       new ParallelDeadlineGroup(
        new WaitCommand(0.75),
        new DriveCommand(drivetrain, () -> {return -0.7;}, () -> {return 0.0;}, () -> {return 0.0;})),

      // Turn right 90 degrees
      new ParallelDeadlineGroup(
        new WaitCommand(1.85),
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return -1.0;})),

      // Turn on Intake and Drive towards ball 3
      new ParallelDeadlineGroup(
        new WaitCommand(5),
        new IntakeSpeed(intakeMotor, 0.5),
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return -0.7;}, () -> {return 0.0;})),

        // Turn Launcher towards the goal
        new ParallelDeadlineGroup(
          new WaitCommand(0.75),
          new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return 1.0;}))

        // Back up to Goal - commenting out for now 
        //new ParallelDeadlineGroup(
          //new WaitCommand(0.5),
          //new DriveCommand(drivetrain, () -> {return -0.7;}, () -> {return 0.0;}, () -> {return 0.0;}))

   ); //end of addCommands
  }
}
