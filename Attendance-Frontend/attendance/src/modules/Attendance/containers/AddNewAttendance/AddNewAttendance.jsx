import { connect } from 'react-redux';
import React, { Component } from 'react';
import { bindActionCreators } from "redux";

import {ButtonContainer} from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp'; 
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

function AddNewAttendance({ }) {

    return(
        <div>
            <TextInput
            label="Attendance Name"
            value="List number 4"
            disabled={true}>
            </TextInput>
            <TextInput
            label="Student Name">
            </TextInput>
            <TextSelect
            label="Select Group"> 
            </TextSelect>
            <ButtonContainer>
                <Button>Save</Button>
                <Button>Cancel</Button>
            </ButtonContainer>
        </div>
    );

}
const mapDispatchToProps = dispatch => ({
    ...bindActionCreators(
      {  }
    )
  });
  
  export default connect(null, { mapDispatchToProps })(AddNewAttendance);