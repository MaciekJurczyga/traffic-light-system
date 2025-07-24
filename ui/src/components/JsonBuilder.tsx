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
                alert('ID pojazdu jest wymagane.');
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
                throw new Error(`Błąd serwera: ${response.status}`);
            }
            const result: SimulationResult = await response.json();
            onSimulationStart(result, commands);
        } catch (error) {
            console.error('Błąd podczas uruchamiania symulacji:', error);
            alert(`Nie udało się uruchomić symulacji. Sprawdź konsolę i upewnij się, że serwer działa na ${url}.`);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="builder-container">
            <h2>Krok 1: Zbuduj scenariusz</h2>
            <div className="form-group">
                <label>Typ komendy:</label>
                <select
                    value={commandType}
                    onChange={(e) => setCommandType(e.target.value as 'addVehicle' | 'step')}
                >
                    <option value="addVehicle">Dodaj Pojazd (addVehicle)</option>
                    <option value="step">Krok Symulacji (step)</option>
                </select>
            </div>

            {commandType === 'addVehicle' && (
                <div id="vehicle-fields">
                    <div className="form-group">
                        <label>ID Pojazdu:</label>
                        <input
                            type="text"
                            value={vehicleId}
                            onChange={(e) => setVehicleId(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Droga początkowa:</label>
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
                        <label>Droga końcowa:</label>
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

            <button onClick={handleAddCommand}>Dodaj komendę</button>

            <h3>Lista komend:</h3>
            <pre className="commands-preview">{JSON.stringify({ commands }, null, 2)}</pre>

            <button onClick={handleSimulate} disabled={commands.length === 0}>
                Wyślij i Symuluj
            </button>
        </div>
    );
};

export default JsonBuilder;
