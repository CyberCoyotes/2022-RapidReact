package frc.robot.commands.launcher;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;

public class setLaunchSpeed extends CommandBase {
    
    // The subsystem the command runs on
    private final Launcher subsystem;
    private double speedBack; // Does this need to be seperated into Top and Bottom?
    private double speedFront;

    public setLaunchSpeed(Launcher subsystem, double speedFront, double speedBack) {
      this.subsystem = subsystem;
      this.speedFront = speedFront;
      this.speedBack = speedBack;
      addRequirements(subsystem); // clever way to call in case you change the name of the subsystem
    }
  
    @Override
    public void initialize() {
      subsystem.setLauncherSpeed(speedFront, speedBack);
    }
  
    @Override
    public void end(boolean interrupted){ 
      subsystem.stopLauncher();
    }
    
    
  } // end class