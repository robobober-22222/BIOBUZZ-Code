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
            // TODO: check why -1 
            // and try to limit it with max_ticks 
            double sliderPower = gamepad1.left_stick_y * -1;
            robot.setSlidePower(sliderPower);
           
        }
    robot.stopMotor();
    }
}