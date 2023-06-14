import React from 'react';
import ReactDOM from 'react-dom/client';
import './App.css';
class AccountType extends React.Component {
    constructor(){
        super();
        this.state = {data:[]};
        this.deleteAccountType = this.deleteAccountType.bind(this);

//        this.setState({open: false});

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
        var accountType = this.state.data[this.state.data.findIndex(e => (e.id === accountTypeId))];
//        alert(accountType['id']);
        var url  = 'http://localhost:8080/api/accountType/'+accountType['id'];
        alert(url);
        fetch(url, { method: 'DELETE' })
            .then(() => {
                this.state.data.splice(index, 1);
                this.setState({data:this.state.data});
            })
    };

    render() {
        const DisplayData=this.state.data.map(
            (info)=>{
                return(
                    <tr key={info.id}>
                        <td>{info.id}</td>
                        <td>{info.name}</td>
                        <td>{info.description}</td>
                        <td><button onClick={()=>this.deleteAccountType(info.id)}>Delete</button></td>
                    </tr>
                )
            }
        )

        return(
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
                        {DisplayData}
                    </tbody>
               </table>
            </div>
       );
    }
}
export default AccountType;