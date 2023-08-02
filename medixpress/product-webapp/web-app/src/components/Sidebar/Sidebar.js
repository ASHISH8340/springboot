import React,{useState} from "react";
import { ProSidebar, Menu, MenuItem, SubMenu,  SidebarHeader,SidebarFooter, } from 'react-pro-sidebar';
import { FaGem, FaList, FaRegHeart } from "react-icons/fa";
import { FiHome, FiLogOut, FiArrowLeftCircle, FiArrowRightCircle } from "react-icons/fi";
import { RiPencilLine } from "react-icons/ri";
import { BiCog } from "react-icons/bi";
import 'react-pro-sidebar/dist/css/styles.css';
import './style.css'
function Sidebar(){
    const [menuCollapse, setMenuCollapse] = useState(true)

    //create a custom function that will change menucollapse state from false to true and true to false
  const menuIconClick = () => {
    //condition checking to change state from true to false and vice versa
    menuCollapse ? setMenuCollapse(false) : setMenuCollapse(true);
  };
    return(
                <div id="header" onMouseEnter={menuIconClick} onMouseLeave={menuIconClick}>
                           <ProSidebar collapsed={menuCollapse}>
  <Menu iconShape="square">
    <MenuItem icon={<FaGem />} >Dashboard</MenuItem>
    <MenuItem icon={<FaList />}>Category</MenuItem>
              <MenuItem icon={<FaRegHeart />}>Favourite</MenuItem>
              <MenuItem icon={<RiPencilLine />}>Author</MenuItem>
              <MenuItem icon={<BiCog />}>Settings</MenuItem>
  </Menu>
</ProSidebar>
                </div>
    )
}
export default Sidebar;