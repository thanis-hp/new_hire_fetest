

function getLayout() {

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


    return {

        start:{
            id:'start-panel',
            title:'HP FE Test Environment',
            layout:'fit',
            bodyStyle:'padding:25px',
            contentEl:'start-div'
        },
        //profileManagement:getProfileManagementMain(),
        //profileGrids:getProfileGrids(),
        //userAccessTable:getUserAccessGrid(),
        userManagement:getUserGrid()
        //userManagement:getUserGrid(),
        //sampleAlarmManagement: getAlarmGrid(),
        //albHostManagement:getAlbHostGrid()
    };
}

	 
