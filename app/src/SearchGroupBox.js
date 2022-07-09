import React, { useState } from "react";
import PlanetBox from "./PlanetBox.js"
import ComparisonBox from "./ComparisonBox.js"
import "./SearchGroupBox.css"

function SearchGroupBox(props) {
    const [planetResult, setPlanetResult] = useState(null);
    const [exoplanetResult, setExoplanetResult] = useState(null);
    return (
        <>
            <div id="search-group">
                <PlanetBox considerExoplanets={false} searchResult={planetResult} onSearchResultChange={setPlanetResult} />
                <ComparisonBox planetResult={planetResult} exoplanetResult={exoplanetResult} />
                <PlanetBox considerExoplanets={true} searchResult={exoplanetResult} onSearchResultChange={setExoplanetResult} />
            </div>
        </>
    );
}

export default SearchGroupBox;