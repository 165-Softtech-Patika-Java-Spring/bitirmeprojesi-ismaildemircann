import { useEffect, useCallback, useState } from 'react';
import './App.css';
import { Route, Routes, useNavigate } from 'react-router-dom';
import Menu from './Components/menu/Menu';
import HomePage from './Components/homePage/HomePage';
import ErrorPage from './Components/error/ErrorPage';
import Dashboard from './Components/dashboard/Dashboard';
import LoginPage from './Components/loginPage/LoginPage';
import SignUpPage from './Components/signUpPage/SignUpPage';

const App = () => {

  const navigate = useNavigate();

  const [isLogged, setIsLogged] = useState(false);

  useEffect(() => {
    getIsLogged();
  });

  const getIsLogged = () => {
    const token = sessionStorage.getItem('token');
    token ? setIsLogged(true) : setIsLogged(false);
  }

  const login = useCallback(
    () => {
      navigate('/dashboard', { replace: true });
    },
    [navigate],
  );

  const logout = useCallback(
    () => {
      sessionStorage.clear();
    },
    [],
  );

  return (
    <div className="App">

      <Menu isLoggedOn={isLogged} logout={logout}></Menu>

      <Routes>
        <Route path='/' element={<HomePage />}></Route>
        <Route path='*' element={<ErrorPage />}></Route>
        <Route path='/dashboard' element={<Dashboard />}></Route>
        <Route path='login' element={<LoginPage login={login} />}></Route>
        <Route path='sign-up' element={<SignUpPage />}></Route>
      </Routes>

    </div>
  );
}

export default App;

