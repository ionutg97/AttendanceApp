import { combineReducers } from "redux";


const initialEntitiesState = {
    type:"",
    username: "",
    isAdmin:true,
    isUser:false
};

const loginReducer = (state = initialEntitiesState, action) => {
    switch (action.type) {
        case "LOGIN": {
            return {
                ...state,
                type: action.payload.type
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