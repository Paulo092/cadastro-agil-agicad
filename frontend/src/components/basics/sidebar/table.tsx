import * as FaIcons from "react-icons/fa";
import * as AiIcons from "react-icons/ai";
import * as IoIcons from "react-icons/io";

export const SidebarData = [
  {
    title: "Home",
    path: "/",
    icon: <AiIcons.AiFillHome />,
    cName: "nav-text",
  },
  {
    title: "Serviços",
    path: "/servicos",
    icon: <FaIcons.FaCartPlus />,
    cName: "nav-text",
  },
  {
    title: "Prestadores",
    path: "/prestadores",
    icon: <IoIcons.IoMdPeople />,
    cName: "nav-text",
  },
  {
    title: "Serviços Prestados",
    path: "/servicosPrestados",
    icon: <FaIcons.FaEnvelopeOpenText />,
    cName: "nav-text",
  }
];
