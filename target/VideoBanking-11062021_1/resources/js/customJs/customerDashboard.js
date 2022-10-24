function openDocumentsCustom(link, linkName) {
    var url = link, title = linkName, w = 790, h = 570;
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var popUp = window.open(url, title, 'scrollbars=yes,toolbar=no,resizable=no,fullscreen=no,width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    if (popUp === null || typeof (popUp) === 'undefined') {
        alert('Please disable your pop-up blocker and click the "Open document" button again.');
    } else {
        popUp.focus();
    }
}


function redirectHome() {
}

$(document).ready(function () {

    document.getElementById('custsubmit').click();
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
            if (elms.length === 0)
            {
                item.innerHTML = prototype.string.autolink(item.innerHTML, '');
            }

        }
    }, 3000);

});


function enableCustometForTakeCall() {
    document.getElementById('enableCustometForTakeCall').click();
}

function enableCancelDia() {
    document.getElementById("initialCallCancelDlgId").style.visibility = "visible";
}

function disableCancelDia() {
    document.getElementById("initialCallCancelDlgId").style.visibility = "hidden";
}

function enableCancelDiaRM() {
    document.getElementById("initialCallCancelDlgIdRM").style.visibility = "visible";
}

function disableCancelDiaRM() {
    document.getElementById("initialCallCancelDlgIdRM").style.visibility = "hidden";
}

function enableCancelDiaSRM() {
    document.getElementById("initialCallCancelDlgIdSRM").style.visibility = "visible";
}

function disableCancelDiaSRM() {
    document.getElementById("initialCallCancelDlgIdSRM").style.visibility = "hidden";
}

function enableCancelDiaBM() {
    document.getElementById("initialCallCancelDlgIdBM").style.visibility = "visible";
}

function disableCancelDiaBM() {
    document.getElementById("initialCallCancelDlgIdBM").style.visibility = "hidden";
}

function clearFileUpload() {
    var ff = document.getElementsByClassName("ui-fileupload-cancel");
    for (var i = 0; i < ff.length; i++) {
        ff[i].click();
    }
}

function doSubmitForCall() {
    document.getElementById('callSubmitBtn').click();
}

function removeOtp() {
    document.getElementById('userid').value = "";
}

function afterJoincall() {
    var flag = 0;
    if (document.getElementById("spinnerReplace").style.display === 'block') {
        if (document.getElementById("initialCallCancelDlgId").style.visibility === 'visible') {
            flag = 1;
            PF('initialCallCancelDlg').hide();
            PF('exitSysVar').show();
        }
    }

    if (flag == 0) {
        document.getElementById('endBtn').click();
    }
}

function check() {
    if (document.getElementById('service_input').selectedIndex != 0) {
        PF('serviceDialog').hide();
        showSpinner();
        console.log('initialCallCancelDlgRM will show now');
        PF('initialCallCancelDlg').show();
        enableCancelDia();
        document.getElementById('serviceSelectBtnId').click();
    }
}

function checkRM() {
    if (document.getElementById('service_input').selectedIndex != 0) {
        PF('serviceDialog').hide();
        showSpinner();
        console.log('initialCallCancelDlgRM will show now');
        PF('initialCallCancelDlgRM').show();
        enableCancelDiaRM();
        document.getElementById('serviceSelectBtnId').click();
    }
}

function checkRetryRM() {
    PF('callNotInitialisedDialogRM').hide();
    PF('initialCallCancelDlgRM').show();
    enableCancelDiaRM();
    document.getElementById('serviceSelectBtnId').click();
}

function routeToAgentOnSchedule() {
    PF('routeToAgentOnScheduleWidget').hide();
    showSpinner();
    console.log('initialCallCancelDlg will show now');
    PF('initialCallCancelDlg').show();
    enableCancelDia();
}

function checkSRM() {
    showSpinner();
    PF('initialCallCancelDlgSRM').show();
    enableCancelDiaSRM();
}

function checkRetrySRM() {
    PF('callNotInitialisedDialogSRM').hide();
    PF('initialCallCancelDlgSRM').show();
    enableCancelDiaSRM();
    document.getElementById('retrybuttonidSRM').click();
}

function checkBM() {
    showSpinner();
    PF('initialCallCancelDlgBM').show();
    enableCancelDiaBM();
}

function checkRetryBM() {
    PF('callNotInitialisedDialogBM').hide();
    PF('initialCallCancelDlgBM').show();
    enableCancelDiaBM();
    document.getElementById('retrybuttonidBM').click();
}

function openCloseDialog() {
    PF('exitSysVar').show();
    hideSpinner();
    document.getElementById('burgeInForm').style.display = 'block';
}


var rmvar;
var isRMCancel = false;
function openRMCloseDialog() {
    console.log('openRMCloseDialog............');
    if (!isRMCancel) {
        console.log('isRMCancel false............');
        PF('tempinitialCallCancelDlgRM').show();
        rmvar = setTimeout(function () {
            PF('tempinitialCallCancelDlgRM').hide();
            PF('exitSysVar').show();
            hideSpinner();
            document.getElementById('burgeInForm').style.display = 'block'
        }, 3000);
    } else {
        console.log('isRMCancel true............');
        return false;
    }
}

function clearAllRM() {
    console.log('clearAllRM............');
    isRMCancel = true;
    if (rmvar) {
        clearTimeout(rmvar);
        rmvar = null;
    }
}

var srmvar;
var isSRMCancel = false;
function openSRMCloseDialog() {
    console.log('openSRMCloseDialog............');
    if (!isSRMCancel) {
        console.log('isSRMCancel false............');
        PF('tempinitialCallCancelDlgSRM').show();
        srmvar = setTimeout(function () {
            PF('tempinitialCallCancelDlgSRM').hide();
            PF('exitSysVar').show();
            hideSpinner();
            document.getElementById('burgeInForm').style.display = 'block';
        }, 3000);
    } else {
        console.log('isSRMCancel true............');
        return false;
    }
}

function clearAllSRM() {
    console.log('clearAllSRM............');
    isSRMCancel = true;
    if (srmvar) {
        clearTimeout(srmvar);
        rmvar = null;
    }
}

var bmvar;
var isBMCancel = false;
function openBMCloseDialog() {
    console.log('openBMCloseDialog............');
    if (!isBMCancel) {
        console.log('isBMCancel false............');
        PF('tempinitialCallCancelDlgBM').show();
        bmvar = setTimeout(function () {
            PF('tempinitialCallCancelDlgBM').hide();
            PF('exitSysVar').show();
            hideSpinner();
            document.getElementById('burgeInForm').style.display = 'block'
        }, 3000);
    } else {
        console.log('isBMCancel true............');
        return false;
    }
}

function clearAllBM() {
    console.log('clearAllBM............');
    isBMCancel = true;
    if (bmvar) {
        clearTimeout(bmvar);
        bmvar = null;
    }
}

function checkDialogRM() {
    if (document.getElementById("spinnerReplace").style.display == 'block') {
        PF('callNotInitialisedDialogRM').show()
    }
}

function checkDialogSRM() {
    if (document.getElementById("spinnerReplace").style.display == 'block') {
        PF('callNotInitialisedDialogSRM').show()
    }
}

function checkDialogBM() {
    if (document.getElementById("spinnerReplace").style.display == 'block') {
        PF('callNotInitialisedDialogBM').show()
    }
}

var dialvar;
function dialCallReceiveCheck() {
    dialvar = setTimeout(function () {
        if (document.getElementById("incallcontainer").style.display === 'none') {
            $("#incomingReject1").trigger("click");
        }
    }, 30000);
}

function clearDialTime() {
    clearTimeout(dialvar);
}

var sctimevar;
function scheduleCallReceiveCheck() {
    sctimevar = setTimeout(function () {
        if (document.getElementById("incallcontainer").style.display === 'none') {
            $("#missschedule").trigger("click");
            closeAllDialog();
            hideSpinner();
            PF('scheduleMiss').show();
        }
    }, 30000);
}

function clearScheduleCallReceiveCheck() {
    clearTimeout(sctimevar);
}

var chatWindowState = 0;
function checkCallType() {
    if (document.getElementById('call_type_id').value === undefined)
        document.getElementById('call_type_id').value = "1";
    PF('directOrScheduleCallWid').hide();
    if (document.getElementById('call_type_id').value == "1") {
        console.log('serviceDialog will show now');
        PF('serviceDialog').show();
    } else if (document.getElementById('call_type_id').value == "2") {
        console.log('makeScCallDialogWid will show now');
        PF('doScheduleDialog').show();
    }
}

function callTypeAssign(val) {
    console.log("VAL::" + val);
    document.getElementById('call_type_id').value = val;
}

function openPdfInTab() {

    var hrefTest = '${request.contextPath}/CustomerFormPdfDownload';

    var popUp = window.open(hrefTest, '_blank', 'width=1,height=1,toolbar=0,menubar=0,location=0,status=0,scrollbars=0,resizable=1,left=10,top=10,visible=none');
    //alert('hi Chayan 2222');
    if (popUp === null || typeof (popUp) === 'undefined') {
        alert('Please disable your pop-up blocker and click the "Download File" link again.');
    } else {
        setTimeout(function () {
            popUp.close();
        }, 3000);
        popUp.focus();
    }
}

function screenShot() {
    urlsToAbsolute(document.images);
    urlsToAbsolute(document.querySelectorAll("link[rel='stylesheet']"));
 
    var screenshot = document.documentElement.cloneNode(true);
    console.log("scr", screenshot);
    var b = document.createElement('base');
    b.href = document.location.protocol + '//' + location.host;
    var head = screenshot.querySelector('head');
    head.insertBefore(b, head.firstChild);

    screenshot.style.pointerEvents = 'none';
    screenshot.style.overflow = 'hidden';
    screenshot.style.webkitUserSelect = 'none';
    screenshot.style.mozUserSelect = 'none';
    screenshot.style.msUserSelect = 'none';
    screenshot.style.oUserSelect = 'none';
    screenshot.style.userSelect = 'none';

    screenshot.dataset.scrollX = window.scrollX;
    screenshot.dataset.scrollY = window.scrollY;

    var script = document.createElement('script');
    script.textContent = '(' + addOnPageLoad_.toString() + ')();'; // self calling.
    screenshot.querySelector('body').appendChild(script);

    var blob = new Blob([screenshot.outerHTML], {type: 'text/html'});
    window.open(window.URL.createObjectURL(blob));
}

function addOnPageLoad_() {
    window.addEventListener('DOMContentLoaded', function (e) {
        var scrollX = document.documentElement.dataset.scrollX || 0;
        var scrollY = document.documentElement.dataset.scrollY || 0;
        window.scrollTo(scrollX, scrollY);
    });
}

function urlsToAbsolute(nodeList) {
    if (!nodeList.length) {
        return [];
    }

    var attrName = 'href';
    if (nodeList[0].__proto__ === HTMLImageElement.prototype ||
            nodeList[0].__proto__ === HTMLScriptElement.prototype) {
        attrName = 'src';
    }

    nodeList = [].map.call(nodeList, function (el, i) {
        var attr = el.getAttribute(attrName);
        // If no src/href is present, disregard.
        if (!attr) {
            return;
        }

        var absURL = /^(https?|data):/i.test(attr);
        if (absURL) {
            return el;
        } else {
            return el;
        }
    });
    return nodeList;
}