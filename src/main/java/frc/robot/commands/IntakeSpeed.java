package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeSpeed extends CommandBase {
  
    // The subsystem the command runs on
    private final Intake subsystem;
  
    private double speed;
  
    public IntakeSpeed(Intake subsystem, double speed) {
      this.subsystem = subsystem;
      this.speed = speed;
      addRequirements(subsystem);
    }
  
    @Override
    public void initialize() {
      subsystem.startIntake(speed);
    }
  
    @Override
    public void end(boolean interrupted){ 
      subsystem.stopIntake();

    }
    
    
  } // end class