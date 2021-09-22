import NavBar from "../../components/basics/navbar";
import Footer from "../../components/basics/footer";
import { Link } from "react-router-dom";
import { FiLogIn } from "react-icons/fi";

import "./styles.css";
import logo from "../../assets/images/builder.svg";
import Sidebar from "../../components/basics/sidebar";

const Home: React.FC = () => {
  return (
    <>
      <div id="sidebar">
        <Sidebar />
      </div>
      <div id="header">
        <NavBar />
      </div>
      <div id="conteudo">
        <div id="contentl">
          <h1>Sistema de Cadastro Ágil</h1>
          <p>
          Agiliza procedimentos de agendamentos e cadastros entre clientes 
          e prestadores de serviços, proporcionando tranquilidade e 
          comodidade para ambos, poupando tempo e otimizando relações.
          </p>
          <Link to="/login">
            <span>
              <FiLogIn />
            </span>
            <strong>Acessar o sistema!</strong>
          </Link>
        </div>
        <div id="contentr">
          <img src={logo} alt="Cadastro Ágil" />
        </div>
      </div>
      <div id="footer">
        <Footer />
      </div>
    </>
  );
};

export default Home;