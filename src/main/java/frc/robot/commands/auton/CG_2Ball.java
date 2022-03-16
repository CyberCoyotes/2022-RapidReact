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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LauncherSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

public class CG_2Ball extends SequentialCommandGroup {
  /** Creates a new Drive2Seconds. */
  public CG_2Ball(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      new CG_1BallPLUS(drivetrain, indexMotors, intakeMotor, launcher),
      new ParallelDeadlineGroup(
        new SequentialCommandGroup(
          new WaitCommand(0.75),
          new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)
        ),
        new LauncherSpeed(launcher, 0.30, 0.60) // FIXME check values
      )
    ); // End of addCommands
  }
}
