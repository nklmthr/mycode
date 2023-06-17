import React from 'react';
import './Categories.css';
import {
  TreeList, Editing, Column, RequiredRule, Lookup, Button,
} from 'devextreme-react/tree-list';

const expandedRowKeys = [1, 2, 3, 4, 5];
var categories = JSON.parse('[{"id":"1","name":"HOME"},{"id":"2","name":"GROCERY","parentCategoryId":"1"}]');
const headDataSource = {
  store: categories,
  sort: 'name',
};

export default class Categories extends React.Component {
  allowDeleting(e) {
    return e.row.data.ID !== 1;
  }
  render() {
      return (
        <div id="tree-list-demo" class="categoriesTypeTable">
          <TreeList
            id="categories"
            dataSource={categories}
            showRowLines={true}
            showBorders={true}
            columnAutoWidth={true}
            defaultExpandedRowKeys={expandedRowKeys}
            keyExpr="id"
            parentIdExpr="parentCategoryId"
            onEditorPreparing={this.onEditorPreparing}
            onInitNewRow={this.onInitNewRow}
          >
            <Editing
              allowUpdating={true}
              allowDeleting={this.allowDeleting}
              allowAdding={true}
              mode="row" />
            <Column
              dataField="name">
              <RequiredRule />
            </Column>
            <Column
              dataField="parentCategoryId"
              caption="Parent">
              <Lookup
                dataSource={headDataSource}
                valueExpr="id"
                displayExpr="parentCategoryId" />
              <RequiredRule />
            </Column>
            <Column type="buttons">
              <Button name="edit" />
              <Button name="delete" />
            </Column>
          </TreeList>
        </div>
      );
    }

    onEditorPreparing(e) {
      if (e.dataField === 'id' && e.row.data.id === 1) {
        e.cancel = true;
      }
    }

    onInitNewRow(e) {
      e.data.Head_ID = 1;
    }

}

