$(document).ready( function () {
	   $('#missing-pets').DataTable({
        "aaSorting": [],
        "columns": [ { "searchable": false }, { "searchable": false }, { "searchable": true } ]
    });
});