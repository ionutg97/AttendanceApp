import React from 'react';
import { connect } from 'react-redux';
import { Tabel ,Td,Th} from '../components/DataTableStyledComp';

class DataTable2 extends React.Component {
    constructor(props) {
       super(props)
       this.state = {
          students: [
             { id: 1, name: 'Wasif', age: 21, email: 'wasif@email.com' },
             { id: 2, name: 'Ali', age: 19, email: 'ali@email.com' },
             { id: 3, name: 'Saad', age: 16, email: 'saad@email.com' },
             { id: 4, name: 'Asad', age: 25, email: 'asad@email.com' }
          ]
       }
    }
 
    renderTableHeader = (heading)=> {
       let header = Object.keys(heading)
       return header.map((key, index) => {
          return <Td key={index}>{key.toUpperCase()}</Td>
       })
    }
 
    renderTableData=(list)=> {
       console.log(Object.keys(list).length)
       if(Object.keys(list).length>0)
       {
          return list.map((list, index) => {
          const { nameStudent, identityNumber, attendance, grade, details } = list //destructuring
          return (
             <tr key={identityNumber}>
                <Td>{nameStudent}</Td>
                <Td>{identityNumber}</Td>
                <Td>{attendance}</Td>
                <Td>{grade}</Td>
                <Td>{details}</Td>
             </tr>
          )
       })
      }
    }
 
    render() {
       return (
          <div>
             <Tabel>
                <tbody>
                   <tr>{this.renderTableHeader(this.props.headings)}</tr>
                   {this.renderTableData(this.props.rows)}
                </tbody>
             </Tabel>
          </div>
       )
    }
 }
 
 const mapStateToProps = state => ({
    headings:state.attendance.attendanceReducer.headings,
    rows: state.attendance.attendanceReducer.rowsObj
});
 export default connect(mapStateToProps,null) (DataTable2);
