$(function(){
	initdata();
	initlistener();
});
function initdata(){
	if(images.length>0){
		var datas = images;
		if(typeof(images) == 'string'){
			datas = JSON.parse(images);
		}
		if(datas.length==1){
			$("#centent").append(createImages(datas[0],true));
			$("#centent").append(createImagesBtn());
		}else{
			for (var i = 0; i < datas.length; i++) {
				if(i==0){
					$("#centent").append(createImages(datas[i],true));
				}else{
					if(i==datas.length-1){
						$("#centent").append(createImages(datas[i],false));
						$("#centent").append(createImagesBtn());
					}else{
						$("#centent").append(createImages(datas[i],false));
					}
				}
			}
		}
	}else{
		$("#centent").append(createImagesBtn());
	}
}
function initlistener(){
	// buttons
	var sliderControl = document.querySelector(".slider-control");

	// slides informations
	var slides = document.querySelectorAll(".slide"),
	    slidesLength = slides.length;

	// slides array
	var slidesArr = [].slice.call(slides);

	// reverse array sorting
	slidesArr = slidesArr.reverse();

	// slide current
	var slideCurrent = 0;

	sliderControl.addEventListener("click", function(e){
	  target = e.target;
	  if(slidesArr.length<=1) return;
	  // get next button
	  if(target.classList.contains("next")){

	    next = e.target,
	    prev = next.previousElementSibling,
	    nextSlide = slidesArr[slideCurrent + 1],
	    slide = slidesArr[slideCurrent];
	    
	    slide.classList.add("slide-on");
	    slide.classList.remove("text-on");
	    nextSlide.classList.add("text-on");
	    
	    slideCurrent += 1;
	    
	    if(slideCurrent > 0) {
	      prev.classList.remove("disabled");
	    }
	    
	    if(slideCurrent === slidesLength - 1){
	      next.classList.add("disabled");
	    }
	  }
	  
	  // get prev button
	  if(target.classList.contains("prev")){
	    
	    slideCurrent -= 1;
	    
	    prev = e.target,
	    next = prev.nextElementSibling,
	    prevSlide = slidesArr[slideCurrent + 1],
	    slide = slidesArr[slideCurrent];
	    
	    prevSlide.classList.remove("text-on");
	    slide.classList.remove("slide-on");
	    slide.classList.add("text-on");
	    
	    if(slideCurrent === slidesLength - 2){
	      next.classList.remove("disabled");
	    }

	    if(slideCurrent === 0){
	      prev.classList.add("disabled");
	    }
	    
	  }

	});

//	balapaCop("Image Slider", "#999");
}

function createImages(data,f){
	var c = "text-on";
	if(!f) c="";
	var html = '<figure class="slide '+c+'">'
					+'<img src="'+content+"/"+data.imgUrl+'" alt="" width="100%" height="100%"/>'
					+'<figcaption>'
						+'<div class="title">'+data.title+'</div>'
						+'<div class="author">'+data.createUser+'</div>'
					+'</figcaption>'
				+'</figure>';
	return html;
}
function createImagesBtn(){
	var html = '<div class="slider-control">'
					+'<div class="control prev disabled">'
						+'<div class="icon ion-chevron-left"></div>'
					+'</div>'
					+'<div class="control next">'
						+'<div class="icon ion-chevron-right"></div>'
					+'</div>'
				+'</div>';
	return html;
}