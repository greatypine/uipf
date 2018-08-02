var editIndex = undefined;
function DGedit(){
	this.endEditing = function(id,field){
		if (editIndex == undefined){return true}
		if ($('#'+id).datagrid('validateRow', editIndex)){
//			var ed = $('#'+id).datagrid('getEditor', {"index":editIndex,"field":field});
//			var productname = $(ed.target).combobox('getText');
//			$('#'+id).datagrid('getRows')[editIndex][field] = productname;
			$('#'+id).datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	this.onClickCell = function(index, field,id){
		if (this.endEditing(id,field)){
			$('#'+id).datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
			editIndex = index;
		}
	}
	this.updateActions = function(id,index){
		$('#'+id).datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
}