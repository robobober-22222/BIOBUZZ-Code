package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo FlowerBoxTip;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()
    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}

    public void FlowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
    }


}
