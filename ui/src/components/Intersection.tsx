// src/components/Intersection.tsx

import React, { useState, useEffect } from 'react';
import type { SimulationResult, Command, VehicleData, AddVehicleCommand } from '../types/simulation';
import Vehicle from './Vehicle';
import '../styles/Intersection.css';

interface IntersectionProps {
    simulationResult: SimulationResult | null;
    commands: Command[];
}

const Intersection: React.FC<IntersectionProps> = ({ simulationResult, commands }) => {
    const [vehicles, setVehicles] = useState<VehicleData[]>([]);
    const [activeVehicleIds, setActiveVehicleIds] = useState<Set<string>>(new Set());

    useEffect(() => {
        if (!simulationResult) return;

        const initialVehicleData: VehicleData[] = commands
            .filter((cmd): cmd is AddVehicleCommand => cmd.type === 'addVehicle')
            .map(cmd => ({ id: cmd.vehicleId, start: cmd.startRoad, end: cmd.endRoad }));

        setVehicles(initialVehicleData);
        setActiveVehicleIds(new Set());

        const runAnimation = async () => {
            const sleep = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));
            await sleep(100);

            for (const step of simulationResult.stepStatuses) {
                setActiveVehicleIds(prevIds => {
                    const newActiveIds = new Set(prevIds);
                    step.leftVehicles.forEach(id => newActiveIds.add(id));
                    return newActiveIds;
                });

                await sleep(3000);
            }
        };

        runAnimation();
    }, [simulationResult, commands]);

    return (
        <div className="simulation-container">
            <h2>Krok 2: Wizualizacja</h2>
            <div id="intersection-container">
                {/* === UKŁAD SIATKI 3x3 === */}
                <div className="grid-item green-corner"></div>
                <div className="grid-item road road-north">
                    <div className="lane"></div>
                    <div className="lane lane-divider"></div>
                </div>
                <div className="grid-item green-corner"></div>

                <div className="grid-item road road-west">
                    <div className="lane"></div>
                    <div className="lane lane-divider"></div>
                </div>
                <div className="grid-item intersection-center"></div>

                {/* === POPRAWIONA DROGA WSCHODNIA === */}
                <div className="grid-item road road-east">
                    <div className="lane"></div>
                    <div className="lane lane-divider"></div>
                </div>

                <div className="grid-item green-corner"></div>

                {/* === POPRAWIONA DROGA POŁUDNIOWA === */}
                <div className="grid-item road road-south">
                    <div className="lane"></div>
                    <div className="lane lane-divider"></div>
                </div>

                <div className="grid-item green-corner"></div>

                {/* Pojazdy renderowane na wierzchu */}
                {vehicles.map(vehicle => (
                    <Vehicle
                        key={vehicle.id}
                        id={vehicle.id}
                        start={vehicle.start}
                        end={vehicle.end}
                        isDriving={activeVehicleIds.has(vehicle.id)}
                    />
                ))}
            </div>
        </div>
    );
};

export default Intersection;