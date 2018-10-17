/**
 * 图标类
 */
function Reports(){
	this.initCustomerConsumption = function(data){
		var customerConsumption = echarts.init(document.getElementById("customerConsumption"),theme);
		var option = {	
			tooltip : {
				trigger : "axis",
				show : true,
				formatter: function(a,b,c){
				    var title = "";
				    var val = "";
				    if(a.length>1){
				    	title = a[0].name;
				    	if(title.length>6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月"+title.substring(6,title.length)+"日";
				    	else if(title.length>4 && title.length<=6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月";
				    	else if(title.length<=4) val = title+"年"
				    	var sbane = "";
				    		sbane = a[0].marker+a[0].seriesName+"："+(a[0].value*10000).toFixed(2)+"元</br>";
				    		sbane += a[1].marker+a[1].seriesName+"："+(a[1].value*10000).toFixed(2)+"元</br>";
				    	val += "</br>"+sbane+a[2].marker+a[2].seriesName+"："+a[2].value+"人</br>"+a[3].marker+a[3].seriesName+"："+a[3].value+"人"
				    }
				    return val; 
		        }
			},
			toolbox: {
		        show: true,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center',
		        feature: {
		            mark: {show: true},
		            dataView: {show: false, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
			calculable : true,
			xAxis : [ {
				type : "category",
				data : data.years,
				axisLabel : {
					// rotate:45, //刻度旋转45度角
					textStyle : {
						fontSize : 12,
					},
					interval : 0
//					formatter : function(params){
//			          return formatAxisLabelExt(params,fontnumber);
//					}
				},
				axisPointer: {
	                type: 'shadow'
	            }
			} ],
			grid : {
				x : 70,
				y : 70,
				x2 : 90,
				y2 : 30,
			},
			yAxis : [
				{
				type : "value",
//				show : false,
				name: '消费金额',
		        splitLine:{show: false},
				axisLabel : {
					// rotate:45, //刻度旋转45度角
					textStyle : {
						fontSize : 12,
					},
					interval : 0,
					formatter: function(val){
						return '￥'+Number(val)*10000;
					}
				}
			},
	        {
	            type: 'value',
	            name: '初/复诊',
	            min: 0,
	            max: data.max,
	            interval: data.interval,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        } ],
			series : [ {
				name:"初诊消费总金额",
				type : "bar",
				data : data.camount,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : function(val){
								return '￥'+(Number(val.value)*10000).toFixed(2);
							}
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			}, {
				name:"复诊消费总金额",
				type : "bar",
				data : data.famount,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : function(val){
								return '￥'+(Number(val.value)*10000).toFixed(2);
							}
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			{
				name:"初诊",
				type : "line",
				data : data.chuzhen,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			{
				name:"复诊",
				type : "line",
				data : data.fuzhen,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			}
			],
			title : {
				text :data.series_name,
				x:'center',
				textStyle : {
					fontFamily : '"微软雅黑",Verdana,Arial,Helvetica,sans-serif',
					fontWeight : 'normal',
				}
			},
			backgroundColor : "#ECF1F4"
		}
		customerConsumption.setOption(option);
	}
	this.initEmployeeTreatOrder = function(data){
		var employeeTreatOrder = echarts.init(document.getElementById("customerConsumption"),theme);
		var option = {	
			tooltip : {
				trigger : "axis",
				show : true,
				formatter: function(a,b,c){
				    var title = "";
				    var val = "";
				    if(a.length>1){
				    	title = a[0].name;
				    	if(title.length>6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月"+title.substring(6,title.length)+"日";
				    	else if(title.length>4 && title.length<=6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月";
				    	else if(title.length<=4) val = title+"年"
				    	var sbane = "";
				    		sbane += a[1].seriesName+"："+(a[1].value)+"</br>";
				    		sbane += a[0].seriesName+"："+(a[0].value*10000).toFixed(2)+"元</br>";
				    		sbane += a[2].seriesName+"："+(a[2].value)+"</br>";
				    	val += "</br>"+sbane
				    }
				    return val; 
		        }
			},
			toolbox: {
		        show: true,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center',
		        feature: {
		            mark: {show: true},
		            dataView: {show: false, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
			calculable : true,
			xAxis : [ {
				type : "category",
				data : data.years,
				axisLabel : {
					// rotate:45, //刻度旋转45度角
					textStyle : {
						fontSize : 12,
					},
					interval : 0
//					formatter : function(params){
//			          return formatAxisLabelExt(params,fontnumber);
//					}
				},
				axisPointer: {
	                type: 'shadow'
	            }
			} ],
			grid : {
				x : 70,
				y : 70,
				x2 : 90,
				y2 : 30,
			},
			yAxis : [
				{
				type : "value",
//				show : false,
				name: '接诊金额',
		        splitLine:{show: false},
				axisLabel : {
					// rotate:45, //刻度旋转45度角
					textStyle : {
						fontSize : 12,
					},
					interval : 0,
					formatter: function(val){
						return '￥'+Number(val)*10000;
					}
				}
			},
	        {
	            type: 'value',
	            name: '接诊总人数',
	            min: 0,
	            max: data.max,
	            interval: data.interval,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        } ],
			series : [ {
				name:"接诊总金额",
				type : "bar",
				data : data.total_amont,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : function(val){
								return '￥'+(Number(val.value)*10000).toFixed(2);
							}
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			 {
				name:"接诊人姓名",
				type : "bar",
				data : data.nickname,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			{
				name:"总接诊人数",
				type : "line",
				data : data.employeeTotalOrder,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			}
			],
			title : {
				text :data.series_name,
				x:'center',
				textStyle : {
					fontFamily : '"微软雅黑",Verdana,Arial,Helvetica,sans-serif',
					fontWeight : 'normal',
				}
			},
			backgroundColor : "#ECF1F4"
		}
		employeeTreatOrder.setOption(option);
	};
	this.initBackEmployeeTreatOrder = function(data){
		var employeeTreatOrder = echarts.init(document.getElementById("customerConsumption"),theme);
		var option = {
			tooltip : {
				trigger : "axis",
				show : true,
				formatter: function(a,b,c){
				    var title = "";
				    var val = "";
				    if(a.length>1){
				    	title = a[0].name;
				    	if(title.length>6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月"+title.substring(6,title.length)+"日";
				    	else if(title.length>4 && title.length<=6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月";
				    	else if(title.length<=4) val = title+"年"
				    	var sbane = "";
				    		sbane += a[1].seriesName+"："+(a[1].value)+"</br>";
				    		sbane += a[0].seriesName+"："+(a[0].value)+"人</br>";
				    	val += "</br>"+sbane
				    }
				    return val; 
		        }
			},
			toolbox: {
		        show: true,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center',
		        feature: {
		            mark: {show: true},
		            dataView: {show: false, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore: {show: true},
		            saveAsImage: {show: true}
		        }
		    },
			calculable : true,
			xAxis : [ {
				type : "category",
				data : data.years,
				axisLabel : {
					textStyle : {
						fontSize : 12,
					},
					interval : 0
				},
				axisPointer: {
	                type: 'shadow'
	            }
			} ],
			grid : {
				x : 70,
				y : 70,
				x2 : 90,
				y2 : 30,
			},
			yAxis : [
	        {
	            type: 'value',
	            name: '预约人数',
	            min: 0,
	            max: data.max,
	            interval: data.interval,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        } ],
			series : [ {
				name:"预约总人数",
				type : "bar",
				data : data.totalOrder,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : function(val){
								return val.value+'人';
							}
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			 {
				name:"预约人姓名",
				type : "bar",
				data : data.nickname,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			}
			],
			title : {
				text :data.series_name,
				x:'center',
				textStyle : {
					fontFamily : '"微软雅黑",Verdana,Arial,Helvetica,sans-serif',
					fontWeight : 'normal',
				}
			},
			backgroundColor : "#ECF1F4"
		}
		employeeTreatOrder.setOption(option);
	};
	
	this.queryCountInventoryPie = function(data){
		var charts = echarts.init(document.getElementById("customerConsumption"),theme);
		var option = {
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c}元 ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: data.productName
			    },
			    toolbox: {
			        show: true,
			        orient: 'vertical',
			        left: 'right',
			        top: 'center',
			        feature: {
			            saveAsImage: {show: true}
			        }
			    },
			    series : [
			        {
			            name: '产品消费',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            selectedMode: 'single',
			            data:data.data,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ],
			    title : {
					text :data.series_name,
					x:'center',
					textStyle : {
						fontFamily : '"微软雅黑",Verdana,Arial,Helvetica,sans-serif',
						fontWeight : 'normal',
					}
				},
				backgroundColor : "#ECF1F4"
			};
		charts.setOption(option);
	};
	
	this.initQReport = function(data){
		var employeeTreatOrder = echarts.init(document.getElementById("indexreport"),theme);
		var option = {	
			tooltip : {
				trigger : "axis",
				show : true,
				formatter: function(a,b,c){
				    var title = "";
				    var val = "";
				    if(a.length>1){
				    	title = a[0].name;
				    	if(title.length>6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月"+title.substring(6,title.length)+"日";
				    	else if(title.length>4 && title.length<=6) val = title.substring(0,4)+"年"+title.substring(4,6)+"月";
				    	else if(title.length<=4) val = title+"年"
				    	var sbane = "";
				    		sbane += a[1].seriesName+"："+(a[1].value)+"人</br>";
				    		sbane += a[0].seriesName+"："+(a[0].value*10000).toFixed(2)+"元</br>";
				    	val += "</br>"+sbane
				    }
				    return val; 
		        }
		    },
			toolbox: {
		        show: true,
		        orient: 'vertical',
		        left: 'right',
		        top: 'center'
		    },
			grid : {
				x : 70,
				y : 70,
				x2 : 90,
				y2 : 30,
			},
			xAxis : [ {
				type : "category",
				data : data.datetime,
				axisLabel : {
					textStyle : {
						fontSize : 12,
					},
					interval : 0
				},
				axisPointer: {
	                type: 'shadow'
	            }
			} ],
			yAxis : [
				{
				type : "value",
//				show : false,
				name: '治疗总金额',
				min:0,
				max:data.maxtotal_amonts,
				axisLabel : {
					// rotate:45, //刻度旋转45度角
					textStyle : {
						fontSize : 12,
					},
					interval : 0,
					formatter: function(val){
						return '￥'+Number(val)*10000;
					}
				}
			},
	        {
	            type: 'value',
	            name: '新顾客',
	            min: 0,
	            max: data.maxnewnumb,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        } ],
			series : [{
				name:"治疗总金额",
				type : "bar",
				data : data.total_amounts,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : function(val){
								return '￥'+(Number(val.value)*10000).toFixed(2);
							}
						},
						labelLine : {
							show : true,
						},
						areaStyle : {
							type : "default"
						}
					}
				}
			},
			{
				name:"新顾客",
				type : "line",
				data : data.newnumbs,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'top',
							formatter : '{c}'
						}
					}
				}
			}
			],
			title : {
				text :data.title,
				x:'center',
				textStyle : {
					fontFamily : '"微软雅黑",Verdana,Arial,Helvetica,sans-serif',
					fontWeight : 'normal',
				}
			},
			backgroundColor : "#fafafa"
		}
		employeeTreatOrder.setOption(option);
	};
	
	this.initProjectType = function(params){
		var projectTypeOrder = echarts.init(document.getElementById("projecttypeReprot"),theme);
		var option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        show: true,
			        orient: 'vertical',
			        left: 'right',
			        top: 'center',
			        feature: {
			            mark: {show: true},
			            dataView: {show: false, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : params.xAxis,
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'数量',
			            type:'bar',
			            barWidth: '60%',
			            data:params.data,
						itemStyle : {
							normal : {
								label : {
									show : true,
									position : 'top',
									formatter : function(val){
										return val.value;
									}
								},
								labelLine : {
									show : true,
								},
								areaStyle : {
									type : "default"
								}
							}
						}
			        }
			    ],
			    backgroundColor : "#fafafa"
			};
		projectTypeOrder.setOption(option);
	};
	
	this.initDataTable = function(params){
		//预编译模板
		rm_datatable = $('#rm_datatable').DataTable({
			processing: true,
			stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
			serverSide: true,  //启用服务器端分页
			searching: false,  //禁用原生搜索
			ordering:  false,
			orderMulti: false,  //启用多列排序
			order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
			renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
			pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
	        ajax:{//ajax请求数据源
	            "url": basePath+"/pmrs/business/queryRMDataList",
	            "type": "get",
	            "data": function (data) {//添加额外的数据给服务器
		             data.pageIndex  = (data.start / data.length)+1;//当前页码
		             var params = collectParams(-1);
		             if(params != null){
		            	 data.store_id = "";
		            	 data.province_code = "";
		            	 data.ad_code = "";
		            	 data.store_group = "";
		            	 if(params.hasOwnProperty("store_id")){
		            		 if(params.store_id == ""){
		            			 if(townMap.size()>0){
		            				 var tempsids = townMap.keySet().join(',');
		            				 data.store_id = tempsids
		            			 }
		            		 }else{
		            			 data.store_id = params.store_id;
		            		 }
		            	 }
		            	 if(params.hasOwnProperty("province_code")){
		            		 data.province_code = params.province_code;
		            	 }
		            	 if(params.hasOwnProperty("city_code")){
		            		 data.city_code = params.city_code;
		            	 }
		            	 if(params.hasOwnProperty("ad_code")){
		            		 data.ad_code = params.ad_code;
		            	 }
		            	 if(params.hasOwnProperty("store_group")){
		            		 data.store_group = params.store_group;
		            	 }
		             }
		            }
	        },
	        "language":{ "url":basePath+"/resource/js/pmrs/Chinese.json"},
	        "columns": [
						{ "data": "areaname",width : "60px" },
						{ "data": "countyname",width : "60px" },
						{ "data": "store_name",width : "60px" },
	                    {
	       	                data: "firstname",
//					       	className : "ellipsis",
	       	                //固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
	       	                //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
	       	                width : "150px"
	                    },
	                    { "data": "lastname",width : "150px"},
	                    { "data": "support",width : "60px" },
	                    { "data": "confidence",width : "60px"},
	                    { "data": "lift",width : "40px" }
	                ]
	    });
	}
}

/**
 * ECHARTS辅助方法 X或者Y轴文字换行
 */
 var formatAxisLabelExt = function(params,numb){
	 var newParamsName = "";
     var paramsNameNumber = params.length;
     var provideNumber = numb;
     var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
     if (paramsNameNumber > provideNumber) {
         for (var p = 0; p < rowNumber; p++) {
             var tempStr = "";
             var start = p * provideNumber;
             var end = start + provideNumber;
             if (p == rowNumber - 1) {
                 tempStr = params.substring(start, paramsNameNumber);
             } else {
                 tempStr = params.substring(start, end) + "\n";
             }
             newParamsName += tempStr;
         }

     } else {
         newParamsName = params;
     }
     return newParamsName;
};