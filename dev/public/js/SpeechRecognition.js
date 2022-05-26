if (!('webkitSpeechRecognition' in window)) {
    //Speech API not supported here…
} else {
    var recognition = new webkitSpeechRecognition(); //That is the object that will manage our whole recognition process.
    recognition.continuous = true;   //Suitable for dictation.
    recognition.interimResults = true;  //If we want to start receiving results even if they are not final.
    //Define some more additional parameters for the recognition:
    recognition.lang = "en-US";
    recognition.maxAlternatives = 1; //Since from our experience, the highest result is really the best...
}

recognition.onstart = function () {
    //Listening (capturing voice from audio input) started.
    //This is a good place to give the user visual feedback about that (i.e. flash a red light, etc.)
};

recognition.onend = function () {
    //Again – give the user feedback that you are not listening anymore. If you wish to achieve continuous recognition – you can write a script to start the recognizer again here.
    console.log("not listening");
};

recognition.onresult = function (event) { //the event holds the results
//Yay – we have results! Let’s check if they are defined and if final or not:
    if (typeof (event.results) === 'undefined') { //Something is wrong…
        recognition.stop();
        return;
    }

    for (var i = event.resultIndex; i < event.results.length; ++i) {
        if (event.results[i].isFinal) { //Final results
            console.log("final results: " + event.results[i][0].transcript);   //Of course – here is the place to do useful things with the results.
            var img2 = '<img src="img/ripple.png" height="50" width="50" >';
            $('#img').html(img2);

            var data = event.results[i][0].transcript; // Speech-to-text final result is passed to the variable data
            if (data.includes("$") && /\d/.test(data)) {
                data;
            } else if (/\d/.test(data)) {
                data = data.toString().replace(/[^0-9.]/g, "");
                var total = data.length;
                if (total==7){
                    data;
                    console.log(data + "here");
                }else{
                    data = event.results[i][0].transcript;
                    console.log(data + "there");
                }
                // return data;
            } else {
                data;
            }

            $(function () {
                $.ajax({
                    headers: {'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')},
                    method: 'post',
                    url: '/intent',
                    data: {
                        data: data // Speech-to-text final result
                    },
                    success: function (answer) {
                        console.log(data);

                        if (answer[0]['note'] === "database") {
                            $(function () {

                                $("#myDiv").show();
                                window.setTimeout(function () {
                                    $('#trans').append('<p><input type="hidden" id="stt" name="' + answer[2] + '" value="' + answer[3] + '" > ' +
                                        '<input type="text" id="stt" name="" value="' + data + '"  class="form-control " placeholder="Answer/ Results"  ></p>' +
                                        '<p >' + answer[0]['result'] + '</p><br>');
                                    $("#myDiv").hide();
                                }, 1000);
                            });
                        } else {
                            if (answer[0]['table'] === "insertInto") { // if it has insertInto its because it needs to be inserted into the forms DB table
                                $(function () {

                                    $("#myDiv").show();
                                    window.setTimeout(function () {
                                        $('#trans').append('<input type="hidden" id="stt" name="formType" value="' + answer[4] + '" >' +
                                            '<input type="hidden" id="stt" name="formName" value="' + answer[3] + '" >' +
                                            '<p> ' + data + ' </p>' +
                                            '<p >' + answer[0]['result'] + '</p><br>');
                                        $("#myDiv").hide();
                                    }, 1000);
                                });
                            } else {
                                $(function () {

                                    $("#myDiv").show();
                                    window.setTimeout(function () {
                                        $('#trans').append('<p> ' + data + ' </p>' +
                                            '<p >' + answer[0]['result'] + '</p><br>');
                                        $("#myDiv").hide();
                                    }, 1000);
                                });
                            }
                        }

                        if (answer[0]['result'] === "Okay, i will submit your form!") {
                            $(function () {
                                window.setTimeout(function () {
                                    $("#submitting").show();
                                }, 2000);

                                window.setTimeout(function () {
                                    $("#submit").submit(); // using ID
                                }, 4000);

                            });

                        } else {

                        }
                        $('#voice').html(JSON.stringify(answer[1]));

                        $("#audio2").attr("src", answer[1]);
                        $('#playbutton').click();
                    }
                });
            });

        } else {   //i.e. interim...
            console.log("interim results: " + event.results[i][0].transcript);  //You can use these results to give the user near real time experience.

            var img = '<img src="img/wave-sound.svg"  height="50" width="50" >'; //gif that display that sound is being transmitted
            $('#img').html(img);

        }
    } //end for loop
};

// doesnt work??
recognition.onspeechstart = function () {
    console.log('Some sound is being received');
};


function play() {
    var audio = document.getElementById("audio1");
    audio.play();
}

<!--this area is for test purposes, can be removed-->
function addUpdateData() { // this function is used for test purposes, this function is used with a click button
    //var data = "hows the weather in Aruba";
    /* var data = ["hi, what is the weather in Suriname",
         "hi, i want to open a new personal account",
     "lol","woof woof Curcao waw","hi, is it hot today in Aruba","i want to open a new personal account",
     "my name is Maria", "my number is 1 2 3 4 5 6 7"];*/
    var data = ["hows the weather in Aruba"];


    var randomNumber = Math.floor(Math.random() * (data.length));
    var datas = data[randomNumber];

    //  var hasNumber = /\d/.test(datas);
    if (datas.includes("$") && /\d/.test(datas)) {
        datas;
    } else if (/\d/.test(datas)) {
        datas = datas.toString().replace(/[^0-9.]/g, "");
        // return data;
    } else {
        datas;
    }
    $.ajax({
        headers: {'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')},
        method: 'post',
        url: '/intent',
        data: {
            data: datas
        },
        success: function (answer) {
            console.log(datas);

            alert(JSON.stringify(answer));
            console.log(answer[1]);
            // console.log(answer[1]);

            if (answer[0]['note'] === "database") {
                $(function () {

                    $("#myDiv").show();
                    window.setTimeout(function () {
                        $('#trans').append('<p><input type="hidden" id="stt" name="' + answer[2] + '" value="' + answer[3] + '" > ' +
                            '<input type="text" id="stt" name="" value="' + datas + '"  class="form-control " placeholder="Answer/ Results"  ></p>' +
                            '<p >' + answer[0]['result'] + '</p><br>');
                        $("#myDiv").hide();
                    }, 2000);
                });
            } else {
                if (answer[0]['table'] === "insertInto") {
                    window.setTimeout(function () {
                        $('#trans').append('<input type="hidden" id="stt" name="formType" value="' + answer[4] + '" >' +
                            '<input type="hidden" id="stt" name="formName" value="' + answer[3] + '" >' +
                            '<p> ' + datas + ' </p>' +
                            '<p >' + answer[0]['result'] + '</p><br>');
                    }, 3000);
                } else {
                    window.setTimeout(function () {
                        $('#trans').append('<p> ' + datas + ' </p>' +
                            '<p >' + answer[0]['result'] + '</p><br>');
                    }, 3000);
                }
            }
            if (answer[0]['result'] === "Okay, i will submit your form!") {
                $(function () {
                    window.setTimeout(function () {
                        $("#submitting").show();
                    }, 2000);

                    window.setTimeout(function () {
                        $("#submit").submit(); // using ID
                    }, 4000);

                });
            } else {

            }
            $('#voice').html(JSON.stringify(answer[1]));

            $("#audio2").attr("src", answer[1]);
            $('#playbutton').click();

        },
        error: function (xhr, status, error) {
            // alert(error + xhr.status);
            $('#exception').html("Server is offline");
            console.log(error);
        },
    });

}
<!--button is for test purposes, can be removed when done-->


function play2() {
    var audio = document.getElementById("audio2");
    audio.play();
}

function play1() {
    var audio = document.getElementById("audio1");
    audio.play();
}

var mary = "Hi, what can i help you with ?";
$(function () {
    $.ajax({
        headers: {'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')},
        method: 'post',
        url: '/tts',
        data: {
            data: mary
        },
        success: function (mary) {
            //console.log(mary);


            //  var marytts = mary;
            if (annyang) {
                // Let's define a command.
                var commands = {
                    'hello': function () {
                        var greeting = "Hi, what can i help you with ?";


                        $('#greeting').html(greeting);
                        //     $('#img').html(img);

                        var audio = new Audio(mary).play();

                        annyang.abort();
                        //recognition.continuous;
                        window.setTimeout(function () {
                            recognition.start();

                        }, 2000);
                        recognition.onspeechstart();

                        recognition.onend = function () {
                            recognition.start();
                        }

                    }
                };

                // Add our commands to annyang
                annyang.addCommands(commands);

                // Start listening.
                annyang.start();
                // annyang.paused;
            }
        }
    })
});


