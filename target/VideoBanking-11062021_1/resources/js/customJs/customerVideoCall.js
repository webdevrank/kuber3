function onPluginLoaded() {
}
var hold = 0;

function replaceVideoStrm() {
    hold = 1;
    document.getElementById('holdmsg').style.visibility = "visible";
    document.getElementById('incallcontainer').style.display = "none";
    showSpinner();
}

function restoreVideoConfrnce() {
    hold = 0;
    document.getElementById('holdmsg').style.visibility = "hidden";
    document.getElementById('incallcontainer').style.display = "";
    hideSpinner();
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

var timevarr;
var myVar;
function joinConferenceOperationchat() {
    $('#downloadFileId').hide();
    document.getElementById('mutemic').style.display = '';
    document.getElementById('unmutemic').style.display = 'none';
    document.getElementById('mutespeaker').style.display = '';
    document.getElementById('unmutespeaker').style.display = 'none';
    document.getElementById('mutevideo').style.display = '';
    document.getElementById('unmutevideo').style.display = 'none';
    document.getElementById('incallcontainer').style.display = 'block';
    document.getElementById('splash').style.display = 'none';
    callJoinConferenceOperationchat();
}


function joinConferenceOperation() {
    $('#downloadFileId').hide();
    document.getElementById('mutemic').style.display = '';
    document.getElementById('unmutemic').style.display = 'none';
    document.getElementById('mutespeaker').style.display = '';
    document.getElementById('unmutespeaker').style.display = 'none';
    document.getElementById('mutevideo').style.display = '';
    document.getElementById('unmutevideo').style.display = 'none';
    document.getElementById('incallcontainer').style.display = 'block';
    callJoinConferenceOperation();
}

function joinConferenceOperationAudio() {
    $('#downloadFileId').hide();
    document.getElementById('mutemic').style.display = '';
    document.getElementById('unmutemic').style.display = 'none';
    document.getElementById('mutespeaker').style.display = '';
    document.getElementById('unmutespeaker').style.display = 'none';
    document.getElementById('mutevideo').style.display = '';
    document.getElementById('unmutevideo').style.display = 'none';
    document.getElementById('incallcontainer').style.display = 'block';
    callJoinConferenceOperationAudio();
}

function callJoinConferenceOperation() {
    startCounterTimer();
    PF('exitSysVar').hide();
    document.getElementById('holdmsg').style.visibility = "hidden";
    if (document.getElementById("backBtn")) {
        document.getElementById("backBtn").disabled = !1;
        document.getElementById("backBtn").className = 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only';
        document.getElementById("backBtn").setAttribute('aria-disabled', !1);
    }
    document.getElementById('vidyoConnector').style.display = 'block';
    document.getElementById('participantDiv').style.display = 'block';
    document.getElementById('videoHead').style.display = 'block';
    document.getElementById('videoHeadchat').style.display = 'none';
    document.getElementById('participant').style.height = '17vh';
    document.getElementById('tbutton').style.display = 'inline';
    document.getElementById('collapse1').style.marginTop = '-4px';
    joinViaBrowser();
    setTimeout(function () {
        PF('exitSysVar').hide();
    }, 5000);
}

function myTimer() {
    if (typeof (vidyoConnector) != "undefined") {
        myStopFunction();
        setTimeout(function () {
            muteVideo();
        }, 1000);
    }
}

function myStopFunction() {
    clearInterval(myVar);
}

function callJoinConferenceOperationAudio() {
    startCounterTimer();
    PF('exitSysVar').hide();
    document.getElementById('holdmsg').style.visibility = "hidden";
    if (document.getElementById("backBtn")) {
        document.getElementById("backBtn").disabled = !1;
        document.getElementById("backBtn").className = 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only';
        document.getElementById("backBtn").setAttribute('aria-disabled', !1);
    }
    document.getElementById('vidyoConnector').style.display = 'block';
    document.getElementById('participantDiv').style.display = 'block';
    document.getElementById('videoHead').style.display = 'block';
    document.getElementById('videoHeadchat').style.display = 'none';
    document.getElementById('participant').style.height = '17vh';
    document.getElementById('tbutton').style.display = 'inline';
    document.getElementById('collapse1').style.marginTop = '-4px';
    joinViaBrowser();
    myVar = setInterval(myTimer, 1000);
//    setTimeout(function(){ muteVideo(); }, 2000);
    setTimeout(function () {
        PF('exitSysVar').hide();
    }, 5000);
}

function callJoinConferenceOperationchat() {
    startCounterTimer();
    PF('exitSysVar').hide();
    document.getElementById('holdmsg').style.visibility = "hidden";
    if (document.getElementById("backBtn")) {
        document.getElementById("backBtn").disabled = !1;
        document.getElementById("backBtn").className = 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only';
        document.getElementById("backBtn").setAttribute('aria-disabled', !1);
    }
    document.getElementById('vidyoConnector').style.display = 'none';
    document.getElementById('participantDiv').style.display = 'none';
    document.getElementById('videoHead').style.display = 'none';
    document.getElementById('videoHeadchat').style.display = 'block';
    document.getElementById('participant').style.height = '50vh';
    document.getElementById('tbutton').style.display = 'none';
    document.getElementById('collapse1').style.marginTop = '0px';
    $(".nav-toggle").trigger("click");

    setTimeout(function () {
        PF('exitSysVar').hide();
    }, 5000);
}

function endConferenceOperation() {
    console.log('endConferenceOperation........');
    document.getElementById('holdmsg').style.visibility = "hidden";
    if (document.getElementById("backBtn")) {
        document.getElementById("backBtn").disabled = !0;
        document.getElementById("backBtn").setAttribute('aria-disabled', !0);
        document.getElementById("backBtn").className = 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only ui-state-disabled';
    }
}

function disconnectCall() {
    leaveConference()
}

function imageUpload() {
    document.getElementById('uploadpanel').style.display = 'none';
    document.getElementById('filePanel').style.visibility = 'visible';
    document.getElementById('upimage').style.display = 'block'
}

function docUpload() {
    document.getElementById('uploadpanel').style.display = 'none';
    document.getElementById('filePanel').style.visibility = 'visible';
    document.getElementById('updoc').style.display = 'block'
}

function showSpinner() {
    $('#spinnerReplace').show();
    document.getElementById("spinnerReplace").style.display = 'block';
    document.getElementById("spinnerReplacerVideo").play();
}
function showSpineerCallForScheduleCall() {
    closeAllDialog();
    showSpinner();
    document.getElementById("scheduleCallByCustomerId").click();
}
function showLoaderBeforeRefresh() {
    $('#spinner').show();
    document.getElementById("spinnerReplace").style.display = 'none';
}

function hideLoaderBeforeRefresh() {
    $('#spinner').hide();
    document.getElementById("spinnerReplace").style.display = 'none';
}

function showPromotionalVideWithoutSpinner() {
    $('#spinner').hide()
}

function showPromotionalVideContinuedWithSpinner() {
    $('#spinner').show()
}

function hideSpinner() {
    $('#spinner').hide();
    document.getElementById("spinnerReplacerVideo").pause();
    $('#spinnerReplace').hide();
    document.getElementById("spinnerReplace").style.display = 'none'
}

function checkDialog() {
    if (document.getElementById("spinnerReplace").style.display == 'block') {
        PF('callNotInitialisedDialog').show()
    }
}

function checkDialog1() {
    if (document.getElementById("spinnerReplace").style.display == 'block') {
        PF('agentnotAvailableDlg').show()
    }
}

function hideConfirm() {
    setTimeout(function () {
        PF('confirmscheduleDlg').hide()
    }, 3000)
}
function hideConfirm2() {
    setTimeout(function () {
        PF('confirmscheduleDlg2').hide()
    }, 3000)
}
function refreshFrame() {
    var ifr = document.getElementsByName('someName')[0];
    ifr.src = ifr.src
}


var tvar;
var tvar1;
var clearAgentPick;
var agentTimeCount = 0;
function startReceiveCheck() {
    if (tvar) {
        clearTimeout(tvar);
        tvar = null;
    }
    tvar = setTimeout(function () {
        document.getElementById("endBtn").click();
        closeAllDialog();
        console.log("agentNotReceived...");
        PF('agentNotReceived').show();
        setTimeout(function () {
            PF('agentNotReceived').hide();
            location.href = contextUrl + '/customerHome';
        }, 5000);

    }, 40000);
}
function clearReceiveCheck() {
    console.log("----------------clearReceiveCheck-----------agentReceived..." + tvar);
    if (tvar) {
        clearTimeout(tvar);
        tvar = null;
        clearTimeout(clearAgentPick);
        clearAgentPick = null;
        agentTimeCount = 0;
        console.log("CLEAR TIME OUT DONE!!!!!!!!!!!!!!");
    } else {
        if (agentTimeCount < 5) {
            clearAgentPick = setTimeout(function () {
                clearReceiveCheck();
            }, 5000);
        } else {
            clearTimeout(clearAgentPick);
            clearAgentPick = null;
            agentTimeCount = 0;
        }
    }
    agentTimeCount = parseInt(agentTimeCount) + 1;
}


function startReceiveCheckRegistered() {
    console.log("startReceiveCheckRegistered............");
    if (tvar1) {
        clearTimeout(tvar1);
        tvar1 = null;
    }
    tvar1 = setTimeout(function () {
        document.getElementById("endBtn").click();
        closeAllDialog();
        console.log("agentNotReceived...");
        PF('agentNotReceived').show();
        setTimeout(function () {
            PF('agentNotReceived').hide();

        }, 5000);

    }, 40000);
}

function clearReceiveCheckRegistered() {
    console.log("---------------------------agentReceived..." + tvar1);
    if (tvar1) {
        clearTimeout(tvar1);
        tvar1 = null;
        clearTimeout(clearAgentPick);
        clearAgentPick = null;
        agentTimeCount = 0;
        console.log("CLEAR TIME OUT DONE!!!!!!!!!!!!!!");
    } else {
        if (agentTimeCount < 5) {
            clearAgentPick = setTimeout(function () {
                clearReceiveCheckRegistered();
            }, 5000);
        } else {
            clearTimeout(clearAgentPick);
            clearAgentPick = null;
            agentTimeCount = 0;
        }
    }
    agentTimeCount = parseInt(agentTimeCount) + 1;
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
    window.name = 'parent';
    $('.nav-toggle').click(function () {
        var collapse_content_selector = $(this).attr('href');
        var toggle_switch = $(this);
        $(collapse_content_selector).toggle(function () {
            if ($(this).css('display') === 'none') {
                toggle_switch.html('<img src="' + contextUrl + '/resources/images/top.png" height="20px" width="28px" style="margin-top:-2px;margin-left:1px;"></img>');
                $('#vidyoConnector').css("height", "56vh");
                $('#parentRenderer').css("height", "56vh");
                $(".frame video > video").css("height", "56vh");

                chatWindowState = 0
            } else {
                chatWindowState = 1;
                $('#vidyoConnector').css("height", "26vh");
                $('#parentRenderer').css("height", "26vh");
                $(".frame video > video").css("height", "26vh");
                toggle_switch.html('<img src="' + contextUrl + '/resources/images/down.png"  height="20px" width="28px" style="margin-top:-2px;margin-left:1px;"></img>');
                if (index === 0) {
                    document.getElementById('_Everyone').click();
                    var elms = document.getElementById('Everyone').getElementsByTagName("div");
                    if (elms.length > 0) {
                        var chatelement = elms[elms.length - 1];
                        chatelement.scrollIntoView()
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

function refreshFrame1() {
    var ifr = document.getElementsByName('someName1')[0];
    ifr.src = ifr.src;
}

function checkDialog2() {
    $('#agentFoundClickOk').click();

}

function openDocInTab(fileName) {
    var hrefTest = contextUrl + '/FileDownLoadCustomServlet?fileName=' + fileName;
    if (fileName !== "") {
        var popUp = window.open(hrefTest, '_blank', 'width=1,height=1,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=10,top=10,visible=none');
        if (popUp === null || typeof (popUp) === 'undefined') {

            alert('Please disable your pop-up blocker and click the "Download File" link again.')
        } else {
            setTimeout(function () {
                popUp.close()
            }, 3000);
            popUp.focus()
        }
    }
}
var timerVarS;
var totalSeconds = 0;
function startCounterTimer() {
    totalSeconds = 0;
    timerVarS = setInterval(countTimer, 1000);
}

function stopCounterTimer() {
    totalSeconds = 0;
    clearInterval(timerVarS);
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
document.cancelFullScreen = document.webkitExitFullscreen || document.mozCancelFullScreen || document.exitFullscreen;
var elem = document.querySelector(document.webkitExitFullscreen ? "#myScreen" : "#myScreen");
document.addEventListener('keydown', function (e) {
    switch (e.keyCode) {
        case 13:
            e.preventDefault();
            document.cancelFullScreen();
            break
    }
}, !1);

function toggleFS(el) {
    if (el.webkitEnterFullScreen) {
        el.webkitEnterFullScreen();
    } else {
        if (el.mozRequestFullScreen) {
            el.mozRequestFullScreen();
        } else {
            el.requestFullscreen();
        }
    }
    el.ondblclick = exitFullscreen;
}

function onFullScreenEnter() {
    console.log("Entered fullscreen!");
    elem.onwebkitfullscreenchange = onFullScreenExit;
    elem.onmozfullscreenchange = onFullScreenExit;
}
;

function onFullScreenExit() {
    console.log("Exited fullscreen!");
}
;

function makeFullScreen() {

    elem = document.getElementById('renderer');

    console.log("enterFullscreen()");
    elem.onwebkitfullscreenchange = onFullScreenEnter;
    elem.onmozfullscreenchange = onFullScreenEnter;
    elem.onfullscreenchange = onFullScreenEnter;
    if (elem.webkitRequestFullscreen) {
        elem.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
    } else {
        if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }

    }
}


function exitFullscreen() {
    console.log("exitFullscreen()");
    if (document.exitFullscreen) {
        document.exitFullscreen();
    } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
    } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
    }


}
document.cancelFullScreen = document.webkitExitFullscreen || document.mozCancelFullScreen || document.exitFullscreen;
var elem = document.querySelector(document.webkitExitFullscreen ? "#myScreen" : "#myScreen");
document.addEventListener('keydown', function (e) {
    switch (e.keyCode) {
        case 13:
            e.preventDefault();
            document.cancelFullScreen();
            break
    }
}, !1);

function toggleFS(el) {
    if (el.webkitEnterFullScreen) {
        el.webkitEnterFullScreen();
    } else {
        if (el.mozRequestFullScreen) {
            el.mozRequestFullScreen();
        } else {
            el.requestFullscreen();
        }
    }
    el.ondblclick = exitFullscreen;
}

function onFullScreenEnter() {
    console.log("Entered fullscreen!");
    elem.onwebkitfullscreenchange = onFullScreenExit;
    elem.onmozfullscreenchange = onFullScreenExit;
}
;

function onFullScreenExit() {
    console.log("Exited fullscreen!");
}
;

function requestFullScreen() {
    elem = document.getElementById('parentRenderer');
    console.log("enterFullscreen()");
    elem.onwebkitfullscreenchange = onFullScreenEnter;
    elem.onmozfullscreenchange = onFullScreenEnter;
    elem.onfullscreenchange = onFullScreenEnter;
    if (elem.webkitRequestFullscreen) {
        elem.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
    } else {
        if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else {
            elem.requestFullscreen();
        }
    }
}

function exitFullscreen() {
    console.log("exitFullscreen()");
    document.cancelFullScreen();
}
$(document).ready(function () {
    document.getElementById('msgToSend').onkeydown = function (e) {
        if (e.keyCode === 13) {
            document.getElementById('send').click();
            document.getElementById('msgToSend').value = '';
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
$(document).ready(function () {
    if (document.getElementById('downloadFileId')) {
        document.getElementById('downloadFileId').style.visibility = "hidden";
    }
})


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

function setIcons() {
    var starList = document.getElementsByClassName('ui-rating-star');
    for (i = 0; i < starList.length; i++) {
        var sublist = starList[i].childNodes;
        if (i === 4 || i === 9 || i === 14) {
            var urlRating5 = 'url("' + contextUrl + '/resources/images/rating_5.png")';
            sublist[0].style.backgroundImage = urlRating5;
            if (i === 4) {
                sublist[0].id = 'r1c5';
            }
            if (i === 9) {
                sublist[0].id = 'r2c5';
            }
            if (i === 14) {
                sublist[0].id = 'r3c5';
            }
        }
        if (i === 3 || i === 8 || i === 13) {
            var urlRating4 = 'url("' + contextUrl + '/resources/images/rating_4.png")';
            sublist[0].style.backgroundImage = urlRating4;
            if (i === 3) {
                sublist[0].id = 'r1c4';
            }
            if (i === 8) {
                sublist[0].id = 'r2c4';
            }
            if (i === 13) {
                sublist[0].id = 'r3c4';
            }
        }
        if (i === 2 || i === 7 || i === 12) {
            var urlRating3 = 'url("' + contextUrl + '/resources/images/rating_3.png")';
            sublist[0].style.backgroundImage = urlRating3;
            if (i === 2) {
                sublist[0].id = 'r1c3';
            }
            if (i === 7) {
                sublist[0].id = 'r2c3';
            }
            if (i === 12) {
                sublist[0].id = 'r3c3';
            }

        }
        if (i === 1 || i === 6 || i === 11) {
            var urlRating2 = 'url("' + contextUrl + '/resources/images/rating_2.png")';
            sublist[0].style.backgroundImage = urlRating2;
            if (i === 1) {
                sublist[0].id = 'r1c2';
            }
            if (i === 6) {
                sublist[0].id = 'r2c2';
            }
            if (i === 11) {
                sublist[0].id = 'r3c2';
            }

        }
        if (i === 0 || i === 5 || i === 10) {

            var urlRating1 = 'url("' + contextUrl + '/resources/images/rating_1.png")';
            sublist[0].style.backgroundImage = urlRating1;
            if (i === 0) {
                sublist[0].id = 'r1c1';
            }
            if (i === 5) {
                sublist[0].id = 'r2c1';
            }
            if (i === 10) {
                sublist[0].id = 'r3c1';
            }

        }
    }

    $('#r1c5').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r1c4').css('background-position', '0 -145px');
        $('#r1c3').css('background-position', '0 -145px');
        $('#r1c2').css('background-position', '0 -145px');
        $('#r1c1').css('background-position', '0 -145px');
    });
    $('#r1c4').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r1c5').css('background-position', '0 -145px');
        $('#r1c3').css('background-position', '0 -145px');
        $('#r1c2').css('background-position', '0 -145px');
        $('#r1c1').css('background-position', '0 -145px');
    });
    $('#r1c3').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r1c5').css('background-position', '0 -145px');
        $('#r1c4').css('background-position', '0 -145px');
        $('#r1c2').css('background-position', '0 -145px');
        $('#r1c1').css('background-position', '0 -145px');
    });
    $('#r1c2').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r1c5').css('background-position', '0 -145px');
        $('#r1c4').css('background-position', '0 -145px');
        $('#r1c3').css('background-position', '0 -145px');
        $('#r1c1').css('background-position', '0 -145px');
    });
    $('#r1c1').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r1c5').css('background-position', '0 -145px');
        $('#r1c4').css('background-position', '0 -145px');
        $('#r1c3').css('background-position', '0 -145px');
        $('#r1c2').css('background-position', '0 -145px');

    });


    $('#r2c5').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r2c4').css('background-position', '0 -145px');
        $('#r2c3').css('background-position', '0 -145px');
        $('#r2c2').css('background-position', '0 -145px');
        $('#r2c1').css('background-position', '0 -145px');
    });
    $('#r2c4').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r2c5').css('background-position', '0 -145px');
        $('#r2c3').css('background-position', '0 -145px');
        $('#r2c2').css('background-position', '0 -145px');
        $('#r2c1').css('background-position', '0 -145px');
    });
    $('#r2c3').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r2c5').css('background-position', '0 -145px');
        $('#r2c4').css('background-position', '0 -145px');
        $('#r2c2').css('background-position', '0 -145px');
        $('#r2c1').css('background-position', '0 -145px');
    });
    $('#r2c2').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r2c5').css('background-position', '0 -145px');
        $('#r2c4').css('background-position', '0 -145px');
        $('#r2c3').css('background-position', '0 -145px');
        $('#r2c1').css('background-position', '0 -145px');
    });
    $('#r2c1').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r2c5').css('background-position', '0 -145px');
        $('#r2c4').css('background-position', '0 -145px');
        $('#r2c3').css('background-position', '0 -145px');
        $('#r2c2').css('background-position', '0 -145px');

    });

    $('#r3c5').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r3c4').css('background-position', '0 -145px');
        $('#r3c3').css('background-position', '0 -145px');
        $('#r3c2').css('background-position', '0 -145px');
        $('#r3c1').css('background-position', '0 -145px');
    });
    $('#r3c4').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r3c5').css('background-position', '0 -145px');
        $('#r3c3').css('background-position', '0 -145px');
        $('#r3c2').css('background-position', '0 -145px');
        $('#r3c1').css('background-position', '0 -145px');
    });
    $('#r3c3').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r3c5').css('background-position', '0 -145px');
        $('#r3c4').css('background-position', '0 -145px');
        $('#r3c2').css('background-position', '0 -145px');
        $('#r3c1').css('background-position', '0 -145px');
    });
    $('#r3c2').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r3c5').css('background-position', '0 -145px');
        $('#r3c4').css('background-position', '0 -145px');
        $('#r3c3').css('background-position', '0 -145px');
        $('#r3c1').css('background-position', '0 -145px');
    });
    $('#r3c1').click(function () {
        $(this).css('background-position', '0 -295px');
        $('#r3c5').css('background-position', '0 -145px');
        $('#r3c4').css('background-position', '0 -145px');
        $('#r3c3').css('background-position', '0 -145px');
        $('#r3c2').css('background-position', '0 -145px');

    });

}

function dynamicallyRemoveJs() {
    $('head script[src*="VidyoClient.js"]').remove();
}

