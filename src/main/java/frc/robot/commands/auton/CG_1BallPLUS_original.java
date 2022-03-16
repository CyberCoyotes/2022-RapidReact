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
import frc.robot.commands.Launcher.LaunchTarmac;

// import frc.robot.Limelight;

public class CG_1BallPLUS_original extends SequentialCommandGroup{
    public CG_1BallPLUS_original(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher){

        addCommands(
            // Start the Launcher - speedFront is first double, speedBack is second
            new LaunchPre(launcher), // This is new since event 1
            new LaunchSpeed(launcher, 0.30, 0.35).withTimeout(0.50),
              // Index the ball #1 into the running Launcher
                new IndexSpeed(indexMotors, 0.5).withTimeout(0.5), 
                  new ParallelDeadlineGroup(
                    new WaitCommand(4),
                    // Maintain Launcher speed
                    new LaunchTarmac(launcher), // This is new since event 1
                    // Intake ball #2 if needed
                    new IntakeSpeed(intakeMotor, 0.5),
                    // Index ball #2 into already running Launcher
                    new IndexSpeed(indexMotors, 0.5),
      
                    // Start the drivetrain
                    new ParallelDeadlineGroup(
                      // Wait command will stop the paralleldeadlinegroup
                      // Other conditions could subsituted for time to make the group stop
                      // 4 seconds of drivetime at 0.70 equates to 105 inches of x,y movement
                      new WaitCommand(3), // 4 seconds drives a little too far
                      new DriveCommand(drivetrain, () -> {return 0.7;}, () -> {return 0.0;}, () -> {return 0.0;}))
                      ) // end of Drive ParallelDeadlineGroup
                      // Launch commands for second Cargo
      
                      // Second set of drive commands
      
            ); //end of addCommands
    }
}