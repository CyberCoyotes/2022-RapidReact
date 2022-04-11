package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class xmode_v2 extends CommandBase {
  
    private final Drivetrain subsystem;

    public xmode_v2(Drivetrain m_drivetrain) {
        this.subsystem = m_drivetrain;
        addRequirements(subsystem);
    }
    
   // Approach with this version was to define "setDefenseDrivetrain()" in the drivetrain subsystem
   // Then call it with this command 
   // This approach is used with different Launcher settings
    @Override
    public void execute() {
        //sets the modules to unmoving, "x" shape
        //-45 45 45 -45 was circle mode
        //45 -45 -45 45 is good
        //when we set the mod states, it gets caught in a process of trying to maintain the config, never stopping. PID I term? dont think its trying to drive, jsut turn 
        subsystem.setDefenseDrivetrain();
    }

    @Override
    public boolean isFinished() {
      return false;
    }    
}