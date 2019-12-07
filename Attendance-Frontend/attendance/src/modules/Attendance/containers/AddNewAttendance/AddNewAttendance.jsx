import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp';
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

import { CancelAdd,addStudentOnAttendance , addStudentDetailsOnAttendance,getStudentsAttendance} from '../../actions/Actions';

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
        if(this.state.studentName!=="")
        {
            let splitted = this.state.studentName.split(" ");
            const student ={
                nameStudent:splitted[0],
                identityNumberStudent:splitted.pop(),
                grade:this.state.grade,
                details:this.state.details
            } 
            console.log(this.props.lists,this.props.nameListSelected);
            let list= this.props.lists.filter(  (item) => {return item.name ===this.props.nameListSelected});
               this.props.addStudentDetailsOnAttendance(list,student);
        }  
    }

    saveOnAttendance=()=>{
        if(this.state.studentName!=="")
        {
            let splitted = this.state.studentName.split(" "); 
            const student ={
                nameStudent:splitted[0],
                identityNumberStudent:splitted.pop()
            } 
           this.props.addStudentOnAttendance(this.props.nameListSelected,student);
        }

    }

    viewAttendance=()=>{
      //  console.log(this.props.nameListSelected);
      //  console.log(this.props.lists);
        let list= this.props.lists.filter(  (item) => {return item.name ===this.props.nameListSelected});
        const {id}=list[0];
      //  console.log("component did mount ",id);
        this.props.getStudentsAttendance(id);
    }

    teacherContainerAddGrades = () => {
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

                             <Button
                        onClick={this.viewAttendance}
                        background="#32CD32">
                        View
                            </Button>
                </ButtonContainer>
            </div>
        );
    }

}
function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        ...bindActionCreators({ CancelAdd ,addStudentOnAttendance, addStudentDetailsOnAttendance,getStudentsAttendance}, dispatch)
    }
}

const mapStateToProps = state => ({
    name: state.attendance.attendanceReducer.name,
    lists: state.attendance.attendanceReducer.attendanceLists,
    students: state.attendance.attendanceReducer.students,
    nameListSelected:state.attendance.attendanceReducer.nameListSelected,
    isAdmin: state.login.login.isAdmin
});

export default connect(mapStateToProps, mapDispatchToProps)(AddNewAttendance);