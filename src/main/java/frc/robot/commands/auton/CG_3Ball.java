// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** FRC 3603
*** Goal: launch cargo from inside the tarmac and drive forward a set time
**/ 

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.Launcher.LaunchAutomatic;
// import frc.robot.commands.Launcher.LaunchExtended;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
import frc.robot.Limelight;

public class CG_3Ball extends SequentialCommandGroup {
  public CG_3Ball(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher, Limelight limelight) {

    addCommands(
      new CG_2BallPLUS(drivetrain, indexMotors, intakeMotor, launcher),
      // Turn left 90 degrees
      new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return -0.7;}).withTimeout(1), // FIXME Use gyro to turn to degrees

      // Drive Backwards
      new DriveCommand(drivetrain, () -> {return -0.7;}, () -> {return 0.0;}, () -> {return 0.0;}).withTimeout(1),
      
      // Launch cargo
      new LaunchAutomatic(launcher, limelight)
      //, new LaunchExtended(launcher) // FIXME remove after LaunchAutomatic tested and confirmed
      );
  }
}
