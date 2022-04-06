package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;



public class CBA extends SequentialCommandGroup {
    /**the coords to for the robot to attempt to replicate; note this will be a shallow attempt, and may need tuning forward*/
    //#region properties
    private CBA1Input[] coords;
    //Subsystem. Drives.
    private Drivetrain driveSubsytem;
    //A simple flag to indicate if the command has finished.
    //private boolean isFin = false;
    //#endregion
   
//#region helpers


    private ParallelDeadlineGroup TranslateIntoUsableCommand(CBA1Input input) {
        WaitCommand deadline = new WaitCommand(input.interval);
        DriveCommand driver = new DriveCommand(driveSubsytem, () -> {return input.x;}, () -> {return input.y;}, () -> {return input.theta;});
    
        return new ParallelDeadlineGroup(deadline, driver);
    
    }
    
//#endregion

//#region constructors
/**
     * @param collections the Double array to construct a new Clock-Based-Auton sequence. The ordering for each double is as follows:
     *  <ol>
     *      <li>X-coord</li>
     *      <li>Y-coord</li>
     *      <li>Theta(turning)</li>
     *      <li>Interval</li>
     *  </ol>
     */
public CBA(Drivetrain subsystem, Double[]... collections) {
    this.driveSubsytem = subsystem;
    for(int i = 0; i < collections.length; i++){
       coords[i].x = collections[i][0];
       coords[i].y = collections[i][1];
       coords[i].theta = collections[i][2];
       coords[i].interval = collections[i][3];
       
    }
}
/**
 * @param subsystem The subsystem
 * @param coords the Inputs to use 
 * 
 */
 public CBA(Drivetrain subsystem,CBA1Input[] coords) {


     this.driveSubsytem = subsystem;
     this.coords = coords;
     

   
     }
     


//#endregion


//#region overrides

    @Override
    public void initialize() {
        
        super.initialize();
        addRequirements(driveSubsytem);
        for(CBA1Input input : coords){
            //Adds a usable version of the input as a command deadlined by the interval for every input.
            //This could possibly be optimized, but list conversions make me wanna cry 
            addCommands(TranslateIntoUsableCommand(input));}
          
    }
    

//#endregion

}
