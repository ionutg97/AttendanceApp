import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import {ButtonContainer} from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp'; 
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

import {CancelAdd} from '../../actions/Actions';

export class AddNewAttendance extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            week: "",
            category: ""
        };
    }

    displayFindAttendance = () => {
        this.props.CancelAdd();    
    }

    render() {
    return(
        <div>
            <TextInput
            label="Attendance Name"
            value={this.props.name}
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
                <Button
                onClick={this.displayFindAttendance}>Cancel</Button>
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
    name: state.attendance.attendanceReducer.name
});
  
  export default connect(mapStateToProps, mapDispatchToProps)(AddNewAttendance);