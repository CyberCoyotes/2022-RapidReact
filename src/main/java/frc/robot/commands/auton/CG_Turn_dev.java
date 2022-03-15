// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** FRC 3603
* Goal: launch cargo from inside the tarmac and drive forward a set time
**/ 

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.LauncherSpeed;
import frc.robot.commands.PathPlannerBased.DriveV;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CG_Turn_dev extends SequentialCommandGroup {
  public CG_Turn_dev(Drivetrain drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {

    addCommands(
      // Start the Launcher - speedFront is first double, speedBack is second
      new LauncherSpeed(launcher, 0.30, 0.35).withTimeout(0.75),
      
      new SequentialCommandGroup(
      /** Hoping to remove the above command at some point by switching to velocity based launcher rather than PercentOutput
       * Logic would be something like; when Launcher velocity >= (mininum) then run IndexMotors
       * Super fancy? add a PID? to maintain that launcher velocity while then intake and/or indexing the second ball
       */
      // Maintain Launcher speed
        new LauncherSpeed(launcher, 0.30, 0.35).withTimeout(0.25).alongWith( 
        // Index the first ball into the running Launcher
          new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)),              
            new ParallelDeadlineGroup(
              // Wait command will stop the paralleldeadlinegroup
              // Other conditions could subsituted for time to make the group stop
              // 4 seconds of drivetime at 0.70 equates to 105 inches of drive movement
              new WaitCommand(3),
              // Start the drivetrain
              new DriveCommand(
                drivetrain,
                // Trying to pass a translationXSupplier, translationYSupplier, and rotationalSupplier
                () -> {return 0.7;}, //Forwards speed
                () -> {return 0.0;}, //Left speed
                () -> {return 0.0;}), //Turn speed
              new LauncherSpeed(launcher, 0.36, 0.42),
              // Intake ball #2 if needed
              new IntakeSpeed(intakeMotor, 0.5),
              // Index ball #2 into already running Launcher
              new IndexSpeed(indexMotors, 0.5)
              ) //end of addCommands
              // TODO See ISSUE #87. Get robot to turn approximately the correct number of degrees. 
              
              // Add gyroreset for assisting the driver going into teleop
               
              )); // end of ParallelDeadlineGroup

  //code is doing something, specifically the ))); part just above
  }
}