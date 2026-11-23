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
        // driveRotate() cannot handle 180 degrees as an argument. Use 170 instead to account for friction.
        if (opModeIsActive()){
            sleep(500); // shoot
            robot.driveStraight(10, 0.3);
            robot.driveRotate(-83, 0.3); // rotate to face the garden | Value needs tweaking
            robot.driveStraight(110, 0.5); // move to the garden
            robot.driveStraight(-110,0.4);
            robot.driveRotate(83,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(-83,0.4);
            robot.driveStraight(100,0.5);
            robot.driveRotate(83,0.4);
            robot.driveStraight(60,0.4);
            //intake
            sleep(200);
            robot.driveStraight(-60,0.4);
            robot.driveRotate(83,0.3);
            robot.driveStraight(100,0.4);
            robot.driveRotate(-83,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(-83,0.4);
            robot.driveStraight(85,0.4);
            robot.driveRotate(83,0.4);
            robot.driveStraight(220,0.4);
        }
    }
}