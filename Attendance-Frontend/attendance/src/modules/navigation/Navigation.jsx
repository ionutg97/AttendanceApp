import React, { Component } from "react";
import { NavLink } from "react-router-dom";
//import { withRouter } from "react-router-dom";
//import { connect } from "react-redux";
//import { bindActionCreators } from "redux";

import { StyledNavigation, StyledNavigationMenu, NavActions, } from "./Navigation.style.jsx";

class Navigation extends Component {
    constructor(props) {
        super(props);

    }

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
