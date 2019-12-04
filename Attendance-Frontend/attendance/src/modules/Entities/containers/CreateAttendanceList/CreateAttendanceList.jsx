import { connect } from 'react-redux';
import React from 'react';
import { bindActionCreators } from "redux";

import { ButtonContainer } from '../CreateStudent/CreateStudentStyleComp';
import Button from '../../components/Button';
import TextInput from '../../components/TextInput';
import TextSelect from '../../components/TextSelect';

import {saveAttendanceList} from '../../actions/Actions';

export class CreateAttendanceList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: "",
            week: "",
            errors: {
                name: "",
                week: ""    
            },
            type: "",
            groups: [],
            backgroundSaveBtn: "#FF8F74"
        }
    }

    onChange = (fieldName, value) => {
        this.setState({
            [fieldName]: value
        });

    };

    onChangeName = event => {
        this.onChange("name", event.target.value);
    }
    onChangeWeek = event => {
        this.onChange("week", event.target.value);
    }
    onChangeType =event =>{
        this.onChange("type", event.target.value);
    }
    onChangeGroups =event =>{
        [...event.target.options]
        .filter(o => o.selected)
        .map(o => {
            let value=o.value;
            if(this.state.groups===null || this.state.groups.find(element => element === o.value)===undefined)
                this.state.groups.push(o.value);
        }) 
    }

    activateSaveButton = () => {
        const { backgroundSaveBtn } = this.state;
        if (this.state.errors.name === null && this.state.errors.week === null
            && this.state.name !== "" && this.state.week !== "") {
            this.setState({
                backgroundSaveBtn: "#32CD32"
            })
        }
        else {
            this.setState({
                backgroundSaveBtn: "#FF8F74"
            })
        }

    }

    verifyInputName = (value) => {
        const { errors } = this.state
        if (this.state.name === null || this.state.name === "") {
            this.setState({
                errors: {
                    ...errors,
                    name: "Field name is required"
                }
            },()=>{this.activateSaveButton()});

        }
        else {
            this.setState({
                errors: {
                    ...errors,
                    name: null
                }
            },()=>{this.activateSaveButton()});

        }
    }

    verifyInputWeek = () => {
        const { errors } = this.state
        if (this.state.week === null || this.state.week === "") {
            this.setState({
                errors: {
                    ...errors,
                    week: "Field week is required"
                }
            });
        }
        else
        if (parseInt(this.state.week) < 0 || parseInt(this.state.week) > 15 || !parseInt(this.state.week)) {
                this.setState({
                    errors: {
                        ...errors,
                        week: "The week value must to be between 1 and 14"
                    }
                },()=>{this.activateSaveButton()});
            }
            else {
                this.setState({
                    errors: {
                        ...errors,
                        week: null
                    }
                },()=>{this.activateSaveButton()});
            }
    }

    saveDateAttendance = () => {
        if (this.state.errors.name === null && this.state.errors.week === null
            && this.state.name !== null && this.state.week !== null) {
            this.props.saveAttendanceList(this.state.name,
                 this.state.week,
                 this.state.type,
                 this.state.groups);
            this.clearValue();
        }

    }

    clearValue = () => {
        const { name, week,type,groups, backgroundSaveBtn } = this.state;
        this.setState({
            week: "",
            name: "",
            type:"",
            groups: [],
            backgroundSaveBtn: "#FF8F74"
        });
    }

    render() {
        return (
            <div>
                <TextInput
                    label="Attendance name"
                    error={this.state.errors.name}
                    onChange={this.onChangeName}
                    onBlur={this.verifyInputName}
                    value={this.state.name}
                >
                </TextInput>
                <TextInput
                    label="Week "
                    error={this.state.errors.week}
                    onChange={this.onChangeWeek}
                    onBlur={this.verifyInputWeek}
                    value={this.state.week}
                >
                </TextInput>
                <TextSelect
                    label="Type"
                    error={null}
                    onChange={this.onChangeType}
                    value={this.state.type}
                    items={["seminary","cours","laboratory"]}
                    height="3rem">
                </TextSelect>
                <TextSelect
                    label="Group(s)"
                    error={null}
                    onChange={this.onChangeGroups}
                    value={this.state.groups}
                    items={this.props.groupsList}
                    multiple={"multiple"}
                    height="5.5rem"
                    >
                </TextSelect>
                <ButtonContainer>
                    <Button
                     background={this.state.backgroundSaveBtn}
                     onClick={this.saveDateAttendance}
                    >
                        Save Attendance
                        </Button>
                    <Button
                        background="#32CD32"
                        onClick={this.clearValue}

                    >
                        Cancel Values
                    </Button>
                </ButtonContainer>
            </div>
        );

    }
}
const mapDispatchToProps = dispatch => {
    return {
        dispatch,
        ...bindActionCreators({ saveAttendanceList }, dispatch)
    }
}

const mapStateToProps = state => ({
    groupsList: state.entities.entitiesReducer.groups
})

export default connect(mapStateToProps, mapDispatchToProps)(CreateAttendanceList);