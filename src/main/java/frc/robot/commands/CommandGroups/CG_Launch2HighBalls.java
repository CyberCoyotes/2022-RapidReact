package frc.robot.commands.CommandGroups;
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

public class CG_Launch2HighBalls extends SequentialCommandGroup {
    
    public CG_Launch2HighBalls(Launcher launcher, Intake intakeMotor, Index indexMotors) {
     addCommands(
      new LaunchHigh(launcher),
      new IndexSpeed(indexMotors, 0.5),
      new IntakeSpeed(intakeMotor, 0.5),
      new LaunchHigh(launcher),
      new IndexSpeed(indexMotors, 0.5)
      ); // end of add commands
    }
    
  } // end class