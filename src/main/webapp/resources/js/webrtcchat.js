$(document).ready(function () {
    $("#addstatictab").dynatabs({
        tabBodyID: 'participant',
        showCloseBtn: !1,
        confirmDelete: !1
    })
});
var tabs = [];
var index = 0;
var sendIndex = 0;
var currentIndex = 0;
var everyoneUnread = 0;
var privateUnread = [];
//var prodNameChat='kuber';

function addNewStaticTab1(title) {
    var ttl = title.replace(/\s+/g, '');
    var added = !1;
    for (index = 0; index < tabs.length; index++) {
        if (tabs[index] === ttl) {
            index++;
            added = !0;
            break
        }
    }
    if (!added) {
        tabs.push(title);
        privateUnread.push(0);
        $.addDynaTab({
            tabID: 'addstatictab',
            type: 'html',
            html: '<p></p>',
            params: {},
            tabTitle: ttl
        });
        index++
    }
    var x = document.getElementsByName("user");
    var i;
    for (i = 0; i < x.length; i++) {
        if (x[i].type === "radio") {
            if (x[i].id === ttl) {
                x[i].checked = !0
            }
        }
    }
}

function addNewStaticTab2(title) {
    var ttl = title.replace(/\s+/g, '');
    var added = !1;
    for (index = 0; index < tabs.length; index++) {
        if (tabs[index] === ttl) {
            index++;
            added = !0;
            break
        }
    }
    if (!added) {
        tabs.push(title);
        privateUnread.push(0);
        $.addDynaTab({
            tabID: 'addstatictab',
            type: 'html',
            html: '<p></p>',
            params: {},
            tabTitle: ttl
        });
        index++
    }
}

function addNewStaticTab(title) {
    var ttl = title.replace(/\s+/g, '');
    var added = !1;
    for (index = 0; index < tabs.length; index++) {
        if (tabs[index] === ttl) {
            index++;
            added = !0;
            break
        }
    }
    if (!added) {
        tabs.push(title);
        privateUnread.push(0);
        $.addDynaTab({
            tabID: 'addstatictab',
            type: 'html',
            html: '<p></p>',
            params: {},
            tabTitle: ttl
        });
        index++
    }
    var d = index;
    document.getElementById('_participant' + d).click();
    var x = document.getElementsByName("user");
    var i;
    for (i = 0; i < x.length; i++) {
        if (x[i].type === "radio") {
            if (x[i].id === ttl) {
                x[i].checked = !0;
                addNewStaticTab1(title);
                if (tabs.length === 1) {
                    if (document.getElementById('Everyone').innerHTML !== '') {
                        document.getElementById('_Everyone').click();
                        document.getElementById('_participant' + d).click()
                    }
                }
            }
        }
    }
}

function addNewStaticTabRec(title) {
    var ttl = title.replace(/\s+/g, '');
    var added = !1;
    for (index = 0; index < tabs.length; index++) {
        if (tabs[index] === ttl) {
            index++;
            added = !0;
            break
        }
    }
    if (!added) {
        tabs.push(title);
        privateUnread.push(0);
        $.addDynaTab({
            tabID: 'addstatictab',
            type: 'html',
            html: '<p></p>',
            params: {},
            tabTitle: ttl
        });
        index++
    }
    var x = document.getElementsByName("user");
    var i;
    for (i = 0; i < x.length; i++) {
        if (x[i].type === "radio") {
            if (x[i].id === ttl) {
                addNewStaticTab2(title)
            }
        }
    }
    var windowHeight = $(window).height();
    var videoheight = $('#vidyoConnector').css("height");
    var ratio = parseInt(videoheight) / parseInt(windowHeight);
    var verth = parseInt(ratio.toString().substring(2, 4));
    if (verth < 10) {
        verth = parseInt(verth) * 10
    }
       
//    if (verth > 48) {
         if (document.getElementById('collapse1').style.display=='none') {
        var msgcount = privateUnread[index - 1] = parseInt(privateUnread[index - 1]) + 1;
        document.getElementById('_participant' + index).style.background = "yellow";
        document.getElementById('_participantspan' + index).style.background = "red";
        document.getElementById('_participantspan' + index).style.display = "inline";
        if (parseInt(msgcount) < 10) {
            document.getElementById('_participantspan' + index).innerHTML = '&nbsp;' + msgcount + '&nbsp;'
        } else {
            document.getElementById('_participantspan' + index).innerHTML = '' + msgcount
        }
    } else if (index !== currentIndex) {
        var msgcount = privateUnread[index - 1] = parseInt(privateUnread[index - 1]) + 1;
        document.getElementById('_participant' + index).style.background = "yellow";
        document.getElementById('_participantspan' + index).style.background = "red";
        document.getElementById('_participantspan' + index).style.display = "inline";
        if (parseInt(msgcount) < 10) {
            document.getElementById('_participantspan' + index).innerHTML = '&nbsp;' + msgcount + '&nbsp;'
        } else {
            document.getElementById('_participantspan' + index).innerHTML = '' + msgcount
        }
    } else {
        var cnt = 0;
        for (cnt = 0; cnt < tabs.length - 1; cnt++) {
            if (tabs[cnt] === ttl) {
                document.getElementById('_participant' + index).style.background = "none"
            }
        }
        document.getElementById('_participant' + index).click()
    }
}

function updateSendMessage(senduser) {
    console.log("updateSendMessage updateSendMessage :" + senduser);
    sendIndex = 0;
    if (senduser !== '') {
        for (inx = 0; inx < tabs.length; inx++) {
            if (tabs[inx] === senduser) {
                sendIndex = parseInt(inx) + 1;
                break
            }
        }
        var namefull = document.getElementById('displayName').value;
        var namesp = namefull.split("~");
        var displayName = namesp[0];
        document.getElementById('participant' + sendIndex).innerHTML += '<div class="chatMessage" style="width:50%;"><span style="font-weight:bold">' + displayName + '</span><span style="color:blue;margin-left:5px;">[' + getCurrentDateTime() + ']</span><br/><div class="dialogbox"><div class="body"><span class="tip tip-up"></span> <div class="message">' + document.getElementById('msgToSend').value + '</div></div></div></div>';
        var elms = document.getElementById('participant' + sendIndex).getElementsByTagName("div");
        var chatelement = elms[elms.length - 1];
        chatelement.scrollIntoView()
    } else {
        var namefull = document.getElementById('displayName').value;
        var namesp = namefull.split("~");
        var displayName = namesp[0];
        document.getElementById('Everyone').innerHTML += '<div class="chatMessage" style="width:50%;"><span style="font-weight:bold">' + displayName + '</span><span style="color:blue;margin-left:5px;">[' + getCurrentDateTime() + ']</span><br/><div class="dialogbox"><div class="body"><span class="tip tip-up"></span> <div class="message">' + document.getElementById('msgToSend').value + '</div></div></div></div>';
        var elms = document.getElementById('Everyone').getElementsByTagName("div");
        var chatelement = elms[elms.length - 1];
        chatelement.scrollIntoView()
    }
}

function setCurrentTab(tabtitle) {
    currentIndex = 0;
    for (inx = 0; inx < tabs.length; inx++) {
        if (tabs[inx] === tabtitle) {
            currentIndex = parseInt(inx) + 1;
            break
        }
    }
}

function setEvery() {
    everyoneUnread = 0;
    index = 0;
    document.getElementById('_Everyonespan').style.display = "none";
    $('#_Everyone').removeAttr('style');
    currentIndex = 0;
    var x = document.getElementsByName("user");
    var i;
    for (i = 0; i < x.length; i++) {
        if (x[i].type === "radio") {
            x[i].checked = !1
        }
    }
    var windowHeight = $(window).height();
    var videoheight = $('#vidyoConnector').css("height");
    var ratio = parseInt(videoheight) / parseInt(windowHeight);
    var verth = parseInt(ratio.toString().substring(2, 4));
    if (verth < 10) {
        verth = parseInt(verth) * 10
    }
       
//    if (verth > 48) {
     if (document.getElementById('collapse1').style.display=='none') {
        $(".nav-toggle").trigger("click")
    } else {
        setScrollToEndForEveryone()
    }
}

function updateEveryone() {
    var windowHeight = $(window).height();
    var videoheight = $('#vidyoConnector').css("height");
    var ratio = parseInt(videoheight) / parseInt(windowHeight);
    var verth = parseInt(ratio.toString().substring(2, 4));
    if (verth < 10) {
        verth = parseInt(verth) * 10
    }
      
//    if (verth > 48) {
 if (document.getElementById('collapse1').style.display=='none') {
        everyoneUnread++;
        document.getElementById('_Everyone').style.background = "yellow";
        document.getElementById('_Everyonespan').style.background = "red";
        document.getElementById('_Everyonespan').style.display = "inline";
        if (parseInt(everyoneUnread) < 10) {
            document.getElementById('_Everyonespan').innerHTML = '&nbsp;' + everyoneUnread + '&nbsp;'
        } else {
            document.getElementById('_Everyonespan').innerHTML = '' + everyoneUnread
        }
    } else if (currentIndex !== 0) {
        everyoneUnread++;
        document.getElementById('_Everyone').style.background = "yellow";
        document.getElementById('_Everyonespan').style.background = "red";
        document.getElementById('_Everyonespan').style.display = "inline";
        if (parseInt(everyoneUnread) < 10) {
            document.getElementById('_Everyonespan').innerHTML = '&nbsp;' + everyoneUnread + '&nbsp;'
        } else {
            document.getElementById('_Everyonespan').innerHTML = '' + everyoneUnread
        }
    }
}

function resetMsgCounter(val) {
    privateUnread[parseInt(val) - 1] = 0
}
function resetTabs() {
    tabs = [];
    index = 0;
    sendIndex = 0;
    currentIndex = 0;
    everyoneUnread = 0;
    privateUnread = [];
    $("#addstatictab").dynatabs({
        tabBodyID: 'participant',
        showCloseBtn: !1,
        confirmDelete: !1
    });
}