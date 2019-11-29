import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { saveStudent } from '../../actions/Actions';
import TextInput from '../../components/TextInput';
import TextSelect from '../../components/TextSelect';
import Button from '../../components/Button';
import { ButtonContainer, StudentContainer } from '../CreateStudent/CreateStudentStyleComp';

function CreateStudent({ }) {

  return (
    <StudentContainer>
      <TextInput
        label="Student Full Name">

      </TextInput>
      <TextInput
        label="Identity Number">
      </TextInput>
      <TextSelect
        label="Group">
      </TextSelect>
      <ButtonContainer>
        <Button>Save Student</Button>
        <Button>Reset Values</Button>
      </ButtonContainer>
    </StudentContainer>


  );
}

const mapDispatchToProps = dispatch => ({
  ...bindActionCreators(
    { saveStudent }
  )
});

export default connect(null, { mapDispatchToProps })(CreateStudent);