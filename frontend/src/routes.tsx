import { Route, BrowserRouter, Switch } from 'react-router-dom'
import Home from './pages/home';
import Login from './pages/login';
import Servicos from './pages/servicos';
import Servico from './pages/servico';
// import Dashboard from './pages/dashboard'
import Prestadores from './pages/prestadores';
import Prestador from './pages/prestador';
import ServicoPrestado from './pages/servicoPrestado';
import ServicosPrestados from './pages/servicosPrestados';


const Routes = () => {
    return(
        <BrowserRouter>
            <Switch>
                <Route component={ Home } path="/" exact />
                <Route component={ Login } path="/login" />
                {/* <Route component={ Home } path="/home" /> */}
                <Route component={ Servicos } path="/servicos" />
                <Route component={ Servico } path="/servico/:servId" />
                <Route component={ Prestadores } path="/prestadores" />
                <Route component={ Prestador } path="/prestador/:presId" />
                <Route component={ ServicoPrestado } path="/servicoprestado/:servPresId" />
                <Route component={ ServicosPrestados } path="/servicosprestados" />
            </Switch>
        </BrowserRouter>
    )
}
export default Routes;