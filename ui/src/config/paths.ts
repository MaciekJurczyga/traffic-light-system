// src/config/paths.ts

export interface PathDefinition {
    angle: number; // Kąt początkowy pojazdu
    path: [number, number][]; // Sekwencja punktów [x, y], przez które przejeżdża pojazd
}

/**
 * Definicje punktów kluczowych na skrzyżowaniu (współrzędne w pikselach).
 * Nazewnictwo odzwierciedla zasady ruchu drogowego i logikę ze schematu.
 */
const points = {
    // === PUNKTY WJAZDOWE (STARTOWE) ===
    // Każdy kierunek ma dwa pasy: jeden dla skrętu w lewo, drugi dla reszty.
    S_ENTRY_LEFT:  [320, 620], // Z Południa, pas do skrętu w lewo
    S_ENTRY_RIGHT: [370, 620], // Z Południa, pas do jazdy prosto i w prawo

    N_ENTRY_LEFT:  [260, -70], // Z Północy, pas do skrętu w lewo
    N_ENTRY_RIGHT: [210, -70], // Z Północy, pas do jazdy prosto i w prawo

    W_ENTRY_LEFT:  [-70, 310], // Z Zachodu, pas do skrętu w lewo
    W_ENTRY_RIGHT: [-70, 350], // Z Zachodu, pas do jazdy prosto i w prawo

    E_ENTRY_LEFT:  [620, 260], // Ze Wschodu, pas do skrętu w lewo (dla kierowcy to lewy pas)
    E_ENTRY_RIGHT: [620, 210], // Ze Wschodu, pas do jazdy prosto i w prawo (dla kierowcy to prawy pas)

    // === PUNKTY ZJAZDOWE (KOŃCOWE) ===
    // Każdy kierunek ma dwa pasy zjazdowe.
    N_EXIT_LEFT:   [310, -70], // Pas zjazdowy na Północ, lewy
    N_EXIT_RIGHT:  [370, -70], // Pas zjazdowy na Północ, prawy

    S_EXIT_LEFT:   [260, 620], // Pas zjazdowy na Południe, lewy
    S_EXIT_RIGHT:  [220, 620], // Pas zjazdowy na Południe, prawy

    W_EXIT_LEFT:   [-80, 250], // Pas zjazdowy na Zachód, lewy (dolny pas)
    W_EXIT_RIGHT:  [-70, 210], // Pas zjazdowy na Zachód, prawy (górny pas)

    E_EXIT_LEFT:   [620, 300], // Pas zjazdowy na Wschód, lewy (górny pas)
    E_EXIT_RIGHT:  [620, 350], // Pas zjazdowy na Wschód, prawy (dolny pas)


    // Skręty w lewo (szerokie łuki)
    CENTER_S_W: [320, 250],
    CENTER_W_N: [310, 310],
    CENTER_N_E: [260, 300],
    CENTER_E_S: [260, 260],

    // Skręty w prawo (ciasne łuki)
    CORNER_S_E: [370, 350],
    CORNER_W_S: [210, 355],
    CORNER_N_W: [210, 210],
    CORNER_E_N: [375, 210],
};


export const paths: Record<string, PathDefinition> = {
    // === RUCH Z POŁUDNIA ===
    'south-to-north': { angle: 0,   path: [points.S_ENTRY_RIGHT, points.N_EXIT_RIGHT] }, // Prosto -> na prawy pas
    'south-to-east':  { angle: 0,   path: [points.S_ENTRY_RIGHT, points.CORNER_S_E, points.E_EXIT_RIGHT] }, // W prawo -> na prawy pas
    'south-to-west':  { angle: 0,   path: [points.S_ENTRY_LEFT, points.CENTER_S_W, points.W_EXIT_LEFT] }, // W lewo -> na lewy pas

    // === RUCH Z PÓŁNOCY ===
    'north-to-south': { angle: 180, path: [points.N_ENTRY_RIGHT, points.S_EXIT_RIGHT] }, // Prosto -> na prawy pas
    'north-to-west':  { angle: 180, path: [points.N_ENTRY_RIGHT, points.CORNER_N_W, points.W_EXIT_RIGHT] }, // W prawo -> na prawy pas
    'north-to-east':  { angle: 180, path: [points.N_ENTRY_LEFT, points.CENTER_N_E, points.E_EXIT_LEFT] }, // W lewo -> na lewy pas

    // === RUCH Z ZACHODU ===
    'west-to-east':   { angle: 90,  path: [points.W_ENTRY_RIGHT, points.E_EXIT_RIGHT] }, // Prosto -> na prawy pas
    'west-to-south':  { angle: 90,  path: [points.W_ENTRY_RIGHT, points.CORNER_W_S, points.S_EXIT_RIGHT] }, // W prawo -> na prawy pas
    'west-to-north':  { angle: 90,  path: [points.W_ENTRY_LEFT, points.CENTER_W_N, points.N_EXIT_LEFT] }, // W lewo -> na lewy pas

    // === RUCH ZE WSCHODU ===
    'east-to-west':   { angle: -90, path: [points.E_ENTRY_RIGHT, points.W_EXIT_RIGHT] }, // Prosto -> na prawy pas
    'east-to-north':  { angle: -90, path: [points.E_ENTRY_RIGHT, points.CORNER_E_N, points.N_EXIT_RIGHT] }, // W prawo -> na prawy pas
    'east-to-south':  { angle: -90, path: [points.E_ENTRY_LEFT, points.CENTER_E_S, points.S_EXIT_LEFT] }, // W lewo -> na lewy pas
};