package frc.robot.commands.launcher;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class xmode extends CommandBase {
  
    Drivetrain driveTrain;
    public xmode(Drivetrain m_drivetrain) {
        this.driveTrain = m_drivetrain;
        addRequirements(m_drivetrain);
    }
    
   
    @Override
    public void execute() {
        //sets the modules to unmoving, "x" shape
        //-45 45 45 -45 was circle mode
        //45 -45 -45 45 is good
        //when we set the mod states, it gets caught in a process of trying to maintain the config, never stopping. PID I term? dont think its trying to drive, jsut turn 
        driveTrain.setModuleStates(
                new SwerveModuleState[]{
                new SwerveModuleState(0, Rotation2d.fromDegrees(45)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(-45)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(-45)),
                new SwerveModuleState(0, Rotation2d.fromDegrees(45))
            });
        
    }

    @Override
    public boolean isFinished() {
      return false;
    }    
}