package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Launcher;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
/**
 * Do not use: further testing required for XMode alone before anything can be used.
 */
public class XLaunch extends SequentialCommandGroup{
/**
 * Xmode Combined with the button-bound launch commnad; lasts for a set period of time.
 * @param m_drivetrain
 * @param indexMotors
 * @param intakeMotor
 * @param launcher
 */
  public XLaunch(Drivetrain m_drivetrain, Index indexMotors, Intake intakeMotor, Launcher launcher) {
      addRequirements(m_drivetrain, indexMotors);
      
      addCommands( //for 2.05 seconds, run the launchers and xmode, giving the robot resistance to obstrusive pests.
        new ParallelDeadlineGroup(
          new WaitCommand(2.05),
          new SequentialCommandGroup(
        new LaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.75),
          new SequentialCommandGroup(
            new LaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.25).alongWith(
              new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)), // shortened between launches, good timing
                new ParallelCommandGroup (
                  new LaunchSpeed(launcher, 0.36, 0.42),
                  new IntakeSpeed(intakeMotor, 0.5),
                  new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)) // FIXME added to stop Index, not stopping still
      )), new xmode(m_drivetrain))
      );
  }
  
    
    
}
