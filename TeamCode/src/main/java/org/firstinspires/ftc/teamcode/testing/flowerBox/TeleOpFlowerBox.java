package org.firstinspires.ftc.teamcode.testing.flowerBox;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.FlowerBoxHardware;

@TeleOp(name = "Teleop Flower First Test")
public class TeleOpFlowerBox extends LinearOpMode {
    FlowerBoxHardware robot;

    @Override
    public void runOpMode() {
        robot = new FlowerBoxHardware(this);
        robot.flowerInitialize();

        waitForStart();

        while (opModeIsActive()) {
            // ------ Movement Calculation ------
            double sliderPower = gamepad1.left_stick_y * -1;
            robot.setSlidePower(sliderPower);
            // NO MORE THAN 84 MILIMETERS I JUST NEED TO CHECK IF ITS RIGHT NUMBER
        }
    robot.stopMotor();
    }
}