$(document).ready(function () {
    window.name = 'parent';

    var enterApiKeysEle = document.getElementById("enterApiKeys");
    if (enterApiKeysEle) {
        enterApiKeysEle.click();
    }
    document.getElementById('custsubmit').click();
    document.getElementById('msgToSend').onkeydown = function (e) {
        if (e.keyCode === 13) {
            document.getElementById('send').click();
        }
    };
    setInterval(function () {
        var cusid_ele = document.getElementsByClassName('chatMessage');
        for (var i = 0; i < cusid_ele.length; ++i) {
            var item = cusid_ele[i];
            var elms = item.getElementsByTagName("a");
            if (elms.length === 0) {
                item.innerHTML = prototype.string.autolink(item.innerHTML, '');
            }
        }
    }, 3000);
});

var childWindow;
var usersonconference = new Array();

var seconds = (-1);
var minuites = 0;
var hours = 0;
var timeOutVar;

var secondsNn = (-1);
var minuitesNn = 0;
var hoursNn = 0;
var timeOutVarNn;

var secondsCount = (-1);
var minuitesCount = 0;
var hoursCount = 0;

var timerVarCount;


function retryJoinCall() {
    console.log('retryJoinCall.....');
    PF('retryjoin').show();
}

function dynamicallyRemoveJs() {
    $('head script[src*="VidyoClient.js"]').remove();
}

function refreshEssentials() {
    if (document.getElementById("downloadPDFId")) {
        document.getElementById("downloadPDFId").style.display = "none";
    }
    hideVideoStream();
    document.getElementById('_Everyone').style.background = 'white';
    document.getElementById('_Everyonespan').style.display = 'none';
    if (document.getElementById('collapse1').style.display == 'block') {
        document.getElementById('tbutton').click();
    }
    document.getElementById('collapse1').style.display = 'none';
    $('#tbutton img').src = '#{request.contextPath}/resources/images/top.png';
    $('#vidyoConnector').css("height", "52vh");
    $('#parentRenderer').css("height", "52vh");
    $(".frame video > video").css("height", "52vh");
    chatWindowState = 0
    document.getElementById('incallcontainer').style.display = 'none';

    stopCounterTimer();
    resetWatch();
    $('#spinner').hide();
    removeObjectTag();
    unmuteSpeaker();
    unmuteMic();
    unmuteVideo();
    document.getElementById("timer").innerHTML = '';
    var cnt = 0;
    resetTabs();
    resetuserList();
    dynamicallyRemoveJs();
    resetVidyoConnectorValues();
    $("#renderer").removeClass("rendererFullScreen");
    document.getElementById('renderer').innerHTML = '';
    $('#addstatictab li').each(function (i) {
        cnt++;
        if (cnt > 2) {
            $(this).remove();

        }

    });
    var counter = 0;
    document.getElementById('Everyone').innerHTML = '';
    $('#participant div').each(function (i) {
        counter++;
        if (counter > 1) {
            $(this).remove();
        }

    });
    $('#participantList').empty();

}
function resetWatch() {
    seconds = 0;
    minuites = 0;
    hours = 0;
    stopWatch();
}

function startWatch() {
    var secondss = '';
    var minuitess = '';
    var hourss = '';
    seconds += 1;
    if (seconds > 59) {
        seconds = 0;
        minuites += 1;
    }
    if (minuites > 59) {
        minuites = 0;
        hours += 1;
    }

    if (hours < 10) {
        hourss = '0' + hours;
    } else {
        hourss = '' + hours;
    }
    if (minuites < 10) {
        minuitess = '0' + minuites;
    } else {
        minuitess = '' + minuites;
    }
    if (seconds < 10) {
        secondss = '0' + seconds;
    } else {
        secondss = '' + seconds;
    }
    if (document.getElementById("stopWatchId"))
        document.getElementById("stopWatchId").innerHTML = (hourss + ":" + minuitess + ":" + secondss);
    timeOutVar = setTimeout("startWatch()", 1000);
}

function startWatchNn() {
    var secondsNns = '';
    var minuitesNns = '';
    var hoursNns = '';
    secondsNn += 1;
    if (secondsNn > 59) {
        secondsNn = 0;
        minuitesNn += 1;
    }
    if (minuitesNn > 59) {
        minuitesNn = 0;
        hoursNn += 1;
    }

    if (hoursNn < 10) {
        hoursNns = '0' + hoursNn;
    } else {
        hoursNns = '' + hoursNn;
    }
    if (minuitesNn < 10) {
        minuitesNns = '0' + minuitesNn;
    } else {
        minuitesNns = '' + minuitesNn;
    }
    if (secondsNn < 10) {
        secondsNns = '0' + secondsNn;
    } else {
        secondsNns = '' + secondsNn;
    }
    if (document.getElementById("stopWatchNnId"))
        document.getElementById("stopWatchNnId").innerHTML = (hoursNns + ":" + minuitesNns + ":" + secondsNns);
    timeOutVarNn = setTimeout("startWatchNn()", 1000);
}

function stopWatch() {
    clearTimeout(timeOutVar);
    clearTimeout(timeOutVarNn);
    secondsNn = 0;
    minuitesNn = 0;
    hoursNn = 0;
    if (document.getElementById("stopWatchNnId"))
        document.getElementById("stopWatchNnId").innerHTML = "";
}

function startCounterTimer() {
    document.getElementById('callDurationPanelId').style.visibility = "visible";
    timerVarCount = setInterval(callTimer, 1000);
}

function callTimer() {
    var secondsCounts = '';
    var minuitesCounts = '';
    var hoursCounts = '';
    secondsCount += 1;
    if (secondsCount > 59) {
        secondsCount = 0;
        minuitesCount += 1;
    }
    if (minuitesCount > 59) {
        minuitesCount = 0;
        hoursCount += 1;
    }

    if (hoursCount < 10) {
        hoursCounts = '0' + hoursCount;
    } else
    {
        hoursCounts = '' + hoursCount;
    }
    if (minuitesCount < 10) {
        minuitesCounts = '0' + minuitesCount;
    } else {
        minuitesCounts = '' + minuitesCount;
    }
    if (secondsCount < 10) {
        secondsCounts = '0' + secondsCount;
    } else {
        secondsCounts = '' + secondsCount;
    }
    document.getElementById("callDuration").innerHTML = "Call Duration " + hoursCounts + ":" + minuitesCounts + ":" + secondsCounts;
}

function stopCallTimer() {
    clearTimeout(timerVarCount);
    seconds = (-1);
    minuites = 0;
    hours = 0;
    secondsCount = 0;
    minuitesCount = 0;
    hoursCount = 0;
    document.getElementById('callDurationPanelId').style.visibility = "hidden";
    document.getElementById("callDuration").innerHTML = "";
}

function visibleCallDuration() {
    document.getElementById('callDurationPanelId').style.visibility = "visible";
}

function holdDisplay() {
    var holdvar;
    document.getElementById("holdForm:mge").innerHTML = "Participant(s) put on hold";
    holdvar = setTimeout(function () {
        if (document.getElementById("holdid"))
            document.getElementById("holdid").style.display = "none";
        if (document.getElementById("unholdid"))
            document.getElementById("unholdid").style.display = "block";
        if (document.getElementById("holdidMenu"))
            document.getElementById("holdidMenu").style.display = "none";
        if (document.getElementById("unholdidMenu"))
            document.getElementById("unholdidMenu").style.display = "block";

    }, 5000);

    startWatchNn();
    startWatch();
}

function unholdDisplay() {
    var unholdvar;
    document.getElementById("holdForm:mge").innerHTML = "";
    unholdvar = setTimeout(function () {
        if (document.getElementById("holdid"))
            document.getElementById("holdid").style.display = "block";
        if (document.getElementById("unholdid"))
            document.getElementById("unholdid").style.display = "none";
        if (document.getElementById("holdidMenu"))
            document.getElementById("holdidMenu").style.display = "block";
        if (document.getElementById("unholdidMenu"))
            document.getElementById("unholdidMenu").style.display = "none";
    }, 5000);

    stopWatch();
}

function disableDial() {
    document.getElementById("nextcall").style.background = "red";
}

function checkCallOption() {
    document.getElementById('checkCallType').click();
}

function call() {
    joinViaBrowser();
    if (document.getElementById('refreshbutton')) {
        document.getElementById('refreshbutton').click();
    }
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('vidyoConnector').style.display = 'block';
    document.getElementById('participantDiv').style.display = 'block';
    document.getElementById('videoHead').style.display = 'block';
    document.getElementById('participant').style.height = '8vh';
    document.getElementById('tbutton').style.display = 'inline';
    document.getElementById('collapse1').style.marginTop = '-4px';
}

function callaudio() {
    console.log("callAudio..........");
    joinViaBrowser();
    if (document.getElementById('refreshbutton')) {
        document.getElementById('refreshbutton').click();
    }
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('vidyoConnector').style.display = 'block';
    document.getElementById('participantDiv').style.display = 'block';
    document.getElementById('videoHead').style.display = 'block';
    document.getElementById('participant').style.height = 'auto';
    document.getElementById('tbutton').style.display = 'inline';
    document.getElementById('collapse1').style.marginTop = '-4px';
    myVar = setInterval(myTimer, 1000);

}


function myTimer() {
    if (vidyoConnector) {
        myStopFunction();
        setTimeout(function () {
            muteVideo();
        }, 1000);

    }
}

function myStopFunction() {
    clearInterval(myVar);
}

function callchat() {
    document.getElementById('refreshbuttonchat').click();
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('vidyoConnector').style.display = 'none';
    document.getElementById('splash').style.display = 'none';
    document.getElementById('participantDiv').style.display = 'none';
    document.getElementById('videoHead').style.display = 'none';
    document.getElementById('participant').style.height = '50vh';
    document.getElementById('tbutton').style.display = 'none';
    document.getElementById('collapse1').style.marginTop = '0px';
    $(".nav-toggle").trigger("click");
}

function forwardcalljoinchat() {
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('vidyoConnector').style.display = 'none';
    document.getElementById('splash').style.display = 'none';
    document.getElementById('participantDiv').style.display = 'none';
    document.getElementById('videoHead').style.display = 'none';
    document.getElementById('participant').style.height = '50vh';
    document.getElementById('tbutton').style.display = 'none';
    document.getElementById('collapse1').style.marginTop = '0px';
    $(".nav-toggle").trigger("click");
}

function forwardcalljoinother() {
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('vidyoConnector').style.display = 'block';
    document.getElementById('participantDiv').style.display = 'block';
    document.getElementById('videoHead').style.display = 'block';
    document.getElementById('participant').style.height = '8vh';
    document.getElementById('tbutton').style.display = 'inline';
    document.getElementById('collapse1').style.marginTop = '-4px';

}

function forwardCall() {
    document.getElementById('forwarded').click();
}

function forwardingCall() {
    document.getElementById('forwarding').click();
}

function callHangup() {
    document.getElementById('hanging').click();
}

function playAudio() {
    try {
        var oAudio = document.getElementById('myaudio');
        oAudio.play();
    } catch (e) {
    }
}

function stopAudio() {
    try {
        var oAudio = document.getElementById('myaudio');
        oAudio.pause();
    } catch (e) {
    }
}

function forceLeave() {
    document.getElementById('dupexit').click();
}
var myVar;

function intervalFotNoti() {
    myVar = setTimeout(function () {
        hideDialog();
    }, 30000);
}

function hideDialog() {
    stopIntervalForNoti();
    document.getElementById('callabondaned1').click();
    stopAudio();
    closeAllDialog();
    PF('misscust').show();
    PF('notDialog').hide();
    PF('calljoin').hide();
}

function stopIntervalForNoti() {
    clearTimeout(myVar);
}
var myVarNotiSch;

function intervalFotNotiSche() {
    myVarNotiSch = setTimeout(function () {
        stopIntervalForNotiSch();
    }, 6000);
}



function stopIntervalForNotiSch() {
    clearTimeout(myVarNotiSch);
}
var myVarSchedule;

function intervalFotScheduleNoti() {
    myVarSchedule = setTimeout(function () {
        if (document.getElementById('incallcontainer').style.display === 'none') {
//        disconnect();
//        disconnectCall();
            hideScheduleDialog();
        }
    }, 60000);
}

function hideScheduleDialog() {
    stopIntervalForScheduleNoti();
    document.getElementById('callabondaned1').click();
    stopAudio();
    closeAllDialog();
    PF('misscust').show();
    PF('scheduleCallNotiWid').hide();
    PF('calljoin').hide();
}

function stopIntervalForScheduleNoti() {
    console.log("timer stopped....")
    clearTimeout(myVarSchedule);
}

function hideConfirm() {
    setTimeout(function () {
        PF('confirmscheduleDlg').hide();
    }, 3000);
}
function hideConfirm2() {
    setTimeout(function () {
        PF('confirmscheduleDlg2').hide();
    }, 3000);
}





var timevar;

function checkIntervalforForwardResponse() {
    var count = 0;
    timevar = setInterval(function () {
        document.getElementById('checkforward').click();
        count++;
        if (count === 8) {
            PF('callforwarding').hide();
            stopIntervalforForwardResponse();
            stopAudio();
            closeAllDialog();
            PF('forfailure').show();
        }
    }, 5000);
}

function stopIntervalforForwardResponse() {
    clearInterval(timevar);
}
var timevar1 = 0;

function checkReceiveForwardInterval() {
    if (timevar1 === 0) {
        timevar1 = setTimeout(setTimeoutForCheckReceiveForwardInterval, 40000);
    }
}

function setTimeoutForCheckReceiveForwardInterval() {
    PF('forwardedNotification').hide();
    stopAudio();
    stopReceiveForwardInterval();
    PF('forwardjoin').hide();
    document.getElementById('mocknotReadyForward').click();
}

function stopReceiveForwardInterval() {
    clearTimeout(timevar1);
    timevar1 = 0;
}
var timeMul = 0;

function checkReceiveMutiwayInterval() {
    if (timeMul === 0) {
        timeMul = setTimeout(function () {
            stopAudio();
            deskNotificationClose();
            PF('multiwayNotification').hide();
            PF('missMultiway').show();
            document.getElementById('mocknotReadyForward').click();
        }, 40000);
    }
}

function stopMultiwayReceiveInterval() {
    stopAudio();
    clearTimeout(timeMul);
    timeMul = 0;
}

function hitNotReady() {
    document.getElementById('mocknotReady').click();
}

function hitNotReadyForward() {
    document.getElementById('mocknotReadyForward').click();

}

function openDocInTab(fileName) {
    var hrefTest = contextUrl + '/FileDownLoadCustomServlet?fileName=' + fileName;
    console.log("href tets val is :" + hrefTest);
    if (fileName !== "") {
        var popUp = window.open(hrefTest, '_blank', 'width=1,height=1,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=10,top=10,visible=none');
        if (popUp === null || typeof (popUp) === 'undefined') {

            alert('Please disable your pop-up blocker and click the "Download File" link again.');
        } else {
            setTimeout(function () {
                popUp.close();
            }, 3000);
            popUp.focus();
        }
    }
}

function leavecall() {
    setTimeout("leave();", 2000);
}

function leave() {
    PF('anotherLoggedIn').hide();
    refreshEssentials();

}

function disconnectCall() {
    leaveConference();

}

function customerDisconnected() {
    closeAllDialog();
    document.getElementById('incallcontainer').style.display = 'none';
    PF('customerhangup').show();
}

function customerMissed() {
    closeAllDialog();
    PF('customermissed').show();
}
var popUp;

function openChat(link, linkName) {
    popUp = window.open(link, linkName, 'width=600,height=570,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=800,top=100');
    console.log('popUp=' + popUp);
    if (popUp === null || typeof (popUp) === 'undefined') {
        alert('Please disable your pop-up blocker and click the "Previous Chat History" button again.');
    } else {
        popUp.focus();
    }
}
var timevar22;

function startDialInterval() {
    var count = 0;
    timevar22 = setInterval(function () {
        document.getElementById('checkReceive').click();
        count++;
        if (count === 8) {
            stopAudio();
//            cust.hide();
            stopDialTimer();
            closeAllDialog();
            PF('dialmiss').show();
        }
    }, 5000);
}

function stopDialTimer() {
    clearInterval(timevar22);
}

function sendRoomUrlFn() {
    call();
    vidyoConnectJoin();

}

function showRecording() {
    document.getElementById('recording').style.display = 'block';

}

function hideRecording() {
    document.getElementById('recording').style.display = 'none';

}

function setScrollToEndForEveryone() {
    var elms = document.getElementById('Everyone').getElementsByTagName("div");
    if (elms.length > 0) {
        var chatelement = elms[elms.length - 1];
        chatelement.scrollIntoView();
    }
}
var chatWindowState = 0;
$(document).ready(function () {
    $('.nav-toggle').click(function () {
        var collapse_content_selector = $(this).attr('href');
        var toggle_switch = $(this);
        $(collapse_content_selector).toggle(function () {
            if ($(this).css('display') === 'none') {
                toggle_switch.html('<img src="' + contextUrl + '/resources/images/top.png" height="20px" width="28px" style="margin-top:-2px;margin-left:1px;"></img>');
                $('#vidyoConnector').css("height", "52vh");
                $('#parentRenderer').css("height", "52vh");
                $(".frame video > video").css("height", "52vh");
                chatWindowState = 0;
            } else {
                chatWindowState = 1;

                $('#parentRenderer').css("height", "26vh");
                $('#vidyoConnector').css("height", "26vh");
                $(".frame video > video").css("height", "26vh");
                toggle_switch.html('<img src="' + contextUrl + '/resources/images/down.png"  height="20px" width="28px" style="margin-top:-2px;margin-left:1px;"></img>');
                if (index === 0) {
                    document.getElementById('_Everyone').click();
                    var elms = document.getElementById('Everyone').getElementsByTagName("div");
                    if (elms.length > 0) {
                        var chatelement = elms[elms.length - 1];
                        chatelement.scrollIntoView();
                    }
                } else {
                    document.getElementById('_participant' + index).click();
                    var elms = document.getElementById('participant' + index).getElementsByTagName("div");
                    if (elms.length > 0) {
                        var chatelement = elms[elms.length - 1];
                        chatelement.scrollIntoView();
                    }
                }
            }
        });
    });
});

function chatSave() {
    document.getElementById('inCallChatTextArea').value = document.getElementById('addstatictab').innerHTML;
    document.getElementById('inCallChatTabsArea').value = document.getElementById('participant').innerHTML;
    document.getElementById('chatsave').click();
}

function imageUpload() {
    document.getElementById('uploadpanel').style.display = 'none';
    document.getElementById('filePanel').style.visibility = 'visible';
    document.getElementById('upimage').style.display = 'block';
}

function docUpload() {
    document.getElementById('uploadpanel').style.display = 'none';
    document.getElementById('filePanel').style.visibility = 'visible';
    document.getElementById('updoc').style.display = 'block';
}

function hideUpload() {
    document.getElementById('uploadpanel').style.display = 'block';
    document.getElementById('filePanel').style.visibility = 'hidden';
    document.getElementById('updoc').style.display = 'none';
    document.getElementById('upimage').style.display = 'none';
}

function replaceVideoStrm() {
    if (document.getElementById('holdmsg')) {
        document.getElementById('holdmsg').style.visibility = "visible";
    }


    document.getElementById('incallcontainer').style.display = "none";
    showSpinner();
}

function restoreVideoConfrnce() {
    if (document.getElementById('holdmsg')) {
        document.getElementById('holdmsg').style.visibility = "hidden";
    }


    document.getElementById('incallcontainer').style.display = "";
    hideSpinner();
}

function showSpinner() {
    $('#spinnerReplace').show();
    document.getElementById("spinnerReplace").style.display = 'block';
    document.getElementById("spinnerReplacerVideo").play();
}

function hideSpinner() {
    $('#spinner').hide();
    document.getElementById("spinnerReplacerVideo").pause();
    $('#spinnerReplace').hide();
    document.getElementById("spinnerReplace").style.display = 'none';
}
window.addEventListener('unload', function () {
    refreshEssentials();
    if (popUp && !popUp.closed) {
        popUp.close();
    }
    var xhr = new XMLHttpRequest();
    xhr.open('GET', contextUrl + '/LogoutServlet', true);
    xhr.send(null);
}, !1);

function retryCall() {
    checkHold();
    joinViaBrowser();
}
function checkHold() {
    if (document.getElementById('spinnerReplace').style.display === 'block') {
        document.getElementById('holdBtn').click();
    }
}
var totalSeconds = 0;
function showCustomerInformations() {
    document.getElementById('infomessage').click();
    setTimeout(function () {
        document.getElementById('infomessage').click();
    }, 8000);
}
var timerVarCount;

function startCounterTimer() {
    totalSeconds = 0;
    timerVarCount = setInterval(countTimer, 1000);

}
function stopCounterTimer() {

    totalSeconds = 0;
    clearInterval(timerVarCount);
}


function countTimer() {

    ++totalSeconds;
    var hour = Math.floor(totalSeconds / 3600);
    if (hour < 10) {
        hour = "0" + hour;
    }

    var minute = Math.floor((totalSeconds - hour * 3600) / 60);
    if (minute < 10) {
        minute = "0" + minute;
    }

    var seconds = totalSeconds - (hour * 3600 + minute * 60);
    if (seconds < 10) {
        seconds = "0" + seconds;
    }

    document.getElementById("timer").innerHTML = "Call Time  " + hour + ":" + minute + ":" + seconds;
}

function hideVideoStream() {
    if (document.getElementById('incallcontainer').style.display != 'none') {

        if (bowser.name === "Internet Explorer") {
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '1px';
                    document.getElementById(globalPluginId).style.height = '1px';
                }
            }
            document.getElementById('renderer').style.backgroundColor = 'white';
            document.getElementById('parentRenderer').style.backgroundColor = 'white';
        }
        if (bowser.name === "Chrome") {
            if ((parseInt(bowser.version) < 30)) {
                if (globalPluginId !== null) {
                    if (typeof globalPluginId !== 'undefined') {
                        document.getElementById(globalPluginId).style.width = '1px';
                        document.getElementById(globalPluginId).style.height = '1px';
                    }
                }
                document.getElementById('renderer').style.backgroundColor = 'white';
                document.getElementById('parentRenderer').style.backgroundColor = 'white';
            }
        }
        if (bowser.name === "Firefox") {
            if (parseInt(bowser.version) < 36) {
                if (globalPluginId !== null) {
                    if (typeof globalPluginId !== 'undefined') {
                        document.getElementById(globalPluginId).style.width = '1px';
                        document.getElementById(globalPluginId).style.height = '1px';
                    }
                }
                document.getElementById('renderer').style.backgroundColor = 'white';
                document.getElementById('parentRenderer').style.backgroundColor = 'white';
            }
        }
        if (bowser.name === "Safari") {
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '1px';
                    document.getElementById(globalPluginId).style.height = '1px';
                }
            }
            document.getElementById('renderer').style.backgroundColor = 'white';
            document.getElementById('parentRenderer').style.backgroundColor = 'white';
        }
        if (bowser.name === "Opera") {
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '1px';
                    document.getElementById(globalPluginId).style.height = '1px';
                }
            }
            document.getElementById('renderer').style.backgroundColor = 'white';
            document.getElementById('parentRenderer').style.backgroundColor = 'white';
        }
    }
}

function showVideoStream() {
    if (document.getElementById('incallcontainer').style.display != 'none') {
        if (bowser.name === "Internet Explorer") {
            document.getElementById('parentRenderer').style.backgroundColor = 'black';
            document.getElementById('renderer').style.backgroundColor = 'black';
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '100%';
                    document.getElementById(globalPluginId).style.height = '100%';
                }
            }
        }
        if (bowser.name === "Chrome") {
            if ((parseInt(bowser.version) < 30)) {
                document.getElementById('parentRenderer').style.backgroundColor = 'black';
                document.getElementById('renderer').style.backgroundColor = 'black';
                if (globalPluginId !== null) {
                    if (typeof globalPluginId !== 'undefined') {
                        document.getElementById(globalPluginId).style.width = '100%';
                        document.getElementById(globalPluginId).style.height = '100%';
                    }
                }
            }
        }
        if (bowser.name === "Firefox") {
            if (parseInt(bowser.version) < 36) {
                document.getElementById('parentRenderer').style.backgroundColor = 'black';
                document.getElementById('renderer').style.backgroundColor = 'black';
                if (globalPluginId !== null) {
                    if (typeof globalPluginId !== 'undefined') {
                        document.getElementById(globalPluginId).style.width = '100%';
                        document.getElementById(globalPluginId).style.height = '100%';
                    }
                }
            }
        }
        if (bowser.name === "Safari") {
            document.getElementById('parentRenderer').style.backgroundColor = 'black';
            document.getElementById('renderer').style.backgroundColor = 'black';
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '100%';
                    document.getElementById(globalPluginId).style.height = '100%';
                }
            }
        }
        if (bowser.name === "Opera") {
            document.getElementById('parentRenderer').style.backgroundColor = 'black';
            document.getElementById('renderer').style.backgroundColor = 'black';
            if (globalPluginId !== null) {
                if (typeof globalPluginId !== 'undefined') {
                    document.getElementById(globalPluginId).style.width = '100%';
                    document.getElementById(globalPluginId).style.height = '100%';
                }
            }
        }
    }
}

function removeObjectTag() {
    console.log(" removeObjectTag called .........");
    $("#renderer").removeClass("rendererWithOptions");
    document.getElementById('renderer').innerHTML = '';
    if (bowser.name === "Internet Explorer") {
        document.getElementById('renderer').innerHTML = '';
        $("#VidyoPlugIn_VidyoClientPlugIn object").hide();
        $("#VidyoPlugIn_VidyoClientPlugIn object").remove();
    }
    if (bowser.name === "Chrome") {
        if ((parseInt(bowser.version) < 30)) {
            document.getElementById('renderer').innerHTML = '';
            $("#VidyoPlugIn_VidyoClientPlugIn object").hide();
            $("#VidyoPlugIn_VidyoClientPlugIn object").remove();
        }
    }
    if (bowser.name === "Firefox") {
        if (parseInt(bowser.version) < 36) {
            document.getElementById('renderer').innerHTML = '';
            $("#VidyoPlugIn_VidyoClientPlugIn object").hide();
            $("#VidyoPlugIn_VidyoClientPlugIn object").remove();
        }
    }
    if (bowser.name === "Safari") {
        document.getElementById('renderer').innerHTML = '';
        $("#VidyoPlugIn_VidyoClientPlugIn object").hide();
        $("#VidyoPlugIn_VidyoClientPlugIn object").remove();
    }
    if (bowser.name === "Opera") {
        document.getElementById('renderer').innerHTML = '';
        $("#VidyoPlugIn_VidyoClientPlugIn object").hide();
        $("#VidyoPlugIn_VidyoClientPlugIn object").remove();
    }
}
function dynamicallyRemoveJs() {
    $('head script[src*="VidyoClient.js"]').remove();
}

function disableButton(buttonId) {
    console.log('disableButton buttonId ' + buttonId);
    $('#' + buttonId).prop('disabled', true);
}
function enableButton(buttonId) {
    console.log('enableButton buttonId ' + buttonId);
    $('#' + buttonId).prop('disabled', false);
}
function openDocumentsCustom(link, linkName) {
    var url = link, title = linkName, w = 650, h = 570;


    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var popUp = window.open(url, title, 'scrollbars=yes,toolbar=no,resizable=no,fullscreen=no,width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
    childWindow = popUp;
    console.log('popUp=' + popUp);
    if (popUp === null || typeof (popUp) === 'undefined') {
        alert('Please disable your pop-up blocker and click the "Open document" button again.');
    } else {
        popUp.focus();
    }
}
function childReload() {
    console.log('ChildWINDOW is===' + childWindow);
    if (childWindow === null || typeof (childWindow) === 'undefined') {
    } else {
        if (childWindow.closed) {
        } else {
            childWindow.location.reload();
        }
    }
}

function closeChildWindow() {
    if (childWindow === null || typeof (childWindow) === 'undefined') {
    } else {
        if (childWindow.closed) {

        } else {
            childWindow.close();
        }
    }
}





function disconnect() {
    document.getElementById('recordForm:record').innerHTML = "";
    document.getElementById("holdForm:mge").innerHTML = "";
}

function checkIntervalforSpecialistResponse() {
    var count = 0;
    timevars = setInterval(function () {
        document.getElementById('checkspecialist').click();
        count++;
        if (count === 10) {
            stopAudio();
            stopIntervalforSpecialistResponse();
            closeAllDialog();
            PF('forspecialist').show();
            setTimeout(function () {
                PF('forspecialist').hide();
            }, 3000);
        }

    }, 5000);
}

function stopIntervalforSpecialistResponse() {
    clearInterval(timevars);
}

function openPdfInTab() {

    var hrefTest = '${request.contextPath}/CustomerFormPdfDownload';

    var popUp = window.open(hrefTest, '_blank', 'width=1,height=1,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=10,top=10,visible=none');

    if (popUp === null || typeof (popUp) === 'undefined') {
        alert('Please disable your pop-up blocker and click the "Download File" link again.');
    } else {
        setTimeout(function () {
            popUp.close();
        }, 3000);
        popUp.focus();
    }
}

function openDocInTabForScan(fileName) {
    var hrefTest = '${request.contextPath}/FileScanCustomerServlet';
    if (fileName !== "") {
        var popUp = window.open(hrefTest, '_blank', 'width=1,height=1,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=10,top=10,visible=none');
        if (popUp === null || typeof (popUp) === 'undefined') {
            alert('Please disable your pop-up blocker and click the "Download File" link again.');
        } else {
            setTimeout(function () {
                popUp.close();
            }, 3000);
            popUp.focus();
        }
    }
}

var chatWindowState = 0;
$(document).ready(function () {

});

window.addEventListener('unload', function () {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', '${request.contextPath}/LogoutServlet', true);
    xhr.send(null);
}, false);

function refreshCustomerAccoutDetails() {

    document.getElementById('refreshCustomerAccountDtlBtn').click();
    document.getElementById('refreshExistingCustomerLoanAccountDtlBtn').click();

}



function redirectToLoginPage() {
    console.log('HORRIBLE Shut Down!:::::::::::');
    window.location.reload(true);
}



