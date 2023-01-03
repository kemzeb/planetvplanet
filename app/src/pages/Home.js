import "./Home.css"
import PlanetComparison from "../components/PlanetComparison";

function Home() {
    return (
        <>
            <div className="home-container">
                <div className="home-subtitle">Compare our Solar System with other Star Systems!</div>
                <div className="home-caption">Provided to you thanks to the NASA Exoplanet Archive and NASA Planetary Fact Sheets.</div>
            </div>
            <PlanetComparison />
        </>
    );
}

export default Home;