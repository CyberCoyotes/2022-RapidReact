package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
/**
 * A command that locks the wheels into an "X" shape, to make ramming the bot futile.
 */
public class XLaunch extends ParallelDeadlineGroup{

    public XLaunch(Command launchcommand, Command xmode) {
        
        super(launchcommand, xmode);
    }


    public XLaunch(Command deadline, Command[] commands) {
        super(deadline, commands);

    }



    
    
}
