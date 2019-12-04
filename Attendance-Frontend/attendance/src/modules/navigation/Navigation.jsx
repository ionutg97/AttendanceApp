import React, { Component } from "react";
import { connect } from 'react-redux';
import { NavLink } from "react-router-dom";


import { StyledNavigation, StyledNavigationMenu } from "./Navigation.style.jsx";

class Navigation extends Component {

    render() {
        //if(this.props.type==="admin")
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
        // else
        // return(
        //     null
        // );

    }
}
const mapStateToProps = state => ({
    type: state.login.login.type
  })
  
export default connect (mapStateToProps,null)(Navigation);
