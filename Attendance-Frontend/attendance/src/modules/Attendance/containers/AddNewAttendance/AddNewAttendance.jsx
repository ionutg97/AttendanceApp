import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp';
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

import { CancelAdd } from '../../actions/Actions';

export class AddNewAttendance extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            studentName: "",
            grade: "",
            details: ""
        };
    }

    onChange = (fieldName, value) => {
        this.setState({
            [fieldName]: value
        });
    };

    onStudentNameChange = event => this.onChange("studentName", event.target.value);
    onGradeChange = event => this.onChange("grade", event.target.value);
    onDetailsChange = event => this.onChange("details", event.target.value);

    displayFindAttendance = () => {
        this.props.CancelAdd();
    }

    saveGradeAndDetails=()=>{
            console.log("admin");
    }

    saveOnAttendance=()=>{
        console.log("user")
    }

    teacherContainerAddGrades = () => {
        console.log(this.props.isAdmin)
        if (this.props.isAdmin)
            return (
                <div>
                    <TextInput
                        label="Grade"
                        error={null}
                        onChange={this.onGradeChange}
                    >
                    </TextInput>
                    <TextInput
                        label="Details"
                        error={null}
                        onChange={this.onDetailsChange}
                    >
                    </TextInput>
                </div>
            )
        else
            return (null);
    }

    render() {
        return (
            <div>
                <TextSelect
                    label="Student"
                    error={null}
                    onChange={this.onStudentNameChange}
                    items={this.props.students}
                    //onChange={this.onSelectGroup}
                    height="2.5rem"
                ></TextSelect>

                {this.teacherContainerAddGrades()}

                <ButtonContainer>
                    <Button
                        background="#32CD32"
                        onClick={this.props.isAdmin ? this.saveGradeAndDetails : this.saveOnAttendance}
                    >
                        Save
                        </Button>
                    <Button
                        onClick={this.displayFindAttendance}
                        background="#32CD32">
                        Cancel
                            </Button>
                </ButtonContainer>
            </div>
        );
    }

}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        ...bindActionCreators({ CancelAdd }, dispatch)
    }
}

const mapStateToProps = state => ({
    name: state.attendance.attendanceReducer.name,
    students: state.attendance.attendanceReducer.students,
    isAdmin: state.login.login.isAdmin
});

export default connect(mapStateToProps, mapDispatchToProps)(AddNewAttendance);