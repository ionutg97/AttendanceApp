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
            type: ""
        };
    }

    onChange = (fieldName, value) => {
        this.setState({
            [fieldName]: value
        });
    };

    onChangeWeek = event => this.onChange("week", event.target.value);
    onChangeType = event => this.onChange("type", event.target.value);

    find = () => {
        console.log("here");
        this.props.findAttedance(this.state.week, this.state.type)
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
                    label="Type"
                    onChange={this.onChangeType}>
                </TextSelect>
                <ButtonContainer>
                    <Button
                        onClick={this.find}
                    >Search List</Button>
                </ButtonContainer>
            </div>
        );
    }

}
const mapDispatchToProps = dispatch => ({
    ...bindActionCreators(
        { findAttedance }, dispatch
    )
});

export default connect(null, { mapDispatchToProps })(FindAttendaceList);