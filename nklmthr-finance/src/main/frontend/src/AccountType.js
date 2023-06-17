import React from 'react';
import ReactDOM from 'react-dom/client';
import './App.css';
export default class AccountType extends React.Component {
    constructor(props){
        super(props);
        this.state = {data:[], isEditing:false, isNew: false, editRow:''};
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
        var url  = 'http://localhost:8080/api/accountType/'+accountType['id'];
        fetch(url, { method: 'DELETE' })
            .then(() => {
                var stateData = this.state.data;
                stateData.splice(index, 1);
                this.setState({data: stateData});
            })
    };

    updateStateChangeForEdit = (accountTypeId) => {
        this.setState({isEditing:true});
        this.setState({editRow: accountTypeId});
    }

   draftAccountTypeName = (event)=> {
        if(event.target.name==='accountTypeName'){
            this.setState({newAccountTypeName:event.target.value})
        }
    }
    draftAccountTypeDescription = (event) => {
        if(event.target.name==='accountTypeDescription'){
            this.setState({newAccountTypeDescription:event.target.value})
        }
    }
    createNewAccountType= (accountTypeId) =>{
        var newAccountTypeName = this.state.newAccountTypeName;
        var newAccountTypeDescription = this.state.newAccountTypeDescription;
        if(newAccountTypeName===undefined){
            alert("Account Type Name must Not be Null when adding new Account Type");
            return;
        }
        var payloadStr = '{"name":"'+newAccountTypeName+'","description":"'+newAccountTypeDescription+'"}';
        var payload = JSON.parse(payloadStr);
        var url  = 'http://localhost:8080/api/accountType';
        //alert(url);
        fetch(url, {
                method: 'POST',
                body: JSON.stringify(payload),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
        })
        .then((response) => response.json())
        .then((responseData) => {
              var stateData = this.state.data;
              stateData.push(responseData);
              var index = stateData.findIndex(e => (e.id === ''));
              stateData.splice(index,1);
              this.setState({data:stateData});
              this.setState({isNew:false});
              this.setState({newAccountTypeName:""});
              this.setState({newAccountTypeDescription:""});
        });
    }

    updateAccountType= (accountTypeId) => {
        var newAccountTypeName = this.state.newAccountTypeName;
        var newAccountTypeDescription = this.state.newAccountTypeDescription;
        var index = this.state.data.findIndex(e => (e.id === accountTypeId));
        var accountType = this.state.data[index];
        if(newAccountTypeName===undefined || newAccountTypeName.trim()==='') {
            newAccountTypeName = accountType['name'];
        }
        var payloadStr = '{"id":"'+accountTypeId+'","name":"'+newAccountTypeName+'","description":"'+newAccountTypeDescription+'"}';
        var payload = JSON.parse(payloadStr);
        var url  = 'http://localhost:8080/api/accountType/'+accountType['id'];
        fetch(url, {
                method: 'PUT',
                body: JSON.stringify(payload),
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                },
        })
        .then(() => {
            var stateData = this.state.data;
            stateData.splice(index, 1);
            stateData.push(payload);
            this.setState({data:stateData});
            this.setState({editRow:""});;
            this.setState({isEditing:false});
            this.setState({newAccountTypeName:""});
            this.setState({newAccountTypeDescription:""});
        });
    }
    saveAccountType = (accountTypeId) =>{
        if(this.state.isNew){
            this.createNewAccountType(accountTypeId);
        } else{
            this.updateAccountType(accountTypeId);
        }
    }
    cancelUpdate =() =>{
        this.setState({isEditing:false});
        this.setState({editRow:""});
    }
    addNewAccountType = () =>{
        var newAccountType = '{"id":"", "name":"","description":""}';
        this.state.data.push(JSON.parse(newAccountType));
        this.setState({data:this.state.data, isNew:true});
    }

    updateAccountTypeHMTL = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                if((info.id === this.state.editRow) || (this.state.isNew===true && info.id === '')){
                    return(
                        <tr key={info.id}>
                            <td>{info.id}</td>
                            <td><input type='text' name='accountTypeName' defaultValue={info.name} onChange={this.draftAccountTypeName.bind(this)}/></td>
                            <td><input type='text' name='accountTypeDescription' defaultValue={info.description} onChange={this.draftAccountTypeDescription.bind(this)}/></td>
                            <td>
                                <button className='saveAccountTypeButton' onClick={()=>this.saveAccountType(info.id)}>Save</button>
                                <button className='cancelUpdateButton' onClick={()=>this.cancelUpdate()}>Cancel</button>
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

    listAccountTypeHTML = () => {
        const DisplayData=this.state.data.map(
            (info)=>{
                return(
                    <tr key={info.id}>
                        <td>{info.id}</td>
                        <td>{info.name}</td>
                        <td>{info.description}</td>
                        <td>
                            <div class="btn-group">
                            <button className='deleteAccountTypeButton' onClick={()=>this.deleteAccountType(info.id)}>Delete</button>
                            <button className='updateAccountTypeButton' onClick={()=>this.updateStateChangeForEdit(info.id)}>Update</button>
                            </div>
                        </td>
                    </tr>
                )
            }
        )
        return DisplayData;
    }
    render() {

        var displayHtml;
        if(this.state.isEditing===true || this.state.isNew===true){
                displayHtml = this.updateAccountTypeHMTL();
        } else {
                displayHtml = this.listAccountTypeHTML();
        }
        return (
            <div className="accountTypeTable">
            <button className='addAccountTypeButton' onClick={this.addNewAccountType.bind(this)}>Add</button>
            <br/><br/>
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
