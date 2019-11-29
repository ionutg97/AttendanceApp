import { connect } from 'react-redux';
import React, { Component } from 'react';
import { bindActionCreators } from "redux";

import {ButtonContainer} from '../CreateStudent/CreateStudentStyleComp'; 
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';

function CreateTeacher({ }) {

    return(
        <div>
            <TextInput
            label="Teacher name">
            </TextInput>
            <TextInput
            label="Discipline name">
            </TextInput>
            <ButtonContainer>
                <Button>Save Teacher</Button>
                <Button>Cancel Values</Button>
            </ButtonContainer>
        </div>
    );

}
const mapDispatchToProps = dispatch => ({
    ...bindActionCreators(
      {  }
    )
  });
  
  export default connect(null, { mapDispatchToProps })(CreateTeacher);