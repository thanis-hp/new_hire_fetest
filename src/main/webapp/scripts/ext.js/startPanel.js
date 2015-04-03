Ext.require(['*']);
Ext.ns('App');

App.BTN_OK = 'ok';
App.BTN_YES = 'yes';

Ext.apply(Ext.form.VTypes, {
    password : function(val, field) {
        if (field.initialPassField) {
            var pwd = Ext.getCmp(field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },
    passwordText : 'Password do not match'
});

Ext.Ajax.on('requestcomplete', function (conn, response, options) {

    if(response.responseText.indexOf("<html>") != -1){
        Ext.Msg.alert(
            'Session Expired',
            'Your session expired. Please login to start a new session.',
            function (btn, text) {
                if (btn == App.BTN_OK) {
                    window.location.href='./authenticate.htm';
                }
            }
        );
    }
});
//TreePanel

    var treeStore = Ext.create('Ext.data.TreeStore', {
        root: {
        expanded: true
        },
        proxy: {
        type: 'ajax',
            url: 'getUserTree.htm'
        }
    });

var treePanel = Ext.create('Ext.tree.Panel', {
    id:'tree-panel',
    title:'User Menu',
    region:'west',
    split:true,
    height:300,
    width:'20%',
    minWidth:100,
    rootVisible:false,
    autoScroll:true,
    collapsible:true,
    store:treeStore
});

treePanel.getSelectionModel().on('select', function (selModel, record) {
    treePanel.getSelectionModel().deselectAll(true);
    if (record.get('leaf')) {
        //reset the selectedProfileName
        selectedProfile = undefined;

        Ext.getCmp('content-panel').layout.setActiveItem(record.get('id') + '-panel');
    }
});

treePanel.getSelectionModel().on('beforeselect', function (selModel, record) {
//    treePanel.getSelectionModel().deselectAll(true);

    if (record.get('leaf')) {
        //reset the selectedProfileName

        if (record.get('id') == 'profileGrids') {

            Ext.MessageBox.confirm('Close', 'You may have unsaved changes. Continue?',
                function(btn) {
                    if(btn=='yes') {
                        selModel.suspendEvents();
//                        selModel.hide();  // This cannot be vetoed because no events will be fired.
                        selModel.resumeEvents();
                    }
                });
            return false; //always return false to veto the hide.
        }
    }
});



//TreePanel

var startPanel = function startPanel(){
    //contentPanel
    var layoutItems = [];
    Ext.Object.each(getLayout(), function(name, example) {
        layoutItems.push(example);
    });

    var contentPanel = {
        id: 'content-panel',
        region: 'center', // this is what makes this panel into a region within the containing layout
        layout: 'card',
        margins: '0 2 2 1',
        activeItem: 0,
        border: false,
        items: layoutItems
//        items: grid
    };
    //contentPanel

    var viewport = Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 5
        },
        title: "HP FE Test Environment",
        defaults: {
            split: true
        },
        items: [treePanel, contentPanel],
        renderTo: Ext.getBody()
    });
};














