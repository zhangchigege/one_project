(function(){
  $(function(){
    var self = this,_$indexlist,_$wrap_container,_$pageno=0,_$swiper_img,_$ligin_icon,_type,_pageRows,_index=0;
    //数字暂定全局变量，局部会有问题
    var integerNum,decimalsNum;
    var tab;//其他页面跳转过来的tab
    //var dataArr=[];//缓存数据
    _type = '9NEW';//默认新手专区
    _pageRows = 5;
    _$indexlist = $('#sp_wrap').find('.sp_content');
    _$wrap_container = $('#sp_wrap');
//    $('#taobao').hide();
    $('#jingdong').hide();
    $('#shebao').hide();
    $('#liantong').hide();

    //选项卡
    $("#sp_header_navbar li").click(function() {
      _$pageno = 0;
      $(this).addClass("navbar_active").siblings().removeClass("navbar_active");
      _index = $(this).index();
      tab = _index + 1;
      window.location.href = 'invest.jsp?tab=' + tab;
//      _$wrap_container.html('<ul class="sp_content">'+ '</ul>');
      _$indexlist = $('#sp_wrap').find('.sp_content');//切换tab需重新找dom节点
      $("#sp_wrap ul").eq(_index).css("display","block").siblings().css("display","none");
      switchType(_index);
      //没数据才调接口
      //if (_$wrap_container.children('li').length == 0){
    });
   

    //转换type
    var switchType = function (_index) {
      switch (_index){
        case 0 :
          _type = '9NEW';
          break;
        case 1 :
          _type = 'AIB';
          break;
        case 2 :
          _type = '9HUI';
          break;
        case 3 :
          _type = 'TRS';
         break;
      }
    };

    // 检测其他页面是否传tab参数进来
    (function () {
      var v = parseUrl();
      if (v != undefined){
        tab = v['tab'];
        if (tab == 1) {
          //    默认
        	$('#taobao').show();
        	$('#jingdong').hide();
        	$('#shebao').hide();
        	$('#liantong').hide();
        }else if (tab == 2) {
          _type = 'AIB';
          _index = 1;
          $("#sp_header_navbar").find('li').eq(_index).addClass('navbar_active').siblings().removeClass('navbar_active');
          $('#taobao').hide();
          $('#jingdong').show();
          $('#shebao').hide();
          $('#liantong').hide();
        }else if (tab == 3) {
          _type = '9HUI';
          _index = 2;
          $("#sp_header_navbar").find('li').eq(_index).addClass('navbar_active').siblings().removeClass('navbar_active');
          $('#taobao').hide();
      	  $('#jingdong').hide();
      	  $('#shebao').show();
      	  $('#liantong').hide();
        }else if (tab == 4) {
          _type = 'TRS';
          _index = 3;
          $("#sp_header_navbar").find('li').eq(_index).addClass('navbar_active').siblings().removeClass('navbar_active');
          $('#taobao').hide();
      	  $('#jingdong').hide();
      	  $('#shebao').hide();
      	  $('#liantong').show();
        }
      };
    })();
  });
})();