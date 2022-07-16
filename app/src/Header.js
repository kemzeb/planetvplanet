import React from 'react'
import './Header.css'

function Header(props) {
    return (
        <header className="site-header">
            <h1 id="logo">planetvplanet</h1>
            <nav className="nav">
                <div className="nav-item">
                    <a href="/">About</a>
                </div>
                <div className="nav-item">
                    <a href="https://github.com/kemzeb/planetvplanet"
                        target="_blank" rel="noreferrer">GitHub</a>
                </div>
            </nav>
        </header>
    );
}

export default Header;