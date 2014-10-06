$(function () {
    //
    // DataTables initialisation
    //
    var table;
    var loadData_1 = function () {
        table = $('#datatable').dataTable({
            "pagingType": "full_numbers",
            "processing": true,
            "ajax": {
                "url": "/rest/user/all",
                "type": "GET",
                "async": false
            },
            "filter": false,
            "autowidth": true,
            //"deferRender": true,
            "destroy": true,
            "retrieve": true,
            "language": {
                "processing": "正在加载中...",
                "lengthMenu": "每页显示 _MENU_ 条记录",
                "zeroRecords": "无数据显示",
                "info": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "infoEmpty": "暂无数据",
                "infoFiltered": "(从 _MAX_ 条数据中检索)",
                "paginate": {
                    "first": "首页",
                    "previous": "前一页",
                    "next": "后一页",
                    "last": "最后一页"
                }
            },

            "sAjaxDataProp": "users",

            "columns": [
                {  "mData": "userId" },
                {  "mData": "userDname" },
                {  "mData": "userLoginName" },
                {  "mData": "userLoginPwd" },
                {  "mData": "userMobile" },
                {  "mData": "userEmail"}
            ]

        });
    };
    //end.loadData_1

    loadData_1();

});
