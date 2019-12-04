import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

//import { saveStudent } from '../../actions/Actions';
import TextInput from '../../components/TextInput';
import TextSelect from '../../components/TextSelect';
import Button from '../../components/Button';
import { ButtonContainer, StudentContainer } from '../CreateStudent/CreateStudentStyleComp';

import {saveStudent} from '../../actions/Actions';

export class CreateStudent extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      name: "",
      identityNumber: "",
      errors: {
        name: "",
        identityNumber: ""
      },
      backgroundSaveBtn: "#FF8F74",
      group:""
    }
  }
  onChange = (fieldName, value) => {
    this.setState({
      [fieldName]: value
    });

  };

  onChangeName = event => {
    this.onChange("name", event.target.value);
  }
  onChangeIdentityNumber = event => {
    this.onChange("identityNumber", event.target.value);
  }
  onChangeGroup= event => {
    this.onChange("group", event.target.value);
  }

  activateSaveButton = () => {
    const { backgroundSaveBtn } = this.state;
    if (this.state.errors.name === null && this.state.errors.identityNumber === null
        && this.state.name !== "" && this.state.identityNumber !== "") {
        this.setState({
            backgroundSaveBtn: "#32CD32"
        })
    }
    else {
        this.setState({
            backgroundSaveBtn: "#FF8F74"
        })
    }

}

  verifyInputName = (value) => {
    const { errors } = this.state
    if (this.state.name === null || this.state.name === "") {
      this.setState({
        errors: {
          ...errors,
          name: "Field name is required"
        }
      },()=>{this.activateSaveButton()});

    }
    else {
      this.setState({
        errors: {
          ...errors,
          name: null
        }
      },()=>{this.activateSaveButton()});

    }
  }

  verifyInputIdentityNumber = (value) => {
    const { errors } = this.state
    if (this.state.identityNumber === null || this.state.identityNumber === "") {
      this.setState({
        errors: {
          ...errors,
          identityNumber: "Field Identity Number is required"
        }
      },()=>{this.activateSaveButton()});

    }
    else {
      this.setState({
        errors: {
          ...errors,
          identityNumber: null
        }
      },()=>{this.activateSaveButton()});

    }
  }

  saveDateStudent = () => {
    if (this.state.errors.name === null && this.state.errors.identityNumber === null
        && this.state.name !== null && this.state.identityNumber !== null) {
        this.props.saveStudent(this.state.name,this.state.identityNumber,this.state.group);
        this.clearValue();
    }

}

  clearValue = () => {
    const { name, identityNumber,backgroundSaveBtn } = this.state;
    this.setState({
      identityNumber: "",
      name: "",
      backgroundSaveBtn: "#FF8F74"
    });
  }

  render() {
    return (
      <StudentContainer>
        <TextInput
          label="Student Full Name"
          error={this.state.errors.name}
          onChange={this.onChangeName}
          onBlur={this.verifyInputName}
          value={this.state.name}
        >

        </TextInput>
        <TextInput
          label="Identity Number"
          error={this.state.errors.identityNumber}
          onChange={this.onChangeIdentityNumber}
          onBlur={this.verifyInputIdentityNumber}
          value={this.state.identityNumber}>
        </TextInput>
        <TextSelect
          error={null}
          label="Group"
          onBlur={this.onChangeGroup}
          items={this.props.groupsList}
          height={"2.5rem"}>
        </TextSelect>
        <ButtonContainer>
          <Button
          background={this.state.backgroundSaveBtn}
          onClick={this.saveDateStudent}
          >
            Save Student
            
            </Button>
          <Button
            background="#32CD32"
            onClick={this.clearValue}
          >
            Cancel Values
          </Button>
        </ButtonContainer>
      </StudentContainer>


    );
  }

}

const mapDispatchToProps = dispatch => {
  return {
      dispatch,
      ...bindActionCreators({ saveStudent }, dispatch)
  }
}

const mapStateToProps = state => ({
  groupsList: state.entities.entitiesReducer.groups
})

export default connect(mapStateToProps,  mapDispatchToProps )(CreateStudent);