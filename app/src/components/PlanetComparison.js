import { useState } from "react";
import PlanetBox from "./PlanetBox";
import "./PlanetComparison.css"
import ComparisionBox from "./ComparisonBox";

function PlanetComparison() {
    const [planetResult, setPlanetResult] = useState(null);
    const [exoplanetResult, setExoplanetResult] = useState(null);

    return (
        <div className="planet-comparison-container">
            <PlanetBox isExoplanetComponent={false} onSearchResultChange={setPlanetResult} searchResult={planetResult} />
            <ComparisionBox planetResult={planetResult} exoplanetResult={exoplanetResult} />
            <PlanetBox isExoplanetComponent={true} onSearchResultChange={setExoplanetResult} searchResult={exoplanetResult} />
        </div>
    );
}

export default PlanetComparison;