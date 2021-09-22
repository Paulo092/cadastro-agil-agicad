import { useEffect, useState } from "react";
import { Link, useParams, useHistory } from "react-router-dom";
import "./styles.css";
import logoImage from "../../assets/images/builder.svg";
import api from "../../services/api";

type usu = {
  codigo_usuario: number;
  nome_usuario: string;
};

const Prestador: React.FC = () => {
  const [codigo_prestador, setCodigo] = useState(null);
  const [endereco_prestador, setEndereco] = useState("");
  const [nome_usuario, setNomeUsuario] = useState("");

  const [usuario, setUsuario] = useState<usu>({
    codigo_usuario: 0,
    nome_usuario: "",
  });
  const [usuarios, setUsuarios] = useState<usu[]>([]);

  const { presId } = useParams() as any;

  const token = localStorage.getItem("token");

  const history = useHistory();

  async function getUsuarios() {
    try {
      const response = await api.get(`v1/agicad/usuarios`);
      setUsuarios(response.data._embedded.usuarioDTOList);
    } catch (error) {
      alert("Erro na busca do usuário. Tente novamente");
      history.push("/prestadores");
    }
  }

  async function getPrestador() {
    try {
      const response = await api.get(`/v1/agicad/prestadores/${presId}`);

      setCodigo(response.data.codigo_prestador);
      setEndereco(response.data.endereco_prestador);
      const _codigo_usuario = response.data.usuario.codigo_usuario;
      const _nome_usuario = response.data.usuario.nome_usuario;
      setUsuario({
        codigo_usuario: _codigo_usuario,
        nome_usuario: _nome_usuario,
      });
    } catch (error) {
      alert("Erro na busca do prestador. Tente novamente " + error);
      history.push("/prestadores");
    }
  }

  useEffect(() => {
    getUsuarios();
    if (presId === "0") return;
    else getPrestador();
  }, [presId]);

  async function saveOrUpdate(e: any) {
    e.preventDefault();

    const data = {
      codigo_prestador,
      endereco_prestador,
      usuario,
    };

    try {
      if (presId == "0") {
        await api.post(`v1/agicad/prestadores`, data);
      } else {
        data.codigo_prestador = codigo_prestador;
        await api.post(`v1/agicad/prestadores`, data);
      }
      history.push("/prestadores");
    } catch (error) {
      alert("Erro na inclusão do prestador. " + error + " Tente novamente!");
    }
  }

  return (
    <div className="new-serv-container">
      <div className="content">
        <section className="form">
          <h2>Cadastro de Prestador!</h2>
          <img src={logoImage} alt="Cadastro Ágil" />
          <h3>{presId == "0" ? "Adicionar" : "Atualizar"} Prestador</h3>
          <p>
            Entre com as informações do prestador e clique em Adicionar! Ou
            clique em Listar para ver os prestadores cadastrados.
          </p>
        </section>
        <form onSubmit={saveOrUpdate}>
          <input
            placeholder="Endereço do Prestador"
            value={endereco_prestador}
            onChange={(e) => setEndereco(e.target.value)}
          />
          <select
            value={usuario.codigo_usuario}
            onChange={(e) =>
              setUsuario({
                codigo_usuario: parseInt(e.target.value),
                nome_usuario: "",
              })
            }
          >
            {usuarios.map((item, index) => (
              <option value={item.codigo_usuario}>{item.nome_usuario}</option>
            ))}
          </select>
          <button className="button" type="submit">
            {presId == "0" ? "Adicionar" : "Atualizar"}
          </button>
          <Link to="/prestadores">
            <button className="button"> Listar</button>
          </Link>
        </form>
      </div>
    </div>
  );
};

export default Prestador;
