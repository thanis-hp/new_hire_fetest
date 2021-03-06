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
    store:treeStore,
    fbar:[
        {
            text:'Change Password',
            width:120,
            handler:function () {
                var updatePasswordForm =  new Ext.FormPanel({
                    id: 'update-password-window',
                    width: 300,
                    height: 150,
                    frame:true,
                    url:'updatePassword.htm',

                    items: [
                        {
                            xtype:'textfield',
                            fieldLabel:'Old Password',
                            name:'oldPassword',
                            inputType:'password',
                            allowBlank:false
                        },{
                            xtype:'textfield',
                            fieldLabel:'New Password',
                            id:'newPassword',
                            name:'newPassword',
                            inputType:'password',
                            allowBlank:false
                        },{
                            xtype:'textfield',
                            fieldLabel:'Confirm Password',
                            id:'confirmPassword',
                            name:'confirmPassword',
                            inputType:'password',
                            vtype : 'password',
                            initialPassField : 'newPassword',
                            allowBlank:false
                        }
                    ],
                    buttons: [
                        {
                            text:'Cancel',
                            handler:function () {
                                this.up('window').hide();
                            }
                        },{
                            text: 'Update',
                            disabled: false,
                            formBind:true,
                            handler: function () {

                                var form = this.up('form').getForm();
                                if (form.isValid()) {
                                    this.up('window').hide();
                                    Ext.getBody().mask('Updating Password...');
                                    form.submit({
                                        success:function (form, action) {
                                            Ext.Msg.alert('Success', 'Password updated successfully');
                                            updatePasswordWindow.close();
                                            Ext.getBody().unmask();

                                        },
                                        failure:function (form, action) {
                                            Ext.getBody().unmask();
                                            Ext.Msg.alert('Failed', 'Fail to update password. Please contact administrator.');
                                        }
                                    });
                                }

                            }
                        }
                    ]
                });

                var updatePasswordWindow = new Ext.Window({
                    title: 'Update Password',
                    layout:'fit',
                    width:300,
                    height:150,
                    bodyPadding: 1,
                    modal: true,
                    resizable:false,
                    closeAction: 'destroy',
                    plain:true,
                    border:false,
                    items:[updatePasswordForm]
                });

                updatePasswordWindow.show();
            }
        },{
            text:'Log Out',
            width:120,
            handler:function () {
                Ext.MessageBox.show({
                    title:'Logout from TeMIP ALB GUI',
                    msg:'Are you sure?',
                    buttons:Ext.MessageBox.OKCANCEL,
                    fn:function (btn) {
                        if (btn == 'ok') {
                            //window.location = "logout2.htm";
                            window.location.href='./logout2.htm';
                        }
                    },
                    animateTarget:'tree-panel',
                    icon:Ext.MessageBox.QUESTION
                });


            }
        }
    ]
//    ,
//    listeners:{
//        beforeselect:{
//
//
//        }
//    }
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














