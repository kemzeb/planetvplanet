import { Link } from "react-router-dom";
import "./Header.css"

function Header() {
  return (
    <header>
      <div className="header-title">planetvplanet</div>
      <nav className="header-nav">
        <Link to="/" className="header-nav-link">Home</Link>
        <Link to="/about" className="header-nav-link">About</Link>
        <a href="https://github.com/kemzeb/planetvplanet" target="_blank" rel="noreferrer" className="header-nav-link">GitHub</a>
      </nav>
    </header>
  )
}

export default Header;