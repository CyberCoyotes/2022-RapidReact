// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DEV;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Auton.Ball1Auton;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 1 Ball & Pickup 2nd
 *   
 *   This command shoots the pre-loaded cargo ball, then robot drives out of the tarmac, picks
 *   up a second ball and stops
*/

public class Ball1AutonPlusDEV extends SequentialCommandGroup{
    public Ball1AutonPlusDEV(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher){

        addCommands(
          // Shoots preloaded ball from inside the tarmac  
          new Ball1Auton(indexMotors, intakeMotor, launcher, drivetrain),
          // Start the drivetrains and subsystems to get second cargo
          new ParallelDeadlineGroup(
            new WaitCommand(0.55), // Changed from 1.1 to 0.55
            new IntakeSpeed(intakeMotor, 0.5),
            new DriveCommand(drivetrain, () -> {return 2.0;}, () -> {return 0.0;}, () -> {return 0.0;})), // Changed from 1.0 to 2.0
            new IntakeSpeed(intakeMotor, 0.5).withTimeout(1)

        ); // End of addCommands
      }; 
} // End of class