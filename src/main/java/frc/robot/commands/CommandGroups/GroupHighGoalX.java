package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Launcher;
import frc.robot.commands.Launcher.xmode;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
/**
 * Do not use: further testing required for XMode alone before anything can be used.
 */
public class GroupHighGoalX extends ParallelCommandGroup {
/**
 * Xmode Combined with the button-bound launch commnad; lasts for a set period of time.
 * @param m_drivetrain
 * @param indexMotors
 * @param intakeMotor
 * @param launcher
 */
  public GroupHighGoalX(Launcher launcher, Intake intakeMotor, Index indexMotors, Drivetrain m_drivetrain) {
      
      addCommands( 
        new ParallelCommandGroup (
          new GroupHighGoal(launcher, intakeMotor, indexMotors),
          // Aligns wheels into x pattern so robot isn't pushed around
          new xmode(m_drivetrain))
        );
  }
} // End class