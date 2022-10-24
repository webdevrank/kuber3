var configParams = {};
var platformInfo = {};

if (typeof module === 'object') {
    window.module = module;
    module = undefined;
}
if (window.module) {
    module = window.module;
}
function onVidyoClientLoaded(status) {
    console.log("Status: " + status.state + "Description: " + status.description);
	switch (status.state) {
            case "READY":    // The library is operating normally
		$("#connectionStatus").html("Ready to Connect");
		$("#helper").addClass("hidden");
		window.VC = new window.VidyoClientLib.VidyoClient('', () => {
                // After the VidyoClient is successfully initialized a global VC object will become available
                // All of the VidyoConnector gui and logic is implemented in VidyoConnector.js
                StartVidyoConnector(VC, window.VCUtils ? window.VCUtils.params.webrtc : 'true');
                });
		break;
            case "RETRYING": // The library operating is temporarily paused
		$("#connectionStatus").html("Temporarily unavailable retrying in " + status.nextTimeout/1000 + " seconds");
		break;
            case "FAILED":   // The library operating has stopped
		ShowFailed(status);
		$("#connectionStatus").html("Failed: " + status.description);
		break;
            case "FAILEDVERSION":   // The library operating has stopped
		UpdateHelperPaths(status);
		ShowFailedVersion(status);
		$("#connectionStatus").html("Failed: " + status.description);
		break;
            case "NOTAVAILABLE": // The lioi
		$("#connectionStatus").html(status.description);
		break;
	}
	return true; // Return true to reload the plugins if not available
}
function UpdateHelperPaths(status) {
    $("#helperPlugInDownload").attr("href", status.downloadPathPlugIn);
    $("#helperAppDownload").attr("href", status.downloadPathApp);
}
function ShowFailed(status) {
    var helperText = '';
    helperText += '<h2>An error occurred, please reload</h2>';
    helperText += '<p>' + status.description + '</p>';
    $("#helperText").html(helperText);
    $("#failedText").html(helperText);
    $("#failed").removeClass("hidden");
}
function ShowFailedVersion(status) {
    var helperText = '';
    helperText += '<h4>Please Download a new plugIn and restart the browser</h4>';
    helperText += '<p>' + status.description + '</p>';
    $("#helperText").html(helperText);
}
function loadVidyoClientLibrary(webrtc, plugin) {
    // If webrtc, then set webrtcLogLevel
    var webrtcLogLevel = "";
    if (webrtc) {
    // Set the WebRTC log level to either: 'info' (default), 'error', or 'none'
            webrtcLogLevel = '&webrtcLogLevel=info';
    	}
    //We need to ensure we're loading the VidyoClient library and listening for the callback.
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = contextUrl+'/resources/vidyo/hunter/VidyoClient.js?onload=onVidyoClientLoaded&webrtc=' + webrtc + '&plugin=' + plugin + webrtcLogLevel;
    document.getElementsByTagName('head')[0].appendChild(script);
}
function loadNativeScipWebRTCVidyoClientLibrary() {
    // Assumes that this file is hosted in the Hookflash build environment
    // TODO: update path
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = contextUrl+'/resources/vidyo/latest_build/VidyoClient.js';
    script.onload = function() {
          onVidyoClientLoaded({ state: 'READY', description: 'Native SCIP + WebRTC' });
        };
    document.getElementsByTagName('head')[0].appendChild(script);
    var style = document.createElement('link');
    style.rel = 'stylesheet';
    style.type = 'text/css';
    style.href = contextUrl+'/resources/vidyo/latest_build/VidyoClient.css';
    document.getElementsByTagName('head')[0].appendChild(style);
}
function loadWebRTCControlPanel() {
     let style = document.createElement('link');
     style.rel = 'stylesheet';
     style.type = 'text/css';
     style.href = contextUrl+'/resources/vidyo/hunter/WCtrlPanel.css';
     document.querySelector('head').appendChild(style);
     let script = document.createElement('script');
     script.type = 'text/javascript';
     script.src = contextUrl+'/resources/vidyo/hunter/WCtrlPanel.js';
     document.querySelector('head').appendChild(script);
}
function checkElement(array)
{
    for (var i = 0; i < array.length; i++)
    {
        if (array[i] == 19) {
            return true;
        }
    }
}
function joinViaBrowser() {
    console.log('...................In joinViaBrowser ');
    loadNativeScipWebRTCVidyoClientLibrary();
//    loadWebRTCControlPanel();
    document.getElementById('incallcontainer').style.display = 'block';
   
}
function joinViaPlugIn() {
    $("#helperText").html("Don't have the PlugIn..?");
    $("#helperPicker").addClass("hidden");
    $("#helperPlugIn").removeClass("hidden");
    loadVidyoClientLibrary(false, true);
}
function joinViaElectron() {
    $("#helperText").html("Electron...");
    $("#helperPicker").addClass("hidden");
    loadVidyoClientLibrary(false, true);
}
function joinViaApp() {
    $("#helperText").html("Don't have the app?");
    $("#helperPicker").addClass("hidden");
    $("#helperApp").removeClass("hidden");
    var protocolHandlerLink = 'vidyoconnector://' + window.location.search;
    $("#helperAppLoader").attr('src', protocolHandlerLink);
    loadVidyoClientLibrary(false, false);
}
function joinViaOtherApp() {
    $("#helperText").html("Don't have the app?");
    $("#helperPicker").addClass("hidden");
    $("#helperOtherApp").removeClass("hidden");
    var protocolHandlerLink = 'vidyoconnector://' + window.location.search;
    $("#helperOtherAppLoader").attr('src', protocolHandlerLink);
    loadVidyoClientLibrary(false, false);
}
function loadHelperOptions() {
}
