package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Preload Partner Garden")
public class AutoNoShooterPartner extends LinearOpMode{
    DrivetrainHardware robot;
    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();
        if (opModeIsActive()){
            robot.driveStraight(10,0.5);
            //shoot
            robot.driveRotate(-90,0.4);
            robot.driveStraight(110,0.5);
            robot.driveRotate(-90,0.4);
            robot.driveRotate(-155,0.4);
            robot.driveStraight(300,0.5);
            robot.driveRotate(120,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(100,0.4);
            robot.driveStraight(100,0.5);
            robot.driveRotate(110,0.4);
            robot.driveStraight(300,0.5);
            robot.driveRotate(-160,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(-40,0.4);
            robot.driveStraight(120,0.5);
            robot.driveStraight(-120,0.5);
            robot.driveRotate(40,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(-20,0.4);
            robot.driveStraight(200,0.5);
        }
    }
}
