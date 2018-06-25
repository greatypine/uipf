/**
 * 利用数组和对象实现
 */
function Map1(){
	/**
	 * 存放键
	 */
	this.arr = new Array();
	/**
	 * 存放值
	 */
	this.data = new Object();
	/**
	 * 放入一个键值对
	 */
	this.put = function(key,val){
		if(this.data[key]==null){
			this.arr.push(key);
		}
		this.data[key]=val;
	};
	/**
	 * 通过键获取键值对里面的值
	 */
	this.get = function(key){
		return this.data[key];
	};
	
	/**
	 * 是否map为空
	 */
	this.isEmpty = function(){
		return this.arr.length==0;
	};
	
	this.remove = function(key){ 
		var index = 0;
		for (var i = 0; i < this.arr.length; i++) {
			if(this.arr[i]==key) {index = i;break;}
		}
		if(isNaN(index)||index>=this.length) return false;
		this.arr.splice(index,1);
		this.data[key] = null;
		delete this.data[key];
	};
	
	this.size = function(){
		return this.arr.length;
	};
	
	this.toString = function(){
		var s = "{";
		for (var i = 0; i < this.arr.length; i++) {
			var key = this.arr[i];
			if(i==this.arr.length-1){
				s += key+":"+this.data[key];
			}else{
				s += key+":"+this.data[key]+",";
			}
		}
		return s+"}";
	};
}

/**
 * 利用对象实现
 */
function Map(){
	this.data = new Object();
	this.keySet = function(){
		var keys = new Array();
		var count = 0;
		for(var key in this.data){
			if(key == 'extend') continue;
			keys[count++] = key;
		}
		return keys;
	};
	this.valueSet = function(){
		var values = new Array();
		var count = 0;
		for(var key in this.data){
			if(key == 'extend') continue;
			values[count++] = this.data[key];
		}
		return values;
	};
	
	this.put = function(key,val){
		this.data[key] = val;
	};
	
	this.get = function(key){
		return this.data[key];
	};
	
	this.size = function(){
		var count = 0;
		for(var key in this.data){
			if(key == 'extend') continue;
			count++;
		}
		return count;
	};
	
	this.remove = function(key){
		delete this.data[key];
	};
	
	this.toString = function(){
		var s = "{";
		for(var i=0;i<this.keySet().length;i++,s+=','){     
            var val = this.data[this.keySet()[i]];     
            s += this.keySet()[i]+":"+val;
            if(i==this.keySet().length-1) break;
        }
		return s+"}";
	};
	
	this.isEmpty = function(){
		for(var key in this.data){return false;}
	    return true;
	};
	this.empty = function(){
		for ( var key in this.data) {
			delete this.data[key];
		}
	};
	this.clear = function(){
		this.empty();
	};
}
