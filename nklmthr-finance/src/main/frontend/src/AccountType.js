import React from 'react';
import ReactDOM from 'react-dom/client';
import './App.css';
class AccountType extends React.Component {
    constructor(props){
        super(props);
        this.state = {data:[], isEditing:false};
    }
    componentDidMount(){
        fetch('http://localhost:8080/api/accountType')
            .then(response => response.json())
            .then(response => {
                this.setState({data:response});
            })
    }

    deleteAccountType = (accountTypeId) => {
        var index = this.state.data.findIndex(e => (e.id === accountTypeId));
        var accountType = this.state.data[index];
//        alert(accountType['id']);
        var url  = 'http://localhost:8080/api/accountType/'+accountType['id'];
        alert(url);
        fetch(url, { method: 'DELETE' })
            .then(() => {
                this.state.data.splice(index, 1);
                this.setState({data:this.state.data});
            })
    };

    updateStateChangeForEdit = (accountTypeId) => {
        this.setState({data:this.state.data, isEditing:true, editRow: accountTypeId});
//        alert("updateAccountType"+this.state.isEditing);
    }

   draftAccountTypeName = (event)=> {
        console.log(event.target.name);
        console.log(event.target.value)
        if(event.target.name==='accountTypeName'){
            var state = this.state;
            this.setState({state, newAccountTypeName:event.target.value})
            console.log(JSON.stringify(state));
        }
    }
    draftAccountTypeDescription = (event) => {
        if(event.target.name==='accountTypeDescription'){
            console.log(event.target.name);
            console.log(event.target.value);
            var state = this.state;
            this.setState({state, newAccountTypeDescription:event.target.value})
            console.log(JSON.stringify(state));
        }
    }

    updateAccountType = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                if(info.id === this.state.editRow){
                    return(
                        <tr key={info.id}>
                            <td>{info.id}</td>
                            <td><input type='text' name='accountTypeName' defaultValue={info.name} onChange={this.draftAccountTypeName.bind(this)}/></td>
                            <td><input type='text' name='accountTypeDescription' defaultValue={info.description} onChange={this.draftAccountTypeDescription.bind(this)}/></td>
                            <td>
                                <button onClick={()=>this.saveAccountType(info.id)}>Save</button>

                            </td>
                        </tr>
                    )
                } else {
                    return(
                        <tr key={info.id}>
                            <td>{info.id}</td>
                            <td>{info.name}</td>
                            <td>{info.description}</td>
                            <td></td>
                        </tr>
                   )
                }
            }
        )
        return DisplayData;
    }

    listAccountType = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                return(
                    <tr key={info.id}>
                        <td>{info.id}</td>
                        <td>{info.name}</td>
                        <td>{info.description}</td>
                        <td>
                            <button onClick={()=>this.deleteAccountType(info.id)}>Delete</button>
                            <button onClick={()=>this.updateStateChangeForEdit(info.id)}>Update</button>
                        </td>
                    </tr>
                )
            }
        )
        return DisplayData;
    }
    render() {
//        alert("render:"+this.state.isEditing);
        var displayHtml;
        if(this.state.isEditing===false){
                displayHtml = this.listAccountType();
        } else {
                displayHtml = this.updateAccountType();
        }
        return (
            <div className="accountTypeTable">
                <table>
                    <thead>
                        <tr>
                            <th className="accountTypeIdCol">Account Type Id</th>
                            <th className="accountTypeNameCol">Account Type Name</th>
                            <th className="accountTypeDescCol">Description</th>
                            <th className="accountTypeIdActionCol">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {displayHtml}
                    </tbody>
               </table>
            </div>
        );

    }
}
export default AccountType;