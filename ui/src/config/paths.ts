
export interface PathDefinition {
    angle: number;
    path: [number, number][];
}

const points = {

    S_ENTRY_LEFT:  [320, 620] as [number, number],
    S_ENTRY_RIGHT: [370, 620] as [number, number],

    N_ENTRY_LEFT:  [260, -70] as [number, number],
    N_ENTRY_RIGHT: [210, -70] as [number, number],

    W_ENTRY_LEFT:  [-70, 310] as [number, number],
    W_ENTRY_RIGHT: [-70, 350] as [number, number],

    E_ENTRY_LEFT:  [620, 260] as [number, number],
    E_ENTRY_RIGHT: [620, 210] as [number, number],


    N_EXIT_LEFT:   [310, -70] as [number, number],
    N_EXIT_RIGHT:  [370, -70] as [number, number],

    S_EXIT_LEFT:   [260, 620] as [number, number],
    S_EXIT_RIGHT:  [220, 620] as [number, number],

    W_EXIT_LEFT:   [-80, 250] as [number, number],
    W_EXIT_RIGHT:  [-70, 210] as [number, number],

    E_EXIT_LEFT:   [620, 300] as [number, number],
    E_EXIT_RIGHT:  [620, 350] as [number, number],


    CENTER_S_W: [320, 250] as [number, number],
    CENTER_W_N: [310, 310] as [number, number],
    CENTER_N_E: [260, 300] as [number, number],
    CENTER_E_S: [260, 260] as [number, number],

    CORNER_S_E: [370, 350] as [number, number],
    CORNER_W_S: [210, 355] as [number, number],
    CORNER_N_W: [210, 210] as [number, number],
    CORNER_E_N: [375, 210] as [number, number],
};


export const paths: Record<string, PathDefinition> = {
    'south-to-north': { angle: 0,   path: [points.S_ENTRY_RIGHT, points.N_EXIT_RIGHT] },
    'south-to-east':  { angle: 0,   path: [points.S_ENTRY_RIGHT, points.CORNER_S_E, points.E_EXIT_RIGHT] },
    'south-to-west':  { angle: 0,   path: [points.S_ENTRY_LEFT, points.CENTER_S_W, points.W_EXIT_LEFT] },

    'north-to-south': { angle: 180, path: [points.N_ENTRY_RIGHT, points.S_EXIT_RIGHT] },
    'north-to-west':  { angle: 180, path: [points.N_ENTRY_RIGHT, points.CORNER_N_W, points.W_EXIT_RIGHT] },
    'north-to-east':  { angle: 180, path: [points.N_ENTRY_LEFT, points.CENTER_N_E, points.E_EXIT_LEFT] },

    'west-to-east':   { angle: 90,  path: [points.W_ENTRY_RIGHT, points.E_EXIT_RIGHT] },
    'west-to-south':  { angle: 90,  path: [points.W_ENTRY_RIGHT, points.CORNER_W_S, points.S_EXIT_RIGHT] },
    'west-to-north':  { angle: 90,  path: [points.W_ENTRY_LEFT, points.CENTER_W_N, points.N_EXIT_LEFT] },

    'east-to-west':   { angle: -90, path: [points.E_ENTRY_RIGHT, points.W_EXIT_RIGHT] },
    'east-to-north':  { angle: -90, path: [points.E_ENTRY_RIGHT, points.CORNER_E_N, points.N_EXIT_RIGHT] },
    'east-to-south':  { angle: -90, path: [points.E_ENTRY_LEFT, points.CENTER_E_S, points.S_EXIT_LEFT] },
};