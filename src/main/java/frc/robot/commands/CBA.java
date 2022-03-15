package frc.robot.commands;
import java.util.List;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;



public class CBA extends SequentialCommandGroup {
    /**the coords to for the robot to attempt to replicate; note this will be a shallow attempt, and may need tuning forward*/
    //#region properties
    List<CBA1Input> coords;
    //this timer is the wpilib version, if it doesnt work its the build teams fault
    Timer timer;

    //The paralleldeadlinegroup commands that are executed as part of this command
    List<ParallelDeadlineGroup> commands;
    
    //#endregion
    private Drivetrain driveSubsytem;
    private boolean isFin = false;
    

private ParallelDeadlineGroup TranslateIntoUsableCommand(CBA1Input input){

    WaitCommand deadline = new WaitCommand(input.interval);
    DriveCommand driver = new DriveCommand(driveSubsytem, () -> {return input.x;}, () -> {return input.y;}, () -> {return input.theta;});

return new ParallelDeadlineGroup(deadline, driver);

}

/**
 * @param subsystem The subsystem
 * @param coords the Inputs to use 
 * 
 */
 public CBA(Drivetrain subsystem,List<CBA1Input> coords) {

    //boring init
     this.timer = new Timer();
     this.driveSubsytem = subsystem;
     this.coords = coords;//'this' keyword refers to the instance of the class, not the parameter. Technically 'lesser' practice 
     addRequirements(subsystem);

     //good stuff
     for(CBA1Input input : coords){
         //Adds a usable version of the input as a command deadlined by the interval for every input.
         //This could possibly be optimized, but list conversions make me wanna cry 
         addCommands(TranslateIntoUsableCommand(input));
     }
     

}
    

    @Override
    public void execute() 
        {
            super.execute();


            
            isFin = true;
        }

    @Override
    public boolean isFinished() {
        
        return isFin;
    }

   //end code not currently in place, no need?
}
