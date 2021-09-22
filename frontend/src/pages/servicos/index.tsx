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

interface iServicos {
  codigo_servico: number;
  nome_servico: string;
  descricao_servico: string;
}

const Servicos: React.FC = () => {
  const [servicos, setServicos] = useState<iServicos[]>([]);
  const [page, setPage] = useState(0);

  const username = localStorage.getItem("username");
  const token = localStorage.getItem("token");

  const history = useHistory();

  async function logout() {
    localStorage.clear();
    history.push("/");
  }

  async function editServico(id: any) {
    try {
      history.push(`servico/${id}`);
    } catch (error) {
      alert("Erro na edição do serviço. " + error + "Tente novamente");
    }
  }

  async function deleteServico(id: any) {
    try {
      await api.delete(`v1/agicad/servicos/${id}`, {});
      alert("Exclusão com sucesso");
      loadServicos();
    } catch (error) {
      alert("Erro na edição do serviço. " + error + "Tente novamente");
    }
  }

  async function loadServicos() {
    const response = await api.get("v1/agicad/servicos", {
      params: {
        page: page,
        limite: 4,
        direction: "asc",
      },
    });

    setServicos(response.data._embedded.servicoDTOList);
  }

  useEffect(() => {
    loadServicos();
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
              loadServicos();
            }}
            type="button"
          >
            <FiArrowLeftCircle size={18} color="#251FC5" />
          </button>
          <Link className="button" to="/servico/0">
            Novo Serviço!
          </Link>
          <button onClick={() => {
             setPage(page + 1);
              loadServicos();
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

      <h1>Serviços Cadastrados</h1>

      <Table striped bordered hover className="text-center">
        <thead>
          <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {servicos.map((s) => (
            <tr key={s.codigo_servico}>
              <td>{s.codigo_servico}</td>
              <td>{s.nome_servico}</td>
              <td>{s.descricao_servico}</td>
              <td>
                <button
                  onClick={() => editServico(s.codigo_servico)}
                  type="button"
                >
                  <FiEdit size={20} color="#251FC5" />
                </button>

                <button
                  onClick={() => deleteServico(s.codigo_servico)}
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
export default Servicos;
