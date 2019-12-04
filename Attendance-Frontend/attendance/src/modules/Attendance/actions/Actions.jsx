
export const findStudentsByGroup = (group) => {
  return dispatch => {
    fetch(`http://localhost:8090/students/all?group=${group}`, {
      method: 'get'
    }).then(response => {
      return response.json();

    }).then(data => {
      dispatch({  
        type: "FIND_ATTENDANCE",
        payload: {
          students: data,
          displayFind: false,
          displayAdd: true,
        }
      });

    })
      .catch(err => {
        console.log(err);

      });
  };
}



export const CancelAdd = () => {
  return {
    type: "FIND_ATTENDANCE",
    payload: {
      students: [],
      displayFind: true,
      displayAdd: false,
    }
  };
};

export const getAllLists = (week, type) => {
  return dispatch => {
    fetch(`http://localhost:8090/attendance_lists/all?week=${week}&type=${type.toLowerCase()}`, {
      method: 'get'
    }).then(response => {
      return response.json();

    }).then(data => {
      dispatch({
        type: "LOAD_LISTS",
        payload: {
          lists: data
        }
      });

    })
      .catch(err => {
        console.log(err);

      });
  };
}

export const findGroup = (nameList, week, type) => {
  return dispatch => {
    fetch(`http://localhost:8090/attendance_lists/groups?name=${nameList}&week=${week}&type=${type.toLowerCase()}`, {
      method: 'get'
    }).then(response => {
      return response.json();

    }).then(data => {
      dispatch({
        type: "LOAD_GROUPS_LISTS",
        payload: {
          lists: data
        }
      });

    })
      .catch(err => {
        console.log(err);

      });
  };
}