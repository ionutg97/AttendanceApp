import React, { Component }  from 'react';

import FindAttedanceList from '../AddNewAttendance/FindAttendanceList';
import AddNewAttendance from '../AddNewAttendance/AddNewAttendance';

import {FindAttendanceContainer,AddNewAttendanceContainer} from './AttendanceStyleComp';

export class Attedance extends Component {
    constructor(props) {
        super(props);
    }

    render(){
        return (
            <div>
                <FindAttendanceContainer>
                    <FindAttedanceList>   
                    </FindAttedanceList>
                </FindAttendanceContainer>
                <AddNewAttendanceContainer>
                    <AddNewAttendance>    
                    </AddNewAttendance>
                </AddNewAttendanceContainer>

            </div>
        );
    }
}

export default Attedance;
