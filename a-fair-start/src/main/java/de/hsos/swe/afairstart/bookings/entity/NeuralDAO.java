package de.hsos.swe.afairstart.bookings.entity;

import java.util.ArrayDeque;

import de.hsos.swe.afairstart.devices.entity.DeviceType;

public class NeuralDAO {
    public DeviceType device;
    public long level;
    public ArrayDeque<Double> recentBookings;
    public long intendedDuration;
    
    public NeuralDAO(DeviceType device, long level, ArrayDeque<Double> recentBookings, long intendedDuration) {
        this.device = device;
        this.level = level;
        this.recentBookings = recentBookings;
        this.intendedDuration = intendedDuration;
    }
}
