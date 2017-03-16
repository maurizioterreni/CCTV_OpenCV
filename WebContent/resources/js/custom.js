function tableHideAll() {
	$('.table-cell-collapse').collapse('hide');
	showAllButton();
}

function tableShowAll() {
	$('.table-cell-collapse').collapse('show');
	hideAllButton();
}

function hideAllButton() {
	if( $('.toggle-table > a.hide-all').size() > 0 ){
		return;
	}
	$('.toggle-table > a').replaceWith('<a role="button" class="btn btn-default hide-all" onclick="tableHideAll()">'+
	'<span class="button-label">Collapse Table</span> <span class="button-icon glyphicon glyphicon-triangle-top"></span></a>');
}

function showAllButton() {
	if( $('.toggle-table > a.show-all').size() > 0 ){
		return;
	}
	$('.toggle-table > a').replaceWith('<a role="button" class="btn btn-default show-all" onclick="tableShowAll()">'+
	'<span class="button-label">Expand Table</span> <span class="button-icon glyphicon glyphicon-triangle-bottom"></span></a>');
}

function toggleRow(id) {
	$('.tableCollapseRow'+id).collapse('toggle');
	if( $('.collapsible-table .collapse:not(.in)').size() == 0) {
		hideAllButton();
	} else {
		showAllButton();
	}

}

function disableReturn(e, t) {
	if (null == e)
		e = window.event ;
	if (e.keyCode == 13)  {
		t.blur();
		return false;
	}
}

