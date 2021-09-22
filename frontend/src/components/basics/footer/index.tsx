const Footer = () => {
  return (
    <footer className="footer mt-auto py-3 bg-dark">
      <div className="container">
        <p className="text-light">
          App desenvolvido por{" "}
          <a
            href="https://github.com/Japaonez"
            target="_blank"
            rel="noreferrer"
          >
            Jhonata Kumaki
          </a>
          <span>, </span>
          <a
            href="https://github.com/Paulo092"
            target="_blank"
            rel="noreferrer"
          >
            Paulo Sergio
          </a>
          <span>, </span>
          <a
            href="https://github.com/VictorDeLucca"
            target="_blank"
            rel="noreferrer"
          >
            Victor Delucca
          </a>
        </p>
        <p className="text-light">
          <small>
            <strong>Desenvolvimento de Aplicações WEB - BCC - UFJ</strong>
            <br />
            Disciplina de Desenvolvimento de Aplicações WEB:{" "}
            <a
              href="https://github.com/Paulo092/CadastroAgil-AGICAD"
              target="_blank"
              rel="noreferrer"
            >
              AGICAD
            </a>
          </small>
        </p>
      </div>
    </footer>
  );
};
export default Footer;
