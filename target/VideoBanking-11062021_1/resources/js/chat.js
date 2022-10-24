var socketChat = io.connect('' + socketHostPublic, {
    'reconnect': true
});

var senduser = '';
var usersonconference = new Array();
var usernameonconference = new Array();
var scheduleAcknowledgement = false;
var allChat = '';
//var prodName='kuber';


function isOneChecked() {
    senduser = '';
    var chx = document.getElementsByName('user');
    for (var i = 0; i < chx.length; i++) {
        if (chx[i].type === 'radio' && chx[i].checked) {
            senduser = chx[i].value;
            return true;
        }
    }

    return false;
}

function connected() {
    console.log('connected' + document.getElementById('agentId').value);
    socketChat.emit('new user', document.getElementById('agentId').value, function (data) {
    });
}

socketChat.on('usernames', function (usernames) {

    for (var i = 0; i < usernames.length; i++)
    {
        var userConnected = false;
        if (document.getElementById('agentId') !== 'undefined' && document.getElementById('agentId') !== null)
            if (usernames[i] === document.getElementById('agentId').value) {
                userConnected = true;
            }

    }
    if (!userConnected) {
        setTimeout(function () {
            if (document.getElementById('agentId') !== 'undefined' && document.getElementById('agentId') !== null)
            {
                socketChat.emit('new user', document.getElementById('agentId').value, function (data) {
                });
            }
        }, 5000);
    }
});

function sendMessage() {
    var msg = document.getElementById('msgToSend').value;
    if (msg.trim() !== '')
    {
        if (isOneChecked())
        {
            socketChat.emit('private-chat', {customer: senduser, message: document.getElementById('msgToSend').value});

            updateSendMessage(senduser);
            allChat = allChat + "#" + document.getElementById('msgToSend').value + "~" + getCurrentDateTime() + "~" + document.getElementById('agentId').value + ",";
            document.getElementById('inCallChatTextArea').value = document.getElementById('addstatictab').innerHTML;
            document.getElementById('inCallChatTabsArea').value = document.getElementById('participant').innerHTML;
            // document.getElementById('updateChatMsgEverytime').click();
        } else
        {
            socketChat.emit('group-chat', {message: document.getElementById('msgToSend').value, receivers: usersonconference});

            updateSendMessage(senduser);
            allChat = allChat + "#" + document.getElementById('msgToSend').value + "~" + getCurrentDateTime() + "~" + document.getElementById('agentId').value + ",";
            document.getElementById('inCallChatTextArea').value = document.getElementById('addstatictab').innerHTML;
            document.getElementById('inCallChatTabsArea').value = document.getElementById('participant').innerHTML;
            // document.getElementById('updateChatMsgEverytime').click();

        }
    }
}
function sendMessageBargeIn() {
    if (document.getElementById('msgToSend').value !== '')
    {
        if (isOneChecked())
        {
            socketChat.emit('private-chat', {customer: senduser, message: document.getElementById('msgToSend').value});
            updateSendMessage(senduser);
            allChat = allChat + "#" + document.getElementById('msgToSend').value + "~" + getCurrentDateTime() + "~" + document.getElementById('agentId').value + ",";

        } else
        {
            socketChat.emit('group-chat', {message: document.getElementById('msgToSend').value, receivers: usersonconference});
            updateSendMessage(senduser);
            allChat = allChat + "#" + document.getElementById('msgToSend').value + "~" + getCurrentDateTime() + "~" + document.getElementById('agentId').value + ",";


        }
    }
}

function sendAcknowledgement(sendTo) {
    console.log("sendAcknowledgement................" + sendTo);
    socketChat.emit('send message', {customer: sendTo, message: 'incoming_call#received'});
    setTimeout(function () {
        document.getElementById('checkReceiveIncoming').click();
    }, 9000);

}
function sendForwardSuccessAcknowledgement(sendTo, message) {
    socketChat.emit('send message', {customer: sendTo, message: 'forward_success#' + message});
}
function sendScheduleAcknowledgement(sendTo) {
    socketChat.emit('send message', {customer: sendTo, message: 'schedule_call#received'});

}
function sendFileReceiveRequest(sendTo, message) {
    socketChat.emit('send message', {customer: sendTo, message: message});
}
function sendScheduleAcknowledgementNew(sendTo, roomId, roomName, custName, callId) {
    socketChat.emit('send message', {customer: sendTo, message: 'schedule_call#received#' + roomId + '#' + roomName + '#' + custName + '#' + callId});
}

function sendHold(sendTo, holdMsg) {
    console.log('@@@@@@@@@@sendHold   sentTo' + sendTo + '  holdMsg ' + holdMsg);
    socketChat.emit('send message', {customer: sendTo, message: holdMsg});
}
function sendUnhold(sendTo, unholdMsg) {
    console.log('@@@@@@@@@@sendUnhold   sentTo' + sendTo + '  unholdMsg ' + unholdMsg);
    socketChat.emit('send message', {customer: sendTo, message: unholdMsg});
}
function sendIdCardRequest(sendTo) {
    console.log("sendIdCardRequest................" + sendTo);
    socketChat.emit('send message', {customer: sendTo, message: 'idcard#request'});


}


var scvar1;
function joinConferenceOperationSchedule() {
    if (scheduleAcknowledgement) {
        joinConferenceOperation();

    } else {
        closeAllDialog();
        PF('waiting').show();
        var count = 0;
        scvar1 = setInterval(function () {

            count++;
            if (scheduleAcknowledgement) {
                PF('waiting').hide();
                joinConferenceOperation();
                clearInterval(scvar1);
                scheduleAcknowledgement = false;
            }
            if (count === 6) {
                clearInterval(scvar1);
                PF('waiting').hide();
//                agentNotReceived.show();
            }

        }, 5000);
    }
}

socketChat.on('private', function (data) {
    var response = data.msg;
    var resp = response.split("#");

    if (resp[0] === "idcard") {
        var res = resp[1];
        if (res === "request") {
            PF('idcardDlg').show();
        }
    }
    if (resp[0] === "addressproof") {
        var res = resp[1];
        if (res === "request") {
            PF('addressproofDlg').show();
        }
    }

    if (resp[0] === "incoming_call") {
        var res = resp[1];
        if (res === "received") {
            clearReceiveCheck();
            clearReceiveCheckRegistered();
            checkDialog2();
        }
    }
    if (resp[0] === "forward_success") {
        var res = resp[1];
        var ss = res.split('~');
        usersonconference = arrayRemove(usersonconference, ss[0]);
        usernameonconference = arrayRemove(usernameonconference, ss[1]);
    }

    if (resp[0] === "fileSent")
    {
        console.log("res1 " + resp[1] + " res2 " + resp[2]);
        $('#filePathName').val(resp[1]);
        $('#fileName').val(resp[2]);
        $('#fileReceive').click();

    }
    if (resp[0] === "fileSentByCust")
    {
        console.log("res1 " + resp[1] + " res2 " + resp[2]);
        $('#filePathName').val(resp[1]);
        $('#fileName').val(resp[2]);
        $('#fileReceive').click();

    }

    if (resp[0] === "hold") {
        document.getElementById('holdBtn').click();

    }
    if (resp[0] === "unhold") {
        document.getElementById('unholdBtn').click();

    }
    if (resp[0] === "accountSaveNoti") {
        console.log("accountSave got at customer end");
        document.getElementById("accSinatureId").style.display = "block";
    }
    if (resp[0] === "loanAccountSaveNoti") {
        console.log("loanAccountSave got at customer end");
        document.getElementById("loanAccSinatureId").style.display = "block";
    }
    if (resp[0] === "accountOpen") {
        console.log("accountOpen got at customer end");
        var res = resp[1];
        if (res === "success") {
            document.getElementById("accopen").click();
        }
    }
    if (resp[0] === "loanApproved") {
        console.log("loanApproved got at customer end");
        var res = resp[1];
        if (res === "success") {
            document.getElementById("loanapp").click();
        }
    }
    if (resp[0] === "idCardSend") {
        console.log("idCardSend got at agent end");
        var res = resp[1];
        console.log("Idcard Name is==" + res);
        document.getElementById("checkReceiveIdCardValue").value = res;
        document.getElementById("checkReceiveIdCard").click();
    }
    if (resp[0] === "addProofSend") {
        console.log("addProofSend got at agent end");
        document.getElementById("checkReceiveAddProofValue").value = resp[1];
        document.getElementById("checkReceiveAddProof").click();
    }
    if (resp[0] === "selfImageSend") {
        console.log("selfImageSend got at agent end");
        document.getElementById("checkReceiveSelfImageValue").value = resp[1];
        document.getElementById("checkReceiveSelfImage").click();
    }
    if (resp[0] === "accountSaveNotiForAgent") {
        console.log("accountSaveNotiForAgent got at agent end");

        if (document.getElementById("downloadPDFId")) {
            document.getElementById("downloadPDFId").style.display = "none";
        }
    }

});

function closeAllDialog() {

    for (var propertyName in PrimeFaces.widgets) {

        if (PrimeFaces.widgets[propertyName] instanceof PrimeFaces.widget.Dialog ||
                PrimeFaces.widgets[propertyName] instanceof PrimeFaces.widget.LightBox) {

            PrimeFaces.widgets[propertyName].hide();

        }
    }
}

socketChat.on('private-chat', function (data) {

    var divId;
    var done = 0;
    var displayName = '';
    $("#addstatictabdiv a").each(function (i) {
        var tt = $(this).text();
        if (tt === data.nick) {
            if (done === 0)
            {
                for (var i = 0; i < usersonconference.length; i++) {
                    if (usersonconference[i] == data.nick) {
                        displayName = usernameonconference[i];
                        break;
                    }
                }
                allChat = allChat + "$" + data.msg + "~" + getCurrentDateTime() + "~" + displayName + ",";
                divId = ($(this).attr('id')).substring(1);
                document.getElementById('' + divId).innerHTML += '<div class="chatMessage" style="margin-left:50%;width:50%;"><span style="font-weight:bold">' + displayName + '</span><span style="color:blue;margin-left:5px;">[' + getCurrentDateTime() + ']</span><br/><div class="dialogbox"><div class="body"><span class="tip tip-up"></span> <div class="message">' + data.msg + '</div></div></div></div>';
                var elms = document.getElementById('' + divId).getElementsByTagName("div");
                var chatelement = elms[elms.length - 1];
                chatelement.scrollIntoView();
                done = 1;
            }

        } else {
            if (done === 0) {
                for (var i = 0; i < usersonconference.length; i++) {
                    if (usersonconference[i] == data.nick) {
                        displayName = usernameonconference[i];
                        break;
                    }
                }
                addNewStaticTabRec(data.nick);
                receiveMessage(data.nick, data.msg);
                allChat = allChat + "$" + data.msg + "~" + getCurrentDateTime() + "~" + displayName + ",";
                done = 1;
            }

        }
    });

});

function receiveMessage(user, message) {
    var divId;

    var displayName = '';
    $("#addstatictabdiv a").each(function (i) {
        var tt = $(this).text();
        if (tt !== 'Everyone') {
            tt = tt.substring(0, user.length);
        }

        if (tt === user) {
            for (var i = 0; i < usersonconference.length; i++) {
                if (usersonconference[i] == user) {
                    displayName = usernameonconference[i];
                    break;
                }
            }
            divId = ($(this).attr('id')).substring(1);
            document.getElementById('' + divId).innerHTML += '<div class="chatMessage" style="margin-left:50%;width:50%;"><span style="font-weight:bold">' + displayName + '</span><span style="color:blue;margin-left:5px;">[' + getCurrentDateTime() + ']</span><br/><div class="dialogbox"><div class="body"><span class="tip tip-up"></span> <div class="message">' + message + '</div></div></div></div>';
            var elms = document.getElementById('' + divId).getElementsByTagName("div");
            var chatelement = elms[elms.length - 1];
            chatelement.scrollIntoView();
        }

    });
    document.getElementById('inCallChatTextArea').value = document.getElementById('addstatictab').innerHTML;
    document.getElementById('inCallChatTabsArea').value = document.getElementById('participant').innerHTML;
    // document.getElementById('updateChatMsgEverytime').click();
}

socketChat.on('group-chat', function (data) {

    var displayName = '';
    for (var i = 0; i < usersonconference.length; i++) {
        if (usersonconference[i] == data.nick) {
            displayName = usernameonconference[i];
            break;
        }
    }
    document.getElementById('Everyone').innerHTML += '<div class="chatMessage" style="margin-left:50%;width:50%;"><span style="font-weight:bold">' + displayName + '</span><span style="color:blue;margin-left:5px;">[' + getCurrentDateTime() + ']</span><br/><div class="dialogbox"><div class="body"><span class="tip tip-up"></span> <div class="message">' + data.msg + '</div></div></div></div>';
    allChat = allChat + displayName + "[" + getCurrentDateTime() + "]\n" + data.msg + "\n";
    var elms = document.getElementById('Everyone').getElementsByTagName("div");
    var chatelement = elms[elms.length - 1];
    chatelement.scrollIntoView();
    updateEveryone();
    document.getElementById('inCallChatTextArea').value = document.getElementById('addstatictab').innerHTML;
    document.getElementById('inCallChatTabsArea').value = document.getElementById('participant').innerHTML;
    //document.getElementById('updateChatMsgEverytime').click();
});





socketChat.on('reconnecting', function () {
    console.log("reconnecting");
});

socketChat.on('reconnect', function () {
    console.log("reconnected");
    connected();
});

function getCurrentDateTime() {
    var curDateTime = '';
    var currentDate = new Date();
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();
    var currentTime = new Date();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    if (day < 10)
        day = "0" + day;
    if (month < 10)
        month = "0" + month;
    if (minutes < 10)
        minutes = "0" + minutes;
    var suffix = "AM";
    if (hours >= 12) {
        suffix = "PM";
        hours = hours - 12;
    }
    if (hours === 0) {
        hours = 12;
    }
    if (hours < 10)
        hours = "0" + hours;
    curDateTime = month + "-" + day + "-" + year + "&nbsp;" + hours + ":" + minutes + " " + suffix;
    return curDateTime;
}

function browserClosed() {
    if (usersonconference.length > 0) {
        sendEndCallNotificationToAll();
    } else {
        forceHangUp();
    }
    callHangupSelf();

}

function unholdWithPersistantingState() {
    if (muteaudioflag === 0 && mutevideoflag === 0 && mutemicflag === 0)
    {
        unmuteSpeaker();
        unmuteVideo();
        unmuteMic();

    } else if (muteaudioflag === 1 && mutevideoflag === 0 && mutemicflag === 0) {
        muteSpeaker();
        unmuteVideo();
        unmuteMic();

    } else if (muteaudioflag === 0 && mutevideoflag === 1 && mutemicflag === 0) {
        unmuteSpeaker();
        muteVideo();
        unmuteMic();
    } else if (muteaudioflag === 1 && mutevideoflag === 1 && mutemicflag === 0) {
        muteSpeaker();
        muteVideo();
        unmuteMic();
    } else if (muteaudioflag === 0 && mutevideoflag === 0 && mutemicflag === 1)
    {
        unmuteSpeaker();
        unmuteVideo();
        muteMic();

    } else if (muteaudioflag === 1 && mutevideoflag === 0 && mutemicflag === 1) {
        muteSpeaker();
        unmuteVideo();
        muteMic();

    } else if (muteaudioflag === 0 && mutevideoflag === 1 && mutemicflag === 1) {
        unmuteSpeaker();
        muteVideo();
        muteMic();
    } else if (muteaudioflag === 1 && mutevideoflag === 1 && mutemicflag === 1) {
        muteSpeaker();
        muteVideo();
        muteMic();
    }
}

function unholdWithPersistantingStateCustomer() {
    if (muteaudioflag === 0 && mutevideoflag === 0 && mutemicflag === 0)
    {
        unmuteSpeaker();
        unmuteVideo();
        unmuteMic();

    } else if (muteaudioflag === 1 && mutevideoflag === 0 && mutemicflag === 0) {
        muteSpeaker();
        unmuteVideo();
        unmuteMic();

    } else if (muteaudioflag === 0 && mutevideoflag === 1 && mutemicflag === 0) {
        unmuteSpeaker();
        muteVideo();
        unmuteMic();
    } else if (muteaudioflag === 1 && mutevideoflag === 1 && mutemicflag === 0) {
        muteSpeaker();
        muteVideo();
        unmuteMic();
    } else if (muteaudioflag === 0 && mutevideoflag === 0 && mutemicflag === 1)
    {
        unmuteSpeaker();
        unmuteVideo();
        muteMic();

    } else if (muteaudioflag === 1 && mutevideoflag === 0 && mutemicflag === 1) {
        muteSpeaker();
        unmuteVideo();
        muteMic();

    } else if (muteaudioflag === 0 && mutevideoflag === 1 && mutemicflag === 1) {
        unmuteSpeaker();
        muteVideo();
        muteMic();
    } else if (muteaudioflag === 1 && mutevideoflag === 1 && mutemicflag === 1) {
        muteSpeaker();
        muteVideo();
        muteMic();
    }
}

function resetuserList() {
    senduser = '';
    usersonconference = new Array();
    usernameonconference = new Array();
    scheduleAcknowledgement = false;
    allChat = '';
}
