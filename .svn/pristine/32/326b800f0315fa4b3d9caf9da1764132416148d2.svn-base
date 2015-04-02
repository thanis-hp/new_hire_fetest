

function getLayout() {

    var alarmStore = new Ext.data.JsonStore({
        storeId: 'Alarm',
        autoLoad:false,
        proxy: {
            type: 'ajax',
            url: 'getAlarm.htm',
            reader: {
                type: 'json',
                root: 'alarms'
            }
        },

        fields: ['severity', 'eventTime', 'status', 'managedElement', 'alarmName','location','domain','additionalText']
    });

    Ext.define('Services', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'service'},
            {name: 'subscriberId'}
        ]
    });

    var alarmGridTab = Ext.create('Ext.grid.Panel', {
        id: 'alarmTab',
        store: alarmStore,
        viewConfig: {
            loadMask: false
        },
        columns: [
            Ext.create('Ext.grid.RowNumberer'),
            {text: "Severity", width: 60, sortable: true, dataIndex: 'severity',
                renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    if (value == 'CRITICAL') {
                        return '<div style="background-color:red; text-decoration:blink; color:white;">' + value + '</div>';
                    } else if (value == 'MAJOR') {
                        return '<div style="background-color:orange; text-decoration:blink; color:white;">' + value + '</div>';
                    }

                    return value;
                }},
            {text: "Event Time", width: 120, sortable: true, dataIndex: 'eventTime'},
            {text: "Status", width: 60, sortable: true, dataIndex: 'status'},
            {text: "Managed Object", width: 150, sortable: true, dataIndex: 'managedElement'},
            {text: "Alarm Name", width: 120, sortable: true, dataIndex: 'alarmName'},
            {text: "Location", width: 100, sortable: true, dataIndex: 'location'},
            {text: "Domain", width: 100, sortable: true, dataIndex: 'domain'},
            {text: "Additional Text", flex:1, sortable: true, dataIndex: 'additionalText'}
        ],
        fbar: [
            { xtype: 'button', text: 'Clear Filter',handler: function(){
                selectedNode = undefined; loadAlarmGrid();
                Ext.getCmp('topologyKBGrid').setTitle("Knowledge Base");
            } }
        ],
        columnLines: true,
        title:'Alarm Dashboard',
        autoScroll: true
    });

    var topoServiceCombo = Ext.create('Ext.form.ComboBox', {
        id:'servicesCombo',
        labelWidth:'5',
        queryMode:'local',
        displayField:'name'/*+'-'+'subscriberId'*/,
        valueField:'value',
        name:'value',
        fieldLabel:'Services',
        anchor:'100%',
        emptyText:'Select a Service...',
        store: serviceComboStore,
        listeners:{
            change:function(combo, ewVal, oldVal) {
                doComboSelect(combo, ewVal, oldVal);
            },
            beforequery: function(qe){
                delete qe.combo.lastQuery;
            }
        }
    });

    function doComboSelect(combo, ewVal, oldVal) {
        topoTabPanel.setActiveTab(0);
        is2leg = false;

        toggleVOBBFields(combo.getDisplayValue());

        getTask.stop();  // stop the nodeStatusTask
        Ext.getCmp('txtSubId').setValue(combo.getValue());
        getTopology(combo.getSubmitValue(), is2leg);
    }

    var pbar = Ext.create('Ext.ProgressBar', {
        id    : 'pbar',
        width : 300,
        height:10
    });
    pbar.wait({
        interval: 500,
        //duration: 50000,
        increment: 15,
        text: 'fetching...',
        scope: this
    });


    var modelPBWin  = Ext.widget('window', {
    	id : 'modelPBWin',
        title: 'Please wait, fetch Speed Profile ... ',
        closeAction: 'hide',
        width: 400,
        layout: 'fit',
        resizable: true,
        modal: false,
        items  : [pbar]
    });

    topoTabPanel = Ext.createWidget('tabpanel', {
        region:'south',
        height:240,
        id:'tabTopo',
        layout:'fit',
        border:false,
        collapsible:true,
        items:[alarmGridTab, Ext.getCmp('configGrid-panel'), getTopologyKBGrid(), Ext.getCmp('callIdsGrid-panel'),Ext.getCmp('ftpGrid-panel'),Ext.getCmp('poly-callIdsGrid-panel')]

    });

    topoTabPanel.on('tabchange', function (tabPanel, newCard, oldCard, eOpts) {
        if (newCard.getId() == 'alarmTab') {
        	loadAlarmGrid();
        } else if (newCard.getId() == 'configGrid-panel') {
        	// stop alarm 
           // isIPMSAN = true;  //TODO  remove this hard codeline
            if (isIPMSAN) {
                Ext.getCmp('configGrid-panel').getStore().reload({
                    params: {subscriberId: Ext.getCmp('servicesCombo').getValue()}
                });
                Ext.getCmp('configGrid-panel').getStore().sort('vInterface', 'ASC');
                Ext.getCmp('refreshSP').setDisabled(false);
            } else {
                Ext.getCmp('configGrid-panel').getStore().reload({
                    params: {subscriberId: Ext.getCmp('servicesCombo').getValue()}
                });
                Ext.getCmp('refreshSP').setDisabled(true);
            }
        }

    });

    cw = Ext.create('Ext.draw.Component', {
        id:'topo',
        viewBox : false,
        width : 1024,
        height : 768,
        autoScroll: true
    });

    var drawPanel = {
        id:'topologyPanel',
        region:'center',
        layout: 'fit',
        items: [cw],
        collapsible:false
    };


    function submitVobbBNumber() {
        if (Ext.getCmp('bNumber').getValue() > 0) {
            is2leg = true;
            getTopology(Ext.getCmp('servicesCombo').getValue(), true);
        } else {
            Ext.Msg.alert('Error', 'Please key in the B Number!!! ');
        }
    }

    function resetVobb(){
        is2leg = false;
        Ext.getCmp('bNumber').reset();
        getTopology(Ext.getCmp('servicesCombo').getValue(), is2leg);
        if(Ext.getCmp('tabTopo').getCollapsed()) Ext.getCmp('tabTopo').toggleCollapse();
    }



    return {

        start:{
            id:'start-panel',
            title:'Welcome to HP OWD for TM SPANMS',
            layout:'fit',
            bodyStyle:'padding:25px',
            contentEl:'start-div'
        },
        topology:{
            id:'topology-panel',
            region:'center',
            layout:'border',
            tbar:[topoServiceCombo,{
                    fieldLabel:'B Number',
                    id:'bNumber',
                    xtype:'textfield',
                    name:'subscriberId',
                    allowBlank:true/*,
                    hideTrigger: true,
                    keyNavEnabled: false,
                    mouseWheelEnabled: false*/
                }, {
                    xtype:'button',
                    id:'searchBtn',
                    iconCls: 'icon-search',
                    text:'Search',
                    formBind:true,
                    handler:submitVobbBNumber
                }, {
                    xtype:'button',
                    id:'resetBtn',
                    iconCls: 'icon-reset',
                    text:'Reset',
                    handler:function () {
                        resetVobb();
                    }
                }, '-', {
                    xtype:'button',
                    id:'refreshKpiBtn',
                    iconCls: 'icon-refresh',
                    text:'Refresh Network KPI',
                    formBind:false,
                    handler:selectNetworkKPI
            },'-', {
                xtype:'button',
                id:'getCallIdsBtn',
                iconCls: 'icon-refresh',
                text:'Get Exfo Call Ids',
                formBind:false,
                handler:function () {
                        Ext.getCmp('aNum').setValue(Ext.getCmp('servicesCombo').getValue());
                        Ext.getCmp('bNum').setValue(Ext.getCmp('bNumber').getValue());
                        modelCallIdsWin.show();
                }
            },'-', {
                xtype:'button',
                id:'getPolyCallIdsBtn',
                iconCls: 'icon-refresh',
                text:'Get Polystar Call Ids',
                formBind:false,
                handler:function () {
                    Ext.getCmp('aNum-Txt').setValue(Ext.getCmp('servicesCombo').getValue());
                    Ext.getCmp('bNum-Txt').setValue(Ext.getCmp('bNumber').getValue());
                    polyModelCallIdsWin.show();
                }
            }, '-',{
                xtype:'button',
                id:'showRegButton',
                iconCls:'icon-refresh',
                text:'Exfo Registration Search Page',
                formBind:false,
                handler:function () {
                    window.open("http://10.242.43.235:8080/reports/voip/search/registrations.html", "testing");

                }}],

            listeners:{
                afterRender:function (thisForm, options) {
                    this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
                        enter:submitVobbBNumber,
                        scope:this
                    });
                }
            },
            split:true,
            collapsible:false,
            items:[drawPanel, topoTabPanel]
        },
        serviceDashboard:getServiceDashboardRegion(),
        serviceDashboardLoc:getServiceDashboardLocation(),
        serviceDashboardPrefs:getServiceDashboardPrefs(),
        kpi:getKpiGrid(),
        userAccessTable:getUserAccessGrid(),
        userAccessChart:getUserAccessChart(),
        userManagement:getUserGrid(),
        linkUtilz: getLinkUtilzGrid()

    };
}

	 
