// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;
import frc.robot.subsystems.Intake;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LaunchTarmac;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Limelight;


/** Autonomous place holder for testing purposes
 * Runs the Launcher for second, then runs the launcher again along with index motors with index, launcher, intake, index
*/ 
public class CG_1Ball extends SequentialCommandGroup {
    
    public CG_1Ball(Index indexMotors, Intake intakeMotor, Launcher launcher) {
      addCommands(
        new PreLaunch(launcher).withTimeout(0.75),
        new SequentialCommandGroup(
          new LaunchTarmac(launcher).withTimeout(0.25).alongWith(new IndexSpeed(indexMotors, 0.5).withTimeout(0.25))));
    }
  }