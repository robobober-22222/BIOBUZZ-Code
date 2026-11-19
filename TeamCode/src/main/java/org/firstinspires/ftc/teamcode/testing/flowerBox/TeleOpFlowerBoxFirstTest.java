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
            if (gamepad1.yWasPressed()){
                robot.resetFlower();
            }
            // ------ Movement Calculation ------
            if (gamepad1.aWasPressed()) {
                robot.servoTurn(0.09);
            } if (gamepad1.bWasPressed()) {
                robot.slide(0,0.4);
            } if (gamepad1.xWasPressed()) {
                robot.slide(680.4,0.4);
            } if (gamepad1.dpad_up){
                robot.servoTurn(0);
            }

            double sliderPower = gamepad1.left_stick_y * -1;
            robot.setSlidePower(sliderPower);
            sleep(100);
            if (gamepad1.bWasPressed()){
                robot.flowerEncoder();
            }

            // NO MORE THAN  MILIMETERS I JUST NEED TO CHECK IF ITS RIGHT NUMBER
        }
    robot.stopFlower();
    }
}