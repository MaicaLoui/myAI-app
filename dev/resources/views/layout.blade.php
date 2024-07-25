<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="{{ asset('css/bootstrap.css') }}" rel="stylesheet">
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
    <script>
        var assetBaseUrl = "{{ asset('') }}";
    </script>

    <meta name="csrf-token" content="{{ csrf_token() }}">

</head>

<body>

    <nav class="navbar navbar-light bg-light" style=" border-bottom-style: solid;
  border-bottom-color: #45bad2;">

        <a class="navbar-brand" href="#">
            <img src="{{ asset('img/aibot.svg') }}" width="250" height="50" alt="">
            <img src="img/wave-sound.svg" width="75" height="50" class="center">
        </a>
    </nav>
    <div class="container">
        <div class="container border-left rounded mt-5 cust-border-left ">
            <div class="card-body ">
                <h4 id="exception"></h4>

                <div class="row">
                    <div class="column">
                        <h5 id="greeting"></h5>
                    </div>

                </div>
            </div>

            <div class="card">
                <div class="card-body ">
                    <form method="POST" action="/layout">
                        {{ csrf_field() }}
                        <div class="row">
                            <div id="myDiv" style="display:none">
                                <div class="d-flex justify-content-center">
                                    <img id="myImage" src="{{ asset('img/typing.gif') }}">
                                </div>
                            </div>

                            <div id="trans" class="col-sm-offset-3 col-sm-6 "></div>

                        </div>

                        <div id="subButton" class="col-sm-offset-3 col-sm-6 "></div>
                        <button type="submit" class="btn btn-primary">submit</button>
                    </form>
                    <br>
                    <input type="submit" id="some text" class="btn btn-primary" onclick="addUpdateData(id)"
                        value="Add"></button>

                    <img id="playbutton" value="PLAY" onclick="play2()">
                    <audio id="audio2" src=""></audio>
                </div>
            </div>


        </div>
        <script type="text/javascript" src="{{ asset('js/SpeechRecognition.js') }}"></script>

    </div>

</body>

</html>
