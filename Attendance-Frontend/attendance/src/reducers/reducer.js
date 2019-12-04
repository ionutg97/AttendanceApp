import {combineReducers} from 'redux'

import entitiesReducer from '../modules/Entities/reducers/entitiesReducer';
import attendanceReducer from '../modules/Attendance/reducer/attendanceReducer';
import login from '../modules/Login/reducer';

const reducer = combineReducers({attendance: attendanceReducer, entities: entitiesReducer, login});

export default reducer;