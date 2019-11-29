//import axios from "react-axios";

export const findAttedance = (week, type) => {
    return dispatch => {
        console.log(week, type)
        fetch.get(
        `http://localhost:8090/attendance?week=${week}&per-page=${type}`
      )
        .then(response => {
          dispatch({
            type: "FIND_ATTENDANCE",
            payload: {
                value: response.data.attendacenName
            }
          });
        })
        .catch(err => {
          dispatch({
            type: "LOAD_ERROR",
            payload: {
              error: true
            }
          });
        });
    };
  };