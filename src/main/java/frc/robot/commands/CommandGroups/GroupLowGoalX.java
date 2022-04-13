package frc.robot.commands.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Launcher;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.launcher.setLaunchSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
/**
 * Do not use: further testing required for XMode alone before anything can be used.
 */
public class GroupLowGoalX extends SequentialCommandGroup {
/**
 * Xmode Combined with the button-bound launch commnad; lasts for a set period of time.
 * @param m_drivetrain
 * @param indexMotors
 * @param intakeMotor
 * @param launcher
 */
  public GroupLowGoalX(Launcher launcher, Intake intakeMotor, Index indexMotors, Drivetrain m_drivetrain) {
      
      addCommands(
      new setLaunchSpeed(launcher, 0.20, 0.20).withTimeout(0.75),
      
      // Launch Ball 1
      new SequentialCommandGroup(
        new setLaunchSpeed(launcher, 0.20, 0.25).withTimeout(0.25).alongWith(
        new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),

      // Launches Ball 2 from up close
      new ParallelCommandGroup (
        new setLaunchSpeed(launcher, 0.36, 0.42),
        new IntakeSpeed(intakeMotor, 0.5),
        new IndexSpeed(indexMotors, 0.5)))
        );
  }
} // End class
