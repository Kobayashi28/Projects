import react from 'react';

import './style.css';

import loginImage from '../../assets/loginImage.svg';
import logo from '../../assets/lhflogogray.svg';
import wLogo from '../../assets/logo.svg';

import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';

import { useNavigate} from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Login = ()=>{

    const navigate = useNavigate();

    return(

        <div className="container">
            <div className="containerWrapper">
                <div className="loginImage">
                    <div className="img"></div>
                    <div className="gradient">
                        <h1>Investing<br/><b>Solutions</b></h1>
                        <p>A diverse array of investment strategies focused in secure our investor wealth.</p>
                    </div>
                </div>
                <section className="emailLogin section">
                    <img src={logo} alt="" />
                    <h1>Login to dashboard</h1>
                    <h2>This is a secure site. <br/> Please insert your email to continue.</h2>
                    <input id='email'type="email" placeholder='email@gmail.com'/>
                    <button onClick={handleEmail}>CONTINUE</button>
                    <h3>Don't have an account?</h3>
                    <button onClick={()=>{handleSectionChange(3)}} className='submit'>REGISTER</button>
                </section>
                <section className="passwordLogin section">
                    <FontAwesomeIcon onClick={()=>{handleSectionChange(0)}} className='arrowLeft' icon={faArrowLeft} />
                    <img src={logo} alt="" />
                    <h1>Login to dashboard</h1>
                    <h2>Please enter your PIN</h2>
                    <input id='password' type="password" placeholder='**************'/>
                    <h5 className='passrecovery' onClick={()=>{handleSectionChange(2)}}>Forgot your PIN?</h5>
                    <ul>
                        <li>
                            <button></button>
                            <h4>0 or 4</h4>
                            <button></button>
                        </li>
                        <li>
                            
                            <button></button>
                            <h4>1 or 5</h4>
                            <button></button>
                        </li>
                        <li>
                            
                            <button></button>
                            <h4>2 or 6</h4>
                            <button></button>
                        </li>
                        <li>
                            
                            <button></button>
                            <h4>3 or 8</h4>
                            <button></button>
                        </li>
                        <li>
                            <button></button>
                            <h4>7 or 9</h4>
                            <button></button>
                        </li>
                        <li>
                            <button className='cancelbtn'></button>
                        </li>
                    </ul>
                    <button onClick={()=>{handleLogin(navigate)}} className='submit'>LOGIN</button>

                </section>
                <section className="pwRecovery section">
                    <FontAwesomeIcon onClick={()=>{handleSectionChange(1)}} className='arrowLeft' icon={faArrowLeft} />
                    <img src={logo} alt="" />
                    <h1 style={{fontWeight:'lighter'}}>Forgot your PIN?</h1>
                    <h2 style={{fontSize:'0.9em'}}>Confirm your e-mail and we’ll send the instructions.</h2>
                    <input id='emailToRecover'type="email" placeholder='email@gmail.com'/>
                    <button className='submit'>CONTINUE</button>
                </section>
                <section className="register section">
                    <header>
                        <img src={wLogo} alt="" />
                        <div>
                            <h4>Register</h4>
                            <h6>Please, enter the info below</h6>
                        </div>
                    </header>
                    <div className="registerContent">
                        <form>
                            <input type="text" name='username' placeholder='Full name' className='regForm' />
                            <input type="text" name='address' placeholder='Address' className='regForm' />
                            <input type="text" name='cep' placeholder='CEP' className='regForm' />
                            <input type="text" name='city' placeholder='City' className='regForm' />
                            <select name="state" className='regForm'>
                                <option value="none">State</option>
                                <option value="sp">São Paulo</option>
                                <option value="es">Espiríto Santo</option>
                            </select>
                            <select name="country" className='regForm'>
                                <option value="none">Country</option>
                                <option value="br">Brasil</option>
                            </select>
                            <input type="email" name='email' placeholder='Email' className='regForm' />
                            <input type="text" name='complement' placeholder='Complement' className='regForm' />
                            <div className="docs">
                                <h4>We need photos of your driver's license to verify your identity.</h4>
                                <input type="file" id='front' name='front' className='regForm' />
                                <label className='docLabel' htmlFor="front">
                                    <h5>FRONT of the document</h5>
                                    <div className='browse'>BROWSE</div>
                                </label>
                                <input type="file" id='back' name='back' className='regForm' />
                                <label className='docLabel' htmlFor="back">
                                    <h5>BACK of the document</h5>
                                    <div className='browse'>BROWSE</div>
                                </label>
                            </div>                        
                        </form>
                    </div>
                    <footer>
                        <button onClick={handleFormCancel}>CANCEL</button>
                        <button onClick={handleFormSubmit} className="submit">CONTINUE</button>
                    </footer>
                </section>
            </div>
        </div>
    );
}

const handleFormSubmit = async ()=>{
    var regForm = document.getElementsByClassName('regForm');
    await fetch('http://127.0.0.1:7654/user/create',{
        method:"POST",
        headers: {
            "Content-type": "application/json",
        },
        body: JSON.stringify({
            username:((regForm[0] as HTMLInputElement).value),
            address:((regForm[1] as HTMLInputElement).value),
            cep:((regForm[2] as HTMLInputElement).value),
            city:((regForm[3] as HTMLInputElement).value),
            state:((regForm[4] as HTMLSelectElement).value),
            country:((regForm[5] as HTMLSelectElement).value),
            email:((regForm[6] as HTMLInputElement).value),
            complement:((regForm[7] as HTMLInputElement).value),
            frontDoc:((regForm[8] as HTMLInputElement).value),
            backDoc:((regForm[9] as HTMLInputElement).value)
        }),
        
    }).then((res)=>{ console.log(res.json)});
}

const handleFormCancel = ()=>{
    var rf = document.getElementsByClassName('regForm');
    for(var i = 0; i < rf.length; i++){
        if(rf[i].tagName == 'SELECT')
            (rf[i] as HTMLSelectElement).selectedIndex = 0;
        else
            (rf[i] as HTMLInputElement).value='';
    }
    handleSectionChange(0);
}

const handleSectionChange = (goTo:number)=>{
    var sections = document.getElementsByClassName('section');
    var loginImage = document.getElementsByClassName('loginImage')[0] as HTMLElement;
    loginImage.style.display='flex';
    for(var i = 0; i < sections.length; i++){
        (sections[i] as HTMLElement).style.display='none';
    }
    if(goTo == 3){
        loginImage.style.display='none';
    }
    (sections[goTo] as HTMLElement).style.display='flex';
}
const handlePassRecovery = ()=>{

}
const handleEmail = ()=>{
    var email = (document.getElementById('email') as HTMLInputElement).value;
    if(email == "")
        alert("campo vazio");
    else
        handleSectionChange(1);
}
const handleLogin = (navigate:any)=>{
    var password = (document.getElementById('password') as HTMLInputElement).value;
    if(password == '')
        alert('campo vazio');
    else
        navigate("/dashboard");

    
}

export default Login;