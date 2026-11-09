package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo FlowerBoxTip;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()
    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}

    public void FlowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
        FlowerBoxTip = opMode.hardwareMap.get(Servo.class, "place holder");
 //       FlowerBoxTip.scaleRange(); (add if needed)
    }
// 132 mm
    public void Slider(double distance, double power){

    }
    public void ServoTurn(double position){
            // servo turns
            FlowerBoxTip.setPosition(position);
            opMode.sleep(500);
    }


}
