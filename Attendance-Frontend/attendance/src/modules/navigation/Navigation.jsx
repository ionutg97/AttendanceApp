import React, { Component } from "react";
import { NavLink } from "react-router-dom";


import { StyledNavigation, StyledNavigationMenu } from "./Navigation.style.jsx";

class Navigation extends Component {

    render() {
        return (
            <StyledNavigation>
                <StyledNavigationMenu>
                    <li>
                        <NavLink to="/psbd/entities" activeClassName="active">
                            Create Entities
                            </NavLink>
                    </li>
                    <li>
                        <NavLink to="/psbd/attendance" activeClassName="active">
                            Attendance
                            </NavLink>
                    </li>
                </StyledNavigationMenu>
            </StyledNavigation>
        );
    }
}

export default Navigation;
