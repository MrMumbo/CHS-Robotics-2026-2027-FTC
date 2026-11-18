package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimeLight {

    /* Useful methods:
       getTargetPoseRobotSpace()
       getRobotPoseFieldSpace()
       boolean uploaded = uploadFieldmap(fieldMap, null)
     */
    public Limelight3A limelight3A;

    private LLResult result;

    public double side1 = 0;
    public double side2 = 0;

    public boolean currentTip = false;

    public int primeID = 0;

    public boolean alliance = false;

    public int llHeight = 0;

    public int llDegrees = 0;

    public void initLL(HardwareMap hwMap)
    {
        limelight3A = hwMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(0);
    }

    public void defineLLConstants(int height, int degrees)
    {
        llHeight = height;
        llDegrees = degrees;
    }

    public void updateAlliance(boolean gamepadControl)
    {
        if (gamepadControl)
        {
            alliance = true;
        }
        else
        {
            alliance = false;
        }
    }

    public void printAlliance(Telemetry telemetry)
    {
        if (alliance)
        {
            telemetry.addLine("Alliance Blue");
            telemetry.addLine("");
        }
        else
        {
            telemetry.addLine("Alliance Red");
            telemetry.addLine("");
        }
    }

    public void updateVision()
    {
        result = limelight3A.getLatestResult();
        updateHiveHeights(alliance);
    }

    public LLResult getTagValues(int ID)
    {
        if (result != null && result.isValid())
        {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults())
            {
                if (tag.getFiducialId() == ID)
                {
                    if (LLActive(tag.getFiducialId()))
                    {
                        return result;
                    }
                }
            }
        }

        return null;
    }

    public void printTagIds(Telemetry telemetry)
    {
        if (result != null && result.isValid())
        {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults())
            {
                telemetry.addData("LLID: ", tag.getFiducialId());
            }
        }
        else
        {
            telemetry.addLine("Limelight Failed to fetch data");
        }
    }

    public double getAprilTagPoseY(int ID)
    {
        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {

                int id = tag.getFiducialId();

                Pose3D tagPose = tag.getTargetPoseRobotSpace();

                double y = tagPose.getPosition().y;

                if (id == ID)
                {
                    return y;
                }
            }
        }
        return 0;
    }

    public boolean LLActive()
    {
        if (result != null && result.isValid())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean LLActive(int id)
    {
        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {

                if (tag.getFiducialId() == id && tag != null)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int grabTagValueAlliance(boolean alliance, boolean currentFlip)
    {
        int[] tagIds;

        if (alliance)
        {
            if (currentFlip)
            {
                tagIds = new int[]{42, 43, 44, 45};
            }
            else
            {
                tagIds = new int[]{38, 39, 40, 41};
            }
        }
        else
        {
            if (currentFlip)
            {
                tagIds = new int[]{34, 35, 36, 37};
            }
            else
            {
                tagIds = new int[]{30, 31, 32, 33};
            }
        }

        if (result != null && result.isValid()) {
            int bestTagId = 0;
            double minAbsTx = Double.MAX_VALUE;

            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {
                int id = tag.getFiducialId();
                for (int ididx : tagIds) {
                    if (id == ididx) {
                        double tx = tag.getTargetXDegrees();
                        if (Math.abs(tx) < minAbsTx) {
                            minAbsTx = Math.abs(tx);
                            bestTagId = id;
                        }
                    }
                }
            }
            return bestTagId;
        }
        return 0;
    }

    public double updateVelocity()
    {
        return 1080 + ((20 - (float) Math.abs(((getAprilTagPoseY(primeID) - llHeight) / (Math.tan(Math.toRadians(llDegrees + getTagValues(primeID).getTy())))))) * 5);
    }

    public double compareAndGrabHeights(int height)
    {
        double tempHeight = Double.NEGATIVE_INFINITY;
        boolean foundTag = false;
        int[] tagsToCheck = new int[0];
        if (height == 1 || height == 2)
        {
            int[] Tags1 = {34, 35, 36, 37};
            int[] Tags2 = {30, 31, 32, 33};
            tagsToCheck = (height == 1) ? Tags1 : Tags2;
        }
        else
        {
            int[] Tags1 = {42, 43, 44, 45};
            int[] Tags2 = {38, 39, 40, 41};
            tagsToCheck = (height == 3) ? Tags1 : Tags2;
        }

        for (int tagId : tagsToCheck) {
            LLResult res = getTagValues(tagId);
            if (res != null && res.isValid()) {
                double y = getAprilTagPoseY(tagId);
                if (!foundTag || tempHeight < y) {
                    tempHeight = y;
                    foundTag = true;
                }
            }
        }
        return foundTag ? (Math.abs(tempHeight) * 100) : 0;
    }

    public void resetHiveHeights()
    {
        side1 = 0;
        side2 = 0;
    }

    public void updateHiveHeights(boolean alliance)
    {
        // 151 is Up
        if (alliance)
        {
            side1 = compareAndGrabHeights(3);
            side2 = compareAndGrabHeights(4);
        }
        else
        {
            side1 = compareAndGrabHeights(1);
            side2 = compareAndGrabHeights(2);
        }

        boolean oldTip = currentTip;

        if (side1 > 130 || side2 > 130)
        {
            currentTip = (side1 >= side2);
        }

        if (oldTip != currentTip)
        {
            primeID = 0;
        }
    }

    public void grabHiveHeightDiagnostics(Telemetry telemetry)
    {
        if (!currentTip)
        {
            telemetry.addLine("Facing us is up");
        }
        else
        {
            telemetry.addLine("Facing us is down");
        }

        telemetry.addData("Back Tip: ", side1);
        telemetry.addData("Front Tip: ", side2);
        telemetry.addLine("");
        //if current tip true facing us if up is false facing us is down
    }
    public void resetPrimeID()
    {
        primeID = 0;
    }

    public void startCamera()
    {
        limelight3A.start();
    }

    public void stopCamera()
    {
        limelight3A.stop();
    }

    public void printStatus(Telemetry telemetry)
    {
        if (limelight3A != null) {
            LLStatus status = limelight3A.getStatus();
            telemetry.addData("LL Connected", limelight3A.isConnected());
            telemetry.addData("LL FPS", status != null ? status.getFps() : 0);
            telemetry.addData("LL Valid Result", result != null && result.isValid());
        } else {
            telemetry.addData("Limelight", "Not Initialized");
        }
    }
}

