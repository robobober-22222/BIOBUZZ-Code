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
            robot.driveStraight(320, 0.3); // move to the opposite side of the playing field
            sleep(300); // intake activation
            // TODO: fix, this will not work
            robot.driveRotate(180, 0.3); // rotate to face the HIVE
            sleep(500); // shoot
            robot.driveStraight(300, 0.3); // move to the opposite side of the playing field
            robot.driveSide(150, 0.3); // move towards GARDEN
            robot.driveStraight(45,0.3); // move to the GARDEN position
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