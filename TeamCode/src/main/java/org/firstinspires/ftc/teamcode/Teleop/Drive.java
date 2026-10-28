package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@TeleOp(name="Drive Logic", group="Teleop")
public class Drive extends OpMode {
    Hardware hw = new Hardware();
    ButtonMapping controller = new ButtonMapping();

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    @Override
    public void loop() {
        controller.UpdateButtonMap(gamepad1, 0);

        /* TODO:
           Add Enum for Red and Blue tips and IDs
           Develop y tracking and hive flip tracking system using getAprilTagPoseY()
           Maybe: Get the field maps switching depending on hive flips
         */

        hw.limeLight.updateVision();

        hw.drive(controller.axialMovementX, -controller.axialMovementY, controller.rotationalMovement);

        hw.shootOnKey(controller.shootKey);
        hw.intakeOnKey(controller.intakeKey);

        hw.liftBalls(controller.flipperKey);

        telemetry.update();
    }
}
