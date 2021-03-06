package frc.robot.commands.CommandGroups;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.Launcher.setLaunchSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;

/** These values and sequence may not reflect the most recent
 * Hoping to replace the multi-command sequence in robot container with this 
 * It will allow easier recycing of launcer code for auton
 * */

public class GroupHighGoalX extends SequentialCommandGroup {

  // Launches a ball from split-the-tape position
  public GroupHighGoalX(Launcher launcher, Intake intakeMotor, Index indexMotors, Drivetrain m_drivetrain) {
     addCommands(
      // Prelaunch: new setLaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.75),
      // new InstantCommand(()-> m_drivetrain.setXstance().withTimeout(0.1), m_drivetrain),
      new setLaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.75),
      // Launch Ball 1
      new SequentialCommandGroup(
        // setHighGoal: new setLaunchSpeed(launcher, 0.40,0.45).withTimeout(0.25),
        new setLaunchSpeed(launcher, 0.40, 0.45).withTimeout(0.25),
        // Index the ball: new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
        new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
      // Launches Ball 2 from split-the-tape position
      new ParallelCommandGroup (
        new setLaunchSpeed(launcher, 0.36, 0.42),
        new IntakeSpeed(intakeMotor, 0.5),
        new IndexSpeed(indexMotors, 0.5))
      ); // end of add commands
    }
  } // end class