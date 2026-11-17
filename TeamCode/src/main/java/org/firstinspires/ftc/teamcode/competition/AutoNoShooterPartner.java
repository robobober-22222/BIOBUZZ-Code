package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;


@Autonomous(name = "Auto No Shooter Partner")
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
            robot.driveStraight(-110,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(255,0.5);
            robot.driveRotate(170,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(110,0.4);
            robot.driveRotate(-110,0.5);
            //shoot
            robot.driveStraight(10,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(100,0.5);
            robot.driveStraight(-100,0.5);
            robot.driveRotate(-90,0.4);
            robot.driveStraight(290,0.5);
            robot.driveRotate(170,0.4);
            //shoot
            sleep(200);
            robot.driveRotate(-90,0.4);
            robot.driveStraight(120,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(60,0.5);
            //intake
            robot.driveStraight(-60,0.5);
            robot.driveRotate(90,0.4);
            robot.driveStraight(100,0.5);
            robot.driveRotate(-90,0.4);
            //shoot
            sleep(200);
            robot.driveSide(100,0.5);
            robot.driveStraight(180,0.5);

        }
    }
}
