package org.firstinspires.ftc.teamcode.testing.flowerBox;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.FlowerBoxHardware;


// TODO: the same name 
@TeleOp(name = "Teleop Drivetrain First Test")
public class TeleopFlowerBox extends LinearOpMode {
    FlowerBoxHardware robot;

    @Override
    public void runOpMode() {
        robot = new FlowerBoxHardware(this);
        robot.flowerInitialize();

        waitForStart();

        while (opModeIsActive()) {
            // ------ Gamepad Trackers ------
            double straightPower = gamepad1.left_stick_y * -1;

            // ------ Movement Calculations ------
            double leftFrontDrive = (straightPower);
            robot.setSlidePower(leftFrontDrive);
            // NO MORE THAN 84 MILIMETERS I JUST NEED TO CHECK IF ITS RIGHT NUMBER

        }

    robot.stopMotor();

    }
}