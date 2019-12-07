import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../../../Entities/containers/CreateStudent/CreateStudentStyleComp';
import Button from '../../../Entities/components/Button';
import TextInput from '../../../Entities/components/TextInput';
import TextSelect from '../../../Entities/components/TextSelect';

import { findStudentsByGroup, getAllLists, findGroup } from '../../actions/Actions';

export class FindAttendaceList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            week: "",
            category: "seminary",
            nameList: "",
            errors: {
                week: ""
            },
            group: "",
            backgroundSaveBtn: "#FF8F74"
        };
    }

    onChange = (fieldName, value) => {
        this.setState({
            [fieldName]: value
        });
    };

    onChangeWeek = event => this.onChange("week", event.target.value);
    onChangeCategory = event => this.onChange("category", event.target.value);
    onSelectList = event => this.onChange("nameList", event.target.value);
    onSelectGroup = event => this.onChange("group", event.target.value);

    verifyInputWeek = () => {
        const { errors, backgroundSaveBtn } = this.state
        if (this.state.week === null || this.state.week === "") {
            this.setState({
                errors: {
                    ...errors,
                    week: "Field week is required"
                }, backgroundSaveBtn: "#FF8F74"
            });
        }
        else
            if (parseInt(this.state.week) < 0 || parseInt(this.state.week) > 15 || !parseInt(this.state.week)) {
                this.setState({
                    errors: {
                        ...errors,
                        week: "The week value must to be between 1 and 14"
                    }, backgroundSaveBtn: "#FF8F74"
                });
            }
            else {
                this.setState({
                    errors: {
                        ...errors,
                        week: null
                    }, backgroundSaveBtn: "#32CD32"
                });
            }
    }

    addNewStudentOnAttendanceList = () => {
        const { errors } = this.state
        if (this.state.group !== "") {
            this.props.findStudentsByGroup(this.state.group);
            this.setState({
                week: "",
                category: "seminary",
                nameList: "",
                errors: {
                    week: ""
                },
                group: "",
                backgroundSaveBtn: "#FF8F74"
            });
        }
    }

    findAllAttedanceName = () => {
        this.props.getAllLists(this.state.week, this.state.category);
    }

    findGroupByName = () => {
        console.log(this.state.nameList)
        if (this.state.nameList !== "") {
            this.props.findGroup(this.state.nameList, this.state.week, this.state.category);
        }

    }

    render() {
        return (
            <div>
                <TextInput
                    label="Week"
                    error={this.state.errors.week}
                    onChange={this.onChangeWeek}
                    onBlur={this.verifyInputWeek}
                    value={this.state.week}
                >
                </TextInput>
                <TextSelect
                    label="Category"
                    error={null}
                    onChange={this.onChangeCategory}
                    value={this.state.category}
                    items={["seminary", "cours", "laboratory"]}
                    height="2.5rem"
                >
                </TextSelect>
                <ButtonContainer>
                    <Button
                        background={this.state.backgroundSaveBtn}
                        onClick={this.findAllAttedanceName}
                    >Search List</Button>
                </ButtonContainer>
                <TextSelect
                    label="Attendance Lists"
                    error={null}
                    onChange={this.onSelectList}
                    onBlur={this.findGroupByName}
                    items={ ["--none--", ...this.props.lists.map(item =>item.name)]}
                    value={this.state.nameList}
                    height="2.5rem"
                ></TextSelect>
                <TextSelect
                    label="Groups"
                    error={null}
                    items={["--none--", ...this.props.groups]}
                    onChange={this.onSelectGroup}
                    height="2.5rem"
                    
                ></TextSelect>
                <ButtonContainer>
                    <Button
                        background={this.state.backgroundSaveBtn}
                        onClick={this.addNewStudentOnAttendanceList}
                    >Search student</Button>
                </ButtonContainer>
            </div>
        );
    }

}

function mapDispatchToProps(dispatch) {
    return {
        dispatch,
        ...bindActionCreators({ findStudentsByGroup, getAllLists, findGroup }, dispatch)
    }
}

const mapStateToProps = state => ({
    lists: state.attendance.attendanceReducer.attendanceLists,
    groups: state.attendance.attendanceReducer.groups
});


export default connect(mapStateToProps, mapDispatchToProps)(FindAttendaceList);