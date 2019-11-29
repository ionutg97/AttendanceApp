import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp';
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

import { findAttedance } from '../../actions/Actions';

export class FindAttendaceList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            week: "",
            category: ""
        };
    }

    onChange = (fieldName, value) => {
        this.setState({
            [fieldName]: value
        });
    };

    onChangeWeek = event => this.onChange("week", event.target.value);
    onChangeCategory= event => this.onChange("category", event.target.value);

    findAttendanceName = () => {
        this.props.findAttedance(this.state.week, this.state.category);    
    }

    render() {
        return (
            <div>
                <TextInput
                    label="Week"
                    onChange={this.onChangeWeek}
                >
                </TextInput>
                <TextSelect
                    label="Category"
                    onChange={this.onChangeCategory}>
                </TextSelect>
                <ButtonContainer>
                    <Button
                        onClick={this.findAttendanceName}
                    >Search List</Button>
                </ButtonContainer>
            </div>
        );
    }

}

function mapDispatchToProps(dispatch) {
    return {
      dispatch,
      ...bindActionCreators({ findAttedance }, dispatch)
    }
  }


 export default connect(null,  mapDispatchToProps )(FindAttendaceList);