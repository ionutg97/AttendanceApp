import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../CreateStudent/CreateStudentStyleComp';
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';
import TextSelect from '../../components/TextSelect';

function CreateAttendanceList({ }) {

    return (
        <div>
            <TextInput
                label="Attendance name">
            </TextInput>
            <TextInput
                label="Week ">
            </TextInput>
            <TextSelect
                label="Type">
            </TextSelect>
            <TextSelect
                label="Group(s)">
            </TextSelect>
            <ButtonContainer>
                <Button>Save Attendance</Button>
                <Button>Cancel Values</Button>
            </ButtonContainer>
        </div>
    );

}
const mapDispatchToProps = dispatch => ({
    ...bindActionCreators(
        {}
    )
});

export default connect(null, { mapDispatchToProps })(CreateAttendanceList);