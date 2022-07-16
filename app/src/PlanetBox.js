import React, { useCallback, useState } from "react";
import { URL } from "./const.js"
import "./PlanetBox.css"

// TODO: A lot of the logic in this component should be in their own component.

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

        const stringQueryUppercase = event.target.query.value.toUpperCase();
        if (stringQueryUppercase.length < 1) {
            return;
        }

        const searchSuggestion = newSearchSuggestions.find(suggestion => {
            return suggestion.planetName.toUpperCase() === stringQueryUppercase
        });

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

    const handleRandomSubmit = (event) => {
        event.preventDefault();

        // Fetch a random planet from the backend and set it as the
        // new search result.
        fetch(URL + `/search/random?exo_flag=${exoplanetFlag}`)
            .then(response => response.json())
            .then(data => handleSearchResultChange(data))
            .catch(error => console.log(error))
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
        <div className="planet-box">
            <div className="submission-content">
                <form onSubmit={handleSearchSubmit}>
                    <input className="search-container" type="search" list={listName} onChange={event => handleSearchSuggestions(event)} name="query"></input>
                    <datalist id={listName}>
                        {newSearchSuggestions.map(planet => (<option key={planet.id}>{planet.planetName}</option>))}
                    </datalist>
                    <input className="submit-button" type="submit" value="Submit"></input>
                </form>
                <form onSubmit={handleRandomSubmit} >
                    <input type="submit" value="Random"></input>
                </form>
            </div>
            <div className="planet-info-content">
                {handlePlanetInfoRender()}
            </div>
        </div>
    );
}

export default PlanetBox;