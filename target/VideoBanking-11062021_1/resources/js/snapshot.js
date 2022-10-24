function clearPic() {
    document.getElementById('form_snapshot_div').innerHTML = '';
}

var canvas;
$(document).ready(function () {

    var capture = document.getElementById('capture');

       capture.onclick = (function () {
        var varray = document.getElementsByClassName('vidyo-rendering-container')[0].getElementsByTagName('div');
        canvas = document.createElement("canvas");
        canvas.setAttribute("id", "canvas");

        var context = canvas.getContext('2d');
        var stream;
        var videoDiv;
        for (var i = 0; i < varray.length; i++) {
            if (varray[i].classList.contains('remote-track')) {
                videoDiv = varray[i];
            }
        }
        if (videoDiv) {
            var varrayV = videoDiv.getElementsByTagName('video');
            video = varrayV[0];
        }
        if (video)
        {

            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;

            if (bowser.name === "Firefox") {
                stream = video.mozCaptureStream(25);
            } else {
                stream = video.captureStream(25);
            }
            if (stream.getVideoTracks().length > 0) {
                context.drawImage(video, 0, 20, canvas.width, canvas.height);

                var html = "<img id='snapped' src='" + canvas.toDataURL('image/png') + "'/>"
                document.getElementById('form_snapshot_div').innerHTML = html;
                var canvasData = canvas.toDataURL("image/png");
                validateImage();
                PF('snapshotvar').show();

            } else {
                PF('notAllowedSnapshotvar').show();
            }

        } else {
            PF('notAllowedSnapshotvar').show();
        }

    });


    document.getElementById('download1').addEventListener('click', function () {
        var canvasData = croppedCanvas.toDataURL("image/png");
        downloadCanvas(this, 'canvas', 'snapshot.jpg')
    }, !1);

    document.getElementById('download').addEventListener('click', function () {
        var canvasData = croppedCanvas.toDataURL("image/png");
        document.getElementById('scanval').value = canvasData;
        document.getElementById('captureImgBtn').click();
    }, !1);

    document.getElementById('captureidcard').addEventListener('click', function () {
        var canvasData = croppedCanvas.toDataURL("image/png");
        document.getElementById('scanval').value = canvasData;
        document.getElementById('captureIdCardBtn').click();
    }, !1);

    document.getElementById('captureaddproof').addEventListener('click', function () {
        var canvasData = croppedCanvas.toDataURL("image/png");
        document.getElementById('scanval').value = canvasData;
        document.getElementById('captureAddProofBtn').click();
    }, !1);


    document.getElementById('previewDownload').addEventListener('click', function () {

        PF('previewSnapshotvar').show();
        var cropped = document.getElementById("cropped_preview");
        cropped.setAttribute('src', croppedCanvas.toDataURL());
    });
});

function validateImage() {

    var cropDetail;
    var snapped = document.getElementById("snapped");
    snapped.src = canvas.toDataURL('image/png');
    var cropper = new Cropper(snapped, {
        zoomable: false,
        movable: false,
        crop: function (event) {
            cropDetail = event.detail;
            croppedCanvas = cropper.getCroppedCanvas();
        }
    });
}

function downloadCanvas(link, canvasId, filename) {
    link.href = croppedCanvas.toDataURL("image/png");
    link.download = filename;
}
