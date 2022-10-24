var vidyoConnector;
var participantList = [];

var cameraPrivacy = false;
var microphonePrivacy = false;
var speakerPrivacy = false;
var statsSendingTool = null;

// Run StartVidyoConnector when the VidyoClient is successfully loaded
function StartVidyoConnector(VC, webrtc) {
   
    var cameras = {};
    var microphones = {};
    var speakers = {};
    let devicesMuteState = {
        cameraPrivacy: false,
        microphonePrivacy: false,
        speakerPrivacy: false
    };
    var configParams = {};

    $("#options").removeClass("vidyo-hide-options");
    $("#optionsVisibilityButton").removeClass("hidden");
    $("#renderer").removeClass("hidden");
    let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    var remoteParticipantsAmount = width < 500 ? 3 : 8;

    VC.CreateVidyoConnector({
        viewId: "renderer", // Div ID where the composited video will be rendered, see VidyoConnector.html;
        viewStyle: "VIDYO_CONNECTORVIEWSTYLE_Default", // Visual style of the composited renderer
        remoteParticipants: remoteParticipantsAmount,     // Maximum number of participants to render
        logFileFilter: "debug@VidyoClient debug@VidyoSDP debug@VidyoResourceManager all@VidyoSignaling",
        logFileName:"",
        userData:0
    }).then(function(vc) {
        vidyoConnector = vc;
        parseUrlParameters(configParams);
        registerDeviceListeners(vidyoConnector, cameras, microphones, speakers, devicesMuteState);
        handleDeviceChange(vidyoConnector, cameras, microphones, speakers);
        registerAdvancedSettingsListeners(vidyoConnector);
        handleAdvancedSettingsChange(vidyoConnector);
        handleParticipantChange(vidyoConnector, devicesMuteState);
        handleReconnect(vidyoConnector);
        handleSharing(vidyoConnector, webrtc);
        handleChat(vidyoConnector);

//        statsSendingTool = getLokiImplementation(configParams.enableLoki, configParams.lokiBaseUrl);

        // XXX: ResourceManagerEventListener EXAMPLE - uncomment the line below to subscribe to ResourceManagerEvents
        // registerResourceManagerEventListener(vidyoConnector);

        // XXX: CUSTOM LOGGING EXAMPLE - uncomment the line below to use the custom logging
        // registerLogEventListener(vidyoConnector, onVidyoLog, "debug sent@VidyoClient enter@VidyoClient");
        // XXX: some other examples of logLevelFilter
        // info sent@VidyoClient -=received@VidyoClient =enter@VidyoClient

        // Populate the connectionStatus with the client version
        vidyoConnector.GetVersion().then(function(version) {
            $("#clientVersion").html("v " + version);
        }).catch(function() {
            console.error("GetVersion failed");
        });

        if (configParams.disableAudioLevelMonitor === 'true') {
          vidyoConnector.SetAdvancedConfiguration({disableAudioEnergyMonitor: true})
        }

        if (configParams.dynamicAudioSources) {
          vidyoConnector.SetAdvancedConfiguration({dynamicAudioSources: configParams.dynamicAudioSources})
        }

        // If enableDebug is configured then enable debugging
        if (configParams.enableDebug === "1") {
            vidyoConnector.EnableDebug({port:7776, logFilter: "debug sent@VidyoClient enter@VidyoClient all@VidyoSignaling"}).then(function() {
                console.log("EnableDebug success");
            }).catch(function() {
                console.error("EnableDebug failed");
            });
        }

        // Join the conference if the autoJoin URL parameter was enabled
//        if (configParams.autoJoin === "1") {
          joinLeave();
//        } else {
//          // Handle the join in the toolbar button being clicked by the end user.
//          $("#joinLeaveButton").one("click", joinLeave);
//        }

        if (configParams.enableAutoReconnect) {
            vidyoConnector.SetAdvancedConfiguration({ enableAutoReconnect: configParams.enableAutoReconnect });
        }

        if (configParams.maxReconnectAttempts) {
            vidyoConnector.SetAdvancedConfiguration({ maxReconnectAttempts: configParams.maxReconnectAttempts });
        }

        if (configParams.reconnectBackoff) {
            vidyoConnector.SetAdvancedConfiguration({ reconnectBackoff: configParams.reconnectBackoff });
        }

        if (configParams.enableAudioOnlyMode === 'false') {
            vidyoConnector.SetAdvancedConfiguration({ enableAudioOnlyMode: false});
        }

//        initWCtrlPanel(vidyoConnector);

      // XXX: this is an example how to registering energy level metering callbacks
      // let localMicrophoneEnergyListener = {onEnergy: onLocalMicrophoneEnergyLevel}; //, meteringPeriod: 1000};
      // vidyoConnector.RegisterLocalMicrophoneEnergyListener(localMicrophoneEnergyListener);
      // let remoteMicrophoneEnergyListener = {onEnergy: onRemoteMicrophoneEnergyLevel}; // , meteringPeriod: 1000};
      // vidyoConnector.RegisterRemoteMicrophoneEnergyListener(remoteMicrophoneEnergyListener);
    }).catch(function(err) {
        console.error("CreateVidyoConnector Failed " + err);
    });

  // XXX: this is an example how to use energy level metering API
  // function onLocalMicrophoneEnergyLevel(localMicrophone, mStats){
  //   let d = new Date();
  //   let ds = d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds() + ':' + d.getMilliseconds();
  //   console.log('onLocalMicrophoneEnergyLevel: ' + ds + ': ' + JSON.stringify(localMicrophone) + ' = ' + mStats);
  // }
  // XXX: this is an example how to use energy level metering API
  // function onRemoteMicrophoneEnergyLevel(remoteMicrophone, remoteParticipant, mStats){
  //   let d = new Date();
  //   let ds = d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds() + ':' + d.getMilliseconds();
  //   console.log('onRemoteMicrophoneEnergyLevel: ' + ds + ': ' + JSON.stringify(remoteMicrophone) + ': ' + JSON.stringify(remoteParticipant) + ' = ' + mStats);
  // }

    // Handle the camera privacy button, toggle between show and hide.
    $("#cameraButton").click(function() {
        // CameraPrivacy button clicked
        cameraPrivacy = !cameraPrivacy;
        vidyoConnector.SetCameraPrivacy({
            privacy: cameraPrivacy
        }).then(function() {
            if (cameraPrivacy) {
                $("#cameraButton").addClass("cameraOff").removeClass("cameraOn");
            } else {
                $("#cameraButton").addClass("cameraOn").removeClass("cameraOff");
            }
            console.log("SetCameraPrivacy Success");
        }).catch(function() {
            console.error("SetCameraPrivacy Failed");
        });
    });

    // Handle the microphone mute button, toggle between mute and unmute audio.
    $("#microphoneButton").click(function() {
        // MicrophonePrivacy button clicked
        microphonePrivacy = !microphonePrivacy;
        vidyoConnector.SetMicrophonePrivacy({
            privacy: microphonePrivacy
        }).then(function() {
            if (microphonePrivacy) {
                $("#microphoneButton").addClass("microphoneOff").removeClass("microphoneOn");
            } else {
                $("#microphoneButton").addClass("microphoneOn").removeClass("microphoneOff");
            }
            console.log("SetMicrophonePrivacy Success");
        }).catch(function() {
            console.error("SetMicrophonePrivacy Failed");
   
    });
    });     
  
class DroppedTilesIndicator {
    static maxRemoteSources = 0;
    static numberOfRemoteCameras = 0;
    static numberOfRemoteShares = 0;
    static participantLimit = 0;

    static update({ maxRemoteSources, numberOfRemoteCameras, numberOfRemoteShares, participantLimit } = {}) {
        if (typeof maxRemoteSources === 'number') {
            DroppedTilesIndicator.maxRemoteSources = maxRemoteSources;
        }
        if (typeof numberOfRemoteCameras === 'number') {
            DroppedTilesIndicator.numberOfRemoteCameras = numberOfRemoteCameras;
        }
        if (typeof numberOfRemoteShares === 'number') {
            DroppedTilesIndicator.numberOfRemoteShares = numberOfRemoteShares;
        }
        if (typeof participantLimit === 'number') {
            DroppedTilesIndicator.participantLimit = participantLimit;
        }
        const numberOfDynamicVideoTilesWeWantToShow = Math.min(
            DroppedTilesIndicator.numberOfRemoteCameras,
            DroppedTilesIndicator.participantLimit
        );
        const numberOfDynamicVideoTilesWeCanShow = Math.min(
            DroppedTilesIndicator.maxRemoteSources - DroppedTilesIndicator.numberOfRemoteShares,
            numberOfDynamicVideoTilesWeWantToShow
        );
        const noneOfVideoTilesDropped = numberOfDynamicVideoTilesWeCanShow >= numberOfDynamicVideoTilesWeWantToShow;
        $('#dropped-tiles-indicator').toggleClass('hidden', noneOfVideoTilesDropped);
    }
}


    $("#speakerButton").click(function() {
        speakerPrivacy = !speakerPrivacy;
        vidyoConnector.SetSpeakerPrivacy({
            privacy: speakerPrivacy
        }).then(function() {
            if (speakerPrivacy) {
                $("#speakerButton").addClass("speakerOff").removeClass("speakerOn");
            } else {
                $("#speakerButton").addClass("speakerOn").removeClass("speakerOff");
            }
            console.log("SetSpeakerPrivacy Success");
        }).catch(function() {
            console.error("SetSpeakerPrivacy Failed");
        });
    });

    // Handle the chat button - open/close the right chat/participant pane.
    $("#chatButton").click(function() {
      onChatButtonClicked();
      let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
      if ( width < 500 && $("#optionsVisibilityButton").hasClass("hideOptions")) {
          $("#options").addClass("vidyo-hide-options");
          $("#optionsVisibilityButton").addClass("showOptions").removeClass("hideOptions");
          $("#renderer").addClass("rendererFullScreen").removeClass("rendererWithOptions");
     }
    });

    // Handle the chat tab and participant tab clicks.
    $("#chatTabButton").click({tabName:"chatTab", color:"blue"}, openTab);
    $("#participantTabButton").click({tabName:"participantTab", color:"green"}, openTab);

    // Handle the options visibility button, toggle between show and hide options.
    $("#optionsVisibilityButton").click(function() {
        // OptionsVisibility button clicked
        if ($("#optionsVisibilityButton").hasClass("hideOptions")) {
            $("#options").addClass("vidyo-hide-options");
            $("#optionsVisibilityButton").addClass("showOptions").removeClass("hideOptions");
            $("#renderer").addClass("rendererFullScreen").removeClass("rendererWithOptions");
        } else {
            $("#options").removeClass("vidyo-hide-options");
            $("#optionsVisibilityButton").addClass("hideOptions").removeClass("showOptions");
            $("#renderer").removeClass("rendererFullScreen").addClass("rendererWithOptions");
            let width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
            if(width<500 && chatData.chatOpen){
                onChatButtonClicked();
                console.log(width);
            }
        }
    });

    function joinLeave() {
        // join or leave dependent on the joinLeaveButton, whether it
        // contains the class callStart of callEnd.
        if ($("#joinLeaveButton").hasClass("callStart")) {
            $("#connectionStatus").html("Connecting...");
            $("#joinLeaveButton").removeClass("callStart").addClass("callEnd");
            $('#joinLeaveButton').prop('title', 'Leave Conference');
            connectToConference(vidyoConnector);
            $('#spinner').hide()
        } else {
            $("#connectionStatus").html("Disconnecting...");
            vidyoConnector.Disconnect().then(function() {
                $("#localParticipant").remove();
                console.log("Disconnect Success");
                $('#spinner').hide();
            }).catch(function() {
                $('#spinner').hide();
                console.error("Disconnect Failure");
            });
        }
        $("#joinLeaveButton").one("click", joinLeave);
    }

}

function registerAdvancedSettingsListeners(vidyoConnector) {
    var setActiveLogs;
    vidyoConnector._registerAdvancedSettingsEventListener({
        onAddGoogleConferenceFlagChanged: function(addGoogleConferenceFlag) {
            $('#advanced-addGoogleConferenceFlag').prop('checked', addGoogleConferenceFlag);
        },
        onLogCategoryChanged: function(logs) {
            if(logs.logLevel && !setActiveLogs) {
                createTable(logs);
                setActiveLogs = setActiveLogLevels(logs.activeLogs,logs.logLevel);
                setActiveLogs(logs.activeLogs);
            }else {
                setActiveLogs(logs)
            }
        },
        onDisableStatsChanged: function(disableStats) {
            $('#advanced-disableStats').prop('checked', disableStats);
        },
        onEnableSimpleAPILoggingChanged: function(enableSimpleAPILogging) {
            $('#advanced-loggingSimpleAPIMethods').prop('checked', enableSimpleAPILogging);
        },
        onEnableVidyoConnectorAPILoggingChanged: function(enableVidyoConnectorAPILogging) {
            $('#advanced-loggingVidyoConnectorAPI').prop('checked', enableVidyoConnectorAPILogging);
        },
        onEnableSimulcastChanged: function(enableSimulcast) {
            $('#advanced-enableSimulcast').prop('checked', enableSimulcast);
        },
        onEnableTransportCcChanged: function(enableTransportCc) {
            $('#advanced-enableTransportCc').prop('checked', enableTransportCc);
        },
        onEnableUnifiedPlanChanged: function(enableUnifiedPlan) {
            $('#advanced-enableUnifiedPlan').prop('checked', enableUnifiedPlan);
        },
        onParticipantLimitChanged: function(participantLimit) {
            $('#advanced-participantLimit').val(participantLimit);
        },
        onShowStatisticsOverlayChanged: function(showStatisticsOverlay) {
            $('#advanced-showStatisticsOverlay').prop('checked', showStatisticsOverlay);
        },
        onEnableAudioOnlyModeChanged: function(enableAudioOnlyMode) {
            $('#advanced-enableAudioOnlyMode').prop('checked', enableAudioOnlyMode);
        },
        onEnableAutoReconnectChanged: function(isEnabled) {
            $('#advanced-enableAutoReconnect').prop('checked', isEnabled);
        },
        onMaxReconnectAttemptsChanged: function(maxAttempts) {
            $('#advanced-maxReconnectAttempts').val(maxAttempts);
        },
        onReconnectBackoffChanged: function(reconnectBackoff) {
            $('#advanced-reconnectBackoff').val(reconnectBackoff);
        }
    });
}


var localParticipant;
function registerDeviceListeners(vidyoConnector, cameras, microphones, speakers, devicesMuteState) {
    // Map the "None" option (whose value is 0) in the camera, microphone, and speaker drop-down menus to null since
    // a null argument to SelectLocalCamera, SelectLocalMicrophone, and SelectLocalSpeaker releases the resource.
    cameras[0]     = null;
    microphones[0] = null;
    speakers[0]    = null;

    const remoteCameras = new Map();

    // Handle appearance and disappearance of camera devices in the system
    vidyoConnector.RegisterLocalCameraEventListener({
        onAdded: function(localCamera) {
            // New camera is available
            $("#cameras").append("<option value='" + window.btoa(localCamera.id) + "'>" + localCamera.name + "</option>");
            $("#cameraShare").append("<option value='" + window.btoa(localCamera.id) + "'>" + localCamera.name + "</option>");
            cameras[window.btoa(localCamera.id)] = localCamera;

        },
        onRemoved: function(localCamera) {
            // Existing camera became unavailable
            $("#cameras option[value='" + window.btoa(localCamera.id) + "']").remove();
            $("#cameraShare option[value='" + window.btoa(localCamera.id) + "']").remove();
            if(localParticipant){
            setLocalParticipantCameraPrivacy(true);
            }
            delete cameras[window.btoa(localCamera.id)];
        },
        onSelected: function(localCamera) {
            // Camera was selected/unselected by you or automatically
            if(localCamera) {
                setLocalParticipantCameraPrivacy(devicesMuteState.cameraPrivacy);
                $("#cameras option[value='" + window.btoa(localCamera.id) + "']").prop('selected', true);
            }
        },
        onStateUpdated: function(localCamera, state) {
          devicesMuteState.cameraPrivacy = state === 'VIDYO_DEVICESTATE_Stopped';
          if (devicesMuteState.cameraPrivacy) {
            $("#cameraButton").addClass("cameraOff").removeClass("cameraOn");
          } else {
            $("#cameraButton").addClass("cameraOn").removeClass("cameraOff");
          }
          if(localParticipant){
            setLocalParticipantCameraPrivacy(devicesMuteState.cameraPrivacy);
          }
        }
    }).then(function() {
        console.log("RegisterLocalCameraEventListener Success");
    }).catch(function() {
        console.error("RegisterLocalCameraEventListener Failed");
    });

    // Handle appearance and disappearance of microphone devices in the system
    vidyoConnector.RegisterLocalMicrophoneEventListener({
        onAdded: function(localMicrophone) {
            // New microphone is available
            $("#microphones").append("<option value='" + window.btoa(localMicrophone.id) + "'>" + localMicrophone.name + "</option>");
            microphones[window.btoa(localMicrophone.id)] = localMicrophone;
        },
        onRemoved: function(localMicrophone) {
            // Existing microphone became unavailable
            $("#microphones option[value='" + window.btoa(localMicrophone.id) + "']").remove();
            delete microphones[window.btoa(localMicrophone.id)];
        },
        onSelected: function(localMicrophone) {
            // Microphone was selected/unselected by you or automatically
            if(localParticipant){
                setLocalParticipantMicrophonePrivacy(devicesMuteState.microphonePrivacy);
            }
            if(localMicrophone)
                $("#microphones option[value='" + window.btoa(localMicrophone.id) + "']").prop('selected', true);

        },
        onStateUpdated: function(localMicrophone, state) {
          devicesMuteState.microphonePrivacy = state === 'VIDYO_DEVICESTATE_Stopped';
          if (devicesMuteState.microphonePrivacy) {
            $("#microphoneButton").addClass("microphoneOff").removeClass("microphoneOn");
          } else {
            $("#microphoneButton").addClass("microphoneOn").removeClass("microphoneOff");
          }
          // Microphone state was updated
          if(localParticipant) {
            setLocalParticipantMicrophonePrivacy(devicesMuteState.microphonePrivacy);
          }
        }
    }).then(function() {
        console.log("RegisterLocalMicrophoneEventListener Success");
    }).catch(function() {
        console.error("RegisterLocalMicrophoneEventListener Failed");
    });

    // Handle appearance and disappearance of speaker devices in the system
    vidyoConnector.RegisterLocalSpeakerEventListener({
        onAdded: function(localSpeaker) {
            // New speaker is available
            $("#speakers").append("<option value='" + window.btoa(localSpeaker.id) + "'>" + localSpeaker.name + "</option>");
            speakers[window.btoa(localSpeaker.id)] = localSpeaker;
        },
        onRemoved: function(localSpeaker) {
            // Existing speaker became unavailable
            $("#speakers option[value='" + window.btoa(localSpeaker.id) + "']").remove();
            delete speakers[window.btoa(localSpeaker.id)];
        },
        onSelected: function(localSpeaker) {
            // Speaker was selected/unselected by you or automatically
            if(localSpeaker)
                $("#speakers option[value='" + window.btoa(localSpeaker.id) + "']").prop('selected', true);
        },
        onStateUpdated: function(localSpeaker, state) {
            // Speaker state was updated
        }
    }).then(function() {
        console.log("RegisterLocalSpeakerEventListener Success");
    }).catch(function() {
        console.error("RegisterLocalSpeakerEventListener Failed");
    });

    // Handle remote cameras
    vidyoConnector.RegisterRemoteCameraEventListener({
        onAdded: function(remoteCamera, participant) {
            // New remote camera is available
            $("#"+jqid(participant.id)).find( "#cameraPrivacyTable").removeClass('cameraOff').addClass('cameraOn');
            participantsMap.set(participant.id, $("#"+jqid(participant.id)));
            remoteCameras.set(participant.id, remoteCamera);
            DroppedTilesIndicator.update({ numberOfRemoteCameras: remoteCameras.size });
        },
        onRemoved: function(remoteCamera, participant) {
            // Remote camera became unavailable
            $("#"+jqid(participant.id)).find( "#cameraPrivacyTable").removeClass('cameraOn').addClass('cameraOff');
            participantsMap.set(participant.id, $("#"+jqid(participant.id)));
            remoteCameras.delete(participant.id);
            DroppedTilesIndicator.update({ numberOfRemoteCameras: remoteCameras.size });
        },
        onStateUpdated: function(remoteCamera, participant) {
            // Remote camera state was updated
        }
    }).then(function() {
        console.log("RegisterRemoteCameraEventListener Success");
    }).catch(function() {
        console.error("RegisterRemoteCameraEventListener Failed");
    });

    vidyoConnector.RegisterRemoteMicrophoneEventListener({
        onAdded: function(remoteMicrophone, participant) {
            // New remote microphone is available
        },
        onRemoved: function(remoteMicrophone, participant) {
            // Remote microphone is unavailable
            $("#"+jqid(participant.id)).find( "#microphonePrivacyTable").removeClass('microphoneOn').addClass('microphoneOff');
        },
        onStateUpdated: function(remoteMicrophone, participant, state) {
            // Remote microphone state was updated
            if(state === 'VIDYO_DEVICESTATE_Resumed'){
                $("#"+jqid(participant.id)).find( "#microphonePrivacyTable").removeClass('microphoneOff').addClass('microphoneOn');
            }
            if(state === 'VIDYO_DEVICESTATE_Paused'){
                $("#"+jqid(participant.id)).find( "#microphonePrivacyTable").removeClass('microphoneOn').addClass('microphoneOff');
            }
            participantsMap.set(participant.id, $("#"+jqid(participant.id)));
        }
    }).then(function() {
        console.log("RegisterRemoteMicrophoneEventListener Success");
    }).catch(function() {
        console.error("RegisterRemoteMicrophoneEventListener Failed");
    });
}


function setLocalParticipantMicrophonePrivacy(microphonePrivacy) {
    if(localParticipant){
        if(!microphonePrivacy){
            $("#"+jqid(localParticipant.id)).find( "#microphonePrivacyTable").removeClass('microphoneOff').addClass('microphoneOn');
        } else {
            $("#"+jqid(localParticipant.id)).find( "#microphonePrivacyTable").removeClass('microphoneOn').addClass('microphoneOff');
        }
    }

}

function setLocalParticipantCameraPrivacy(cameraPrivacy) {
    if(localParticipant) {
        if(!cameraPrivacy){
            $("#"+jqid(localParticipant.id)).find( "#cameraPrivacyTable").removeClass('cameraOff').addClass('cameraOn');
        } else {
            $("#"+jqid(localParticipant.id)).find( "#cameraPrivacyTable").removeClass('cameraOn').addClass('cameraOff');
        }
    }
}
/**
 * @param {VidyoConnector} vidyoConnector
 * @param {function(LogRecord)} onLog
 * @param {string} filter
 */
function registerLogEventListener(vidyoConnector, onLog, filter) {
  "use strict";
  vidyoConnector.RegisterLogEventListener({onLog, filter});
  // XXX: use vidyoConnector.UnregisterLogEventListener() to stop custom logging
}

/** @param {LogRecord} logRecord */
function onVidyoLog(logRecord){
  "use strict";
  console.log("%cCUSTOM LOGGING EXAMPLE: %c" + JSON.stringify(logRecord), 'color: red;', 'color: salmon;');
}

/**
 * @param {VidyoConnector} vidyoConnector
 */
function registerResourceManagerEventListener(vidyoConnector) {
  "use strict";
  vidyoConnector.RegisterResourceManagerEventListener({onAvailableResourcesChanged, onMaxRemoteSourcesChanged});
  // XXX: use vidyoConnector.UnregisterResourceManagerEventListener() to stop events handling
}

/**@param cpuEncodeObj number
 * @param cpuDecodeObj number
 * @param bandwidthSendObj number
 * @param bandwidthReceiveObj number
 */
function onAvailableResourcesChanged(cpuEncodeObj, cpuDecodeObj, bandwidthSendObj, bandwidthReceiveObj){
  "use strict";
  console.log("%conAvailableResourcesChangedCallback EXAMPLE: %c" + 'cpuEncodeObj=' + cpuEncodeObj + '; cpuDecodeObj=' + cpuDecodeObj + '; bandwidthSendObj=' + bandwidthSendObj + '; bandwidthReceiveObj=' + bandwidthReceiveObj, 'color: red;', 'color: salmon;');
}
/**@param maxRemoteSourcesObj number
 */
function onMaxRemoteSourcesChanged(maxRemoteSourcesObj){
  "use strict";
  console.log("%conMaxRemoteSourcesChangedCallback EXAMPLE: %c" + 'maxRemoteSourcesObj=' + maxRemoteSourcesObj, 'color: red;', 'color: salmon;');
}

function handleAdvancedSettingsChange(vidyoConnector) {
    $('#advanced-addGoogleConferenceFlag').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ addGoogleConferenceFlag: $(this).prop('checked') });
    });
    $('#advanced-disableStats').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ disableStats: $(this).prop('checked') });
    });
    $('#advanced-enableSimulcast').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableSimulcast: $(this).prop('checked') });
    });
    $('#advanced-enableTransportCc').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableTransportCc: $(this).prop('checked') });
    });
    $('#advanced-enableUnifiedPlan').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableUnifiedPlan: $(this).prop('checked') });
    });
    $('#advanced-participantLimit').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ participantLimit: $(this).val() });
    });
    $('#advanced-showStatisticsOverlay').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ showStatisticsOverlay: $(this).prop('checked') });
    });
    $('#advanced-enableAudioOnlyMode').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableAudioOnlyMode: $(this).prop('checked') });
    });
    $('#advanced-loggingSimpleAPIMethods').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableSimpleAPILogging: $(this).prop('checked') });
    });
    $('#advanced-loggingVidyoConnectorAPI').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableVidyoConnectorAPILogging: $(this).prop('checked') });
    });
    $('#advanced-enableAutoReconnect').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ enableAutoReconnect: $(this).prop('checked') });
    });
    $('#advanced-maxReconnectAttempts').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ maxReconnectAttempts: $(this).val() });
    });
    $('#advanced-reconnectBackoff').change(function() {
        vidyoConnector.SetAdvancedConfiguration({ reconnectBackoff: $(this).val() });
    });
}

function handleDeviceChange(vidyoConnector, cameras, microphones, speakers) {
    // Hook up camera selector functions for each of the available cameras
    $("#cameras").change(function() {
        // Camera selected from the drop-down menu
        $("#cameras option:selected").each(function() {
            camera = cameras[$(this).val()];
            vidyoConnector.SelectLocalCamera({
                localCamera: camera
            }).then(function() {
                console.log("SelectCamera Success");
            }).catch(function() {
                console.error("SelectCamera Failed");
            });
        });
    });

    // Hook up microphone selector functions for each of the available microphones
    $("#microphones").change(function() {
        // Microphone selected from the drop-down menu
        $("#microphones option:selected").each(function() {
            microphone = microphones[$(this).val()];
            vidyoConnector.SelectLocalMicrophone({
                localMicrophone: microphone
            }).then(function() {
                console.log("SelectMicrophone Success");
            }).catch(function() {
                console.error("SelectMicrophone Failed");
            });
        });
    });

    // Hook up speaker selector functions for each of the available speakers
    $("#speakers").change(function() {
        // Speaker selected from the drop-down menu
        $("#speakers option:selected").each(function() {
            speaker = speakers[$(this).val()];
            vidyoConnector.SelectLocalSpeaker({
                localSpeaker: speaker
            }).then(function() {
                console.log("SelectSpeaker Success");
            }).catch(function() {
                console.error("SelectSpeaker Failed");
            });
        });
    });

    // Hook up camera selector functions for each of the available cameras
    $("#cameraShare").change(function() {
        // Camera selected from the drop-down menu
        $("#cameraShare option:selected").each(function() {
            const camera = cameras[$(this).val()];
            vidyoConnector.SelectVideoContentShare({
                localCamera: camera
            }).then(function() {
                console.log("SelectVideoContentShare Success");
            }).catch(function() {
                console.error("SelectVideoContentShare Failed");
            });
        });
    });
}

function handleSharing(vidyoConnector, webrtc) {
    var isSafari = navigator.userAgent.indexOf('Chrome') < 0 && navigator.userAgent.indexOf('Safari') >= 0;
    var monitorShares = {};
    var windowShares  = {};
    var isSharingWindow = false;          // Flag indicating whether a window is currently being shared
    var isSharingMonitor = false;
    var webrtcMode = (webrtc === "true"); // Whether the app is running in plugin or webrtc mode

    // The monitorShares & windowShares associative arrays hold a handle to each window/monitor that are available for sharing.
    // The element with key "0" contains a value of null, which is used to stop sharing.
    monitorShares[0] = null;
    windowShares[0]  = null;


    const selectLocalShare = (share) => {
      // Select the local window share
      vidyoConnector.SelectLocalWindowShare({
        localWindowShare: share
      }).then(function (isSelected) {
          if(!isSelected){
            $("#windowShares option[value='0']").prop('selected', true);
          }
        console.log("SelectLocalWindowShare Success");
       
      }).catch(function (error) {
        // This API will be rejected in case any error occurred including:
        // - permission is not given on the OS level (macOS).
        $("#windowShares option[value='0']").prop('selected', true);
        console.error("SelectLocalWindowShare Failed:", error);
      });
    };

    const selectLocalMonitor = (share) => {
      // Select the local monitor
      vidyoConnector.SelectLocalMonitor({
        localMonitor: share
      }).then(function(isSelected) {
        if(!isSelected){
            $("#monitorShares option[value='0']").prop('selected', true);
          }
        console.log("SelectLocalMonitor Success", isSelected);
      }).catch(function(error) {
        // This API will be rejected in case any error occurred including:
        // - permission is not given on the OS level (macOS).
        $("#monitorShares option[value='0']").prop('selected', true);
        console.error("SelectLocalMonitor Failed:", error);
      });
    };


    StartWindowShare();
    StartMonitorShare();

    function StartWindowShare() {
        // Register for window share status updates, which operates differently in plugin vs webrtc:
        //    plugin: onAdded and onRemoved callbacks are received for each available window
        //    webrtc: a popup is displayed (an extension to Firefox/Chrome) which allows the user to
        //            select a share; once selected, that share will trigger an onAdded event
        vidyoConnector.RegisterLocalWindowShareEventListener({
            onAdded: function(localWindowShare) {
                // New share is available so add it to the windowShares array and the drop-down list
                if (localWindowShare.name != "") {
                    var shareVal;
                    if (localWindowShare.applicationName) {
                        shareVal = localWindowShare.applicationName + " : " + localWindowShare.name;
                    } else {
                        shareVal = localWindowShare.name;
                    }
                    if(isSafari) {
                      let button = document.createElement('button');
                      button.innerHTML = shareVal;
                      button.id = localWindowShare.id;
                      button.onclick = (e) => {
                        e.preventDefault();
                        e.stopPropagation();
                        selectLocalShare(localWindowShare);
                      };
                      $("#windowSharesSafari").append(button);
                    } else {
                      $("#windowShares").append("<option value='" + window.btoa(localWindowShare.id) + "'>" + shareVal + "</option>");
                    }
                    windowShares[window.btoa(localWindowShare.id)] = localWindowShare;
                    console.log("Window share added, name : " + localWindowShare.name + " | id : " + window.btoa(localWindowShare.id));
                }
            },
            onRemoved: function(localWindowShare) {
                // Existing share became unavailable
              if(isSafari) {
                $("#" + localWindowShare.id).remove();
              } else {
                $("#windowShares option[value='" + window.btoa(localWindowShare.id) + "']").remove();
              }
              delete windowShares[window.btoa(localWindowShare.id)];
               if (document.getElementById('shareScreen')) {
                    document.getElementById('shareScreen').style.display = 'block';
                }
               if (document.getElementById('stopshareScreen')) {
                    document.getElementById('stopshareScreen').style.display = 'none';
                }
            },
            onSelected: function(localWindowShare) {
                // Share was selected/unselected by you or automatically
                if (localWindowShare) {
                    if(!isSafari) {
                      $("#windowShares option[value='" + window.btoa(localWindowShare.id) + "']").prop('selected', true);
                    }
                    isSharingWindow = true;
                    console.log("Window share selected : " + localWindowShare.name);
                    document.getElementById('shareScreen').style.display = 'none';
                    document.getElementById('stopshareScreen').style.display = 'block'
                } else {
                  if(!isSafari) {
                    $("#windowShares option[value='0']").prop('selected', true);
                  }
                  isSharingWindow = false;
                  document.getElementById('shareScreen').style.display = 'block';
                  document.getElementById('stopshareScreen').style.display = 'none';
                }
            },
            onStateUpdated: function(localWindowShare, state) {
                // localWindowShare state was updated
            }
        }).then(function() {
            console.log("RegisterLocalWindowShareEventListener Success");
        }).catch(function() {
            console.error("RegisterLocalWindowShareEventListener Failed");
        });
    }

    function StartMonitorShare() {
        // Register for monitor share status updates
        vidyoConnector.RegisterLocalMonitorEventListener({
            onAdded: function(localMonitorShare) {
                // New share is available so add it to the monitorShares array and the drop-down list
                if (localMonitorShare.name != "") {
                  if(isSafari) {
                    let button = document.createElement('button');
                    button.innerHTML = localMonitorShare.name;
                    button.id = localMonitorShare.id;
                    button.onclick = (e) => {
                      e.preventDefault();
                      e.stopPropagation();
                      selectLocalMonitor(localMonitorShare);
                    };
                    $("#monitorSharesSafari").append(button);
                  } else {
                    $("#monitorShares").append("<option value='" + window.btoa(localMonitorShare.id) + "'>" + localMonitorShare.name + "</option>");
                  }
                  monitorShares[window.btoa(localMonitorShare.id)] = localMonitorShare;
                  console.log("Monitor share added, name : " + localMonitorShare.name + " | id : " + window.btoa(localMonitorShare.id));
                }
            },
            onRemoved: function(localMonitorShare) {
                if(isSafari) {
                  $("#" + localMonitorShare.id).remove();
                } else {
                  // Existing share became unavailable
                  $("#monitorShares option[value='" + window.btoa(localMonitorShare.id) + "']").remove();
                }
                delete monitorShares[window.btoa(localMonitorShare.id)];
            },
            onSelected: function(localMonitorShare) {
                // Share was selected/unselected by you or automatically
                if (localMonitorShare) {
                    if(!isSafari) {
                      $("#monitorShares option[value='" + window.btoa(localMonitorShare.id) + "']").prop('selected', true);
                    }
                    console.log("Monitor share selected : " + localMonitorShare.name);
                    isSharingMonitor = true;
                } else {
                  if(!isSafari) {
                    $("#monitorShares option[value='0']").prop('selected', true);
                  }
                  isSharingMonitor = false;
                }
            },
            onStateUpdated: function(localMonitorShare, state) {
                // localMonitorShare state was updated
            }
        }).then(function() {
            console.log("RegisterLocalMonitorShareEventListener Success");
        }).catch(function() {
            console.error("RegisterLocalMonitorShareEventListener Failed");
        });
    }

    if(isSafari) {
      $("#monitorSharesButtonNone").click((e) => {
        e.stopPropagation();
        e.preventDefault();
        selectLocalMonitor(null);
      });
      $("#windowSharesButtonNone").click((e) => {
        e.stopPropagation();
        e.preventDefault();
        selectLocalShare(null);
      });
    } else {
      // A monitor was selected from the "Monitor Share" drop-down list (plugin mode only).
      $("#monitorShares").change(function() {
        console.log("*** Monitor shares change called");

        // Find the share selected from the drop-down list
        $("#monitorShares option:selected").each(function() {
          share = monitorShares[$(this).val()];
          selectLocalMonitor(share);
        });
      });
      // A window was selected from the "Window Share" drop-down list.
      // Note: in webrtc mode, this is only called for the "None" option (to stop the share) since
      //       the share is selected in the onAdded callback of the LocalWindowShareEventListener.
      $("#windowShares").change(function () {
        console.log("*** Window shares change called");

        // Find the share selected from the drop-down list
        $("#windowShares option:selected").each(function () {
          share = windowShares[$(this).val()];
          selectLocalShare(share);
        });
      });
    }
}

function getParticipantName(participant, cb) {
    if (!participant) {
        cb("Undefined");
        return;
    }

    if (participant.name) {
        cb(participant.name);
        return;
    }

    participant.GetName().then(function(name) {
        cb(name);
    }).catch(function() {
        cb("GetNameFailed");
    });
}

var participantsMap = new Map();
function handleParticipantChange(vidyoConnector, devicesMuteState) {
    vidyoConnector.ReportLocalParticipantOnJoined({
        reportLocalParticipant: true
    });
    vidyoConnector.RegisterParticipantEventListener({
        onJoined: function (participant) {
            getParticipantName(participant, function (name) {

                    if (jQuery.inArray(name, participantList) === -1) {
                        participantList.push(name);
                    }
                     if (name.indexOf('~') !== -1) {
                        name = name.split('~')[0];
                    }
                    $("#participantStatus").html("" + name + " Joined");
                    
                    getUserPerticipantList();
                     
                    $("#participantTable").append($('<tr class="ParticipantTableRow" id="' + participant.id.toString() + '"><td class="participantTableData"><span>' + participant.name + '</span><div id="cameraPrivacyTable" style="display: inline-block" class="cameraOff participanttablecontent"></div> <div id="microphonePrivacyTable" style="display: inline-block" class="microphoneOff participanttablecontent"></div></td></tr>'));
                    participantsMap.set(participant.id, $("#" + jqid(participant.id)));


                if (participant.isLocal) {
                    localParticipant = participant;
                     setLocalParticipantCameraPrivacy(devicesMuteState.cameraPrivacy);
                setLocalParticipantMicrophonePrivacy(devicesMuteState.microphonePrivacy);
//                statsSendingTool.setLocalParticipantId(participant.id);
                }
                else {
//                activateChannel(participant);
              }
            });
        },
        onLeft: function (participant) {
            getParticipantName(participant, function (name) {
                var ss = name.indexOf("ccmsSupervisor") !== -1;
                if (!ss) {
                    ss = name.indexOf("ccmsAdministrator") !== -1;
                }
                if (!ss) {
                    
                var username = name.split("~")[0];
                var loginId = name.split("~")[1];
                usernameonconference = removeValue(usernameonconference, username);
                usersonconference = removeValue(usersonconference, loginId);
                participantList=removeValue(participantList,name);
                getUserPerticipantList();
                    
                    $("#" + jqid(participant.id)).remove();
                    participantsMap.delete(participant.id);
                     if (name.indexOf('~') !== -1) {
                        name = name.split('~')[0];
                    }
                    $("#participantStatus").html("" + name + " Left");
                }


            });
            if (!participant.isLocal) {
//                deactivateChannel(participant);
            }
        },
        onDynamicChanged: function (participants, cameras) {
            // Order of participants changed
            $("#participantTable").empty();
            participants.forEach((participant) => {
                $("#participantTable").append(participantsMap.get(participant.id));
            });
            DroppedTilesIndicator.update();
        },
        onLoudestChanged: function (participant, audioOnly) {
            getParticipantName(participant, function (name) {
                var ss = name.indexOf("ccmsSupervisor") !== -1;
                if (!ss) {
                    ss = name.indexOf("ccmsAdministrator") !== -1;
                }
                if (!ss) {
                    getUserPerticipantList();
                     if (name.indexOf('~') !== -1) {
                        name = name.split('~')[0];
                    }
                    $("#participantStatus").html("" + name + " Speaking");
                }

            });
        }
    }).then(function () {
        console.log("RegisterParticipantEventListener Success");
    }).catch(function () {
        console.err("RegisterParticipantEventListener Failed");
    });

    vidyoConnector.RegisterRecorderInCallEventListener({
        onRecorderInCallChanged: function (hasRecorder, isPaused, isWebcasting) {
            if (hasRecorder && !isPaused) {
                $("#recorder").addClass("recorderOn").removeClass("recorderPaused");
            } else if (hasRecorder && isPaused) {
                $("#recorder").addClass("recorderPaused").removeClass("recorderOn");
            } else if (!hasRecorder) {
                $("#recorder").removeClass("recorderPaused").removeClass("recorderOn");
            }
            if (isWebcasting) {
                $("#webcasting").addClass("webcastingOn");
            } else {
                $("#webcasting").removeClass("webcastingOn");
            }
        }
    }).then(function () {
        console.log("RegisterRecorderInCallEventListener Success");
    }).catch(function () {
        console.error("RegisterRecorderInCallEventListener Failed");
    });
}

function handleReconnect(vidyoConnector) {
    vidyoConnector.RegisterReconnectEventListener({
        onReconnecting: (attempt, attemptTimeout, lastReason) => {
            console.warn(`Reconnecting: attempt=${attempt}, attemptTimeout=${attemptTimeout}s, lastReason=${lastReason}`);
        },
        onReconnected: () => {
            console.warn('Reconnected');
        },
        onConferenceLost: (lastReason) => {
            console.warn(`ConferenceLost: lastReason=${lastReason}`);
        }
    }).then(function() {
        console.log("RegisterReconnectEventListener Success");
    }).catch(function() {
        console.error("RegisterReconnectEventListener Failed");
    });
}

function parseUrlParameters(configParams) {
    // Fill in the form parameters from the URI
    var host = getUrlParameterByName("host");
    if (host)
        $("#host").val(host);
    var token = getUrlParameterByName("token");
    if (token)
        $("#token").val(token);
    var roomKey = getUrlParameterByName("roomKey");
    if (roomKey)
        $("#roomKey").val(roomKey);
    var roomPin = getUrlParameterByName("roomPin");
    if (roomPin)
        $("#roomPin").val(roomPin);
    var displayName = getUrlParameterByName("displayName");
    if (displayName)
        $("#displayName").val(displayName);
    var resourceId = getUrlParameterByName("resourceId");
    if (resourceId)
        $("#resourceId").val(resourceId);
    const extData = getUrlParameterByName("extData");
    if (extData)
        $("#extData").val(extData);
    const extDataType = getUrlParameterByName("extDataType");
    if (extDataType)
        $("#extDataType").val(extDataType);
    configParams.disableAudioLevelMonitor = getUrlParameterByName("disableAudioLevelMonitor");
    configParams.dynamicAudioSources = getUrlParameterByName("dynamicAudioSources");
    configParams.enableAudioOnlyMode = getUrlParameterByName("enableAudioOnlyMode");
    configParams.enableLoki = getUrlParameterByName("enableLoki") === 'true';
    configParams.lokiBaseUrl = getUrlParameterByName("lokiBaseUrl");

    var displayName = getUrlParameterByName("displayName");
    configParams.autoJoin    = getUrlParameterByName("autoJoin");
    configParams.enableDebug = getUrlParameterByName("enableDebug");
    var hideConfig = getUrlParameterByName("hideConfig");
    var showAdvancedSettings = getUrlParameterByName("showAdvancedSettings");

    const enableAutoReconnect = getUrlParameterByName("enableAutoReconnect");
    const maxReconnectAttempts = getUrlParameterByName("maxReconnectAttempts");
    const reconnectBackoff = getUrlParameterByName("reconnectBackoff");

    if (enableAutoReconnect) {
        configParams.enableAutoReconnect = ['true', '1'].includes(enableAutoReconnect);
    }
    if (maxReconnectAttempts) {
        configParams.maxReconnectAttempts = maxReconnectAttempts;
    }
    if (reconnectBackoff) {
        configParams.reconnectBackoff = reconnectBackoff;
    }

    var locationTag = getUrlParameterByName("locationTag");
    if(locationTag && locationTag !== '') {
        vidyoConnector.SetPool({
            name: locationTag,
        })
    }

    // If the parameters are passed in the URI, do not display options dialog
    if (host && token && displayName && resourceId) {
        $("#optionsParameters").addClass("hiddenPermanent");
    }

    if (hideConfig=="1") {
        $("#options").addClass("hiddenPermanent");
        $("#optionsVisibilityButton").addClass("hiddenPermanent");
        $("#renderer").addClass("rendererFullScreenPermanent");
    }

    if (showAdvancedSettings === 'true') {
        $("#advancedSettings").removeClass("hiddenPermanent");
    }

    return;
}

// Attempt to connect to the conference
// We will also handle connection failures
// and network or server-initiated disconnects.
function connectToConference(vidyoConnector) {
    // Abort the Connect call if resourceId is invalid. It cannot contain empty spaces or "@".
    if ( $("#resourceId").val().indexOf(" ") != -1 || $("#resourceId").val().indexOf("@") != -1) {
        console.error("Connect call aborted due to invalid Resource ID");
        connectorDisconnected("Disconnected", "");
        $("#error").html("<h3>Failed due to invalid Resource ID" + "</h3>");
        return;
    }

    // Clear messages
    $("#error").html("");
    $("#message").html("<h3 class='blink'>CONNECTING...</h3>");
    const loggerUrl = $("#loggerURL").val();
    const extData = $("#extData").val();
    const extDataType = $("#extDataType").val();
    vidyoConnector.SetAdvancedConfiguration({
        loggerURL: loggerUrl,
        extData,
        extDataType
    });

    vidyoConnector.ConnectToRoomAsGuest({
        // Take input from options form
        host: $("#host").val(),
        roomKey: $("#roomKey").val(),
        displayName: $("#displayName").val(),
        roomPin: $("#roomPin").val(),
        // Define handlers for connection events.
        onSuccess: function() {
            // Connected
            console.log(`vidyoConnector.ConnectToRoomAsGuest : onSuccess callback received`);
            $("#connectionStatus").html("Connected");
            $("#options").addClass("vidyo-hide-options");
            $("#optionsVisibilityButton").addClass("showOptions").removeClass("hideOptions");
            $("#renderer").addClass("rendererFullScreen").removeClass("rendererWithOptions");
            $("#message").html("");
            $("#connectionStatus").html('Connected...');
            document.getElementById('startRecording').click();
//            vidyoConnector.GetConnectionProperties().then(props => {
//              props.forEach((prop) => {
//                if (prop.name === 'Room.displayName') {
//                  $("#connectionStatus").html(`Connected to ${prop.value}`);
//                }
//              })
//            });
//            statsSendingTool.startSendingStats();
        },
        onFailure: function(reason) {
            // Failed
            console.error("vidyoConnector.Connect : onFailure callback received");
            connectorDisconnected("Failed", "");

            $("#error").html("<h3>Call Failed: " + reason + "</h3>");
            $("#options").removeClass("vidyo-hide-options");
            $("#optionsVisibilityButton").addClass("hideOptions").removeClass("showOptions");
            $("#renderer").addClass("rendererWithoutChat").removeClass("rendererWithChat");
            $("#renderer").removeClass("rendererFullScreen").addClass("rendererWithOptions");
            PF('retryjoin').show();
//            statsSendingTool.stopSendingStats();
        },
        onDisconnected: function(reason) {
            // Disconnected
            console.log("vidyoConnector.Connect : onDisconnected callback received");
            connectorDisconnected("Disconnected", "Call Disconnected: " + reason);
            $("#renderer").addClass("rendererWithoutChat").removeClass("rendererWithChat");
            $("#options").removeClass("vidyo-hide-options");
            $("#optionsVisibilityButton").addClass("hideOptions").removeClass("showOptions");
            $("#renderer").removeClass("rendererFullScreen").addClass("rendererWithOptions");
//            statsSendingTool.stopSendingStats();
        }
    }).then(function(status) {
        if (status) {
            console.log("Connect Success");
        } else {
            console.error("Connect Failed");
            connectorDisconnected("Failed", "");
            $("#error").html("<h3>Call Failed" + "</h3>");
        }
    }).catch(function() {
        console.error("Connect Failed");
        connectorDisconnected("Failed", "");
        $("#error").html("<h3>Call Failed" + "</h3>");
    });
}



function leaveConference() {
    if (vidyoConnector) {
          vidyoConnector.SetCameraPrivacy({
            privacy: !0
        }).then(function () {
            console.log("SetCameraPrivacy Success");
        }).catch(function () {
            console.error("SetCameraPrivacy Failed");
        });
        vidyoConnector.SetMicrophonePrivacy({
            privacy: !0
        }).then(function () {
            console.log("SetMicrophonePrivacy Success");
        }).catch(function () {
            console.error("SetMicrophonePrivacy Failed");
        });
        vidyoConnector.Disconnect().then(function () {
            console.log("Disconnect Success");
           
           
        }).catch(function () {
           
//            console.error("Disconnect Failure");
        });
 
        document.getElementById('incallcontainer').style.display = 'none';
        
    }
}



function startShare() {
    var l= document.getElementById('windowShares').length;
    if(l>1){
        document.getElementById('windowShares').selectedIndex = '1';
        $("#windowShares").trigger("change");
    }
}

function stopShare() {
    document.getElementById('windowShares').selectedIndex = '0';
    $("#windowShares").trigger("change");
//    $("#windowShares").trigger("click");
    document.getElementById('shareScreen').style.display = 'block';
    document.getElementById('stopshareScreen').style.display = 'none';
    resetShares();
}

function resetShares() {
    localShareId_ = -1;
    pendingRequestId_ = -1;
    shareSelectedCallback_ = null;
    localSharePeerConnection_ = null;
    localShareStream_ = [];
    localShareElement_ = null;
    localShareOffer_ = null;
    iceCandidateTimeout_ = null;
}



// Connector either fails to connect or a disconnect completed, update UI elements
function connectorDisconnected(connectionStatus, message) {
    $("#connectionStatus").html(connectionStatus);
    $("#message").html(message);
    $("#participantStatus").html("");
    $("#joinLeaveButton").removeClass("callEnd").addClass("callStart");
    $('#joinLeaveButton').prop('title', 'Join Conference');
    $("#recorder").removeClass("recorderPaused").removeClass("recorderOn");
    $("#webcasting").removeClass("webcastingOn");
    if (chatData.chatOpen) {
        onChatButtonClicked();
    }
    resetChatData();
}

// Extract the desired parameter from the browser's location bar
function getUrlParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}

function initWCtrlPanel(vidyoConnector) {
  WCtrlPanel.createWCtrlPanel(vidyoConnector)
    .then(
      /** @param {WCtrlPanel} wCtrlPanel */
      (wCtrlPanel) => {
      let showAdvancedSettings = getUrlParameterByName("showAdvancedSettings");
      if (showAdvancedSettings === 'true') {
        wCtrlPanel.showPanel();
        initLogModal();
      }
    });
}
function initLogModal() {
    var modal = document.getElementById('log-popup');
    var span = document.getElementsByClassName("close")[0];
    $('#showLogs').click(function() {
        modal.style.display = "block";
    })
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
}
function createTable(data) {
    let logLevelsName = Object.keys(data.logLevel);
    let logCategory = data.logCategory;
    let levels = $("#levels");
    let table = $("#log-table");
    $("#enable-all").change(handleLogCategoryChanged.bind(this));
    $.each(logLevelsName, function(i) {
        let th = $('<th/>')
            .appendTo(levels);
        let levelLable = $('<label/>')
            .addClass("container")
            .appendTo(th);
        let levelCheckbox = $('<input />', { type: 'checkbox',  value: logLevelsName[i] + '@*' })
            .addClass("log-check level")
            .attr('id', 'all-' + logLevelsName[i])
            .appendTo(levelLable);
        $('<span/>')
            .addClass('checkmark')
            .appendTo(levelLable);
        $('<p/>')
            .text(logLevelsName[i])
            .appendTo(th)
            levelCheckbox.change(handleLogCategoryChanged.bind(this))
    });
    $.each(logCategory, function(i) {
        let tr = $('<tr/>')
            .addClass(logCategory[i])
            .appendTo(table);
        let categoryNameCell = $('<td/>')
            .appendTo(tr);
        let categoryLabel = $('<label/>')
            .addClass("container")
            .appendTo(categoryNameCell);
        let categoryCheckbox = $('<input />', { type: 'checkbox',  value: 'all@' + logCategory[i] })
            .addClass("log-check")
            .attr('id', 'all-' + logCategory[i])
            .appendTo(categoryLabel);
        $('<span/>')
            .addClass('checkmark')
            .appendTo(categoryLabel);
        $('<span/>')
            .text(logCategory[i])
            .appendTo(categoryNameCell)
        categoryCheckbox.change(handleLogCategoryChanged.bind(this))
        $.each(logLevelsName, function(k) {
        let logCell =  $('<td/>')
        .appendTo(tr);
        let logLabel = $('<label/>')
            .addClass("container")
            .appendTo(logCell);
        let logCheckbox = $('<input />', { type: 'checkbox',  value: logLevelsName[k] + '@' + logCategory[i] })
            .addClass("log-check")
            .addClass(logLevelsName[k])
            .attr('id', logLevelsName[k] + '-' + logCategory[i])
            .appendTo(logLabel);
        $('<span/>')
            .addClass('checkmark')
            .appendTo(logLabel);
            logCheckbox.change(handleLogCategoryChanged.bind(this));
        })
    });
    if(getUrlParameterByName('enableDebug') === '1') {
        vidyoConnector.SetAdvancedConfiguration({ addLogCategory: 'all'});
    }
}

function setActiveLogLevels(activeLogs, logLevels) {
    let i = 0;
    let prevLogs = {};
    return function() {
        for(let activeLog in activeLogs) {
        if(activeLogs.hasOwnProperty(activeLog)){
            if(activeLogs[activeLog] === prevLogs[activeLog]) continue;
            for(let level in logLevels ) {
                    if(activeLogs[activeLog] & logLevels[level]) {
                        $(`#${level}-${activeLog}`).prop( "checked", true);
                        i++;
                        if(i === Object.keys(logLevels).length) {
                            $(`#all-${activeLog}`).prop( "checked", true);
                            i = 0;
                            checkLevelCheckbox(level)
                        }else {
                            $(`#all-${activeLog}`).prop( "checked", false);
                            checkLevelCheckbox(level);
                        }
                    }else {
                        $(`#${level}-${activeLog}`).prop( "checked", false);
                        $(`#all-${activeLog}`).prop( "checked", false);
                        checkLevelCheckbox(level);
                    }
                }
                i = 0;
            }
        }
    prevLogs = Object.assign({},activeLogs);
    }
}

function handleLogCategoryChanged(val) {
    let onOff = $(val.target).prop('checked');
    vidyoConnector.SetAdvancedConfiguration({ addLogCategory: (!onOff) ? '-' + $(val.target).val(): $(val.target).val()});
}

function checkLevelCheckbox(level) {
    let length = $(`.${level}:checked`).length == $(`.${level}`).length;
    if (length) {
        $(`#all-${level}`).prop( "checked", true);
    }else {
        $(`#all-${level}`).prop( "checked", false);
        }
    let enableAllLogs = $(`.level:checked`).length == $(`.level`).length;
    if(enableAllLogs) {
        $(`#enable-all`).prop('checked', true);
    }else {
        $(`#enable-all`).prop('checked', false);
    }
}

function jqid (id) {
    return (!id) ? null : id.replace(/(:|\.|\[|\]|,|=|@|;|-)/g, '\\$1');
}

function getLokiImplementation(isPushEnabled, baseURL) {
  const LOKI_SERVER_URL = 'vidyoinsights.vidyoqa.com';
  const LOKI_PUSH_URL = `https://${baseURL || LOKI_SERVER_URL}/loki/api/v1/push`;
  const interval = 10*1000;

  let intervalID;
  let timeoutID;
  let localParticipantId = null;

  const stopSendingStats = function() {
    clearInterval(intervalID);
    clearTimeout(timeoutID);
    localParticipantId = null;
  }

  if (isPushEnabled) {
    console.log('Loki push enabled');
  } else {
    console.log('Loki push disabled');
  }

  const startSendingStats = function() {
    if (!isPushEnabled || !LOKI_PUSH_URL || !statsProvider) {
      return;
    }
    //make sure previous interval is stoped
    stopSendingStats();

    function sendStats() {
      statsProvider().then(function (stats) {
        fetch(LOKI_PUSH_URL, {
          method: "POST",
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            streams: [
              {
                stream: stats.stream,
                values: [[Date.now() + "000000", JSON.stringify(stats.data)]],
              },
            ],
          }),
        });
      });
    }

    intervalID = setInterval(sendStats, interval);
    timeoutID = setTimeout(sendStats, 1000);
  }

  const statsProvider = function() {
    // provider
    return vidyoConnector.GetStatsJson().then((stats) => {
      let data = JSON.parse(stats);
      if (
        !(data.userStats[0].roomStats.length && localParticipantId)
      ) {
        return false;
      }

      return {
        stream: {
          EventType: "WebRTCClientStats",
          ClientRouterName: data.userStats[0].roomStats[0].reflectorId,
          ClientParticipantId: localParticipantId,
          ClientConferenceId: data.userStats[0].roomStats[0].conferenceId,
        },
        data: data,
      };
    });
  };

  const setLocalParticipantId = function(participantId) {
    localParticipantId = participantId;
  }

  return {
    startSendingStats: startSendingStats,
    stopSendingStats: stopSendingStats,
    setLocalParticipantId: setLocalParticipantId
  }
}
function getUserPerticipantList() {
    if (participantList !== undefined && participantList !== 'undefined' && participantList !== null && participantList !== "") {
        var displayName = document.getElementById('displayName').value;

        if (jQuery.inArray(displayName, participantList) === -1) {
            participantList.push(displayName);
        }
        populateUsers();
    }
}

function populateUsers() {
    var chx = document.getElementsByName('user');
    console.log('#########  user' + chx);
    var checkedUser = '';
    for (var i = 0; i < chx.length; i++) {
        if (chx[i].type === 'radio' && chx[i].checked) {
            checkedUser = chx[i].value;
            break
        }
    }
    var user = null;
    user = participantList;
    var tbl = "";
    if (user !== null) {
        for (var i = 0; i < user.length; i++) {
            var webrtcusername = user[i];
            var username = '';
            var loginId = '';
            if (webrtcusername.indexOf('~') !== -1) {
                username = webrtcusername.split('~')[0];
                loginId = webrtcusername.split('~')[1]
            } else {
                username = webrtcusername;
                loginId = '';
            }
            if (loginId !== '') {
                var ss = loginId.indexOf("ccmsSupervisor") !== -1;
                if (!ss) {
                    ss = loginId.indexOf("ccmsAdministrator") !== -1;
                }
                if (!ss) {
                    if (loginId !== document.getElementById('agentId').value) {
                        loginId = loginId.replace(/\s+/g, '');
                        if (loginId !== checkedUser) {
                            tbl = tbl + '<input type="radio" name="user" style="display:none"  value="' + loginId + '" id="' + loginId + '"></input>';
                        } else {
                            tbl = tbl + '<input type="radio" checked="checked" name="user" style="display:none"  value="' + loginId + '" id="' + loginId + '"></input>';
                        }
                        tbl = tbl + "<div class='user_details'  onclick=javascript:addNewStaticTab('" + loginId + "');><h2 style='cursor: pointer;'> " + username + "</h2><h3>" + loginId + "</h3></div>";
                        if (usersonconference.indexOf(loginId) === -1) {
                            usernameonconference.push(username);
                            usersonconference.push(loginId);
                        }
                    } else {
                        tbl = tbl + "<div class='user_details'><b>" + username + "</h2>";
                        tbl = tbl + "<h3>" + loginId + "</h3>";
                        tbl = tbl + "</div>";
                    }
                } else {
                    loginId = loginId.replace(/\s+/g, '');
                    if (loginId !== checkedUser) {
                        tbl = '<input type="radio" name="user" style="display:none;height:0px;"  value="' + loginId + '" id="' + loginId + '"></input>' + tbl;
                    } else {
                        tbl = '<input type="radio" checked="checked" name="user" style="display:none;height:0px;"  value="' + loginId + '" id="' + loginId + '"></input>' + tbl;
                    }
                    if (loginId !== document.getElementById('agentId').value) {
                        if (usersonconference.indexOf(loginId) === -1) {
                            usersonconference.push(loginId);
                            usernameonconference.push(username);
                        }
                    }
                }
            } else {
                var ss = username.indexOf("(ccmsSupervisor)") !== -1;
                if (!ss) {
                    ss = username.indexOf("(ccmsAdministrator)") !== -1;
                }
                if (!ss) {
                    tbl = tbl + "<div class='user_details'><h2>" + username + "</h2>";
                    tbl = tbl + "<h3>" + loginId + "</h3>";
                    tbl = tbl + "</div>";
                }
            }
        }
    }
    document.getElementById('participantList').innerHTML = tbl
}
function removeValue(arr, value) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == value) {
            arr.splice(i, 1);
            break
        }
    }
    return arr;
}
function resetVidyoConnectorValues() {
    participantList = [];
}

function insertParticipants(loginId) {
    var resp = loginId.split("~");
    if (usersonconference.indexOf(resp[0]) === -1) {
        usersonconference.push(resp[0]);
        usernameonconference.push(resp[1]);
    }

}

function arrayRemove(arr, value) {

    return arr.filter(function (ele) {
        return ele != value;
    });
}

class DroppedTilesIndicator {
    static maxRemoteSources = 0;
    static numberOfRemoteCameras = 0;
    static numberOfRemoteShares = 0;
    static participantLimit = 0;

    static update({ maxRemoteSources, numberOfRemoteCameras, numberOfRemoteShares, participantLimit } = {}) {
        if (typeof maxRemoteSources === 'number') {
            DroppedTilesIndicator.maxRemoteSources = maxRemoteSources;
        }
        if (typeof numberOfRemoteCameras === 'number') {
            DroppedTilesIndicator.numberOfRemoteCameras = numberOfRemoteCameras;
        }
        if (typeof numberOfRemoteShares === 'number') {
            DroppedTilesIndicator.numberOfRemoteShares = numberOfRemoteShares;
        }
        if (typeof participantLimit === 'number') {
            DroppedTilesIndicator.participantLimit = participantLimit;
        }
        const numberOfDynamicVideoTilesWeWantToShow = Math.min(
            DroppedTilesIndicator.numberOfRemoteCameras,
            DroppedTilesIndicator.participantLimit
        );
        const numberOfDynamicVideoTilesWeCanShow = Math.min(
            DroppedTilesIndicator.maxRemoteSources - DroppedTilesIndicator.numberOfRemoteShares,
            numberOfDynamicVideoTilesWeWantToShow
        );
        const noneOfVideoTilesDropped = numberOfDynamicVideoTilesWeCanShow >= numberOfDynamicVideoTilesWeWantToShow;
        $('#dropped-tiles-indicator').toggleClass('hidden', noneOfVideoTilesDropped);
    }
}
