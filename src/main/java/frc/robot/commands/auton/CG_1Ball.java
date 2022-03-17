// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.LaunchPre;
import frc.robot.commands.Launcher.LaunchTarmac;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Limelight;


/** Autonomous place holder for testing purposes
 * Runs the Launcher for second, then runs the launcher again along with index motors with index, launcher, intake, index
*/ 
public class CG_1Ball extends SequentialCommandGroup {
    
    public CG_1Ball(Index indexMotors, Intake intakeMotor, Launcher launcher) {
      addCommands(
      // Start the Launcher - speedFront is first double, speedBack is second
        new LaunchPre(launcher).withTimeout(0.75),
          new SequentialCommandGroup(
          // Maintain Launcher speed
            new LaunchPre(launcher),
            // ORIGINAL new LaunchSpeed(launcher, 0.30, 0.35).withTimeout(0.25).alongWith( 
            // Index the ball #1 into the running Launcher
            new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)), 
                         
            new ParallelCommandGroup (
              // Maintain Launcher speed
              new LaunchTarmac(launcher),
              // Intake ball #2 if needed
              new IntakeSpeed(intakeMotor, 0.5),
              // Index ball #2 into already running Launcher
              new IndexSpeed(indexMotors, 0.5)
              )); // end of add commands
}    
    
  } // end class

  /** 
  new SequentialCommandGroup(
    // Runs Launch Motors withTimeout of 0.75 seconds to get up to speed of high hoop launch sequence
    // First attempt was 0.35, 0.40
    new LauncherSpeed(launcher, 0.30, 0.35).withTimeout(0.75), 
      new SequentialCommandGroup(
        //runs Launcher & Index motors to launch ball out to score high hoop
        new LauncherSpeed(launcher, 0.30, 0.35).withTimeout(0.25).alongWith( // First attempt was 0.35, 40
        new IndexSpeed(indexMotors, 0.5).withTimeout(0.25)),
            new ParallelCommandGroup (
              // Runs Launcher, Intake, Index and Drive robot all at once to grab second ball
              // Runs all 3 Launcher, Intake & Index motors withTimout of 5 seconds.
              // Also make robot drive path from step 1
              
              // First attempt was 0.36, 0.42
              new LauncherSpeed(launcher, 0.40, 0.45).withTimeout(5), 
              new IntakeSpeed(intakeMotor, 0.5).withTimeout(5),
              new IndexSpeed(indexMotors, 0.5).withTimeout(5),
              new InstantCommand(() //Move this command out of Parallel group if this breaks things
                -> m_drivetrainSubsystem.resetOdometry(m_path.getInitialPose())),
                swerveControllerCommand).andThen(
                //end of command - stop robot
                new InstantCommand(() 
                    -> m_drivetrainSubsystem.stop())))
  ));
  */