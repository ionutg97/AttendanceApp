
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


export const findStudentsInfoByGroup = (group) => {
  return dispatch => {
    fetch(`http://localhost:8090/students/all_info?group=${group}`, {
      method: 'get'
    }).then(response => {
      return response.json();

    }).then(data => {
      dispatch({
        type: "FIND_ALL_STUDENTS_INFO",
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
          lists: data,
          nameListSelected: nameList
        }
      });

    })
      .catch(err => {
        console.log(err);

      });
  };
}

export const addStudentOnAttendance = (nameList, student) => {
  const { nameStudent, identityNumberStudent } = student //destructuring
  console.log(nameList, nameStudent, identityNumberStudent)
  return dispatch => {
    fetch(`http://localhost:8090/attendance_item/save_student?attendance_name=${nameList}`, {
      method: 'post',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: null,
        name: nameStudent,
        identityNumber: identityNumberStudent,
        group: null
      })
    }).then(response => {
      console.log(response.json());

    })
      .catch(err => {
        console.log(err);

      });
  };
}

export const addStudentDetailsOnAttendance = (list, student) => {
  const { nameStudent, identityNumberStudent, grade, details } = student //destructuring
  const { id, name, cat, type } = list[0];

  console.log("here", { ...list[0] })
  console.log(student)
  return dispatch => {
    fetch(`http://localhost:8090/attendance_item/save_student_details`, {
      method: 'put',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: null,
        classroom: null,
        teacher: null,
        attendanceList: {
          id: id,
          name: name,
          category: cat,
          type: type
        },
        students: [{
          id: null,
          name: nameStudent,
          identityNumber: identityNumberStudent,
          group: null
        }],
        grade: grade,
        details: details
      })
    }).then(response => {
      console.log(response.json());

    })
      .catch(err => {
        console.log(err);

      });
  };
}

export const getStudentsAttendance = (id) => {
  return dispatch => {
    fetch(`http://localhost:8090/attendance_item/all/${id}`, {
      method: 'get'
    }).then(response => {
      return response.json();

    }).then(data => {
      console.log(data)
      dispatch({
        type: "LOAD_STUDENT_ATTENDANCE",
        payload: {
          rows: data
        }
      });

    })
      .catch(err => {
        console.log(err);

      });
  };
}
