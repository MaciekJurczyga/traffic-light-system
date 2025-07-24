import React, { useState, useEffect, useRef } from 'react';
import { paths } from '../config/paths';
import type { Road } from '../types/simulation';

interface VehicleProps {
    id: string;
    start: Road;
    end: Road;
    isDriving: boolean;
    duration?: number;
}

const Vehicle: React.FC<VehicleProps> = ({ id, start, end, isDriving, duration = 3000 }) => {
    const pathKey = `${start}-to-${end}`;
    const pathDefinition = paths[pathKey];

    if (!pathDefinition) {
        console.error(`Did not find path for: ${pathKey}`);
        return null;
    }

    const { angle: initialAngle, path } = pathDefinition;
    const initialPosition = path[0];

    const [style, setStyle] = useState<React.CSSProperties>({
        transform: `translate(${initialPosition[0]}px, ${initialPosition[1]}px) rotate(${initialAngle}deg)`,
    });

    const animationFrameId = useRef<number | null>(null);

    useEffect(() => {
        if (!isDriving) return;

        let startTime: number | null = null;

        const animate = (timestamp: number) => {
            if (!startTime) startTime = timestamp;
            const progress = Math.min((timestamp - startTime) / duration, 1);

            const pathSegmentIndex = Math.floor(progress * (path.length - 1));
            const segmentProgress = (progress * (path.length - 1)) % 1;

            const startPoint = path[pathSegmentIndex];
            const endPoint = path[pathSegmentIndex + 1] || startPoint;

            const currentX = startPoint[0] + (endPoint[0] - startPoint[0]) * segmentProgress;
            const currentY = startPoint[1] + (endPoint[1] - startPoint[1]) * segmentProgress;

            const angleRad = Math.atan2(endPoint[1] - startPoint[1], endPoint[0] - startPoint[0]);
            const angleDeg = angleRad * 180 / Math.PI + 90;

            setStyle(prevStyle => ({
                ...prevStyle,
                transform: `translate(${currentX}px, ${currentY}px) rotate(${angleDeg}deg)`,
            }));

            if (progress < 1) {
                animationFrameId.current = requestAnimationFrame(animate);
            } else {
                animationFrameId.current = null;
            }
        };

        animationFrameId.current = requestAnimationFrame(animate);

        return () => {
            if (animationFrameId.current !== null) {
                if (typeof animationFrameId.current === "number") {
                    cancelAnimationFrame(animationFrameId.current);
                }
            }
        };
    }, [isDriving, duration, path, initialAngle, initialPosition]);


    return (
        <div id={id} className="vehicle" style={style}>
            {id.replace('vehicle', 'V')}
        </div>
    );
};

export default Vehicle;