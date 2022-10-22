// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;
import frc.robot.subsystems.Intake;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.LaunchTarmac;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Limelight;
import edu.wpi.first.wpilibj2.command.WaitCommand;


/**  Auton file name for Auton Chooser = 1 Ball & Stay
 *   
 *   This command shoots the pre-loaded cargo ball and robot stays put inside of the tarmac
*/ 

public class Ball1Auton extends SequentialCommandGroup {
    
    public Ball1Auton(Index indexMotors, Intake intakeMotor, Launcher launcher, Drivetrain drivetrain) {
      addCommands(
        // Launches the Ball 1 (preloaded)
        new PreLaunch(launcher).withTimeout(0.75),
        new SequentialCommandGroup(
          new LaunchTarmac(launcher).withTimeout(0.25).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25))),
          
        // Moves out of tarmac and intakes Ball 2
        new SequentialCommandGroup(
        new ParallelDeadlineGroup(
          new WaitCommand(0.85), // FIXME changed from 0.6 to 0.7 at worlds. 0.7 still short. Trying 0.85
          new IntakeSpeed(intakeMotor, 0.6),
          //? the big baby
          new DriveCommand(drivetrain, () -> {return 2.0;}, () -> {return 0.0;}, () -> {return 0.0;})),
          
          
          
        //: until we figure out what is wrong with auton3, gonna try to do this to remove state
        
        new DriveCommand(drivetrain, () -> {return 0.0;}, () -> {return 0.0;}, () -> {return 0.0;}))) // 1.0 -> 2.0
        ;
    }
  }