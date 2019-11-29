//import axios from "react-axios";

// export const findAttedance = (week, type) => {
//     return dispatch => {
//         console.log(week, type)
//         dispatch({
//                 type: "FIND_ATTENDANCE",
//                 payload: {
//                     name:`List for ${week} ${type}`,
//                     displayFind: false,
//                     displayAdd: true,  
//                 } 
//               });

//       //   fetch.get(
//       //   `http://localhost:8090/attendance?week=${week}&per-page=${type}`
//       // )
//       //   .then(response => {
//       //     dispatch({
//       //       type: "FIND_ATTENDANCE",
//       //       payload: {
//       //           value: response.data.attendacenName
//       //       }
//       //     });
//       //   })
//       //   .catch(err => {
//       //     dispatch({
//       //       type: "LOAD_ERROR",
//       //       payload: {
//       //         error: true
//       //       }
//       //     });
//       //   });
//     };
//   };

export const findAttedance = (week, category) => {
  return {
    type: "FIND_ATTENDANCE",
    payload: {
      name: `List for ${week} ${category}`,
      displayFind: false,
      displayAdd: true,
    }
  };
};

export const CancelAdd = () => {
  return {
    type: "FIND_ATTENDANCE",
    payload: {
      name: "",
      displayFind: true,
      displayAdd: false,
    }
  };
};