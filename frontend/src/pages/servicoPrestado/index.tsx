import { useEffect, useState } from "react";
import { Link, useParams, useHistory } from "react-router-dom";
import "./styles.css";
import logoImage from "../../assets/images/builder.svg";
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
  usuarioPrestador: usu;
}

const ServicoPrestado: React.FC = () => {
  const [codigo, setCodigo] = useState(null);
  const [preco, setPreco] = useState(null);

  const [servico, setServico] = useState<serv>({
    codigo_servico: 0,
    nome_servico: "",
  });
  const [servicos, setServicos] = useState<serv[]>([]);

  const [usuario, setUsuario] = useState<usu>({
    codigo_usuario: 0,
    nome_usuario: "",
  });
  const [usuarios, setUsuarios] = useState<usu[]>([]);

  const [prestador, setPrestador] = useState<pres>({
    codigo_prestador: 0,
    endereco_prestador: "",
    usuarioPrestador: usuario
  });
  const [prestadores, setPrestadores] = useState<pres[]>([]);

  const { servPresId } = useParams() as any;

  const token = localStorage.getItem("token");

  const history = useHistory();

  async function getUsuarios() {
    try {
      const response = await api.get(`v1/agicad/usuarios`);
      setUsuarios(response.data._embedded.usuarioDTOList);
    } catch (error) {
      alert("Erro na busca do usuário. Tente novamente");
      history.push("/servicosPrestados");
    }
  }

  async function getServicos() {
    try {
      const response = await api.get(`/v1/agicad/servicos`);
      setServicos(response.data._embedded.servicoDTOList);
    } catch (error) {
      alert("Erro na busca do serviço. Tente novamente " + error);
      history.push("/servicosPrestados");
    }
  }

  async function getPrestadores() {
    try {
      const response = await api.get(`/v1/agicad/prestadores`);
      setPrestadores(response.data._embedded.prestadorDTOList);
    } catch (error) {
      alert("Erro na busca do prestador. Tente novamente " + error);
      history.push("/servicosPrestados");
    }
  }

  async function getServicoPrestado() {
    try {
      const response = await api.get(`/v1/agicad/servicos/${servPresId}`);

      setCodigo(response.data.codigo);
      setPreco(response.data.preco);

      const _codigo_usuario = response.data.usuario.codigo_usuario;
      const _nome_usuario = response.data.usuario.nome_usuario;
      const _codigo_servico = response.data.servico.codigo_servico;
      const _nome_servico = response.data.servico.nome_servico;
      const _codigo_prestador = response.data.prestador.codigo_prestador;
      const _endereco_prestador = response.data.prestador.endereco_prestador;
      const _usuario = response.data.prestador.usuario;

      setUsuario({codigo_usuario: _codigo_usuario, nome_usuario: _nome_usuario})
      setServico({codigo_servico: _codigo_servico, nome_servico: _nome_servico})
      setPrestador({codigo_prestador: _codigo_prestador, endereco_prestador: _endereco_prestador, usuarioPrestador: _usuario})

    } catch (error) {
      alert("Erro na busca do serviço prestado. Tente novamente " + error);
      history.push("/servicosPrestados");
    }
  }

  useEffect(() => {
    getUsuarios();
    getPrestadores();
    getServicos();
    if (servPresId == "0") return;
    else getServicoPrestado();
  }, [servPresId]);

  async function saveOrUpdate(e: any) {
    e.preventDefault();

    const data = {
      codigo,
      servico,
      prestador,
      preco
    };

    try {
      if (servPresId == "0") {
        await api.post(`v1/agicad/servicos`, data, {
        });
      } else {
        data.codigo = codigo;
        await api.post(`v1/agicad/servicos`, data, {
        });
      }
      history.push("/servicosPrestadores");
    } catch (error) {
      alert(
        "Erro na inclusão do serviço prestado. " + error + " Tente novamente!"
      );
    }
  }

  return (
    <div className="new-serv-container">
      <div className="content">
        <section className="form">
          <h2>Cadastro de Serviços!</h2>
          <img src={logoImage} alt="Gestão de Obras" />
          <h3>{servPresId == "0" ? "Adicionar" : "Atualizar"} Serviço</h3>
          <p>
            Entre com as informações do serviço e clique em Adicionar! Ou
            clique em Listar para ver os serviços cadastrados.
          </p>
        </section>
        <form onSubmit={saveOrUpdate}>
        <select
            value={servico.codigo_servico}
            onChange={(e) =>
              setServico({
                codigo_servico: parseInt(e.target.value),
                nome_servico: "",
              })
            }
          >
            {servicos.map((item, index) => (
              <option value={item.codigo_servico}>{item.nome_servico}</option>
            ))}
          </select>
          <select
            value={prestador.codigo_prestador}
            onChange={(e) =>
              setPrestador({
                codigo_prestador: parseInt(e.target.value),
                endereco_prestador: "",
                usuarioPrestador: usuario,
              })
            }
          >
            {prestadores.map((item, index) => (
              <option value={item.codigo_prestador}>{item.endereco_prestador}</option>
            ))}
          </select>
          <button className="button" type="submit">
            {servPresId == "0" ? "Adicionar" : "Atualizar"}
          </button>
          <Link to="/servicos">
            <button className="button"> Listar</button>
          </Link>
        </form>
      </div>
    </div>
  );
};

export default ServicoPrestado;
