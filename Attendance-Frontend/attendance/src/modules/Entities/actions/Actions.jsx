
export const saveTeachers = (nameTeacher) => {
    return dispatch => {
        console.log(nameTeacher);

        fetch('http://localhost:8090/teachers', {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: null, name: nameTeacher })
        }).then(response => {
            console.log(response.json())

        })
            .catch(err => {
                console.log(err);

            });
    };
};

export const saveResource = (path, nameValue) => {
    return dispatch => {
        console.log(path, nameValue);
        fetch(`http://localhost:8090/${path}`, {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: null, name: nameValue })
        }).then(response => {
            console.log(response.json())

        })
            .catch(err => {
                console.log(err);

            });
    };
};

export const saveStudent = (nameValue, identityN, groupName) => {
    return dispatch => {

        fetch(`http://localhost:8090/students`, {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: null,
                name: nameValue,
                identityNumber: identityN,
                group: groupName
            })
        }).then(response => {
            console.log(response.json())

        })
            .catch(err => {
                console.log(err);

            });
    };
}



export const saveAttendanceList = (nameValue, week, type, groupName) => {
    return dispatch => {
        console.log(nameValue,week,type.toUpperCase());
        fetch(`http://localhost:8090/attendance_lists`, {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: null,
                name: `${nameValue} group ${groupName} week ${week}`,
                category: type.toUpperCase()
            })
        }).then(response => {
            console.log(response.json())

        })
            .catch(err => {
                console.log(err);

            });
    };
}

