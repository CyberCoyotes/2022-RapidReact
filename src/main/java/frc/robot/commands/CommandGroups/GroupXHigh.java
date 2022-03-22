package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Launcher;
import frc.robot.commands.Launcher.xmode;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
/**
 * Do not use: further testing required for XMode alone before anything can be used.
 */
public class GroupXHigh extends SequentialCommandGroup{
/**
 * Xmode Combined with the button-bound launch commnad; lasts for a set period of time.
 * @param m_drivetrain
 * @param indexMotors
 * @param intakeMotor
 * @param launcher
 */
  public GroupXHigh(Drivetrain m_drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {
      addRequirements(m_drivetrain, indexMotors);
      
      addCommands( 
        new Group2BallsHigh(launcher, intakeMotor, indexMotors),
        // Aligns wheels into x pattern so robot isn't pushed around
        new xmode(m_drivetrain) 
      ); // end of commands
  } // 
  
} // End class
