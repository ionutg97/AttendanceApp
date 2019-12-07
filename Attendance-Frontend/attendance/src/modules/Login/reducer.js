import { combineReducers } from "redux";


const initialEntitiesState = {
    type:"",
    isAdmin:true
};

const loginReducer = (state = initialEntitiesState, action) => {
    switch (action.type) {
        case "LOGIN": {
            return {
                ...state,
                type: action.payload.type,
                isAdmin: action.payload.isAdmin
            }
        }
        default: {
            return state;

        }
    }
}


const login = combineReducers({
    login: loginReducer
});

export default login;