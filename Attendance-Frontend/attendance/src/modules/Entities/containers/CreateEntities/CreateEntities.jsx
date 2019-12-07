import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import CreateStudent from '../CreateStudent/CreateStudent';
import CreateClassroom from '../CreateClassroom/CreateClassroom';
import CreateGroup from '../CreateGroup/CreateGroup';
import CreateTeacher from '../CreateTeacher/CreateTeacher';
import CreateAttendanceList from '../CreateAttendanceList/CreateAttendanceList';

import { GeneralContainer, InputContainer } from './CreateEntitiesStyleCom';

import { getAllGroups } from '../../actions/Actions';

export class CreateEntities extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.getAllGroups();
    }

    render() {
        return(
        <GeneralContainer>
            <InputContainer>
                <CreateStudent></CreateStudent>
            </InputContainer>
            <InputContainer>
                <CreateAttendanceList></CreateAttendanceList>
            </InputContainer>
            <InputContainer>
                <CreateTeacher></CreateTeacher>
            </InputContainer>
            <InputContainer>
                <CreateClassroom></CreateClassroom>
            </InputContainer>
            <InputContainer>
                <CreateGroup></CreateGroup>
            </InputContainer>


        </GeneralContainer>
        )
    }
}
const mapDispatchToProps = dispatch => {
    return {
        dispatch,
        ...bindActionCreators({ getAllGroups }, dispatch)
    }
}

export default connect(null, mapDispatchToProps)(CreateEntities);
