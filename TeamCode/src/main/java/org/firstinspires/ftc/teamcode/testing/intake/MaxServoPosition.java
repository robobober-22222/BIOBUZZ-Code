package org.firstinspires.ftc.teamcode.testing.intake;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOp Max Servo")
public class MaxServoPosition extends LinearOpMode {

    Servo IntakeServo;
    double position = 0;
// 0.525 servo down

    @Override
    public void runOpMode() {
        IntakeServo = hardwareMap.get(Servo.class, "IntakeServo");
        IntakeServo.scaleRange(-1,1);
        IntakeServo.setPosition(0);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.aWasPressed()){
                position -= 0.05;
            } else if (gamepad1.bWasPressed()) {
                position += 0.05;
            }
            IntakeServo.setPosition(position);
            telemetry.addData("Servo",IntakeServo.getPosition());
            telemetry.update();
            sleep(100);
        }
    }
}
