package org.firstinspires.ftc.teamcode.testing.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@TeleOp(name = "Teleop Drivetrain First Test")
public class TeleopDrivetrainFirstTest extends LinearOpMode{
    DrivetrainHardware robot;

    @Override
    public void runOpMode(){
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();

        while (opModeIsActive()){
            // ------ Gamepad Trackers ------
            double straightPower = gamepad1.left_stick_y * -1;
            double rotatePower = gamepad1.right_stick_x;
            double sidePower = gamepad1.right_trigger - gamepad1.left_trigger;

            // ------ Movement Calculations ------
            double leftFrontDrive = (straightPower + rotatePower + sidePower)*0.6;
            double leftBackDrive = (straightPower + rotatePower - sidePower)*0.6;
            double rightFrontDrive = (straightPower - rotatePower - sidePower)*0.6;
            double rightBackDrive = (straightPower - rotatePower + sidePower)*0.6;

            robot.setDrivePower(leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive);
        }
    robot.setDrivePower(0,0,0,0);
    }
}
