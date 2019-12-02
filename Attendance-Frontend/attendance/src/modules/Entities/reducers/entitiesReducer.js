import { combineReducers } from "redux";


const initialEntitiesState = {
    groups: []
};

const createStudentReducer = (state = initialEntitiesState, action) => {
    switch (action.type) {
        case "LOAD_GROUPS": {
            return {
                ...state,
                groups: action.payload.groups
            }
        }
        default: {
            return state;

        }
    }
}


const entitiesReducer = combineReducers({
    entitiesReducer: createStudentReducer
});

export default entitiesReducer;