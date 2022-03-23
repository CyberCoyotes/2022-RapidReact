// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

/**  Auton file name for Auton Chooser = 1 Ball & Pickup 2nd
 *   
 *   This command shoots the pre-loaded cargo ball, then robot drives out of the tarmac, picks
 *   up a second ball and stops
*/

public class Ball1PlusAuton extends SequentialCommandGroup{
    public Ball1PlusAuton(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher){

        addCommands(
            new Ball1Auton(indexMotors, intakeMotor, launcher),
            // Start the drivetrain
            new ParallelDeadlineGroup(
              new WaitCommand(1.1),  
              new IntakeSpeed(intakeMotor, 0.5),
              new DriveCommand(drivetrain, () -> {return 1.0;}, () -> {return 0.0;}, () -> {return 0.0;})),
              new IntakeSpeed(intakeMotor, 0.5).withTimeout(1)

        ); // End of addCommands
      }; 
} // End of class