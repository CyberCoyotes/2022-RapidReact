// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** FRC 3603
* Goal: launch cargo from inside the tarmac and drive forward a set time
**/ 

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.*;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
// import frc.robot.Limelight;

public class CG_1BallPLUS extends SequentialCommandGroup {
  public CG_1BallPLUS(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {
    addCommands(
      new ParallelDeadlineGroup( // FIXME This parallel group is still running (I think) and causing illegal operation for subsystems resources
        new WaitCommand(1.5), // added b/c of parallel group error, but still not working
        new LaunchPre(launcher),
        new SequentialCommandGroup(
          new WaitCommand(0.75),
          new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
          new LaunchSpeed(launcher, 0.30, 0.35)));

      new ParallelDeadlineGroup(
        new WaitCommand(3),
        new ParallelCommandGroup(
          new IntakeSpeed(intakeMotor, 0.5),
          // (+) Forward speed || (+) Strafe left speed || (+) Turn speed
          new DriveCommand(drivetrain, () -> {return 0.7;}, () -> {return 0.0;}, () -> {return 0.0;})));
      
  }
}