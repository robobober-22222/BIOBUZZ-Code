package org.firstinspires.ftc.teamcode.testing.intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOp Intake")
public class MaxServoPosition extends LinearOpMode {

    Servo IntakeServo;
    double position = 0;


    @Override
    public void runOpMode() {
        IntakeServo = hardwareMap.get(Servo.class, "IntakeServo");

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.aWasPressed()){
                position -= 0.1;
            } else if (gamepad1.bWasReleased()) {
                position += 0.1;
            }
            IntakeServo.setPosition(position);
            telemetry.addData("Servo",IntakeServo.getPosition());
            telemetry.update();
            sleep(100);
        }
    }
}
