
import sha256 from 'crypto-js/sha256';

export const login = (name, password) => {
    return dispatch => {
        fetch(`http://localhost:8090/students/login?username=${name}&password=${sha256(password)}`, {
            method: 'get'
        }).then(response => {
            return response.json();

        }).then (data => {
            dispatch({
                type: "LOGIN",
                payload: {  
                  type: data
                }
              });
        
        })
            .catch(err => {
                console.log(err);

            });
    };
}
