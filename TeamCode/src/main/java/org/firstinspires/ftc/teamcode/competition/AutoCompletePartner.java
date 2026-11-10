package org.firstinspires.ftc.teamcode.competition;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Complete Partner")
public class AutoCompletePartner extends LinearOpMode{
    DrivetrainHardware robot;
    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();

        if (opModeIsActive()){
            sleep(500); // shoot
            robot.driveStraight(10, 0.3);
            // TODO: think why it may not work
            robot.driveRotate(180,0.3); // rotate to face the garden
            robot.driveSide(-210, 0.5); // move to the garden
            robot.driveStraight(10, 0.3); // collect POLLEN from GARDEN
            sleep(300); // intake activation
            robot.driveStraight(-10, 0.3); // move away from GARDEN
            robot.driveSide(-210, 0.5); // move to the HIVE shooting position
            // TODO here as well
            robot.driveRotate(180, 0.3); // rotate to face the HIVE
        }
    }
}