import { combineReducers } from "redux";

const intialiAttendanceState = {
    name: "",
    error: false,
    displayFind: true,
    displayAdd: false
}

const attendanceReducerLocal = (state = intialiAttendanceState, action) => {
    switch (action.type) {
        case "FIND_ATTENDANCE": {
            return {
                ...state,
                name: action.payload.name,
                displayFind: action.payload.displayFind,
                displayAdd: action.payload.displayAdd,
            };
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
