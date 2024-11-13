// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.bluecheese;

import java.nio.ByteBuffer;
import java.util.HashMap;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.networktables.GenericPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.networktables.PubSubOption;

/** Add your docs here. */
public class betterLogger {
    private static NetworkTableInstance netInstance;
    private static GenericPublisher publisher;
    private static HashMap publisherMap;

    public static enum LogMode {
        ROBOT,
        SIMULATOR,
        REPLAY
    }

    public static void Start(LogMode currentMode) {
        switch (currentMode) {
            case ROBOT:
                netInstance = NetworkTableInstance.getDefault();
                netInstance.startServer();
                netInstance.setServer("host", netInstance.kDefaultPort4);    
                break;
            case SIMULATOR:
                netInstance = NetworkTableInstance.getDefault();
                netInstance.startServer();
                netInstance.setServer("host", netInstance.kDefaultPort4);    
                break;
            case REPLAY:
                break;
        }
        publisherMap = new HashMap<String,String>();
    }
    /* 
    Simple Main Table Logging
    It allows for custom tables and subtables 
    allowing better organization of logs
    */ 
    
    public static void Log(String Table, String key, Object value) {
        NetworkTable currentTable = netInstance.getTable(Table);
        currentTable.getEntry(key).setValue(value);
    }

    public static void Log(String Table, String Subtable, String key, Object value) {
        NetworkTable currentTable = netInstance.getTable(Table).getSubTable(Subtable);
        currentTable.getEntry(key).setValue(value);
    }

    public static void Log(String Table, String key, Pose2d value) {
        NetworkTable currentTable = netInstance.getTable(Table);
        if (publisherMap.get(Table+key) == "Pose2") {

        } else {
            publisher = currentTable.getTopic(key).genericPublish(Pose2d.struct.getTypeString(), PubSubOption.keepDuplicates(false));
            publisherMap.put(Table+key, "Pose2");
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(Pose2d.struct.getSize());
        Pose2d.struct.pack(byteBuffer, value);
        publisher.setRaw(byteBuffer);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }

    public static void Log(String Table, String Subtable, String key, Pose2d value) {
        NetworkTable currentTable = netInstance.getTable(Table).getSubTable(Subtable);
        if (publisherMap.get(Table+Subtable+key) == "Pose2") {
        } else {
            publisher = currentTable.getTopic(key).genericPublish(Pose2d.struct.getTypeString(), PubSubOption.keepDuplicates(false));
            publisherMap.put(Table+Subtable+key, "Pose2");
        }
        publisher = currentTable.getTopic(key).genericPublish(Pose2d.struct.getTypeString(), PubSubOption.keepDuplicates(false));
        ByteBuffer byteBuffer = ByteBuffer.allocate(Pose2d.struct.getSize());
        Pose2d.struct.pack(byteBuffer, value);
        publisher.setRaw(byteBuffer);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }

    public static void Log(String Table, String key, Pose3d value) {
        NetworkTable currentTable = netInstance.getTable(Table);
        publisher = currentTable.getTopic("Pose3").genericPublish(Pose2d.struct.getTypeString(), PubSubOption.keepDuplicates(false));
        ByteBuffer byteBuffer = ByteBuffer.allocate(Pose3d.struct.getSize());
        Pose3d.struct.pack(byteBuffer, value);
        publisher.setRaw(byteBuffer);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }

    public static void Log(String Table, String Subtable, String key, Pose3d value) {
        NetworkTable currentTable = netInstance.getTable(Table).getSubTable(Subtable);
        publisher = currentTable.getTopic("Pose3").genericPublish(Pose2d.struct.getTypeString(), PubSubOption.keepDuplicates(false));
        ByteBuffer byteBuffer = ByteBuffer.allocate(Pose3d.struct.getSize());
        Pose3d.struct.pack(byteBuffer, value);
        publisher.setRaw(byteBuffer);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }

    public static void Log(String Table, String key, Transform2d value) {
        NetworkTable currentTable = netInstance.getTable(Table);
        ByteBuffer byteBuffer = ByteBuffer.allocate(Transform2d.struct.getSize());
        Transform2d.struct.pack(byteBuffer, value);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }

    public static void Log(String Table, String Subtable, String key, Transform2d value) {
        NetworkTable currentTable = netInstance.getTable(Table).getSubTable(Subtable);
        ByteBuffer byteBuffer = ByteBuffer.allocate(Transform2d.struct.getSize());
        Transform2d.struct.pack(byteBuffer, value);
        currentTable.putValue(key, NetworkTableValue.makeRaw(byteBuffer.array()));
    }
}