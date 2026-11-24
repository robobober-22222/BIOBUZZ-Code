package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeHardware {
    private DcMotor intake;
    private Servo pollenRetrieval;
    private LinearOpMode opMode; // This lets us use OpMode features like sleep.
    public IntakeHardware(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void intakeInitialize() {
        intake = opMode.hardwareMap.get(DcMotor.class, "Intake");
        // TODO: pollenRetireval are you sure about the name ?
        pollenRetrieval = opMode.hardwareMap.get(Servo.class, "pollenRetireval");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // ====== INTAKE START/STOP ======
    public void intakeStart(double power) {
        intake.setPower(power);
    }
    public void intakeStop(){
        intake.setPower(0);
    }

    // ====== POLLEN RETRIEVAL ======
    public void setPollenRetrieval(boolean position) { // true for down, false for up
        if (position) {
            pollenRetrieval.setPosition(0); // Change to down position
        } else { // Has if statement in case argument was entered incorrectly
            pollenRetrieval.setPosition(1); // Change to up position
        }
    }
}
