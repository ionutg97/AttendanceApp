import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../CreateStudent/CreateStudentStyleComp';
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';

import { saveTeachers } from '../../actions/Actions';

export class CreateTeacher extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: "",
            discipline: "",
            errors: {
                name: "",
                discipline: ""
            },
            backgroundSaveBtn: "#FF8F74"
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
    onChangeDiscipline = event => {
        this.onChange("discipline", event.target.value);   
    }


    activateSaveButton = () => {
        const { backgroundSaveBtn } = this.state;
        if (this.state.errors.name === null && this.state.errors.discipline === null
            && this.state.name !== "" && this.state.discipline !== "") {
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

    verifyInputName = () => {
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
        //this.activateSaveButton();
    }

    verifyInputDiscipline = (callback) => {
        const { errors } = this.state
        if (this.state.discipline === null || this.state.discipline === "") {
            this.setState({
                errors: {
                    ...errors,
                    discipline: "Field discipline is required"
                }
            },()=>{this.activateSaveButton()});
        }
        else {
            this.setState({
                errors: {
                    ...errors,
                    discipline: null
                }
            },()=>{this.activateSaveButton()});
        }
       // this.activateSaveButton();
    }

    saveDateTeacher = () => {
        if (this.state.errors.name === null && this.state.errors.discipline === null
            && this.state.name !== null && this.state.discipline !== null) {
            this.props.saveTeachers(this.state.name);
            this.clearValue();
        }

    }

    clearValue = () => {
        const { name, discipline, backgroundSaveBtn } = this.state;
        this.setState({
            discipline: "",
            name: "",
            backgroundSaveBtn: "#FF8F74"
        });
    }

    render() {
        return (
            <div>
                <TextInput
                    label="Teacher name"
                    error={this.state.errors.name}
                    onChange={this.onChangeName}
                    onBlur={this.verifyInputName}
                    value={this.state.name}
                >
                </TextInput>
                <TextInput
                    label="Discipline name"
                    error={this.state.errors.discipline}
                    onChange={this.onChangeDiscipline}
                    onBlur={this.verifyInputDiscipline}
                    value={this.state.discipline}
                >
                </TextInput>
                <ButtonContainer>
                    <Button
                        background={this.state.backgroundSaveBtn}
                        onClick={this.saveDateTeacher}
                    >
                        Save Teacher
                    </Button>

                    <Button
                        background="#32CD32"
                        onClick={this.clearValue}

                    >
                        Cancel Values
                    </Button>
                </ButtonContainer>
            </div>
        );
    }

}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        ...bindActionCreators({ saveTeachers }, dispatch)
    }
}


export default connect(null, mapDispatchToProps)(CreateTeacher)