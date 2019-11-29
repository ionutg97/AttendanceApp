import React, { Component }  from 'react';

import CreateStudent from '../CreateStudent/CreateStudent';
import CreateClassroom from '../CreateClassroom/CreateClassroom';
import CreateGroup from '../CreateGroup/CreateGroup';
import CreateTeacher from '../CreateTeacher/CreateTeacher';
import CreateAttendanceList from '../CreateAttendanceList/CreateAttendanceList';

import {GeneralContainer, InputContainer} from './CreateEntitiesStyleCom'; 

export class CreateEntities extends Component {
    constructor(props) {
        super(props);
    }

    render(){
        return (
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
        );
    }
}

export default CreateEntities;
