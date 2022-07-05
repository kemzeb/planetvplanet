import React from "react";
import "./SiteInfo.css"

function SiteInfo(props) {
    return (
        <div id="site-info">
            <p class="info-item" id="intro">Compare your favorite planets in our Solar System with the planets
                of other star systems!
            </p>
            <p class="info-item" id="data">The exoplanet data that has been used was provided by the <a href="https://exoplanetarchive.ipac.caltech.edu/index.html"
                target="_blank" rel="noreferrer">NASA Exoplanet Archive</a>.
            </p>
        </div>
    );
}

export default SiteInfo;