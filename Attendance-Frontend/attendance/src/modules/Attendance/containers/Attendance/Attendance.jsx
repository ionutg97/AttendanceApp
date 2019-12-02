import React, { Component } from 'react';
import { connect } from 'react-redux';

import FindAttedanceList from '../AddNewAttendance/FindAttendanceList';
import AddNewAttendance from '../AddNewAttendance/AddNewAttendance';

import { FindAttendanceContainer, AddNewAttendanceContainer } from './AttendanceStyleComp';

export class Attedance extends Component {
    render() {
        return (
            
            <div>
                <FindAttendanceContainer
                    displayed={this.props.displayFind}>
                    <FindAttedanceList>
                    </FindAttedanceList>
                </FindAttendanceContainer>
                <AddNewAttendanceContainer
                    displayed={this.props.displayAdd}>
                    <AddNewAttendance>
                    </AddNewAttendance>
                </AddNewAttendanceContainer>

            </div>
        );
    }
}

const mapStateToProps = state => ({
    displayFind: state.attendance.attendanceReducer.displayFind,
    displayAdd: state.attendance.attendanceReducer.displayAdd
});

export default connect(mapStateToProps,null)(Attedance);
