import React, { Component } from 'react';
import { connect } from 'react-redux';

import FindAttedanceList from '../AddNewAttendance/FindAttendanceList';
import AddNewAttendance from '../AddNewAttendance/AddNewAttendance';
import DataTable2 from '../../components/DataTable2';

import { FindAttendanceContainer, AddNewAttendanceContainer, AttendanceContainer, DisplayAttendanceContainer } from './AttendanceStyleComp';

export class Attedance extends Component {
    render() {
        return (
            <div>
                <FindAttendanceContainer
                    displayed={this.props.displayFind}>
                    <FindAttedanceList>
                    </FindAttedanceList>
                </FindAttendanceContainer>
                <AttendanceContainer>

                    <AddNewAttendanceContainer
                        displayed={this.props.displayAdd}>
                        <AddNewAttendance>
                        </AddNewAttendance>
                    </AddNewAttendanceContainer>
                    <DisplayAttendanceContainer
                        displayed={this.props.displayAdd}
                    >
                        <DataTable2 
                       
                        >
                        </DataTable2>
                    </DisplayAttendanceContainer>

                </AttendanceContainer>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    displayFind: state.attendance.attendanceReducer.displayFind,
    displayAdd: state.attendance.attendanceReducer.displayAdd,
    heading:state.attendance.attendanceReducer.headings,
    rows: state.attendance.attendanceReducer.rows
});

export default connect(mapStateToProps, null)(Attedance);
