package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;



public class CBAv2 extends SequentialCommandGroup {
    /**the coords to for the robot to attempt to replicate; note this will be a shallow attempt, and may need tuning forward*/
    //#region properties

    private ICBACommand[] commands;
    //Subsystem. Drives.
    private Drivetrain driveSubsytem;
    //A simple flag to indicate if the command has finished.
    //private boolean isFin = false;
    //#endregion
   
//#region helpers


    // private ParallelDeadlineGroup TranslateIntoUsableCommand(CBA1Input input) {
    //     WaitCommand deadline = new WaitCommand(input.interval);
    //     DriveCommand driver = new DriveCommand(driveSubsytem, () -> {return input.x;}, () -> {return input.y;}, () -> {return input.theta;});
    
    //     return new ParallelDeadlineGroup(deadline, driver);
    
    // }
    
//#endregion

//#region constructors
/**

/**
 * @param subsystem The subsystem
 * @param commands the commands to use; implements the interface for CBA commands, giving the option of either:
 * <ol>
 * 
 * </ol> 
 * 
 */
 public CBAv2(Drivetrain subsystem, ICBACommand[] commands) {


     this.driveSubsytem = subsystem;
     this.commands = commands;
     

   
     }
     


//#endregion


//#region overrides

    @Override
    public void initialize() {
        
        super.initialize();
        addRequirements(driveSubsytem);
        for(ICBACommand command : commands){
            //Adds a usable version of the input as a command deadlined by the interval for every input.
            //This could possibly be optimized, but list conversions make me wanna cry 
            addCommands(command);}
          
    }
    

//#endregion

}
