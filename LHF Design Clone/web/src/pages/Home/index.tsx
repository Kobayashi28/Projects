import react from 'react';

import './style.css';

import { faMessage, faMouse, faSitemap, faClipboardList, faChartLine, faChevronDown} from '@fortawesome/free-solid-svg-icons';

import logo from '../../assets/logo.svg';
import example from '../../assets/Exemplos.svg';
import results from '../../assets/resultados.svg';
import btApple from '../../assets/bt-apple.svg';
import btGoogle from '../../assets/bt-play.svg';
import asset from '../../assets/asset.svg';
import privatelogo from '../../assets/private.svg';
import map from '../../assets/Map.svg';
import grayLogo from '../../assets/lhflogogray.svg';
import arrowDiagonal from '../../assets/arrowDiagonal.svg';


import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

const Home = ()=>{
    return(
        <div id='container'>

            <header className='lpHeader'>
                    <img src={logo} alt = ""/>

                    <ul className="options">
                        <li>Discover</li>
                        <li>Products</li>
                        <li>About</li>
                        <li>|</li>
                        <li>
                            <div className='language-selector'>
                                <FontAwesomeIcon icon={faMessage} />
                                <text className="language">Language :</text>
                                <select id='languages'>
                                    <option value="en">English</option>
                                    <option value='pt'>Português</option>
                                    <option value='es'>Español</option>
                                </select>
                            </div>
                        </li>
                        <li>
                            <Link to='/login'><button>Login {'>'}</button></Link>
                        </li>
                    </ul>

                </header>

            <section className='info'>
                <div id="infoContent">
                    <h1>Investing<br/>Solutions</h1>
                    <p>We are a <b>global investiment</b> management firm<br/>
                       built on a <b>sophisticated operating system</b><br/>
                       engineered to <b>accelerate success.</b>
                    </p>
                    <div id="scroll">
                        <FontAwesomeIcon icon={faMouse} />
                        SCROLL
                    </div>
                </div>
                <img id='example' src={example} alt="" />
                <div id="tri"> </div>
            </section>

            <section className='points'>
                <img src={results} alt=''/>
                <div className="pointsContent">
                    <p>DISCOVER</p>
                    <h1>We offer some of the best <br/>equity growth services</h1>
                    <h2>LHF seeks to pursue a diverse array of <b>investment<br/>strategies</b>, focused in <b>secure our inverstor</b> wealth while<br/>we look after <b>great results.</b></h2>
                    <ul>
                        <li>
                            <FontAwesomeIcon className='icons' icon={faSitemap} />
                            <p>Enviroment Analysis</p>
                            <FontAwesomeIcon className='chevron' icon={faChevronDown} />
                        </li>
                        <li>
                            <FontAwesomeIcon className='icons' icon={faClipboardList} />
                            <p>Development Planning</p>
                            <FontAwesomeIcon className='chevron' icon={faChevronDown} />
                        </li>
                        <li>
                            <FontAwesomeIcon className='icons' icon={faChartLine} />
                            <p>Execution</p>
                            <FontAwesomeIcon className='chevron' icon={faChevronDown} />
                        </li>
                    </ul>
                </div>
                
            </section>

            <section className='services'>
                <div className="triangle"></div>
                <div className="app">
                    <div className="effect">
                        <div className="wrapper">
                            <h2>PRODUCTS</h2>
                            <h1>Two impressive ways to <br/> guarantee the best results</h1>
                            <p>Lorem ipsum dolor sit amet, consectur adipiscing <br/> edit. Cras lorem ipsum, eleifend loctus eros vítae, <br/> sagittis varius purus. Pellentesque a egestas dolor</p>
                            <div className="imgWrapper">
                                <img src={btApple} alt="" />
                                <img src={btGoogle} alt="" />
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div className="about">
                    <div className="effect">
                        <div className="gridWrapper">
                            <img className='grid1' src={asset} alt="" />
                            <img className='grid2' src={privatelogo} alt="" />
                            <h1 className='grid3'>For who is it?</h1>
                            <p className='grid4'>This is our entry product, it is made for those who seeks <b>new investments</b> opportunities that are more exclusive than the average ones.</p>
                            <h1 className='grid5'>How it works</h1>
                            <p className='grid6'>Created to support medium and small investors from Brazil to achieve above the average results based on Ibovespa Index.</p>
                            
                            <h1 className='grid7'>For who is it?</h1>
                            <p className='grid8'>We like to say that LHF Private is for those who like things that are tailor made and need <b>professional help</b> managing and growing your wealth.</p>
                            <h1 className='grid9'>How it works</h1>
                            <p className='grid10'><b>Private and Personal</b> are the words that defines it. With LHF Private, our investor have a full portforlio management advice, tax planning, legal support and investment consulting for different markets.</p>
                        </div>
                    </div>
                </div>

            </section>

            <section className='history'>
                <img src={map} alt="" />
                <div className="historyWrapper">
                    <h2>ABOUT</h2>

                    <h1>We're passionate in <br/> constant evolution</h1>

                    <p>Consistency doesn’t come easily.<br/>
                        Delivering <b>high-quality returns</b> requires unyielding focus, continually adapting to dynamic conditions and actively pursuing market opportunities.
                        
                    </p>
                    <p>
                        In seeking to achieve this, we invest significantly In <b>technology and infrastructure</b> and bring together diverse perspectives and approaches.
                    </p>
                    <ul>
                        <li><b>08</b> Years of history</li>
                        <li><b>29</b> Operating countries</li>
                        <li><b>03</b> Head Quarters</li>
                    </ul>
                </div>
                

            </section>

            <footer className='lpFooter'>
                <img src={grayLogo} alt="" />
                <h6>Login to dashboard</h6>
                <div className='language-selector'>
                    <FontAwesomeIcon icon={faMessage} />
                    <text className="language">Language</text>
                    <select id='languages'>
                        <option value="en">English</option>
                        <option value='pt'>Português</option>
                        <option value='es'>Español</option>
                    </select>
                </div>
                <h5>2023 © LHF Asset Management</h5>
            </footer>
        </div>
        
    );
}

export default Home;