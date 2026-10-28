package org.firstinspires.ftc.teamcode.Tests.CoachRobot;

import com.qualcomm.robotcore.hardware.Gamepad;


public class ButtonMappingCoach {
    public float axialMovementX;

    public float axialMovementY;

    public float rotationalMovement;

    public int shootKey;

    public int intakeKey;

    public int flipperKey;
    public int flowerGrabbingKey;
    public void UpdateButtonMap(Gamepad controller, int driver)
    {
        if (driver == 0)
        {
            axialMovementX = controller.left_stick_x;

            axialMovementY = controller.left_stick_y;

            rotationalMovement = controller.right_stick_x;

            shootKey = (int) controller.right_trigger;

            intakeKey = (int) controller.left_trigger;

            flipperKey = controller.left_stick_button ? 0 : 1;

            flowerGrabbingKey = controller.right_bumper ? 0 : 1;
        }
        if (driver == 1)
        {
            axialMovementX = controller.left_stick_x;

            axialMovementY = controller.left_stick_y;

            rotationalMovement = controller.right_stick_x;

            shootKey = (int) controller.right_trigger;

            intakeKey = controller.right_bumper ? 0 : 1;

            flipperKey = (int) controller.left_trigger;

            flowerGrabbingKey = controller.left_bumper ? 0 : 1;
        }
        if (driver == 2)
        {
            axialMovementX = controller.left_stick_x;

            axialMovementY = controller.left_stick_y;

            rotationalMovement = controller.right_stick_x;

            shootKey = (int) controller.right_trigger;

            intakeKey = (int) controller.left_trigger;

            flipperKey = controller.left_bumper ? 0 : 1;

            flowerGrabbingKey = controller.right_bumper ? 0 : 1;
        }
    }
}
