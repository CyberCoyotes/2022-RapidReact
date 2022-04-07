package frc.robot.commands.CommandGroups;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.LaunchHigh;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.commands.Launcher.PreLaunch;
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
      new LaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.75),
      new SequentialCommandGroup(
        new LaunchHigh(launcher).withTimeout(0.25),
        new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
       // end of commands
      // Revs wheels for Ball 2
      // Launches Ball 2 from split-the-tape position
        new ParallelCommandGroup (
          new LaunchSpeed(launcher, 0.36, 0.42),
          new IntakeSpeed(intakeMotor, 0.5),
          new IndexSpeed(indexMotors, 0.5))
      ); // end of add commands
    }
  } // end class