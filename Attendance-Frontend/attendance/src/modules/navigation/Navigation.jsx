import React, { Component } from "react";
import { connect } from 'react-redux';
import { NavLink } from "react-router-dom";


import { StyledNavigation, StyledNavigationMenu } from "./Navigation.style.jsx";

class Navigation extends Component {

    isAdmin = () => {
        if (this.props.isAdmin) {
            return (
                <li>
                    <NavLink to="/psbd/entities" activeClassName="active">
                        Create Entities
                            </NavLink>
                </li>
            )
        }
        else return(null);
    }

    render() {
        //if(this.props.type==="admin")
        return (
            <StyledNavigation>
                <StyledNavigationMenu>

                    {this.isAdmin()}

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
    type: state.login.login.type,
    isAdmin: state.login.login.isAdmin
})
export default connect(mapStateToProps, null)(Navigation);
