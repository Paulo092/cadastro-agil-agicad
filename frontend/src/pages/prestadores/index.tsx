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

type usu = {
  codigo_usuario: number,
  nome_usuario: string
}

interface iPrestadores {
  codigo_prestador: number;
  endereco_prestador: string;
  usuario: usu;
}

const Prestadores: React.FC = () => {
  const [prestadores, setPrestadores] = useState<iPrestadores[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push("/");
  }

  async function editPrestador(id: any) {
    try {
      history.push(`prestador/${id}`);
    } catch (error) {
      alert("Erro na edição do prestador. " + error + "Tente novamente");
    }
  }

  async function deletePrestador(id: any) {
    try {
      await api.delete(`v1/agicad/prestadores/${id}`, {});
      alert("Exclusão com sucesso");
      loadPrestadores();
    } catch (error) {
      alert("Erro na edição do prestador. " + error + "Tente novamente");
    }
  }

  async function loadPrestadores() {
    const response = await api.get("v1/agicad/prestadores", {
      params: {
        page: page,
        limite: 4,
        direction: "asc",
      },
    });

    setPrestadores(response.data._embedded.prestadorDTOList);
  }

  useEffect(() => {
    loadPrestadores();
  }, [page]);

  return (
    <div className="prestador-container">
      <header>
        <img src={logoImage} alt="Cadastro Ágil" />
        <span>
          Bem-vindo, <strong>{username?.toUpperCase()}</strong>!
        </span>
        <div className="subheader">
          <button
            onClick={() => {
              setPage(page - 1);
              loadPrestadores();
            }}
            type="button"
          >
            <FiArrowLeftCircle size={18} color="#251FC5" />
          </button>
          <Link className="button" to="/prestador/0">
            Novo Prestador!
          </Link>
          <button onClick={() => {
             setPage(page + 1);
              loadPrestadores();
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

      <h1>Prestadores Cadastrados</h1>

      <Table striped bordered hover className="text-center">
        <thead>
          <tr>
            <th>Código</th>
            <th>Endereço</th>
            <th>Nome do prestador</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {prestadores.map((p) => (
            <tr key={p.codigo_prestador}>
              <td>{p.codigo_prestador}</td>
              <td>{p.endereco_prestador}</td>
              <td>{p.usuario.codigo_usuario} - {p.usuario.nome_usuario}</td>
              <td>
                <button
                  onClick={() => editPrestador(p.codigo_prestador)}
                  type="button"
                >
                  <FiEdit size={20} color="#251FC5" />
                </button>

                <button
                  onClick={() => deletePrestador(p.codigo_prestador)}
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
export default Prestadores;
