import React, { useState } from 'react';
import type {
    Command,
    Road,
    SimulationResult
} from "../types/simulation.ts";

interface JsonBuilderProps {
    onSimulationStart: (result: SimulationResult, commands: Command[]) => void;
    setIsLoading: (isLoading: boolean) => void;
}

const JsonBuilder: React.FC<JsonBuilderProps> = ({ onSimulationStart, setIsLoading }) => {
    const [commands, setCommands] = useState<Command[]>([]);
    const [commandType, setCommandType] = useState<'addVehicle' | 'step'>('addVehicle');
    const [vehicleId, setVehicleId] = useState<string>(`vehicle${commands.length + 1}`);
    const [startRoad, setStartRoad] = useState<Road>('south');
    const [endRoad, setEndRoad] = useState<Road>('north');

    const handleAddCommand = () => {
        let newCommand: Command;
        if (commandType === 'addVehicle') {
            if (!vehicleId) {
                alert('Vehicle ID is required.');
                return;
            }
            newCommand = {
                type: 'addVehicle',
                vehicleId,
                startRoad,
                endRoad
            };
        } else {
            newCommand = { type: 'step' };
        }

        const updatedCommands = [...commands, newCommand];
        setCommands(updatedCommands);
        setVehicleId(`vehicle${updatedCommands.length + 1}`);
    };

    const handleClearCommands = () => {
        setCommands([]);
        setVehicleId('vehicle1');
    };

    const handleSimulate = async () => {
        setIsLoading(true);
        const payload = { commands };
        const url = 'http://localhost:8080/simulation/run';

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                const errorData = await response.json();
                const message = errorData?.message || `Server error: ${response.status}`;
                throw new Error(message);
            }

            const result: SimulationResult = await response.json();
            onSimulationStart(result, commands);

        } catch (error) {
            console.error('Error while running the simulation:', error);

            const errorMessage = error instanceof Error ? error.message : 'An unknown error occurred';
            alert(`Failed to start simulation: ${errorMessage}`);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="builder-container">
            <h2>Step 1: Build a Scenario</h2>
            <div className="form-group">
                <label>Command Type:</label>
                <select
                    value={commandType}
                    onChange={(e) => setCommandType(e.target.value as 'addVehicle' | 'step')}
                >
                    <option value="addVehicle">Add Vehicle (addVehicle)</option>
                    <option value="step">Simulation Step (step)</option>
                </select>
            </div>

            {commandType === 'addVehicle' && (
                <div id="vehicle-fields">
                    <div className="form-group">
                        <label>Vehicle ID:</label>
                        <input
                            type="text"
                            value={vehicleId}
                            onChange={(e) => setVehicleId(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Start Road:</label>
                        <select
                            value={startRoad}
                            onChange={(e) => setStartRoad(e.target.value as Road)}
                        >
                            {['north', 'south', 'west', 'east'].map((r) => (
                                <option key={r} value={r}>{r}</option>
                            ))}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>End Road:</label>
                        <select
                            value={endRoad}
                            onChange={(e) => setEndRoad(e.target.value as Road)}
                        >
                            {['north', 'south', 'west', 'east'].map((r) => (
                                <option key={r} value={r}>{r}</option>
                            ))}
                        </select>
                    </div>
                </div>
            )}

            <button onClick={handleAddCommand}>Add Command</button>

            <h3>Command List:</h3>
            <pre className="commands-preview">{JSON.stringify({ commands }, null, 2)}</pre>

            <div style={{ display: 'flex', gap: '10px', marginTop: '1rem' }}>
                <button onClick={handleSimulate} disabled={commands.length === 0}>
                    Submit and Simulate
                </button>
                <button onClick={handleClearCommands} disabled={commands.length === 0}>
                    Clear Commands
                </button>
            </div>

        </div>
    );
};

export default JsonBuilder;
