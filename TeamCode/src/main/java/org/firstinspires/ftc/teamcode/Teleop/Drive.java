package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@TeleOp(name="Drive Logic", group="Teleop")
public class Drive extends OpMode {
    Hardware hw = new Hardware();
    ButtonMapping controller = new ButtonMapping();

    int driver = 0;

    public float MAXVEL = 0;

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    @Override
    public void loop() {
        controller.UpdateButtonMap(gamepad1, driver);

        if (gamepad1.dpadRightWasPressed() && driver < 1)
        {
            driver ++;
        }
        if (gamepad1.dpadLeftWasPressed() && driver > 0)
        {
            driver --;
        }

        if (gamepad1.dpadUpWasPressed())
        {
            MAXVEL += 10;
        }
        if (gamepad1.dpadDownWasPressed())
        {
            MAXVEL -= 10;
        }

        /* TODO:
           Add Enum for Red and Blue tips and IDs
           Develop y tracking and hive flip tracking system using getAprilTagPoseY()
           Maybe: Get the field maps switching depending on hive flips
         */

        hw.limeLight.updateVision();

        hw.drive(controller.axialMovementX, -controller.axialMovementY, controller.rotationalMovement);

        hw.shootOnKey(controller.shootKey, MAXVEL);
        hw.intakeOnKey(controller.intakeKey);

        hw.liftBalls(controller.flipperKey);

        telemetry.addData("Button Map: ", controller.name);
        telemetry.addData("Button Map#: ", driver);
        telemetry.addData("MAX Velocity: ", MAXVEL);
        telemetry.addData("Predicted Vel: ", controller.shootKey * MAXVEL);
        telemetry.addData("Motor Wheel Vel: ", hw.flyWheel.getVelocity());
        telemetry.update();
    }
}
