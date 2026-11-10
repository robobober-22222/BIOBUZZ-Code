package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Preload Partner Garden")
public class AutoPreloadPartnerGarden extends LinearOpMode{
    DrivetrainHardware robot;
    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();

        if (opModeIsActive()){
            sleep(2500);
            //shoot
            robot.driveStraight(5,0.5);
            robot.driveRotate(9,0.4);
            //intake
            robot.driveRotate(9,0.4);
            //shoot
            robot.driveStraight(290,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(110,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(40,0.5);
            robot.driveStraight(-20,0.5);
            robot.driveRotate(-90,0.4);
            robot.driveStraight(100,0.5);
            robot.driveRotate(-90,0.4);
            //shoot
            robot.driveRotate(90,0.4);
            robot.driveStraight(100,0.4);
            robot.driveRotate(-90,0.4);
            robot.driveStraight(40,0.4);
            robot.driveStraight(-40,0.4);
            robot.driveRotate(-90,0.4);
            robot.driveStraight(100,0.5);
            robot.driveRotate(-90,0.4);
            //shoot
            robot.driveRotate(90,0.4);
            robot.driveStraight(90,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(140,0.5);
        }
    }
}