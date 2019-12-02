import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../CreateStudent/CreateStudentStyleComp';
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';

import { saveResource } from '../../actions/Actions';

export class CreateClassroom extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            name: "",
            errors: {
                name: ""
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

    verifyInputName = (value) => {
        const { errors, backgroundSaveBtn } = this.state
        if (this.state.name === null || this.state.name === "") {
            this.setState({
                errors: {
                    ...errors,
                    name: "Field classroom name is required",

                }, backgroundSaveBtn: "#FF8F74"
            });

        }
        else {
            this.setState({
                errors: {
                    ...errors,
                    name: null
                },
                backgroundSaveBtn: "#32CD32"
            });

        }
    }

    saveDateClassroom = () => {
        if (this.state.errors.name === null
            && this.state.name !== null) {
            this.props.saveResource("classrooms", this.state.name);
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
            <div>
                <TextInput
                    label="Create a Classroom"
                    error={this.state.errors.name}
                    onChange={this.onChangeName}
                    onBlur={this.verifyInputName}
                    value={this.state.name}>
                </TextInput>
                <ButtonContainer>
                    <Button
                        background={this.state.backgroundSaveBtn}
                        onClick={this.saveDateClassroom}
                    >
                        Save Classroom
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
const mapDispatchToProps = dispatch => {
    return {
        dispatch,
        ...bindActionCreators({ saveResource }, dispatch)
    }
}

export default connect(null, mapDispatchToProps)(CreateClassroom);