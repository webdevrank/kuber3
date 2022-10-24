var video;
var mutevideoflag = 0;
var muteaudioflag = 0;
var mutemicflag = 0;

function vidyoConnectJoin() {
    mutevideoflag = 0;
    muteaudioflag = 0;
    mutemicflag = 0;
}

function muteVideo() {
    mutevideoflag = 1;
    if ($("#cameraButton").hasClass("cameraOn")) {
        $("#cameraButton").click();
    }
    document.getElementById('mutevideo').style.display = 'none';
    document.getElementById('unmutevideo').style.display = '';
}

function unmuteVideo() {
    mutevideoflag = 0;
    if ($("#cameraButton").hasClass("cameraOff")) {
        $("#cameraButton").click();
    }
    document.getElementById('mutevideo').style.display = '';
    document.getElementById('unmutevideo').style.display = 'none';
}

function muteMic() {
    mutemicflag = 1;
    if ($("#microphoneButton").hasClass("microphoneOn")) {
        $("#microphoneButton").click();
    }
    document.getElementById('mutemic').style.display = 'none';
    document.getElementById('unmutemic').style.display = '';
}

function unmuteMic() {
    mutemicflag = 0;
    if ($("#microphoneButton").hasClass("microphoneOff")) {
        $("#microphoneButton").click();
    }
    document.getElementById('mutemic').style.display = '';
    document.getElementById('unmutemic').style.display = 'none';
}

function muteSpeaker() {
    muteaudioflag = 1;
    if ($('#speakerButton').hasClass('speakerOn')) {
        $("#speakerButton").click();
    }
    document.getElementById('mutespeaker').style.display = 'none';
    document.getElementById('unmutespeaker').style.display = '';
}

function unmuteSpeaker() {
    muteaudioflag = 0;
    if ($('#speakerButton').hasClass('speakerOff')) {
        $("#speakerButton").click();
    }
    document.getElementById('mutespeaker').style.display = '';
    document.getElementById('unmutespeaker').style.display = 'none';
}

function muteAll() {
    if ($("#cameraButton").hasClass("cameraOn")) {
        $("#cameraButton").click();
    }
    if ($("#microphoneButton").hasClass("microphoneOn")) {
        $("#microphoneButton").click();
    }
    if ($('#speakerButton').hasClass('speakerOn')) {
        $("#speakerButton").click();
    }
    document.getElementById('mutemic').style.display = 'none';
    document.getElementById('unmutemic').style.display = '';
    document.getElementById('mutevideo').style.display = '';
    document.getElementById('unmutevideo').style.display = 'none';
    document.getElementById('mutespeaker').style.display = '';
    document.getElementById('unmutespeaker').style.display = 'none';
}

function displayList() {
    document.getElementById('participantDiv').style.display = '';
    document.getElementById('showParticipants').style.display = '';
    document.getElementById('hideParticipants').style.display = 'none';
    $('#videoHead').removeAttr('style');
    $('#renderer').css("width", "100%");
}

function hideList() {
    document.getElementById('participantDiv').style.display = 'none';
    document.getElementById('showParticipants').style.display = 'none';
    document.getElementById('hideParticipants').style.display = '';
    var vidyoConnector = document.getElementById('vidyoConnector');
    $('#videoHead').css("width", "150%");
    $('#renderer').css("width", "150%");
}



function onLeftSelf() {
    console.log("onLeftSelf------------------");
    document.getElementById('onSelfLeftVidyoConnectBtn').click();
}

function setUserLeftFalse() {
    leaveConfCheck = !0;
}

function setLeaveConfStatus() {
    leaveConfCheck = !0;
    leaveConfMultiway = !0;
}

document.cancelFullScreen = document.webkitExitFullscreen || document.mozCancelFullScreen || document.exitFullscreen;
var elem = document.querySelector(document.webkitExitFullscreen ? "#myScreen" : "#myScreen");

document.addEventListener('keydown', function (e) {
    switch (e.keyCode) {
        case 13: // ENTER. ESC should also take you out of fullscreen by default.
            e.preventDefault();
            document.cancelFullScreen(); // explicitly go out of fs.
            break;

    }
}, false);

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
function onFullScreenExit() {
    console.log("Exited fullscreen!");
}

function makeFullScreen() {
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