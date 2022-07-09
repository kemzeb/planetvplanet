import React, { useCallback, useState } from "react";
import { URL } from "./const.js"
import "./PlanetBox.css"

function PlanetBox({ considerExoplanets, searchResult, onSearchResultChange }) {
    let [newSearchSuggestions, setNewSearchSuggestions] = useState([])

    const exoplanetFlag = considerExoplanets ? 1 : 0;
    const listName = considerExoplanets ? "exoplanets" : "planets";

    const handleSearchResultChange = useCallback(newResult => {
        onSearchResultChange(newResult)
    }, [onSearchResultChange])

    const handleSearchSuggestions = (event) => {
        const searchStr = event.target.value;
        if (searchStr.length > 1) {
            fetch(URL + `/search?input=${searchStr}&exo_flag=${exoplanetFlag}`)
                .then(response => response.json())
                .then(data => setNewSearchSuggestions(data))
                .catch(error => console.log(error))
        } else {
            setNewSearchSuggestions([]);
        }
    }

    const handleSearchSubmit = (event) => {
        event.preventDefault();

        const stringQuery = event.target.query.value;
        if (stringQuery.length < 1) {
            return;
        }

        const searchSuggestion = newSearchSuggestions.find(suggestion => suggestion.planetName === stringQuery)
        if (searchSuggestion) {
            // Fetch the requested planet.
            fetch(URL + `/search/planet?id=${searchSuggestion.id}`)
                .then(response => response.json())
                .then(data => handleSearchResultChange(data))
                .catch(error => console.log(error))
        } else {
            handleSearchResultChange(null)
        }
    }

    const handlePlanetInfoRender = () => {
        if (!searchResult) {
            return <div>Search for {listName}!</div>
        } else {
            const noDataStr = "n/a";

            return (
                <>
                    <h3>Planet {searchResult.planetName || noDataStr}</h3>
                    <h5>Host Star: {searchResult.hostName || noDataStr}</h5>
                    <article># of Stars: {searchResult.systemNumStars || noDataStr}</article>
                    <article># of Planets: {searchResult.systemNumPlanets || noDataStr}</article>
                    <article>Discovery Year: {searchResult.discoveryYear || noDataStr}</article>
                    <article>Discovery Method: {searchResult.discoveryMethod || noDataStr}</article>
                    <article>Discovery Facility: {searchResult.discoveryFacility || noDataStr}</article>
                    <article>Orbital Period Days: {searchResult.orbitalPeriodDays || noDataStr}</article>
                    <article>Mass (Earth Mass): {searchResult.planetEarthMass || noDataStr}</article>
                    <article>Radius (Earth Radius): {searchResult.planetEarthRadius || noDataStr}</article>
                    {considerExoplanets === true && <article>System Distance (Parsecs): {searchResult.systemDistanceInParsecs || noDataStr}</article>}
                </>
            );
        }
    }

    return (
        <div id="planet-box">
            <div id="submission-content">
                <form onSubmit={handleSearchSubmit}>
                    <input id="search-container" type="search" list={listName} onChange={event => handleSearchSuggestions(event)} name="query"></input>
                    <datalist id={listName}>
                        {newSearchSuggestions.map(planet => (<option key={planet.id}>{planet.planetName}</option>))}
                    </datalist>
                    <input id="submit-button" type="submit" value="Submit"></input>
                </form>
                <form >
                    <input type="button" value="Random"></input>
                </form>
            </div>
            <div id="planet-info-content">
                {handlePlanetInfoRender()}
            </div>
        </div>
    );
}

export default PlanetBox;