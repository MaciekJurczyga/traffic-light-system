import { useState } from 'react';
import JsonBuilder from './components/JsonBuilder';
import Intersection from './components/Intersection';
import './styles/App.css';

import type { SimulationResult, Command } from './types/simulation';

function App() {
    const [simulationResult, setSimulationResult] = useState<SimulationResult | null>(null);
    const [commands, setCommands] = useState<Command[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const handleSimulationStart = (result: SimulationResult, commands: Command[]) => {

        setSimulationResult(null);
        setCommands([]);

        setTimeout(() => {
            setSimulationResult(result);
            setCommands(commands);
        }, 50);
    };

    return (
        <div className="app-container">
            <JsonBuilder onSimulationStart={handleSimulationStart} setIsLoading={setIsLoading} />
            <div className="simulation-container-wrapper">
                {isLoading && <div className="loader"></div>}
                <Intersection simulationResult={simulationResult} commands={commands} />
            </div>
        </div>
    );
}

export default App;
