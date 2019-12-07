import styled from 'styled-components';

// export const AttendanceContainer = styled.div`
//     width:100%;
// `;

export const FindAttendanceContainer = styled.div`
    width:25%;
    display: ${props => (props.displayed ? "block" : "none")};
    padding: 1rem;
`;



export const AttendanceContainer = styled.div`
    display: ${props => (props.displayed ? "block" : "none")};
    display: flex;
    width:100%;
    justify-content: space-around;
    flex-flow: nowrap;
    padding: 1rem;
`;

export const DisplayAttendanceContainer= styled.div`
    display: ${props => (props.displayed ? "block" : "none")};
    width:75%;
    padding: 3rem;
`;

export const AddNewAttendanceContainer = styled.div`
    display: ${props => (props.displayed ? "block" : "none")};
    width:25%;
    padding: 4rem;
`


  


