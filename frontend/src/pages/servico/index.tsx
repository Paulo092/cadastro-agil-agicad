import { useEffect, useState } from "react";
import { Link, useParams, useHistory } from "react-router-dom";
import "./styles.css";
import logoImage from "../../assets/images/builder.svg";
import api from "../../services/api";

const Servico: React.FC = () => {
  const [codigo_servico, setCodigo] = useState(null);
  const [nome_servico, setNome] = useState("");
  const [descricao_servico, setDescricao] = useState("");

  const { servId } = useParams() as any;

  const token = localStorage.getItem("token");

  const history = useHistory();

  async function getServico() {
    try {
      const response = await api.get(`/v1/agicad/servicos/${servId}`);

      setCodigo(response.data.codigo_servico);
      setNome(response.data.nome_servico);
      setDescricao(response.data.descricao_servico);
    } catch (error) {
      alert("Erro na busca do serviço. Tente novamente " + error);
      history.push("/servicos");
    }
  }

  useEffect(() => {
    if (servId == "0") return;
    else getServico();
  }, [servId]);

  async function saveOrUpdate(e: any) {
    e.preventDefault();

    const data = {
      codigo_servico,
      nome_servico,
      descricao_servico,
    };

    try {
      if (servId == "0") {
        await api.post(`v1/agicad/servicos`, data, {
        });
      } else {
        data.codigo_servico = codigo_servico;
        await api.post(`v1/agicad/servicos`, data, {
        });
      }
      history.push("/servicos");
    } catch (error) {
      alert(
        "Erro na inclusão do serviço. " + error + " Tente novamente!"
      );
    }
  }

  return (
    <div className="new-serv-container">
      <div className="content">
        <section className="form">
          <h2>Cadastro de Serviços!</h2>
          <img src={logoImage} alt="Gestão de Obras" />
          <h3>{servId == "0" ? "Adicionar" : "Atualizar"} Serviço</h3>
          <p>
            Entre com as informações do serviço e clique em Adicionar! Ou
            clique em Listar para ver os serviços cadastrados.
          </p>
        </section>
        <form onSubmit={saveOrUpdate}>
          <input
            placeholder="Nome do Serviço"
            value={nome_servico}
            onChange={(e) => setNome(e.target.value)}
          />
          <input
            placeholder="E-mail do Serviço"
            value={descricao_servico}
            onChange={(e) => setDescricao(e.target.value)}
          />
          <button className="button" type="submit">
            {servId == "0" ? "Adicionar" : "Atualizar"}
          </button>
          <Link to="/servicos">
            <button className="button"> Listar</button>
          </Link>
        </form>
      </div>
    </div>
  );
};

export default Servico;
