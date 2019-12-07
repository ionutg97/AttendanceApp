import { combineReducers } from "redux";

const intialiAttendanceState = {
    students: [],
    attendanceLists: [],
    nameListSelected:"",
    groups: [],
    error: false,
    displayFind: true,
    displayAdd: false,
    headings: {
        name: 'Student name',
        number: 'Identity Number',
        attendance: 'Attendance',
        grade: 'Grade',
        details: 'Details'
    },
    rows: [],
    rowsObj:[]
}

const attendanceReducerLocal = (state = intialiAttendanceState, action) => {
    switch (action.type) {
        case "FIND_ATTENDANCE": {
            return {
                ...state,
                students: action.payload.students,
                displayFind: action.payload.displayFind,
                displayAdd: action.payload.displayAdd,
                groups: []
            };
        }
        case "LOAD_LISTS": {
            return {
                ...state,
                attendanceLists: action.payload.lists
            }

        }
        case "LOAD_GROUPS_LISTS": {
            return {
                ...state,
                groups: action.payload.lists,
                nameListSelected:action.payload.nameListSelected
            }
        }
        case "FIND_ALL_STUDENTS_INFO": {
            return {
                ...state,
                rows: action.payload.students
            }
        }
        case "LOAD_STUDENT_ATTENDANCE":{
            return{...state,
            rowsObj: action.payload.rows
            }
        }

        default: {
            return state;
            
        }
    }
}

const attendanceReducer = combineReducers({
    attendanceReducer: attendanceReducerLocal
});

export default attendanceReducer;
