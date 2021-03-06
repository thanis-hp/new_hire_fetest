Ext.util.Format.comboRenderer = function(combo){
        return function(value){
        var record = combo.findRecord(combo.valueField || combo.displayField, value);
        return record ? record.get(combo.displayField) : combo.valueNotFoundText;
        }
    };

function getUserGrid() {

    var userStore = new Ext.data.JsonStore({
        storeId:'user',
        autoLoad:false,
        proxy:{
            type:'ajax',
            url:'getUserList.htm',
            reader:{
                type:'json',
                root:'users'
            }
        },

        fields:['userId', 'userName', 'name', 'dept', 'role', 'enabled', {name: 'lastLogin', type: 'date', dateFormat: 'Y-m-d h:i:s A'}]
    });

    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
        clicksToEdit:1
    });

    var addUserForm = {
        xtype:'form',
        id:'addUserForm',
        layout:'form',
        frame: true,
        width   : 600,
        bodyPadding: 10,
        defaults: {
            anchor: '100%',
            labelWidth: 100
        },
        url:'insertNewUsers.htm',
        items:[
            {
                xtype:'textfield',
                id:'userName',
                name:'userName',
                value:'',
                fieldLabel:'User Name',
                allowBlank:false
            },
            {
                xtype:'textfield',
                id:'name',
                name:'name',
                value:'',
                fieldLabel:'Name',
                allowBlank:false
            },
            {
                xtype:'textfield',
                id:'dept',
                name:'dept',
                value:'',
                fieldLabel:'Department',
                allowBlank:false
            },
            {
                xtype: 'fieldset',
                title: 'User Config',
                collapsible: false,
                defaults: {
                    labelWidth: 89,
                    anchor: '100%',
                    layout: {
                        type: 'hbox',
                        defaultMargins: {top: 0, right: 5, bottom: 0, left: 0}
                    }
                },
                items: [
                    {
                        width:          50,
                        xtype:          'combo',
                        mode:           'local',
                        value:          'User',
                        triggerAction:  'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel:     'User Role',
                        name:           'role',
                        displayField:   'name',
                        valueField:     'value',
                        queryMode: 'local',
                        store: Ext.create('Ext.data.Store', {
                            fields : ['name', 'value'],
                            data   : [
                                {name : 'User',   value: 'ROLE_USER'},
                                {name : 'Administrator',   value: 'ROLE_ADMIN'}
                            ]
                        })
                    }
                ]
            }
        ],
        buttons:[
            {
                text:'Cancel',
                handler:function () {
                    this.up('window').hide();
                }
            },
            {
                text:'Add User',
                formBind:true,
                handler:function () {
                    var form = this.up('form').getForm();
                    if (form.isValid()) {
                        this.up('window').hide();
                        Ext.getBody().mask('Adding New User...');
                        form.submit({
                            success:function (form, action) {
                                Ext.Msg.alert('Success', 'New User added successfully');
                                userStore.reload();
                                Ext.getBody().unmask();

                            },
                            failure:function (form, action) {
                                Ext.getBody().unmask();
                                Ext.Msg.alert('Failed', 'Fail to sync.');
                            }
                        });
                    }
                }
            }
        ]
    };

    var addUserModelWin = Ext.widget('window', {
        title:'Add New User',
        closeAction:'hide',
        width:450,
        layout:'fit',
        modal:true,
        items:[addUserForm]
    });

    var roleCombo = new Ext.form.ComboBox({
        xtype:'combo',
        mode:'local',
        triggerAction:'all',
        queryMode:'local',
        displayField:'name',
        valueField:'value',
        store:Ext.create('Ext.data.Store', {
            fields:['name', 'value'],
            data:[
                {name : 'User',   value: 'ROLE_USER'},
                {name : 'Administrator',   value: 'ROLE_ADMIN'}
            ]
        })
    });

    var enabledCombo = new Ext.form.ComboBox({
        xtype:'combo',
        mode:'local',
        triggerAction:'all',
        queryMode:'local',
        displayField:   'name',
        valueField:     'value',
        store:Ext.create('Ext.data.Store', {
            fields:['name', 'value'],
            data:[
                {name:'Active', value:'1'},
                {name:'Disabled', value:'0'}
            ]
        })
    });


    var userGrid = Ext.create('Ext.grid.Panel', {
        id:'userMgmt-panel',
        store:userStore,
        columns:[
            Ext.create('Ext.grid.RowNumberer'),
            {text:"User ID", dataIndex:'userId', hidden: true},
            {text:"User Name", flex:1, sortable:true, dataIndex:'userName'},
            {text:"Name", flex:1, sortable:true, dataIndex:'name',
                editor:{
                    xtype:'textfield'
                }},
            {text:"Department", flex:1, sortable:true, dataIndex:'dept',
                editor:{
                    xtype:'textfield'
                }},
            {text:"Role", flex:1, sortable:true, dataIndex:'role',
                editor: roleCombo, renderer: Ext.util.Format.comboRenderer(roleCombo)},
            {text:"Status", flex:1, sortable:true, dataIndex:'enabled',
                editor:enabledCombo, renderer: Ext.util.Format.comboRenderer(enabledCombo)},
            {text:"Last Login", width:140, sortable:true, dataIndex:'lastLogin', renderer: Ext.util.Format.dateRenderer('Y-m-d h:i:s A')}
        ],
        columnLines:true,
        collapsible:false,
        title:'User Management',
        iconCls:'icon-grid',
        autoScroll:true,
        plugins:[cellEditing],
        selModel:{
            selType:'cellmodel'
        },
        tbar:[
            {
                xtype:'button',
                text:'Refresh',
                iconCls: 'icon-refresh',
                handler:function () {
                    userStore.reload();
                }
            },
            {
                xtype:'button',
                text:'Save All',
                iconCls: 'icon-save',
                handler:function () {
                    var test = userStore.getModifiedRecords();
                    if (test.length > 0) {
                        var dataArray = new Array();

                        for (var i in test) {
                            dataArray.push(test[i].data);

                        }
                        var postValue = JSON.stringify(dataArray);
                        postValue = "{\"users\":" + postValue + "}";
                        Ext.getBody().mask('Saving All User Details...');
                        Ext.Ajax.request(
                            {
                                url:'updateUsers.htm',
                                method:'POST',
                                params:{dataArray:postValue},
                                success:function (response) {
                                    Ext.Msg.alert("Success", "All User Details saved successfully.");
                                    userStore.reload();
                                },
                                failure:function (response) {
                                    Ext.Msg.alert("Fail", "Fail to save User Details.");
                                },
                                callback:function () {
                                    Ext.getBody().unmask();
                                    userStore.reload();
                                }
                            }
                        );
                    } else {
                        Ext.Msg.alert("No Changes", "Nothing to save.")
                    }
                }
            },{
                xtype:'button',
                iconCls: 'icon-add-user',
                text:'Add New User',
                handler:function () {
                    addUserModelWin.show();
                }
            }
        ],
        listeners:{
            activate: function(grid,eOpts) {
                userStore.reload();
            }
        }
    });


    return userGrid;
}


