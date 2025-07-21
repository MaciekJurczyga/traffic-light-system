package org.example.commands;

import org.example.vehicle.Vehicle;

public class AddVehicle implements Command {

    private Vehicle vehicle;

    @Override
    public void executeCommand(){
        return;
    }

    public AddVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public String toString() {
        return "AddVehicle{" +
                "vehicleId='" + vehicle.getVehicleId() + '\'' +
                '}';
    }
}
