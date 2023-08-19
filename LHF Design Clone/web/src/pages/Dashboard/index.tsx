import react from 'react';

import './style.css';

import { Chart, ArcElement, CategoryScale, LinearScale, PointElement, LineElement, Tooltip} from 'chart.js';
import { Doughnut, Line } from 'react-chartjs-2';

import setacimabaixo from '../../assets/setacimabaixo.svg';
import arrowright from '../../assets/arrowright.svg';
import plus from '../../assets/plus.svg';
import minus from '../../assets/minus.svg';
import lhfgray from '../../assets/LHFgraylogo.svg';
import pwc from '../../assets/PWC.svg';
import actTrades from '../../assets/Activtrades.svg';
import logo from '../../assets/LHFsilver.svg';
import profilePic from '../../assets/user.jpeg';
import dbIcon from '../../assets/dashboardIcon.svg';
import withdrawIcon from '../../assets/withdrawIcon.svg';
import logout from '../../assets/logout.svg';
import bgCircles from '../../assets/bgCircles.svg';

Chart.register(ArcElement, CategoryScale, LinearScale, PointElement, LineElement, Tooltip);

const dougData = {
    datasets:[
        {
            data: [90,10],
            backgroundColor:['#081326', 'EDEFF2'],
            borderColor:['']
        }
    ]
}
const accHistoryLabels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
const accHdata = {
    labels:accHistoryLabels,
    datasets:[{
        label:'title',
        data:[20000, 21000, 19000, 22300, 23700, 22335, 24350, 20480, 19200 , 19800, 25500, 26700],
        borderColor:'blue',
        backgroundColor:'blue'
    },{
        label:'title2',
        data:[15000, 16500, 16800, 17300, 19400, 20600, 23300, 15700, 14200, 14900, 13400, 13000],
        borderColor:'red',
        backgroundColor:'red'
    },{
        label:'title3',
        data:[6000, 9000, 7500, 6900, 8000, 7700, 7300, 4500, 5000, 5200, 4200, 3400],
        borderColor:'green',
        backgroundColor:'green'
    }]
}

const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Set', 'Oct', 'Nov', 'Dec'];    
const d = new Date();
const Dashboard = ()=>{
    return(
        <div className="db-container">


            <header className="db-header">
                <div className="db-headWrapper">
                    <div className="db-headInfo">
                        <h1>Dashboard</h1>
                        <h6>{months[d.getMonth()]}, {d.getDate()} <b>{d.getFullYear()}</b></h6>
                    </div>

                    <div className="db-headUser">
                        <img src={profilePic} alt="" />
                        <h5>Hi, username</h5>
                        <h4>{'>'}</h4>
                    </div>
                </div>
                
            </header>


            <nav className="db-navbar">
                <div className="db-navLogo">
                    <img src={logo} alt="" />
                </div>
                <ul className="db-navList">
                    <li className='db-navOption db-navSelected'>
                        <img src={dbIcon} alt="" />
                        <h4>Dashboard</h4>
                    </li>
                    <li className='db-navOption'>
                        <img src={withdrawIcon} alt="" />
                        <h4>Withdraw</h4>
                    </li>
                </ul>
                <footer className="db-navFoot">
                    <div className="db-navSup">
                        <ul>
                            <li>
                                <span>?</span>
                                <h4>Support</h4>
                            </li>
                            <li>
                                <p>If you have any questions or a problem to report, please contact us.</p>
                            </li>
                            <li>
                                <button>Contact support {'>'}</button>
                            </li>
                        </ul>
                    </div>
                    <div className="db-logout" onClick={()=>{alert('a')}}>
                        <img src={logout} alt="" />
                        <h4>Logout</h4>
                    </div>
                </footer>
            </nav>


            <div className="db-content">
                <div className="db-resume">
                    <h4 className="totalProfits">Total balance <span className='totalPspan'>/ Performance</span></h4>
                    <h4 className="totalProfits">Total profits</h4>
                    <h4 className="totalProfits">Monthly profit <span className='totalPspan'>AVG</span> </h4>
                    <h3 className="totalValue">R$<b>22.335</b>,85</h3>
                    <h3 className="totalValue">R$<b>12.355</b>,85</h3>
                    <h3 className="totalValue">R$<b>426</b>,06</h3>

                </div>


                <div className="db-accountHistory">
                    <header className="db-accHeader">
                        <h1 className='db-title'>Account History</h1>
                        <select className='db-select'>
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <option value="2021">2021</option>
                            <option value="2020">2020</option>
                        </select>
                    </header>
                    <div className="db-subHeader">
                        <div className="cat">
                            <ul>
                                <li>RESULTS</li>
                                <li>PERFORM</li>
                                <li>HISTORY</li>
                            </ul>
                        </div>
                        <div className="legend">
                            <ul>
                                <li>Total</li>
                                <li>Investment</li>
                                <li>Profit</li>
                            </ul>
                        </div>
                    </div>
                    <div className="graph">
                        <Line data={accHdata}/>
                    </div>
                </div>


                <div className="db-operationsHistory">
                    <header className="db-opeader">
                        <h1 className='db-title'>Operations History</h1>
                        <select className='db-select'>
                            <option value="2023">2023</option>
                            <option value="2022">2022</option>
                            <option value="2021">2021</option>
                            <option value="2020">2020</option>
                        </select>
                        <select className='db-select'>
                            <option value="all">All</option>
                        </select>
                    </header>
                    <div className="db-subOpeader">
                        <ul>
                            <li>
                                <p>Type</p>
                                <img src={setacimabaixo} alt="" />
                            </li>
                            <li className='db-subOptions'>
                                <p>Date</p>
                                <img src={setacimabaixo} alt="" />
                            </li>
                            <li className='db-subOptions'>
                                <p>Status</p>
                                <img src={setacimabaixo} alt="" />
                            </li>
                            <li className='db-subOptions'>
                                <p>Amount</p>
                                <img src={setacimabaixo} alt="" />
                            </li>
                        </ul>
                    </div>
                    <ul className="db-opeGrid">
                        <li className="db-gridLine">
                            <div className="db-typeWrapper">
                                <img src={arrowright} alt="" />
                                <h4>New investment</h4>
                            </div>
                            <h5>NOV,02</h5>
                            <h6 className='db-gridLineCompleted'>Completed</h6>
                            <div className="db-amountWrapper">
                                <img src={plus} alt="" />
                                <h2>R$70.000,00</h2>
                            </div>

                        </li>
                        <li className="db-gridLine">
                            <div className="db-typeWrapper">
                                <img src={arrowright} alt="" />
                                <h4>Withdraw</h4>
                            </div>
                            <h5>NOV,02</h5>
                            <h6 className='db-gridLineCompleted'>Completed</h6>
                            <div className="db-amountWrapper">
                                <img src={minus} alt="" />
                                <h2>R$54.000,00</h2>
                            </div>

                        </li>
                        <li className="db-gridLine">
                            <div className="db-typeWrapper">
                                <img src={arrowright} alt="" />
                                <h4>New investment</h4>
                            </div>
                            <h5>NOV,02</h5>
                            <h6 className='db-gridLinePending'>Pending</h6>
                            <div className="db-amountWrapper">
                                <img src={plus} alt="" />
                                <h2>R$40.000,00</h2>
                            </div>

                        </li>
                        <li className="db-gridLine">
                            <div className="db-typeWrapper">
                                <img src={arrowright} alt="" />
                                <h4>Withdraw</h4>
                            </div>
                            <h5>NOV,02</h5>
                            <h6 className='db-gridLineCompleted'>Completed</h6>
                            <div className="db-amountWrapper">
                                <img src={minus} alt="" />
                                <h2>R$100.000,00</h2>
                            </div>

                        </li>
                    </ul>
                </div>


                <div className="db-results">
                    <header className="db-reader">
                        <h1>Results</h1>
                        <select onChange={()=>{alert('mudou :)')}} className="readerSelectors">
                            <option value="2023" className="yearOption">2023</option>
                            <option value="2022" className="yearOption">2022</option>
                            <option value="2021" className="yearOption">2021</option>
                            <option value="2020" className="yearOption">2020</option>
                        </select>
                        <select onChange={()=>{alert('mudou :/')}} className="readerSelectors">
                            <option value="jan" className="months">Jan</option>
                            <option value="feb" className="months">Feb</option>
                            <option value="mar" className="months">Mar</option>
                        </select>
                    </header>
                    <div className="doug">
                        <Doughnut data={dougData}/>
                    </div>
                    <footer className="db-rooter">
                        <button>{'<'}</button>
                        <div className="resultsToChange">
                            <h6 className='rtc'>Nov - 2021</h6>
                            <h5 className='rtc'>Total balance</h5>
                            <h5 className='rtc'>Profits</h5>
                            <h4 className='rtc'>R$<b>12.355</b><span>,85</span></h4>
                            <h4 className='rtc'>R$<b>749</b><span>,73</span></h4>
                        </div>
                        <button>{'>'}</button>
                    </footer>
                </div>


                <div className="db-capitalInfo">
                    <h1 className="db-capHeader">Capital info</h1>
                    <ul>
                        <li>
                            <h5>Total</h5>
                            <div className="db-capLine"></div>
                            <h6>R$<b>93.603.708</b>,47</h6>
                        </li>
                        <li>
                            <h5>Invested</h5>
                            <div className="db-capLine"></div>
                            <h6>R$<b>77.485.100</b>,00</h6>
                        </li>
                        <li>
                            <h5>Liquid</h5>
                            <div className="db-capLine"></div>
                            <h6>R$<b>16.119.608</b>,47</h6>
                        </li>
                    </ul>
                </div>


                <div className="db-copyright">
                    <header className="db-copyHeader">
                        <img src={actTrades} alt="" />
                        <img src={pwc} alt="" />
                    </header>
                    <p className="db-copyContent">
                        LHF LLC is authorized and regulated 
                        by Legal Entity Identifier Bloomberg number 2549004QPNK4IKNRCT42.
                        LHF LLC is an international company registered with the Belize Companies & Corporate 
                        Affairs Registry, registration number IFSC / 200 / LLC755.
                        Our custody is carried out by ActiveTrades and we are audited by PWC.
                    </p>
                    <footer className="db-copyFoot">
                        <img src={lhfgray} alt="" />
                        <h6>2023 © LHF Assset Management</h6>
                    </footer>
                </div>


            </div>

        </div>
    );
}

export default Dashboard;