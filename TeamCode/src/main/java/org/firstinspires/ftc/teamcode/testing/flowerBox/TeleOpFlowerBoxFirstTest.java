package org.firstinspires.ftc.teamcode.testing.flowerBox;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.FlowerBoxHardware;

@TeleOp(name = "TeleOp Flower Box First Test")
public class TeleOpFlowerBoxFirstTest extends LinearOpMode {
    FlowerBoxHardware robot;

    @Override
    public void runOpMode() {
        robot = new FlowerBoxHardware(this);
        robot.flowerInitialize();

        waitForStart();

        while (opModeIsActive()) {
            // ------ Movement Calculation ------
            if (gamepad1.aWasPressed()) {
                robot.servoTurn(0.03);
            } else if (gamepad1.bWasPressed()) {
                robot.servoTurn(0);
            } if (gamepad1.xWasPressed()) {
                robot.slide(82,0.2);
            }

            double sliderPower = gamepad1.left_stick_y * -1;
            robot.setSlidePower(sliderPower);
            sleep(100);
            robot.getAngle();
            robot.getButton();
            // NO MORE THAN 84 MILIMETERS I JUST NEED TO CHECK IF ITS RIGHT NUMBER
        }
    robot.stopFlower();
    }
}