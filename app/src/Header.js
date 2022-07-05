import React from 'react'
import './Header.css'

function Header(props) {
    return (
        <header class="site-header">
            <h1 id="logo">planetvplanet</h1>
            <nav class="nav">
                <div class="nav-item">
                    <a href="/">About</a>
                </div>
                <div class="nav-item">
                    <a href="https://github.com/kemzeb/planetvplanet"
                        target="_blank" rel="noreferrer">GitHub</a>
                </div>
            </nav>
        </header>
    );
}

export default Header;