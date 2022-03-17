// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** FRC 3603
* Goal: launch cargo from inside the tarmac and drive forward a set time
**/ 

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


public class CG_2BallPLUS_RIGHT extends SequentialCommandGroup {
  public CG_2BallPLUS_RIGHT(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      new CG_2Ball(drivetrain, indexMotors, intakeMotor, launcher),
      // Turn right 90 degrees
      new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return -1.0;}).withTimeout(2.25),

      // Turn on Intake and Drive forward
      new ParallelDeadlineGroup(
        new WaitCommand(3),
        new IntakeSpeed(intakeMotor, 0.5),
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return -0.7;}, () -> {return 0.0;})),

        // Turn Launcher towards the goal
        new ParallelDeadlineGroup(
          new WaitCommand(2),
          new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return 1.0;})),

        // Back up to Goal
        new ParallelDeadlineGroup(
          new WaitCommand(0.5),
          new DriveCommand(drivetrain, () -> {return -0.7;}, () -> {return 0.0;}, () -> {return 0.0;}))
    ); //end of addCommands
  }
}