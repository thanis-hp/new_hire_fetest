function getUserAccessGrid() {

    var userAccessGridStore = new Ext.data.JsonStore({
        storeId:'AccessList',
        autoLoad:false,
        proxy:{
            type:'ajax',
            url:'getUserAccessList.htm',
            reader:{
                type:'json',
                root:'accessList'
            }
        },

        fields:['userId', {name: 'strDateTime', type: 'date', dateFormat: 'Y-m-d h:i:s A'}, 'actionType', 'sessionId', 'user.name', 'user.dept']
    });

    var userAccessGrid = Ext.create('Ext.grid.Panel', {
        id:'userAccess-panel',
        store:userAccessGridStore,
        columns:[
            Ext.create('Ext.grid.RowNumberer',{width:50}),
            {text:"User ID", flex:1, sortable:true, dataIndex:'userId'},
            {text:"Name", flex:1, sortable:true, dataIndex:'user.name'},
            {text:"Department", flex:1, sortable:true, dataIndex:'user.dept'},
            {text:"Action Type", flex:1, sortable:true, dataIndex:'actionType'},
            {text:"Date & Time", flex:1, sortable:true, dataIndex:'strDateTime', renderer: Ext.util.Format.dateRenderer('Y-m-d h:i:s A')}
        ],
        columnLines:true,
        collapsible:false,
        title:'User Access Table',
        iconCls:'icon-grid',
        autoScroll:true,
        tbar:[
            {
                xtype:'button',
                iconCls: 'icon-refresh',
                text:'Refresh',
                handler:function () {
                    userAccessGridStore.reload();
                }
            }, {
                xtype: 'datefield',
                fieldLabel: 'Filter by Date:',
                name: 'dateFilter' ,
                format: 'Y-m-d',
                listeners:{
                    change: function (me, newValue, oldValue, eOpts) {
                        if (newValue != oldValue) {
                            userAccessGridStore.load({
                                params: {
                                    dateFilter: newValue
                                }
                            });
                        }
                    } ,
                    render : function(datefield) {
                        datefield.setValue(new Date());
                    }
                }

            }
        ],
        listeners:{
            activate: function(grid,eOpts) {
                userAccessGridStore.reload();
            }
        }
    });


    return userAccessGrid;
}


