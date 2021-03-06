
package frc.robot.DEV;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LaunchHigh;
import frc.robot.commands.Launcher.PreLaunch;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
// import frc.robot.Limelight;

/** These values and sequence may not reflect the most recent
 * Hoping to replace the multi-command sequence in robot container with this 
 * It will allow easier recycing of launcer code for auton
 * */

public class Group1BallHighDEV extends SequentialCommandGroup {
    
    public Group1BallHighDEV(Launcher launcher, Intake intakeMotor, Index indexMotors) {
     addCommands(
       new PreLaunch(launcher).withTimeout(0.4),
       new SequentialCommandGroup(
         new LaunchHigh(launcher, indexMotors).withTimeout(0.25),
         new IndexSpeed(indexMotors, 0.5).withTimeout(0.4))
         ); // end of commands

    } // end of CG
    
  } // end class