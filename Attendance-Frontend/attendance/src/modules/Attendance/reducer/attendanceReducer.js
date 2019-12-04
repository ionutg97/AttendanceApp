import { combineReducers } from "redux";

const intialiAttendanceState = {
    students:[],
    attendanceLists:[],
    groups:[],
    error: false,
    displayFind: true,
    displayAdd: false
}

const attendanceReducerLocal = (state = intialiAttendanceState, action) => {
    switch (action.type) {
        case "FIND_ATTENDANCE": {
            return {
                ...state,
                students: action.payload.students,
                displayFind: action.payload.displayFind,
                displayAdd: action.payload.displayAdd,
                attendanceLists:[],
                groups:[]
            };
        }
        case "LOAD_LISTS" : {
            return {
                ...state,
                attendanceLists: action.payload.lists
            }

        }
        case "LOAD_GROUPS_LISTS":{
            return{
                ...state,
                groups:action.payload.lists
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
