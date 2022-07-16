import React from "react";
import "./ComparisonBox.css"

function ComparisonBox({ planetResult, exoplanetResult }) {
    const noDataStr = "n/a";
    const moreThanStr = " more than";
    const lessThanStr = " less than";
    const biggerThanStr = " times bigger than";
    const smallerThanStr = " times smaller than";
    const maxDecimalPlace = 4;

    const performComparison = () => {
        if (!planetResult || !exoplanetResult) {
            return;
        }

        const compareBySubtraction = (num1, num2) => {
            if (!num1 || !num2) {
                return noDataStr;
            }
            let comparison = num1 - num2;
            let comparisonStrResult = moreThanStr;

            if (comparison === 0) {
                return "same number compared to";
            } else if (comparison < 0) {
                comparison = -comparison;
                comparisonStrResult = lessThanStr;
            }

            const isFloat = num => {
                return num % 1 !== 0;
            }
            return isFloat(comparison) ? comparison.toFixed(maxDecimalPlace) + comparisonStrResult :
                comparison + comparisonStrResult;
        };

        const compareByDivision = (num1, num2) => {
            if (!num1 || !num2) {
                return noDataStr;
            }
            let comparisonResult = null;
            let comparisonStrResult = null;
            if (num1 < num2) {
                comparisonResult = num2 / num1;
                comparisonStrResult = smallerThanStr;
            } else {
                comparisonResult = num1 / num2;
                comparisonStrResult = biggerThanStr;
            }
            return comparisonResult.toFixed(maxDecimalPlace) + comparisonStrResult;
        };


        return (
            <div id="comparison-content">
                <h4>Considering Planet {planetResult.planetName}...</h4>
                <article># of Stars:{' '}
                    {compareBySubtraction(planetResult.systemNumStars, exoplanetResult.systemNumStars)}
                </article>
                <article># of Planets:{' '}
                    {compareBySubtraction(planetResult.systemNumPlanets, exoplanetResult.systemNumPlanets)}
                </article>
                <article>Mass (Earth Mass):{' '}
                    {compareByDivision(planetResult.planetEarthMass, exoplanetResult.planetEarthMass)}
                </article>
                <article>Radius (Earth Radius):{' '}
                    {compareByDivision(planetResult.planetEarthRadius, exoplanetResult.planetEarthRadius)}
                </article>
                <article>Orbital Period (Days):{' '}
                    {compareBySubtraction(planetResult.orbitalPeriodDays, exoplanetResult.orbitalPeriodDays)}
                </article>
                <h4>... Exoplanet {exoplanetResult.planetName}</h4>
            </div>
        );
    }

    return (
        <div id="comparison-box">
            {performComparison()}
        </div>
    );
}

export default ComparisonBox;