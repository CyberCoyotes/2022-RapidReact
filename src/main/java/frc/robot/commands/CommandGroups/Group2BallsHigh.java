package frc.robot.commands.CommandGroups;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.LaunchHigh;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
// import frc.robot.Limelight;


/** These values and sequence may not reflect the most recent
 * Hoping to replace the multi-command sequence in robot container with this 
 * It will allow easier recycing of launcer code for auton
 * */

public class Group2BallsHigh extends SequentialCommandGroup {
    
    public Group2BallsHigh(Launcher launcher, Intake intakeMotor, Index indexMotors) {
     addCommands(
      // Launches Ball 1 from split-the-tape position
      new Group1BallHigh(launcher, intakeMotor, indexMotors),
      // Revs wheels for Ball 2
      new LaunchHigh(launcher).withTimeout(0.4), 
      // Launches Ball 2 from split-the-tape position
        new ParallelCommandGroup (
          new LaunchHigh(launcher),
          new IntakeSpeed(intakeMotor, 0.5),
          new IndexSpeed(indexMotors, 0.5))
      ); // end of add commands
    }
  } // end class