import {combineReducers} from 'redux'

import entitiesReducer from '../modules/Entities/reducers/entitiesReducer';
import attendanceReducer from '../modules/Attendance/reducer/attendanceReducer';

const reducer = combineReducers({attendance: attendanceReducer, entities: entitiesReducer});

export default reducer;