import { connect } from 'react-redux';
import React, { Component } from 'react';
import { bindActionCreators } from "redux";

import {ButtonContainer} from '../CreateStudent/CreateStudentStyleComp'; 
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';

function CreateClassroom({ }) {

    return(
        <div>
            <TextInput
            label="Create a Classroom">
            </TextInput>
            <ButtonContainer>
                <Button>Save Classroom</Button>
                <Button>Cancel  Values</Button>
            </ButtonContainer>
        </div>
    );

}
const mapDispatchToProps = dispatch => ({
    ...bindActionCreators(
      {  }
    )
  });
  
  export default connect(null, { mapDispatchToProps })(CreateClassroom);