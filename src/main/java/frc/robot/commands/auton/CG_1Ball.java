// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** Autonomous place holder for testing purposes
 * Runs the Launcher for second, then runs the launcher again along with index motors with index, launcher, intake, index
*/ 
public class CG_1Ball extends SequentialCommandGroup {
    
    public CG_1Ball(Index indexMotors, Intake intakeMotor, Launcher launcher) {
      addCommands(
      // Start the Launcher - speedFront is first double, speedBack is second
        new LaunchSpeed(launcher, 0.30, 0.35).withTimeout(0.75),
          new SequentialCommandGroup(
          // Maintain Launcher speed
            new LaunchSpeed(launcher, 0.30, 0.35).withTimeout(0.25).alongWith( 
            // Index the ball #1 into the running Launcher
            new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)), 
                         
            new ParallelCommandGroup (
              // Maintain Launcher speed
              new LaunchSpeed(launcher, 0.36, 0.42),
              // Intake ball #2 if needed
              new IntakeSpeed(intakeMotor, 0.5),
              // Index ball #2 into already running Launcher
              new IndexSpeed(indexMotors, 0.5)
              ))); // end of add commands
}    
    
  } // end class