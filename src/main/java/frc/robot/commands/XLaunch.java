package frc.robot.commands;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
/**
 * Do not use: further testing required for XMode alone before anything can be used.
 */
public class XLaunch extends ParallelDeadlineGroup{

    public XLaunch(Command launchcommand, Command xmode) {
        
        super(launchcommand, xmode);
    }


    public XLaunch(Command deadline, Command[] commands) {
        super(deadline, commands);

    }



    
    
}
