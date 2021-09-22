import { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import {
  FiPower,
  FiEdit,
  FiTrash2,
  FiArrowRightCircle,
  FiArrowLeftCircle,
} from "react-icons/fi";
import { Table } from "react-bootstrap";
import logoImage from "../../assets/images/builder.svg";
import "./styles.css";
import api from "../../services/api";

type serv = {
  codigo_servico: number;
  nome_servico: string;
}
type usu = {
  codigo_usuario: number;
  nome_usuario: string;
}

type pres = {
  codigo_prestador: number;
  endereco_prestador: string;
  usuario: usu;
}

interface iServicosPrestados {
  codigo: number;
  servico: serv;
  prestador: pres;
  preco: number;
}

const ServicosPrestados: React.FC = () => {
  const [servicosPrestados, setServicosPrestados] = useState<iServicosPrestados[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push("/");
  }

  async function editServicoPrestado(id: any) {
    try {
      history.push(`servico/${id}`);
    } catch (error) {
      alert("Erro na edição do serviço prestado. " + error + "Tente novamente");
    }
  }

  async function deleteServicoPrestado(id: any) {
    try {
      await api.delete(`v1/agicad/servicos_prestados/${id}`, {});
      alert("Exclusão com sucesso");
      loadServicosPrestados();
    } catch (error) {
      alert("Erro na edição do serviço prestado. " + error + "Tente novamente");
    }
  }

  async function loadServicosPrestados() {
    const response = await api.get("v1/agicad/servicos_prestados", {
      params: {
        page: page,
        limite: 4,
        direction: "asc",
      },
    });

    setServicosPrestados(response.data._embedded.servicoPrestadoDTOList);
  }

  useEffect(() => {
    loadServicosPrestados();
  }, [page]);

  return (
    <div className="servico-container">
      <header>
        <img src={logoImage} alt="Cadastro Ágil" />
        <span>
          Bem-vindo, <strong>{username?.toUpperCase()}</strong>!
        </span>
        <div className="subheader">
          <button
            onClick={() => {
              setPage(page - 1);
              loadServicosPrestados();
            }}
            type="button"
          >
            <FiArrowLeftCircle size={18} color="#251FC5" />
          </button>
          <Link className="button" to="/servicoPrestado/0">
            Novo Serviço Prestado!
          </Link>
          <button onClick={() => {
             setPage(page + 1);
              loadServicosPrestados();
            }}
            type="button"
          >
            <FiArrowRightCircle size={18} color="#251FC5" />
          </button>
          <button onClick={logout} type="button">
            <FiPower size={18} color="#251FC5" />
          </button>
        </div>
      </header>

      <h1>Serviços Prestados Cadastrados</h1>

      <Table striped bordered hover className="text-center">
        <thead>
          <tr>
            <th>Código</th>
            <th>Serviço</th>
            <th>Prestador</th>
            <th>Endereço</th>
            <th>Preço</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {servicosPrestados.map((sP) => (
            <tr key={sP.codigo}>
              <td>{sP.codigo}</td>
              <td>{sP.servico.codigo_servico} - {sP.servico.nome_servico}</td>
              <td>{sP.prestador.usuario.codigo_usuario} - {sP.prestador.usuario.nome_usuario}</td>
              <td>{sP.prestador.endereco_prestador}</td>
              <td>{sP.preco}</td>
              <td>
                <button
                  onClick={() => deleteServicoPrestado(sP.codigo)}
                  type="button"
                >
                  <FiTrash2 size={20} color="#251FC5" />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};
export default ServicosPrestados;
