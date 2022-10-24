window.onload = function() {


    var platformInfo = {};
    var redirectUrl;
    var links,
        os = {
            android: navigator.userAgent.match(/Android/i),
            ios: navigator.userAgent.match(/iPhone|iPad|iPod/i)
        };
    var userAgent = navigator.userAgent || navigator.vendor || window.opera;
    // Edge has multiple browsers inside its useragent header. Check it first and the make sure it's not edge in each possible browser
    // Edge 20+
    platformInfo.isEdge = userAgent.indexOf('Edge') != -1;
    // Opera 8.0+
    platformInfo.isOpera = userAgent.indexOf("Opera") != -1 || userAgent.indexOf('OPR') != -1;
    // Firefox
    platformInfo.isFirefox = !platformInfo.isEdge && (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf('FxiOS') != -1);
    // Chrome 1+
    platformInfo.isChrome = !platformInfo.isEdge && (userAgent.indexOf("Chrome") != -1 || userAgent.indexOf('CriOS') != -1);
    // Safari
    platformInfo.isSafari = !platformInfo.isEdge && (!platformInfo.isFirefox && !platformInfo.isChrome && userAgent.indexOf("Safari") != -1);
    // AppleWebKit
    platformInfo.isAppleWebKit = !platformInfo.isEdge && (!platformInfo.isSafari && !platformInfo.isFirefox && !platformInfo.isChrome && userAgent.indexOf("AppleWebKit") != -1);
    // Internet Explorer 6-11
    platformInfo.isIE = (userAgent.indexOf("MSIE") != -1) || (!!document.documentMode == true);
    // Check if Mac
    platformInfo.isMac = navigator.platform.indexOf('Mac') > -1;
    // Check if Windows
    platformInfo.isWin = navigator.platform.indexOf('Win') > -1;
    // Check if Linux
    platformInfo.isLinux = navigator.platform.indexOf('Linux') > -1;
    // Check if iOS
    platformInfo.isiOS = userAgent.indexOf("iPad") != -1 || userAgent.indexOf('iPhone') != -1;
    // Check if Android
    platformInfo.isAndroid = userAgent.indexOf("android") > -1;
    // Check if Electron
    platformInfo.isElectron = (typeof process === 'object') && process.versions && (process.versions.electron !== undefined);
    // Check if WebRTC is available
    platformInfo.isWebRTCAvailable = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || (navigator.mediaDevices ? navigator.mediaDevices.getUserMedia : undefined)) ? true : false;
    // Check if 64bit
    platformInfo.is64bit = navigator.userAgent.indexOf('WOW64') > -1 || navigator.userAgent.indexOf('Win64') > -1 || window.navigator.platform == 'Win64';


    var scheduleCallId, type;
    var params = window.location.href.split('?')[1];
    var paramsObj = params ? $.parseJSON('{"' + decodeURI(params).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}') : {};

    if (window.location.href.indexOf('scheduleCallId') > -1) {
        scheduleCallId = paramsObj.scheduleCallId;
    }
    if (window.location.href.indexOf('type') > -1) {
        type = paramsObj.type;
    }

    if (platformInfo.isiOS) {
        if (scheduleCallId) {
        	
        	redirectUrl = 'kuber://custSchedule?scheduleCallId=' + scheduleCallId;
        //alert(""+redirectUrl);
        if (type && type === 'guest') {
            redirectUrl = redirectUrl + '&type=' + type;
        }
       // var img = document.createElement('img');
      //  img.setAttribute("alt","Guest");
        
        var mydiv = document.getElementById("app");
        var aTag = document.createElement('a');
        aTag.setAttribute('href','https://itunes.apple.com/app/id310633997');
        aTag.id= 'appStore';
        //img.src = contextUrl+'/images/vd_icon.png';
        //img.onload = function() {
        aTag.innerHTML = '<img src="'+contextUrl+'/images/appstore.png" alt="AppStore"/> Open in App Store';
        //}
        
        //aTag.innerHTML = "Open in App Store";
        mydiv.appendChild(aTag);
        
      //  var mydiv = document.getElementById("app");
        var bTag = document.createElement('a');
        bTag.setAttribute('href',redirectUrl);
        bTag.setAttribute('class','em_btn');
        bTag.id= 'appRef';
        bTag.innerHTML = '<img src="'+contextUrl+'/images/vd_icon.png" alt="Conference" /> Open in App';
       // aTag.innerHTML = "Open in App";
        mydiv.appendChild(bTag);
        //var fallBackUrl = 'itms-apps://itunes.apple.com/app/facebook/id284882215';
       //var timeout = setTimeout(openFallback(Date.now(),fallBackUrl), 1000);
        
        } 
        else {

            //                        	alert("object",contextUrl+"/welcome");
            window.location.href = contextUrl + "/welcome";
            // window.location.reload(true);
            // window.location.assign(window.location.href);
            //                            	setTimeout(function(){window.location.href=contextUrl+"/welcome";window.location.reload();},250);

        }

    } else {
        if (scheduleCallId) {
        	
            redirectUrl = contextUrl+'/custSchedule?scheduleCallId='+scheduleCallId;
                    	  if(type && type==='guest') {
                      		redirectUrl=redirectUrl+'&type='+type;
                      	}
                    	  window.location.href=redirectUrl;
        


            
            /*setTimeout(() => {
            	var mobile = document.getElementById('mobile');
            	alert('mobile',mobile.getAttribute('href'));
            	mobile.click();
			}, 3000);*/
            
            
        	
        } else {

            window.location.href = contextUrl + "/welcome";
            //  window.location.reload(true);
            //  window.location.assign(window.location.href);
            //                    		  setTimeout(function(){window.location.href=contextUrl+"/welcome";window.location.reload();},250);

        }
    }

};

var openFallback = function(ts, appLink) {
    return function() {
        var link = appLink;
        var wait = 1000 + 500;
        if (typeof link === "string" && (Date.now() - ts) < wait) {
            window.location.href = link;
        }
    }
}