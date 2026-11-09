package org.firstinspires.ftc.teamcode.testing.flowerBox;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;
import org.firstinspires.ftc.teamcode.hardware.FlowerBoxHardware;

@Autonomous(name = "Auto Parameters Test FlowerBox")
public class AutoParametersTestFlowerBox extends LinearOpMode {
    FlowerBoxHardware robot; // We here introduce a variable robot and connect it to our hardware class
    // Now we can use all the variables that we added in our drivetrain hardware

    @Override
    public void runOpMode() {
        // Connect and prepare the robot hardware
        robot = new FlowerBoxHardware(this);
        robot.flowerInitialize();
        if (opModeIsActive()) {
            robot.slider(84,0.1);
            robot.slider(-84,0.1);
            robot.servoTurn(10);
            robot.servoTurn(-10);
        }

        waitForStart();
    }
}
