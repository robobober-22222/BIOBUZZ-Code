package org.firstinspires.ftc.teamcode.competition;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Preload Partner Flower")
public class AutoPreloadPartnerFlower extends LinearOpMode{
    DrivetrainHardware robot;
    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();

        if (opModeIsActive()){
            sleep(500); // shoot
            robot.driveStraight(360, 0.3); // move to the flower

            // TODO: think why it may not work
            sleep(300); // intake activation
            sleep(500); // shoot
            robot.driveRotate(180,0.3);
            robot.driveStraight(360, 0.3); // move away from flower
            // TODO here as well
            robot.driveRotate(180, 0.3); // rotate to face the garden
            robot.driveStraight(120,0.3); // move to the garden
            sleep(500); // activate intake
            robot.driveStraight(-120, 0.3); // move back to shooting the position
            robot.driveRotate(90,0.3);
            sleep(500); // shoot
            robot.driveRotate(-50,0.3); // rotate to the flower
            robot.driveStraight(170, 0.3); // move to the flower
            sleep(500); // activate intake
            robot.driveStraight(-170,0.3); // move to shooting position
            robot.driveRotate(50,0.3); // rotate to hive
            sleep(500); // shoot
            robot.driveRotate(-75,0.3);
            robot.driveStraight(270,0.3);
        }
    }
}