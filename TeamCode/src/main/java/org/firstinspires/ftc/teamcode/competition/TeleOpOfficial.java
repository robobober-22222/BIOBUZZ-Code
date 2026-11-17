package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;
import org.firstinspires.ftc.teamcode.hardware.IntakeHardware;

@TeleOp(name = "TeleOp Official")
public class TeleOpOfficial extends LinearOpMode{
    DrivetrainHardware robot;
    IntakeHardware intake;

    @Override
    public void runOpMode(){
        robot = new DrivetrainHardware(this);
        robot.initialize();
        intake = new IntakeHardware(this);
        intake.intakeInitialize();


        waitForStart();

        while (opModeIsActive()){
            // ------ Gamepad Trackers ------
            double straightPower = gamepad1.left_stick_y * -1;
            double rotatePower = gamepad1.right_stick_x;
            double sidePower = gamepad1.right_trigger - gamepad1.left_trigger;

            double intakeRotate = gamepad1.left_stick_y;


            // ------ Movement Calculations ------
            double leftFrontDrive = (straightPower + rotatePower + sidePower) * 0.6;
            double leftBackDrive = (straightPower + rotatePower - sidePower) * 0.6;
            double rightFrontDrive = (straightPower - rotatePower - sidePower) * 0.6;
            double rightBackDrive = (straightPower - rotatePower + sidePower) * 0.6;

            robot.setDrivePower(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive);
            intake.intakeStart(intakeRotate*0.6);
        }
        robot.setDrivePower(0,0,0,0);
    }
}
