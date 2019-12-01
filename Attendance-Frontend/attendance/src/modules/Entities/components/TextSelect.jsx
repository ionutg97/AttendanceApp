import React from 'react';
import styled from 'styled-components';
import { StyledLabel, StyledError } from './TextInput';


const OptionValueInput = styled.option`
    value: ${props => props.value}
    width:100%;
    font-size: 1rem;
    padding-left: 1rem;
    padding-right:1rem;
`;

const StyledSelectInput = styled.select.attrs(props => ({
    type: "text"

}))`
    border: ${props => props.isValid ? "1px solid #C7D0DA" : "1px solid red"};
    width:100%;
  
    height: 2.5rem;
    font-size: 1rem;
    padding-left: 1rem;
    padding-right:1rem;
    box-sizing: border-box;
    border-radius: 4px;
    
    ::placeholder{
        font-size: 0.75rem;
        color: #999;
    }
    :focus{
        outline: none;
    }
`;

// const GenerateOptionValueInput = props => {
//     const {  ...other } = props;
//     console.log(...other);
//     return  <OptionValueInput value={...other}></OptionValueInput>;
//   };

export class TextSelect extends React.Component {

    isInputValid = () => {
        return this.props.error === null || this.props.error === "";
    }

    // items = this.props.data["items"].map(function(itemData) {
    //     var component = Components[itemData['itemClass']];
    //     return React.createElement(component, {
    //         data: itemData,
    //         key: itemData['id']
    //     });
    // });


    render() {
        return (
            <React.Fragment>
                <StyledLabel>{this.props.label}</StyledLabel>
                <StyledSelectInput
                    placeholder={`Enter ${this.props.label.toLowerCase()}`}
                    onChange={this.props.onChange}
                    onBlur={this.props.onBlur}
                    isValid={this.isInputValid()}
                    onClick={this.props.onClick}
                >
                    {this.props.items.map((message) => <OptionValueInput value={message} >{message}</OptionValueInput>)}

                </StyledSelectInput>
                <StyledError>{this.props.error} </StyledError>
            </React.Fragment>
        );
    }
}

export default TextSelect;